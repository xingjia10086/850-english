package com.example.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AppRepository(private val context: Context) {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "basic_850_english.db"
        ).build()
    }

    private val wordDao = db.wordDao()
    private val userStatsDao = db.userStatsDao()
    private val chatMessageDao = db.chatMessageDao()

    val allWords: Flow<List<Word>> = wordDao.getAllWords()
    val bookmarkedWords: Flow<List<Word>> = wordDao.getBookmarkedWords()
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

        // Check if words exist
        val wordsCount = WordListData.initialWords.size
        // Pre-insert words in conflict strategy IGNORE
        wordDao.insertAll(WordListData.initialWords)
    }

    fun getWordsByLevel(levelIndex: Int): Flow<List<Word>> {
        return wordDao.getWordsByLevel(levelIndex)
    }

    suspend fun getWordsByLevelSync(levelIndex: Int): List<Word> = withContext(Dispatchers.IO) {
        wordDao.getWordsByLevelSync(levelIndex)
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
