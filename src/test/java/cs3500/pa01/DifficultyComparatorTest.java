package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The type Difficulty comparator test.
 */
class DifficultyComparatorTest {

  /**
   * Test difficulty comparator when comparing two questions.
   */
  @Test
  public void testDifficultyComparator() {
    Question q1 = new Question("What color is the sky?:::Blue---EASY");
    Question q2 = new Question("What color is the sky?:::Blue---HARD");

    assertEquals(1, q1.compareTo(q2));
  }
}