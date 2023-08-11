package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a comparator that compares the difficulty of two Questions
 */
public class DifficultyComparator implements Comparator<Question> {

  /**
   * @param q1 the first object to be compared.
   * @param q2 the second object to be compared.
   * @return - int representing the difference between the two Questions
   */
  @Override
  public int compare(Question q1, Question q2) {
    return q1.compareTo(q2);
  }
}