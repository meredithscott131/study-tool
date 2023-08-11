package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The type Session controller test.
 */
class SessionControllerTest {

  /**
   * Innit.
   *
   * @throws IOException the io exception
   */
  @BeforeEach
  public void innit() throws IOException {
    Path path = Paths.get("sampleQuestions/sampleQuestions.sr");
    ArrayList<Question> questions =
        new ArrayList<>(Arrays.asList(new Question("What is 2 + 2?:::4---HARD"),
                                      new Question("What color is the sky?:::blue---EASY"),
                                      new Question("Best snack food?:::Pretzels---EASY"),
                                      new Question("Best drink to code with?:::Tea---EASY")));
    StudyFileWriter fw = new StudyFileWriter();
    fw.rewriteQuestionBank(path, questions);
  }

  /**
   * Test session quit.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionQuit() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\n5\nQ");
    SessionController sc = new SessionController(in, out, new Random(seed));
    sc.startStudy();

    String expected = """
        +*********************************************************************************+
        Choose an SR Question Bank File:
        +*********************************************************************************+
        How many questions would you like to practice today?:
        +*********************************************************************************+
        What is 2 + 2?
        Press [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]
        +*********************************************************************************+
        Good Job!
        You answered 0 questions.
        0 questions went from easy to hard
        0 questions went from hard to easy
        Current Counts in Question Bank:
        1 Hard Questions
        3 Easy Questions
        +*********************************************************************************+""";

    assertEquals(expected.trim().replaceAll("[\r\n]+", ""),
        out.toString().trim().replaceAll("[\r\n]+", ""));
  }

  /**
   * Test session change a question to hard.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionChangeHard() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\n1\nH");
    SessionController sc = new SessionController(in, out, new Random(seed));
    sc.startStudy();

    String expected = """
        +*********************************************************************************+
        Choose an SR Question Bank File:
        +*********************************************************************************+
        How many questions would you like to practice today?:
        +*********************************************************************************+
        What is 2 + 2?
        Press [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]
        +*********************************************************************************+
        Good Job!
        You answered 1 questions.
        0 questions went from easy to hard
        0 questions went from hard to easy
        Current Counts in Question Bank:
        1 Hard Questions
        3 Easy Questions
        +*********************************************************************************+""";

    assertEquals(expected.trim().replaceAll("[\r\n]+", ""),
        out.toString().trim().replaceAll("[\r\n]+", ""));
  }

  /**
   * Test session change a question to easy.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionChangeEasy() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\n1\nE");
    SessionController sc = new SessionController(in, out, new Random(seed));
    sc.startStudy();

    String expected = """
        +*********************************************************************************+
        Choose an SR Question Bank File:
        +*********************************************************************************+
        How many questions would you like to practice today?:
        +*********************************************************************************+
        What is 2 + 2?
        Press [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]
        +*********************************************************************************+
        Good Job!
        You answered 1 questions.
        0 questions went from easy to hard
        1 questions went from hard to easy
        Current Counts in Question Bank:
        0 Hard Questions
        4 Easy Questions
        +*********************************************************************************+""";

    assertEquals(expected.trim().replaceAll("[\r\n]+", ""),
        out.toString().trim().replaceAll("[\r\n]+", ""));
  }

  /**
   * Test session show answer.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionShowAnswer() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\n1\nS");
    SessionController sc = new SessionController(in, out, new Random(seed));
    sc.startStudy();

    String expected = """
        +*********************************************************************************+
        Choose an SR Question Bank File:
        +*********************************************************************************+
        How many questions would you like to practice today?:
        +*********************************************************************************+
        What is 2 + 2?
        Press [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]
        4
        +*********************************************************************************+
        Good Job!
        You answered 1 questions.
        0 questions went from easy to hard
        0 questions went from hard to easy
        Current Counts in Question Bank:
        1 Hard Questions
        3 Easy Questions
        +*********************************************************************************+""";

    assertEquals(expected.trim().replaceAll("[\r\n]+", ""),
        out.toString().trim().replaceAll("[\r\n]+", ""));
  }

  /**
   * Test session path error.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionPathErr() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("notValid");
    SessionController sc = new SessionController(in, out, new Random(seed));

    assertThrows(RuntimeException.class,
        sc::startStudy);
  }

  /**
   * Test session question amount error.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionQuestionAmountErr() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\np");
    SessionController sc = new SessionController(in, out, new Random(seed));

    assertThrows(RuntimeException.class,
        sc::startStudy);
  }

  /**
   * Test session command error.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testSessionCommandErr() throws IOException {
    int seed = 10;
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("sampleQuestions/sampleQuestions.sr\n1\nO");
    SessionController sc = new SessionController(in, out, new Random(seed));

    assertThrows(RuntimeException.class,
        sc::startStudy);
  }
}