package cs3500.pa01;

/**
 * Represents the difficulty modes of a Question
 */
public enum Difficulty {
  EASY(2),
  HARD(1);

  private final int val;

  /**
   * Instantiates a new Difficulty.
   *
   * @param val - numerical value tied to a difficulty (used for simpler comparing)
   */
  Difficulty(int val) {
    this.val = val;
  }

  /**
   * Gets val of enum
   *
   * @return the val
   */
  public int getVal() {
    return this.val;
  }
}
