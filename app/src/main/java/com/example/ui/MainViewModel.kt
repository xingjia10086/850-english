package com.example.ui

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.network.GeminiApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale

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
        val currentTypedAnswer: String = "" // For typed spelling
    ) : QuizState()
    data class Completed(val score: Int, val maxScore: Int, val coinsEarned: Int, val xpEarned: Int) : QuizState()
}

data class QuizQuestion(
    val type: QuestionType,
    val targetWord: Word,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val sentencePlaceholder: String = "" // Underline sentence
)

enum class QuestionType {
    ENG_TO_CHI,
    CHI_TO_ENG,
    LISTENING,
    CLOZE,
    CARD_SHUFFLE,
    SPELLING
}

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

    private var activeQuestions = listOf<QuizQuestion>()
    private var correctAnswersCount = 0

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

    // --- Dynamic Duolingo-like Exercise Generator ---
    fun startLesson(levelIndex: Int) {
        viewModelScope.launch {
            val levelWords = repository.getWordsByLevelSync(levelIndex)
            if (levelWords.isEmpty()) return@launch

            val otherWordsPool = WordListData.initialWords.filter { it.levelIndex != levelIndex }

            val generatedQuestions = mutableListOf<QuizQuestion>()

            // Generate 6 gamified questions
            // Question 1: ENG to CHI (Multiple choice)
            val w1 = levelWords.random()
            val o1 = (levelWords + otherWordsPool).filter { it.id != w1.id }
                .shuffled().take(3).map { it.translation } + w1.translation
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.ENG_TO_CHI,
                    targetWord = w1,
                    prompt = "What is the Chinese meaning of this word?",
                    options = o1.shuffled(),
                    correctAnswer = w1.translation
                )
            )

            // Question 2: CHI to ENG (Multiple choice)
            val w2 = levelWords.shuffled().firstOrNull { it.id != w1.id } ?: levelWords.random()
            val o2 = (levelWords + otherWordsPool).filter { it.id != w2.id }
                .shuffled().take(3).map { it.word } + w2.word
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.CHI_TO_ENG,
                    targetWord = w2,
                    prompt = "Select the correct English word for: \"${w2.translation}\"",
                    options = o2.shuffled(),
                    correctAnswer = w2.word
                )
            )

            // Question 3: Listening MCQ
            val w3 = levelWords.random()
            val o3 = (levelWords + otherWordsPool).filter { it.id != w3.id }
                .shuffled().take(3).map { it.word } + w3.word
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.LISTENING,
                    targetWord = w3,
                    prompt = "Listen to the word and select what you hear.",
                    options = o3.shuffled(),
                    correctAnswer = w3.word
                )
            )

            // Question 4: Cloze sentence
            val w4 = levelWords.random()
            val clozeSentence = w4.exampleSentence.replace(w4.word, "______", ignoreCase = true)
            val o4 = (levelWords + otherWordsPool).filter { it.word != w4.word }
                .shuffled().take(3).map { it.word } + w4.word
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.CLOZE,
                    targetWord = w4,
                    prompt = "Complete this sentence with the right word: \"${w4.exampleTranslation}\"",
                    options = o4.shuffled(),
                    correctAnswer = w4.word,
                    sentencePlaceholder = clozeSentence
                )
            )

            // Question 5: Card Shuffling (Sentence Builder)
            val w5 = levelWords.shuffled().firstOrNull { it.id != w4.id } ?: levelWords.random()
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.CARD_SHUFFLE,
                    targetWord = w5,
                    prompt = "Arrange these word cards to match: \"${w5.exampleTranslation}\"",
                    correctAnswer = w5.exampleSentence
                )
            )

            // Question 6: Typed spelling exercise
            val w6 = levelWords.shuffled().firstOrNull { it.id != w5.id } ?: levelWords.random()
            generatedQuestions.add(
                QuizQuestion(
                    type = QuestionType.SPELLING,
                    targetWord = w6,
                    prompt = "Spell the English word for: \"${w6.translation}\" ${w6.ipa}",
                    correctAnswer = w6.word
                )
            )

            activeQuestions = generatedQuestions
            correctAnswersCount = 0
            
            // Start Question 0
            val initialShuffleList = generatedQuestions[0].targetWord.exampleSentence
                .split(" ").filter { it.isNotBlank() }.shuffled()

            _quizState.value = QuizState.Active(
                questionIndex = 0,
                currentQuestion = generatedQuestions[0],
                totalQuestions = generatedQuestions.size,
                answered = false,
                selectedOptionIndex = null,
                isCorrect = false,
                buildAnswer = emptyList(),
                currentTypedAnswer = ""
            )

            // If Listening test, speak immediately!
            if (generatedQuestions[0].type == QuestionType.LISTENING) {
                speak(generatedQuestions[0].targetWord.word)
            }
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
            var isCorrectAnswer = false

            when (question.type) {
                QuestionType.ENG_TO_CHI, QuestionType.CHI_TO_ENG, QuestionType.LISTENING, QuestionType.CLOZE -> {
                    val selectedText = current.selectedOptionIndex?.let { question.options.getOrNull(it) }
                    if (selectedText != null && selectedText == question.correctAnswer) {
                        isCorrectAnswer = true
                    }
                }
                QuestionType.CARD_SHUFFLE -> {
                    val userBuilt = current.buildAnswer.joinToString(" ").replace(" .", ".").trim()
                    val targetClean = question.correctAnswer.replace(" .", ".").trim()
                    if (userBuilt.equals(targetClean, ignoreCase = true)) {
                        isCorrectAnswer = true
                    }
                }
                QuestionType.SPELLING -> {
                    if (current.currentTypedAnswer.trim().equals(question.correctAnswer.trim(), ignoreCase = true)) {
                        isCorrectAnswer = true
                    }
                }
            }

            if (isCorrectAnswer) {
                correctAnswersCount++
            }

            // Speak word pronunciation when checking answer so they learn!
            speak(question.targetWord.word)

            _quizState.value = current.copy(
                answered = true,
                isCorrect = isCorrectAnswer
            )
        }
    }

    fun nextQuestion() {
        val current = _quizState.value
        if (current is QuizState.Active && current.answered) {
            val nextIndex = current.questionIndex + 1
            if (nextIndex < current.totalQuestions) {
                val nextQ = activeQuestions[nextIndex]
                _quizState.value = QuizState.Active(
                    questionIndex = nextIndex,
                    currentQuestion = nextQ,
                    totalQuestions = current.totalQuestions,
                    answered = false,
                    selectedOptionIndex = null,
                    isCorrect = false,
                    buildAnswer = emptyList(),
                    currentTypedAnswer = ""
                )
                // If next is listening, speak!
                if (nextQ.type == QuestionType.LISTENING) {
                    speak(nextQ.targetWord.word)
                }
            } else {
                // Completed!
                val score = correctAnswersCount
                val maxScore = current.totalQuestions
                val pct = score.toFloat() / maxScore.toFloat()

                // Calculate rewards
                var coinsEarned = 0
                var xpEarned = 0

                viewModelScope.launch {
                    val originalStats = repository.getUserStatsSync() ?: UserStats()
                    val isCompletingNow = pct >= 0.7f && _currentLevelIndex.value > originalStats.lastCompletedLevel

                    if (pct >= 0.7f) {
                        coinsEarned = 25 + (score * 5)
                        xpEarned = 100

                        // Update word mastery state to "Mastered" (2)
                        val levelWords = repository.getWordsByLevelSync(_currentLevelIndex.value)
                        levelWords.forEach {
                            repository.updateWordMastery(it.id, 2)
                        }

                        val updatedLevel = if (isCompletingNow) _currentLevelIndex.value else originalStats.lastCompletedLevel

                        // Update stats
                        val statsWithStreak = determineStreak(originalStats)
                        repository.updateUserStats(
                            statsWithStreak.copy(
                                xp = statsWithStreak.xp + xpEarned,
                                goldCoins = statsWithStreak.goldCoins + coinsEarned,
                                lastCompletedLevel = updatedLevel
                            )
                        )
                    }

                    _quizState.value = QuizState.Completed(
                        score = score,
                        maxScore = maxScore,
                        coinsEarned = coinsEarned,
                        xpEarned = xpEarned
                    )
                }
            }
        }
    }

    fun quitLesson() {
        _quizState.value = QuizState.Idle
    }

    private fun determineStreak(stats: UserStats): UserStats {
        val now = System.currentTimeMillis()
        val oneDayMs = 24 * 60 * 60 * 1000
        val diff = now - stats.lastActiveTime
        
        return if (diff < oneDayMs * 2) {
            // Keep or increment streak
            val shouldIncrement = diff >= oneDayMs / 2 // At least 12 hours since last
            stats.copy(
                streak = if (shouldIncrement) stats.streak + 1 else stats.streak,
                lastActiveTime = now
            )
        } else {
            // Reset streak to 1
            stats.copy(
                streak = 1,
                lastActiveTime = now
            )
        }
    }

    // --- AI Companion Chat Functions (Gemini API Integration) ---
    private val systemChatPrompt = """
        You are Lily, a cheerful, enthusiastic English tutor. The user is a beginner who knows the Ogden Basic English 850 list.
        IMPORTANT rules for Lily:
        1. Always chat in extremely simple English. Rely primarily on basic keywords and simple grammatical patterns.
        2. Keep your messages and replies extremely short, only 1 to 3 short sentences. You must not send long blocks of text.
        3. At the very end of your response, you must append an accurate Chinese translation in square brackets, e.g., '[你好！很高兴成为你的英语学习伙伴。]'.
        4. If the user makes a minor spelling or grammatical mistake in their English, start your response by gently correcting it (e.g. "Tip: Say 'I want some water' instead of 'I wanting some water'.").
        5. Keep the conversation extremely friendly, conversational, and encouraging! Let's talk about food, weather, friends, family, or work.
    """.trimIndent()

    fun sendChatMessage(content: String) {
        if (content.isBlank()) return

        viewModelScope.launch {
            // Insert user message
            val userMsg = ChatMessage(sender = "user", content = content)
            repository.insertChatMessage(userMsg)

            // Trigger AI call
            _isAiLoading.value = true
            val aiResponseRaw = GeminiApiClient.getGeminiResponse(content, systemPrompt = systemChatPrompt)
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
