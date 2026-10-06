package com.example.data

/**
 * Leitner-style spaced repetition. A word climbs one box per correct first answer and falls back
 * to box 0 on a mistake. Higher boxes are reviewed less often.
 */
object SrsScheduler {
    private const val MINUTE = 60_000L
    private const val DAY = 24 * 60 * MINUTE

    /** Days until the next review for boxes 0..6 (box 0 is handled separately after a mistake). */
    private val intervalDays = longArrayOf(0, 1, 2, 4, 7, 15, 30)
    const val MAX_STAGE = 6

    /** Box from which a word counts as "mastered" (reviewed again no sooner than 7 days). */
    const val MASTERED_STAGE = 4

    fun nextStage(stage: Int, correct: Boolean): Int =
        if (correct) minOf(stage + 1, MAX_STAGE) else 0

    fun nextReviewTime(stage: Int, correct: Boolean, now: Long): Long =
        if (!correct) now + 10 * MINUTE else now + intervalDays[stage.coerceIn(0, MAX_STAGE)] * DAY

    /** 0 = not started, 1 = studying, 2 = mastered. Only call for words that have been answered. */
    fun masteryFor(stage: Int): Int = if (stage >= MASTERED_STAGE) 2 else 1
}
