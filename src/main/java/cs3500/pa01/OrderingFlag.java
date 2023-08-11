package cs3500.pa01;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * This interface represents the ordering flag argument passed
 * into main as a comparator object.
 * These comparators are intended to be used on a list of
 * files in order for them to be sorted in their intended order.
 */
interface OrderingFlag extends Comparator<Path> {
  /**
   * @param p1 the first object to be compared.
   *
   * @param p2 the second object to be compared.
   *
   * @return - a negative or non-negative int representing the difference between the two paths
   */
  public int compare(Path p1, Path p2);
}