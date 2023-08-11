package cs3500.pa01;

/**
 * Represents a Question in a questionBank sr file.
 */
public class Question implements Comparable<Question> {
  private final String prompt;
  private final String answer;
  private Difficulty difficulty;

  /**
   * Instantiates a new Question given a prompt and answer.
   * Automatically sets the difficulty to hard.
   *
   * @param prompt - String of the question prompt
   * @param answer - String of the question answer
   */
  Question(String prompt, String answer) {
    this.prompt = prompt;
    this.answer = answer;
    this.difficulty = Difficulty.HARD;
  }

  /**
   * Instantiates a new Question given a string.
   *
   * @param question - String of full question found in a questionBank sr file
   */
  Question(String question) {
    int difficultyIndex = question.lastIndexOf("---") + 3;

    this.prompt = question.substring(0, question.lastIndexOf(":::"));
    this.answer = question.substring(question.lastIndexOf(":::") + 3, question.lastIndexOf("---"));

    if (question.substring(difficultyIndex).equals("HARD")) {
      this.difficulty = Difficulty.HARD;
    } else if (question.substring(difficultyIndex).equals("EASY")) {
      this.difficulty = Difficulty.EASY;
    } else {
      throw new RuntimeException("Question does not have valid difficulty.");
    }
  }

  /**
   * Update question.
   *
   * @param difficulty - the new difficulty to mark the question as
   */
  public void updateQuestion(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Converts the question to its string representation.
   *
   * @return the current question to its assigned string format
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(prompt);
    sb.append(":::");
    sb.append(answer);
    sb.append("---");
    if (this.difficulty.equals(Difficulty.HARD)) {
      sb.append(("HARD"));
    } else {
      sb.append("EASY");
    }
    return sb.toString();
  }

  /**
   * Gets prompt of the question.
   *
   * @return the prompt
   */
  public String getPrompt() {
    return this.prompt;
  }

  /**
   * Gets answer of the question.
   *
   * @return the answer
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Gets difficulty of the question.
   *
   * @return the difficulty
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * Compare to int.
   *
   * @param q the object to be compared.
   * @return the difference between this Question and the given Question
   */
  public int compareTo(Question q) {
    return this.difficulty.getVal() - q.difficulty.getVal();
  }
}