package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * The type Study file writer test.
 */
class StudyFileWriterTest {

  /**
   * Clear the newly created files after each test.
   */
  @AfterAll
  public static void clearFiles() {
    Paths.get("studyGuide/test.md").toFile().delete();
    Paths.get("studyGuide/test.sr").toFile().delete();
  }

  /**
   * Test write study guide.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testWriteStudyGuide() throws IOException {
    StudyFileWriter writer = new StudyFileWriter();
    writer.writeStudyGuideFile(new StudyGuideWalker(), Paths.get("studyGuide/test.md"));
  }

  /**
   * Test write question bank file.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testWriteQuestionBankFile() throws IOException {
    StudyFileWriter writer = new StudyFileWriter();
    writer.writeQuestionBankFile(new StudyGuideWalker(), Paths.get("studyGuide/test.sr"));
  }

  /**
   * Test rewrite question bank with a hard question.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testRewriteQuestionBankHard() throws IOException {
    File f = File.createTempFile("testQuestions", "sr");
    ArrayList<Question> sampleList = new ArrayList<>();
    sampleList.add(new Question("What month is it?:::May---HARD"));
    StudyFileWriter fw = new StudyFileWriter();
    fw.rewriteQuestionBank(f.toPath(), sampleList);

    BufferedReader reader = new BufferedReader(new FileReader(f));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
    reader.close();

    assertEquals(1, lines);
  }

  /**
   * Test rewrite question bank with an easy question.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testRewriteQuestionBankEasy() throws IOException {
    File f = File.createTempFile("testQuestions", "sr");
    ArrayList<Question> sampleList = new ArrayList<>();
    sampleList.add(new Question("What season is it?:::Spring---EASY"));
    StudyFileWriter fw = new StudyFileWriter();
    fw.rewriteQuestionBank(f.toPath(), sampleList);

    BufferedReader reader = new BufferedReader(new FileReader(f));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
    reader.close();

    assertEquals(1, lines);
  }
}