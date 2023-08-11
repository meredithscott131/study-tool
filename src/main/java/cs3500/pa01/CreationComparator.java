package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * This class represents a comparator that allows for two
 * paths to be compared by creation time in ascending order.
 */
class CreationComparator implements OrderingFlag {
  /**
   * @param p1 the first object to be compared.
   *
   * @param p2 the second object to be compared.
   *
   * @return -  a negative or non-negative int representing the difference between the two paths
   */
  public int compare(Path p1, Path p2) {
    try {
      FileTime p1Time = (FileTime) Files.getAttribute(p1, "creationTime");
      FileTime p2Time = (FileTime) Files.getAttribute(p2, "creationTime");
      return p1Time.compareTo(p2Time);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}