package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The type Study session test.
 */
class StudySessionTest {

  private StudySession session;
  private int seed;

  /**
   * Sets study session and seed for testing random.
   */
  @BeforeEach
  public void setStudySession() {
    session = new StudySession();
    seed = 10;
  }

  /**
   * Test set question bank path errors.
   */
  @Test
  public void testSetQuestionBankPathErr() {
    assertThrows(IllegalArgumentException.class,
        () -> session.setQuestionBankPath(Path.of("notValid/summary.sr")));
    assertThrows(IllegalArgumentException.class,
        () -> session.setQuestionBankPath(Path.of("sampleInput/arrays.md")));
    assertThrows(IllegalArgumentException.class,
        () -> session.setQuestionBankPath(Path.of("notValid/arrays.md")));
    assertDoesNotThrow(() ->
        session.setQuestionBankPath(Path.of("sampleQuestions/animals.sr")));
  }

  /**
   * Test set question path.
   */
  @Test
  public void testSetQuestionPath() {
    assertAll(() ->
        session.setQuestionBankPath(Path.of("sampleQuestions/sampleQuestions.sr")));
    assertThrows(IllegalArgumentException.class,
        () -> session.setQuestionBankPath(Path.of("notValid")));
    assertThrows(IllegalArgumentException.class,
        () -> session.setQuestionBankPath(Path.of("sampleInput/norMarkdown.pdf")));
  }

  /**
   * Test full list.
   */
  @Test
  public void testFullList() {
    session.setQuestionBankPath(Path.of("sampleQuestions/sampleQuestions.sr"));
    session.createQuestionList(new Random(seed));

    assertEquals(4, session.getQuestionList().size());
  }

  /**
   * Test full list err.
   */
  @Test
  public void testFullListErr() {
    try {
      session.setQuestionBankPath(Path.of("notValid"));
    } catch (IllegalArgumentException e) {
      System.out.print("");
    }

    assertThrows(RuntimeException.class,
        () -> session.createQuestionList(new Random(seed)));
  }

  /**
   * Test update question difficulty when the difficulties stay the same.
   */
  @Test
  public void testUpdateDifficultyNoChange() {
    session.setQuestionBankPath(Path.of("sampleQuestions/sampleQuestions.sr"));
    session.createQuestionList(new Random(seed));

    session.updateDifficulty(session.getQuestionList().get(0), Difficulty.HARD);
    assertEquals(session.getQuestionList().get(0).getDifficulty(), Difficulty.HARD);
    assertEquals(0, session.getEasyToHard());

    session.updateDifficulty(session.getQuestionList().get(1), Difficulty.EASY);
    assertEquals(0, session.getHardToEasy());
  }

  /**
   * Test update question when a question is changed from hard to easy.
   */
  @Test
  public void testUpdateQuestionEasy() {
    session.setQuestionBankPath(Path.of("sampleQuestions/sampleQuestions.sr"));
    session.createQuestionList(new Random(seed));

    session.updateDifficulty(session.getQuestionList().get(0), Difficulty.EASY);
    assertEquals(1, session.getHardToEasy());
  }

  /**
   * Test update question when a question is changed from easy to hard.
   */
  @Test
  public void testUpdateQuestionHard() {
    session.setQuestionBankPath(Path.of("sampleQuestions/sampleQuestions.sr"));
    session.createQuestionList(new Random(seed));

    session.updateDifficulty(session.getQuestionList().get(3), Difficulty.HARD);
    assertEquals(1, session.getEasyToHard());
  }


  /**
   * Test get hard questions.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testGetHardQuestions() throws IOException {
    Path p = Paths.get("sampleQuestions/animals.sr");
    session.setQuestionBankPath(p);
    session.createQuestionList(new Random());
    session.rewriteQuestionBank();

    assertEquals(1, session.getHardQuestions());
  }

  /**
   * Test get easy questions.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testGetEasyQuestions() throws IOException {
    StudySession session = new StudySession();
    Path p = Paths.get("sampleQuestions/animals.sr");
    session.setQuestionBankPath(p);
    session.createQuestionList(new Random());
    session.rewriteQuestionBank();

    assertEquals(1, session.getEasyQuestions());
  }
}