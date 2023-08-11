package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The type Difficulty test.
 */
class DifficultyTest {

  /**
   * Test difficulty enum values.
   */
  @Test
  public void testDifficultyEnum() {
    assertEquals(2, Difficulty.EASY.getVal());
    assertEquals(1, Difficulty.HARD.getVal());
  }
}