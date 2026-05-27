package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey val id: Int,
    val word: String,
    val category: String,
    val translation: String,
    val ipa: String,
    val exampleSentence: String,
    val exampleTranslation: String,
    val masteryState: Int = 0, // 0 = Not started, 1 = Studying, 2 = Mastered
    val bookmarked: Boolean = false,
    val levelIndex: Int
)

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val id: Int = 1,
    val streak: Int = 0,
    val lastActiveTime: Long = 0L,
    val xp: Int = 0,
    val goldCoins: Int = 0,
    val lastCompletedLevel: Int = 0
)

@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String, // "user" or "ai"
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val translation: String? = null
)
