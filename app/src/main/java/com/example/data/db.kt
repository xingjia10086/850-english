package com.example.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words ORDER BY id ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE levelIndex = :levelIndex ORDER BY id ASC")
    fun getWordsByLevel(levelIndex: Int): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE levelIndex = :levelIndex ORDER BY id ASC")
    suspend fun getWordsByLevelSync(levelIndex: Int): List<Word>

    @Query("SELECT * FROM words WHERE bookmarked = 1 ORDER BY id ASC")
    fun getBookmarkedWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE masteryState = :state ORDER BY id ASC")
    fun getWordsByState(state: Int): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(words: List<Word>)

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Int): Word?

    @Query("SELECT COUNT(*) FROM words WHERE (correctCount + wrongCount) > 0 AND nextReviewTime <= :now")
    fun countDueWords(now: Long): Flow<Int>

    @Query("SELECT * FROM words WHERE (correctCount + wrongCount) > 0 AND nextReviewTime <= :now ORDER BY nextReviewTime ASC LIMIT :limit")
    suspend fun getDueWordsSync(now: Long, limit: Int): List<Word>

    @Query(
        "UPDATE words SET reviewStage = :stage, nextReviewTime = :next, masteryState = :mastery, " +
            "correctCount = :correct, wrongCount = :wrong WHERE id = :id"
    )
    suspend fun updateReview(id: Int, stage: Int, next: Long, mastery: Int, correct: Int, wrong: Int)

    @Query("UPDATE words SET masteryState = :state WHERE id = :id")
    suspend fun updateWordMastery(id: Int, state: Int)

    @Query("UPDATE words SET bookmarked = :bookmarked WHERE id = :id")
    suspend fun updateWordBookmark(id: Int, bookmarked: Boolean)
}

@Dao
interface UserStatsDao {
    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStatsFlow(): Flow<UserStats?>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStatsSync(): UserStats?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(stats: UserStats)
}

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage)

    @Query("DELETE FROM chat_messages")
    suspend fun clearAllMessages()
}

@Database(entities = [Word::class, UserStats::class, ChatMessage::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun userStatsDao(): UserStatsDao
    abstract fun chatMessageDao(): ChatMessageDao
}

/** v1 -> v2: spaced-repetition columns. Words the old logic called "mastered" become due for a first review. */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE words ADD COLUMN reviewStage INTEGER NOT NULL DEFAULT 0")
        db.execSQL("ALTER TABLE words ADD COLUMN nextReviewTime INTEGER NOT NULL DEFAULT 0")
        db.execSQL("ALTER TABLE words ADD COLUMN correctCount INTEGER NOT NULL DEFAULT 0")
        db.execSQL("ALTER TABLE words ADD COLUMN wrongCount INTEGER NOT NULL DEFAULT 0")
        db.execSQL("UPDATE words SET reviewStage = 1, correctCount = 1 WHERE masteryState = 2")
        db.execSQL("UPDATE words SET masteryState = 1 WHERE masteryState = 2")
    }
}
