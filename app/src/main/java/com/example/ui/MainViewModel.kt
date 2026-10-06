package com.example.ui

import android.app.Application
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.network.ChatTurn
import com.example.network.GeminiApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.TimeZone

sealed class QuizState {
    object Idle : QuizState()
    data class Active(
        val questionIndex: Int,
        val currentQuestion: QuizQuestion,
        val totalQuestions: Int,
        val answered: Boolean,
        val selectedOptionIndex: Int?,
        val isCorrect: Boolean,
        val buildAnswer: List<String> = emptyList(), // For card shuffle
        val currentTypedAnswer: String = "", // For spelling / dictation
        val heardText: String = "", // For read-aloud: what the recognizer heard
        val isReview: Boolean = false
    ) : QuizState()
    data class Completed(
        val score: Int,
        val maxScore: Int,
        val coinsEarned: Int,
        val xpEarned: Int,
        val isReview: Boolean = false,
        val weakWords: List<Word> = emptyList()
    ) : QuizState()
}

data class QuizQuestion(
    val type: QuestionType,
    val targetWord: Word,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val sentencePlaceholder: String = "", // Underline sentence
    val isRetry: Boolean = false
)

enum class QuestionType {
    ENG_TO_CHI,
    CHI_TO_ENG,
    LISTENING,
    CLOZE,
    CARD_SHUFFLE,
    SPELLING,
    DICTATION, // listen, then type the word
    SPEAKING // read the example sentence aloud
}

private const val PASS_RATIO = 0.7f
private const val SPEAKING_PASS_RATIO = 0.6f
private const val REVIEW_BATCH_SIZE = 10

class MainViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository = AppRepository(application)
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    // State flows
    val allWords: StateFlow<List<Word>> = repository.allWords.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val bookmarkedWords: StateFlow<List<Word>> = repository.bookmarkedWords.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val userStats: StateFlow<UserStats?> = repository.userStats.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val chatMessages: StateFlow<List<ChatMessage>> = repository.chatMessages.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Current levels and active choices
    private val _currentLevelIndex = MutableStateFlow(1)
    val currentLevelIndex: StateFlow<Int> = _currentLevelIndex.asStateFlow()

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading: StateFlow<Boolean> = _isAiLoading.asStateFlow()

    private val _grammarCheckResult = MutableStateFlow<String?>(null)
    val grammarCheckResult: StateFlow<String?> = _grammarCheckResult.asStateFlow()

    private val _isCheckingGrammar = MutableStateFlow(false)
    val isCheckingGrammar: StateFlow<Boolean> = _isCheckingGrammar.asStateFlow()

    // Interactive Quiz Session state variables
    private val _quizState = MutableStateFlow<QuizState>(QuizState.Idle)
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    private var activeQuestions = mutableListOf<QuizQuestion>()
    private var baseQuestionCount = 0
    private var correctAnswersCount = 0
    private var skippedCount = 0
    private var reviewSession = false
    private val recordedWordIds = mutableSetOf<Int>()
    private val missedWords = mutableListOf<Word>()

    /** Number of words due for spaced-repetition review right now. */
    val dueWordCount: StateFlow<Int> = repository.dueWordCount.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    /** True when the device has a speech recognizer, so read-aloud questions can be offered. */
    val speechAvailable: Boolean = application.packageManager
        .queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0)
        .isNotEmpty()

    init {
        // Init DB
        viewModelScope.launch {
            repository.checkAndPrepopulate()
        }
        // Init TTS
        tts = TextToSpeech(application, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.let {
                val result = it.setLanguage(Locale.US)
                if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                    ttsReady = true
                }
            }
        }
    }

    fun speak(text: String) {
        if (ttsReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }

    // Set active level index
    fun setLevel(level: Int) {
        _currentLevelIndex.value = level
    }

    fun toggleBookmark(word: Word) {
        viewModelScope.launch {
            repository.updateWordBookmark(word.id, !word.bookmarked)
        }
    }

    // --- Lesson generator ---
    // A level lesson has two parts covering all of the level's words:
    //   A) recognition: see/hear the word and pick the meaning
    //   B) production: fill the gap, dictate, spell, build the sentence, read it aloud
    // Review sessions ask one question per due word, harder types for better-known words.
    fun startLesson(levelIndex: Int) {
        viewModelScope.launch {
            val levelWords = repository.getWordsByLevelSync(levelIndex)
            if (levelWords.isEmpty()) return@launch
            val ordered = levelWords.shuffled()
            val recognition = ordered.mapIndexed { i, w ->
                if (i % 2 == 0) buildQuestion(QuestionType.ENG_TO_CHI, w, levelWords)
                else buildQuestion(QuestionType.LISTENING, w, levelWords)
            }
            val productionTypes = listOf(
                QuestionType.CLOZE, QuestionType.DICTATION, QuestionType.SPEAKING,
                QuestionType.CARD_SHUFFLE, QuestionType.SPELLING
            )
            val production = levelWords.shuffled().mapIndexed { i, w ->
                buildQuestion(productionTypes[i % productionTypes.size], w, levelWords)
            }
            beginSession(recognition + production, isReview = false)
        }
    }

    fun startReview() {
        viewModelScope.launch {
            val due = repository.getDueWords(REVIEW_BATCH_SIZE)
            if (due.isEmpty()) return@launch
            val questions = due.shuffled().map { w ->
                val pool = if (w.reviewStage >= 2) {
                    listOf(
                        QuestionType.CLOZE, QuestionType.DICTATION, QuestionType.SPELLING,
                        QuestionType.SPEAKING, QuestionType.CARD_SHUFFLE, QuestionType.CHI_TO_ENG
                    )
                } else {
                    listOf(
                        QuestionType.ENG_TO_CHI, QuestionType.LISTENING,
                        QuestionType.CHI_TO_ENG, QuestionType.CLOZE
                    )
                }
                buildQuestion(pool.random(), w, due)
            }
            beginSession(questions, isReview = true)
        }
    }

    private fun beginSession(questions: List<QuizQuestion>, isReview: Boolean) {
        activeQuestions = questions.toMutableList()
        baseQuestionCount = questions.size
        correctAnswersCount = 0
        skippedCount = 0
        recordedWordIds.clear()
        missedWords.clear()
        reviewSession = isReview
        showQuestion(0)
    }

    private fun showQuestion(index: Int) {
        val q = activeQuestions[index]
        _quizState.value = QuizState.Active(
            questionIndex = index,
            currentQuestion = q,
            totalQuestions = activeQuestions.size,
            answered = false,
            selectedOptionIndex = null,
            isCorrect = false,
            isReview = reviewSession
        )
        if (q.type == QuestionType.LISTENING || q.type == QuestionType.DICTATION) {
            speak(q.targetWord.word)
        }
    }

    /** Builds one question; falls back to a plain multiple-choice question if [type] does not fit the word. */
    private fun buildQuestion(type: QuestionType, w: Word, sameGroup: List<Word>): QuizQuestion {
        val pool = WordListData.initialWords
        fun distractors(selector: (Word) -> String, correct: String): List<String> {
            val near = sameGroup.filter { it.id != w.id }
            val far = pool.filter { it.id != w.id }.shuffled().take(12)
            return (near.shuffled().take(2) + far)
                .map(selector)
                .filter { it != correct }
                .distinct()
                .take(3)
        }

        val gap = Regex("\\b" + Regex.escape(w.word) + "\\b", RegexOption.IGNORE_CASE)
        val sentenceWords = w.exampleSentence.split(" ").filter { it.isNotBlank() }
        val effective = when {
            type == QuestionType.CLOZE && !gap.containsMatchIn(w.exampleSentence) -> QuestionType.CHI_TO_ENG
            type == QuestionType.CARD_SHUFFLE && sentenceWords.size < 3 -> QuestionType.CLOZE
            type == QuestionType.SPEAKING && !speechAvailable -> QuestionType.DICTATION
            else -> type
        }
        if (effective == QuestionType.CLOZE && effective != type) {
            return buildQuestion(QuestionType.CLOZE, w, sameGroup)
        }

        return when (effective) {
            QuestionType.ENG_TO_CHI -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "这个单词是什么意思？",
                options = (distractors({ it.translation }, w.translation) + w.translation).shuffled(),
                correctAnswer = w.translation
            )
            QuestionType.CHI_TO_ENG -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "选出对应的英文单词",
                options = (distractors({ it.word }, w.word) + w.word).shuffled(),
                correctAnswer = w.word
            )
            QuestionType.LISTENING -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "听一听，选出你听到的单词",
                options = (distractors({ it.word }, w.word) + w.word).shuffled(),
                correctAnswer = w.word
            )
            QuestionType.CLOZE -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "选词填空：${w.exampleTranslation}",
                options = (distractors({ it.word }, w.word) + w.word).shuffled(),
                correctAnswer = w.word,
                sentencePlaceholder = w.exampleSentence.replace(gap, "______")
            )
            QuestionType.CARD_SHUFFLE -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "用单词卡片拼出句子：${w.exampleTranslation}",
                correctAnswer = w.exampleSentence
            )
            QuestionType.SPELLING -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "拼写单词：${w.translation} ${w.ipa}",
                correctAnswer = w.word
            )
            QuestionType.DICTATION -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "听写：听发音，写出单词",
                correctAnswer = w.word
            )
            QuestionType.SPEAKING -> QuizQuestion(
                type = effective, targetWord = w,
                prompt = "大声跟读这句话",
                correctAnswer = w.exampleSentence
            )
        }
    }

    fun selectOption(index: Int) {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered) {
            _quizState.value = current.copy(selectedOptionIndex = index)
        }
    }

    fun addCardToBuild(word: String) {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered) {
            val newList = current.buildAnswer + word
            _quizState.value = current.copy(buildAnswer = newList)
        }
    }

    fun removeCardFromBuild(word: String) {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered) {
            val index = current.buildAnswer.lastIndexOf(word)
            if (index != -1) {
                val newList = current.buildAnswer.toMutableList().apply { removeAt(index) }
                _quizState.value = current.copy(buildAnswer = newList)
            }
        }
    }

    fun updateTypedAnswer(typed: String) {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered) {
            _quizState.value = current.copy(currentTypedAnswer = typed)
        }
    }

    fun checkAnswer() {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered) {
            val question = current.currentQuestion

            val isCorrectAnswer = when (question.type) {
                QuestionType.ENG_TO_CHI, QuestionType.CHI_TO_ENG, QuestionType.LISTENING, QuestionType.CLOZE -> {
                    val selectedText = current.selectedOptionIndex?.let { question.options.getOrNull(it) }
                    selectedText != null && selectedText == question.correctAnswer
                }
                QuestionType.CARD_SHUFFLE -> {
                    val userBuilt = current.buildAnswer.joinToString(" ").replace(" .", ".").trim()
                    val targetClean = question.correctAnswer.replace(" .", ".").trim()
                    userBuilt.equals(targetClean, ignoreCase = true)
                }
                QuestionType.SPELLING, QuestionType.DICTATION ->
                    current.currentTypedAnswer.trim().equals(question.correctAnswer.trim(), ignoreCase = true)
                QuestionType.SPEAKING -> return // graded in submitSpeech
            }
            finishAnswer(current, isCorrectAnswer)
        }
    }

    /** Grades a read-aloud question from the recognizer's candidate transcripts. */
    fun submitSpeech(candidates: List<String>) {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered && current.currentQuestion.type == QuestionType.SPEAKING) {
            val target = normalizeWords(current.currentQuestion.correctAnswer)
            val best = candidates.maxByOrNull { heard ->
                val heardSet = normalizeWords(heard).toSet()
                target.count { it in heardSet }
            } ?: ""
            val heardSet = normalizeWords(best).toSet()
            val matched = target.count { it in heardSet }
            val ok = target.isNotEmpty() && matched.toFloat() / target.size >= SPEAKING_PASS_RATIO
            finishAnswer(current.copy(heardText = best), ok)
        }
    }

    /** The student cannot speak right now (quiet place, no microphone): drop the question from the score. */
    fun skipSpeaking() {
        val current = _quizState.value
        if (current is QuizState.Active && !current.answered && current.currentQuestion.type == QuestionType.SPEAKING) {
            if (!current.currentQuestion.isRetry) skippedCount++
            _quizState.value = current.copy(answered = true, isCorrect = true)
            speak(current.currentQuestion.correctAnswer)
        }
    }

    private fun normalizeWords(text: String): List<String> =
        text.lowercase(Locale.US).replace(Regex("[^a-z0-9' ]"), " ").split(" ").filter { it.isNotBlank() }

    private fun finishAnswer(current: QuizState.Active, isCorrectAnswer: Boolean) {
        val question = current.currentQuestion
        val wordId = question.targetWord.id

        // Only the first attempt of each word counts for the score and for the review schedule.
        if (!question.isRetry) {
            if (isCorrectAnswer) correctAnswersCount++ else missedWords.add(question.targetWord)
            if (recordedWordIds.add(wordId)) {
                viewModelScope.launch { repository.recordAnswer(wordId, isCorrectAnswer) }
            }
            // Ask again at the end of the session, so every mistake gets a second chance.
            if (!isCorrectAnswer) activeQuestions.add(question.copy(isRetry = true))
        }

        // Say the word (or the sentence) so the student hears the right answer.
        speak(if (question.type == QuestionType.SPEAKING) question.correctAnswer else question.targetWord.word)

        _quizState.value = current.copy(
            answered = true,
            isCorrect = isCorrectAnswer,
            totalQuestions = activeQuestions.size
        )
    }

    fun nextQuestion() {
        val current = _quizState.value
        if (current is QuizState.Active && current.answered) {
            val nextIndex = current.questionIndex + 1
            if (nextIndex < activeQuestions.size) {
                showQuestion(nextIndex)
            } else {
                completeSession()
            }
        }
    }

    private fun completeSession() {
        val maxScore = (baseQuestionCount - skippedCount).coerceAtLeast(1)
        val score = correctAnswersCount.coerceAtMost(maxScore)
        val pct = score.toFloat() / maxScore.toFloat()
        val isReview = reviewSession
        val level = _currentLevelIndex.value
        val weak = missedWords.distinctBy { it.id }

        viewModelScope.launch {
            val originalStats = repository.getUserStatsSync() ?: UserStats()
            var coinsEarned = 0
            var xpEarned = 0
            var updatedLevel = originalStats.lastCompletedLevel

            if (isReview) {
                xpEarned = score * 5
                coinsEarned = score
            } else if (pct >= PASS_RATIO) {
                coinsEarned = 25 + (score * 2)
                xpEarned = 100
                if (level > originalStats.lastCompletedLevel) updatedLevel = level
            }

            repository.updateUserStats(
                determineStreak(originalStats).copy(
                    xp = originalStats.xp + xpEarned,
                    goldCoins = originalStats.goldCoins + coinsEarned,
                    lastCompletedLevel = updatedLevel
                )
            )

            _quizState.value = QuizState.Completed(
                score = score,
                maxScore = maxScore,
                coinsEarned = coinsEarned,
                xpEarned = xpEarned,
                isReview = isReview,
                weakWords = weak
            )
        }
    }

    fun quitLesson() {
        _quizState.value = QuizState.Idle
    }

    /** Streak counts calendar days (local time) on which at least one session was finished. */
    private fun determineStreak(stats: UserStats): UserStats {
        val now = System.currentTimeMillis()
        val today = localDay(now)
        val last = localDay(stats.lastActiveTime)
        val streak = when {
            stats.lastActiveTime == 0L -> 1
            today == last -> maxOf(stats.streak, 1)
            today - last == 1L -> stats.streak + 1
            else -> 1
        }
        return stats.copy(streak = streak, lastActiveTime = now)
    }

    private fun localDay(millis: Long): Long =
        (millis + TimeZone.getDefault().getOffset(millis)) / (24L * 60 * 60 * 1000)

    // --- AI Companion Chat Functions (Gemini API Integration) ---
    private val systemChatPrompt = """
        You are Lily, a cheerful, enthusiastic English tutor. The user is a beginner learning the 850 most common English words (Basic English) for daily life.
        IMPORTANT rules for Lily:
        1. Always chat in extremely simple English. Use only very common words and short, simple sentences (present, past and future are fine).
        2. Keep every reply to 1 to 3 short sentences. Never send long blocks of text.
        3. Always end your reply with ONE easy follow-up question, so the user has something to answer.
        4. If the user makes a spelling or grammar mistake, start with a gentle correction (e.g. "Tip: say 'I want some water' instead of 'I wanting some water'.").
        5. At the very end of your response, append an accurate Chinese translation in square brackets, e.g. '[你好！很高兴成为你的英语学习伙伴。]'.
        6. If the user asks to practise a real-life situation (ordering food, asking the way, shopping, seeing a doctor, phone calls, introducing yourself), play the other person in that situation and stay in role.
        7. Keep the conversation friendly, conversational and encouraging.
    """.trimIndent()

    /** Starts a role-play for one everyday situation. [situation] is shown to the tutor in English. */
    fun startScenario(situation: String) {
        sendChatMessage("Let's practise this situation: $situation. Please start the role-play.")
    }

    fun sendChatMessage(content: String) {
        if (content.isBlank()) return

        viewModelScope.launch {
            val history = chatMessages.value.takeLast(10)
                .filterNot { it.content.startsWith("API_KEY_ERROR") || it.content.startsWith("Error contacting") }
                .map { ChatTurn(fromUser = it.sender == "user", text = it.content) }

            // Insert user message
            val userMsg = ChatMessage(sender = "user", content = content)
            repository.insertChatMessage(userMsg)

            // Trigger AI call
            _isAiLoading.value = true
            val aiResponseRaw = GeminiApiClient.getGeminiResponse(content, systemPrompt = systemChatPrompt, history = history)
            _isAiLoading.value = false

            // Insert AI response
            var translation: String? = null
            var finalMsg = aiResponseRaw
            
            // Extract Chinese translation from brackets if present
            if (aiResponseRaw.contains("[") && aiResponseRaw.contains("]")) {
                val start = aiResponseRaw.indexOf("[")
                val end = aiResponseRaw.indexOf("]")
                if (end > start) {
                    translation = aiResponseRaw.substring(start + 1, end).trim()
                    finalMsg = aiResponseRaw.substring(0, start).trim()
                }
            }

            repository.insertChatMessage(
                ChatMessage(
                    sender = "ai",
                    content = finalMsg,
                    translation = translation
                )
            )
        }
    }

    fun clearChat() {
        viewModelScope.launch {
            repository.clearChatHistory()
            // Add a warm welcome message
            repository.insertChatMessage(
                ChatMessage(
                    sender = "ai",
                    content = "Hello! I am Lily, your English tutor here to help you practice our Basic daily words. How are you today? Let's have a nice chat!",
                    translation = "你好！我是你的英语老师莉莉，很高兴协助你练习基础词汇。今天怎么样？咱们聊聊吧！"
                )
            )
        }
    }

    // --- AI Grammar Composition Checker ---
    fun checkGrammar(sentence: String) {
        if (sentence.isBlank()) {
            _grammarCheckResult.value = null
            return
        }

        viewModelScope.launch {
            _isCheckingGrammar.value = true
            val checkPrompt = """
                Check this English sentence written by a beginner: "$sentence".
                Please provide a highly supportive review with these three brief sections:
                1. Correction: If there is any grammatical or spelling problem, correct it. (If perfect, state: "Great! Your sentence is grammatically correct!").
                2. Word Usage: Briefly discuss how they used the words and if it sounds natural in Basic English.
                3. Better Phrase: Suggest 1 alternative simple way to express this idea.
                Format your reply cleanly in English and Chinese, keeping it brief and encouraging.
            """.trimIndent()

            val result = GeminiApiClient.getGeminiResponse(prompt = checkPrompt)
            _grammarCheckResult.value = result
            _isCheckingGrammar.value = false
        }
    }

    fun clearGrammarResult() {
        _grammarCheckResult.value = null
    }
}
