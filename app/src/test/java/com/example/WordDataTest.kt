package com.example

import com.example.data.SrsScheduler
import com.example.data.WordListData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WordDataTest {
  private val words = WordListData.initialWords

  @Test
  fun hasExactly850UniqueWords() {
    assertEquals(850, words.size)
    assertEquals((1..850).toList(), words.map { it.id })
    val duplicates = words.groupBy { it.word.lowercase() }.filterValues { it.size > 1 }.keys
    assertTrue("duplicate words: $duplicates", duplicates.isEmpty())
  }

  @Test
  fun everyLevelHasTenWords() {
    val byLevel = words.groupBy { it.levelIndex }
    assertEquals((1..WordListData.TOTAL_LEVELS).toList(), byLevel.keys.sorted())
    byLevel.forEach { (level, list) -> assertEquals("level $level", 10, list.size) }
  }

  @Test
  fun everyWordHasCompleteContentAndItsExampleContainsIt() {
    words.forEach { w ->
      assertTrue("${w.word}: missing fields", listOf(w.translation, w.ipa, w.exampleSentence, w.exampleTranslation).all { it.isNotBlank() })
      assertTrue("${w.word}: ipa must be in slashes", w.ipa.startsWith("/") && w.ipa.endsWith("/"))
      val whole = Regex("\\b" + Regex.escape(w.word) + "\\b", RegexOption.IGNORE_CASE)
      assertTrue("${w.word}: example '${w.exampleSentence}' lacks the word", whole.containsMatchIn(w.exampleSentence))
    }
  }

  @Test
  fun everyLevelHasATitle() {
    (1..WordListData.TOTAL_LEVELS).forEach { assertFalse(WordListData.levelTitle(it).startsWith("第")) }
  }

  @Test
  fun srsClimbsOnCorrectAndResetsOnMistake() {
    var stage = 0
    repeat(10) { stage = SrsScheduler.nextStage(stage, true) }
    assertEquals(SrsScheduler.MAX_STAGE, stage)
    assertEquals(0, SrsScheduler.nextStage(stage, false))
  }

  @Test
  fun srsSchedulesLaterForHigherStages() {
    val now = 1_000_000L
    assertTrue(SrsScheduler.nextReviewTime(1, true, now) < SrsScheduler.nextReviewTime(4, true, now))
    assertEquals(now + 10 * 60_000L, SrsScheduler.nextReviewTime(0, false, now))
    assertEquals(1, SrsScheduler.masteryFor(SrsScheduler.MASTERED_STAGE - 1))
    assertEquals(2, SrsScheduler.masteryFor(SrsScheduler.MASTERED_STAGE))
  }
}
