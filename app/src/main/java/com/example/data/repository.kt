package com.example.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class AppRepository(private val context: Context) {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "basic_850_english.db"
        ).addMigrations(MIGRATION_1_2).build()
    }

    private val wordDao = db.wordDao()
    private val userStatsDao = db.userStatsDao()
    private val chatMessageDao = db.chatMessageDao()

    val allWords: Flow<List<Word>> = wordDao.getAllWords()
    val bookmarkedWords: Flow<List<Word>> = wordDao.getBookmarkedWords()
    /** Number of words whose review time has come; re-evaluated every minute. */
    val dueWordCount: Flow<Int> = flow {
        while (true) {
            emit(System.currentTimeMillis())
            delay(60_000)
        }
    }.flatMapLatest { now -> wordDao.countDueWords(now) }
    val userStats: Flow<UserStats?> = userStatsDao.getUserStatsFlow()
    val chatMessages: Flow<List<ChatMessage>> = chatMessageDao.getAllMessages()

    suspend fun checkAndPrepopulate() = withContext(Dispatchers.IO) {
        // Simple prepopulation - check if there's any user stats or words
        val currentStats = userStatsDao.getUserStatsSync()
        if (currentStats == null) {
            // First time launch
            userStatsDao.insertOrUpdate(
                UserStats(
                    id = 1,
                    streak = 1,
                    lastActiveTime = System.currentTimeMillis(),
                    xp = 0,
                    goldCoins = 50,
                    lastCompletedLevel = 0
                )
            )
        }

        // IGNORE keeps learning progress and only adds words that are new in this version
        wordDao.insertAll(WordListData.initialWords)
    }

    fun getWordsByLevel(levelIndex: Int): Flow<List<Word>> {
        return wordDao.getWordsByLevel(levelIndex)
    }

    suspend fun getWordsByLevelSync(levelIndex: Int): List<Word> = withContext(Dispatchers.IO) {
        wordDao.getWordsByLevelSync(levelIndex)
    }

    suspend fun getDueWords(limit: Int): List<Word> = withContext(Dispatchers.IO) {
        wordDao.getDueWordsSync(System.currentTimeMillis(), limit)
    }

    /** Records one first-attempt answer for [wordId] and reschedules its next review. */
    suspend fun recordAnswer(wordId: Int, correct: Boolean) = withContext(Dispatchers.IO) {
        val word = wordDao.getWordById(wordId) ?: return@withContext
        val now = System.currentTimeMillis()
        val stage = SrsScheduler.nextStage(word.reviewStage, correct)
        wordDao.updateReview(
            id = wordId,
            stage = stage,
            next = SrsScheduler.nextReviewTime(stage, correct, now),
            mastery = SrsScheduler.masteryFor(stage),
            correct = word.correctCount + if (correct) 1 else 0,
            wrong = word.wrongCount + if (correct) 0 else 1
        )
    }

    suspend fun updateWordMastery(wordId: Int, state: Int) = withContext(Dispatchers.IO) {
        wordDao.updateWordMastery(wordId, state)
    }

    suspend fun updateWordBookmark(wordId: Int, bookmarked: Boolean) = withContext(Dispatchers.IO) {
        wordDao.updateWordBookmark(wordId, bookmarked)
    }

    suspend fun updateUserStats(stats: UserStats) = withContext(Dispatchers.IO) {
        userStatsDao.insertOrUpdate(stats)
    }

    suspend fun getUserStatsSync(): UserStats? = withContext(Dispatchers.IO) {
        userStatsDao.getUserStatsSync()
    }

    suspend fun insertChatMessage(message: ChatMessage) = withContext(Dispatchers.IO) {
        chatMessageDao.insertMessage(message)
    }

    suspend fun clearChatHistory() = withContext(Dispatchers.IO) {
        chatMessageDao.clearAllMessages()
    }
}
