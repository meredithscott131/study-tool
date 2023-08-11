package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CreationComparator class.
 */
class FileNameComparatorTest {

  /**
   * Tests the compare method using the FileNameComparator given two paths.
   */
  @Test
  public void testFileNameComparator() {
    Path p1 = Paths.get("sampleInput/arrays.md");
    Path p2 = Paths.get("sampleInput/vectors.md");

    OrderingFlag c = new FileNameComparator();

    assertEquals(-21, c.compare(p1, p2));
  }
}