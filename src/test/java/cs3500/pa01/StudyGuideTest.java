package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * The type Study guide test.
 */
class StudyGuideTest {

  /**
   * Clear files created during tests.
   */
  @AfterAll
  public static void clearFiles() {
    Path outputPathMd = Paths.get("studyGuide/summary.md");
    Path outputPathSr = Paths.get("studyGuide/summary.sr");
    outputPathMd.toFile().delete();
    outputPathSr.toFile().delete();
  }

  /**
   * Test invalid origin directory.
   */
  @Test
  public void testInvalidOriginDirectory() {
    assertThrows(RuntimeException.class, () -> new StudyGuide("notValid/",
        "modified", "studyGuide/"));
  }

  /**
   * Test invalid ordering flag.
   */
  @Test
  public void testInvalidOrderingFlag() {
    assertThrows(IllegalArgumentException.class,
        () -> new StudyGuide("sampleInput/",
            "notValid", "studyGuide/"));
  }

  /**
   * Test invalid output path.
   */
  @Test
  public void testInvalidOutputPath() {
    assertThrows(RuntimeException.class,
        () -> new StudyGuide("sampleInput/",
            "filename", "notValid/"));
  }

  /**
   * Test create study files with the filename ordering flag.
   */
  @Test
  public void testCreateStudyFilesFilename() {
    Path outputPath = Paths.get("studyGuide/summary.md");
    StudyGuide sg = new StudyGuide("sampleInput/",
        "filename", outputPath.toString());
    sg.createStudyFiles();
    assertTrue(outputPath.toFile().exists());
    assertTrue(outputPath.toFile().exists());
  }

  /**
   * Test create study files with the modified ordering flag.
   */
  @Test
  public void testCreateStudyFilesModified() {
    Path outputPath = Paths.get("studyGuide/summary.md");
    StudyGuide sg = new StudyGuide("sampleInput/",
        "modified", outputPath.toString());
    sg.createStudyFiles();
    assertTrue(outputPath.toFile().exists());
    assertTrue(outputPath.toFile().exists());
  }

  /**
   * Test create study files with the created ordering flag.
   */
  @Test
  public void testCreateStudyFilesCreated() {
    Path outputPath = Paths.get("studyGuide/summary.md");
    StudyGuide sg = new StudyGuide("sampleInput/",
        "created", outputPath.toString());
    sg.createStudyFiles();
    assertTrue(outputPath.toFile().exists());
    assertTrue(outputPath.toFile().exists());
  }
}