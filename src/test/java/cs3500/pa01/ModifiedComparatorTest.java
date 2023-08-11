package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ModifiedComparator class
 */
class ModifiedComparatorTest {
  /**
   * Tests the compare method using the ModifiedComparator given two paths.
   **/
  @Test
  public void testModifiedComparator() {
    try {
      File file1 = File.createTempFile("temp1", "md");
      FileTime now = FileTime.fromMillis(System.currentTimeMillis());
      Files.setLastModifiedTime(file1.toPath(), now);

      File file2 = File.createTempFile("temp2", "md");
      Files.setLastModifiedTime(file2.toPath(), now);

      File file3 = File.createTempFile("notValid", ".pdf");
      Files.setLastModifiedTime(file3.toPath(), now);

      OrderingFlag c = new ModifiedComparator();
      assertEquals(0, c.compare(file1.toPath(), file2.toPath()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}