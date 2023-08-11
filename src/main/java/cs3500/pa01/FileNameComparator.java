package cs3500.pa01;

import java.io.File;
import java.nio.file.Path;

/**
 * This class represents a comparator that allows for two
 * paths to be compared by their filename lexicographically.
 */
class FileNameComparator implements OrderingFlag {
  /**
   * @param p1 the first object to be compared.
   *
   * @param p2 the second object to be compared.
   *
   * @return - a negative or non-negative int representing the difference between the two paths
   */
  public int compare(Path p1, Path p2) {
    File f1 = new File(p1.toString());
    File f2 = new File(p2.toString());

    return f1.compareTo(f2);
  }
}
