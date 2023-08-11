package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The type Session view test.
 */
class SessionViewTest {
  private final SessionView sv = new SessionView();

  /**
   * Test welcome screen.
   */
  @Test
  public void testWelcomeScreen() {
    assertEquals("""
      +*********************************************************************************+
      Choose an SR Question Bank File:""", sv.printWelcomeScreen());
  }

  /**
   * Test number of questions.
   */
  @Test
  public void testNumQuestions() {
    assertEquals("""
      +*********************************************************************************+
      How many questions would you like to practice today?:""", sv.printNumQuestions());
  }

  /**
   * Test print question.
   */
  @Test
  public void testPrintQuestion() {
    assertEquals("""
            +*********************************************************************************+
            How are you?
            Press [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]""",
        sv.printQuestion(new Question("How are you?", "Good!")));
  }

  /**
   * Test print answer.
   */
  @Test
  public void testPrintAnswer() {
    assertEquals("Good!",
        sv.printAnswer(new Question("How are you?", "Good!")));
  }

  /**
   * Test print end screen.
   */
  @Test
  public void testPrintEndScreen() {
    assertEquals("""
            +*********************************************************************************+
            Good Job!
            You answered 10 questions.
            2 questions went from easy to hard
            3 questions went from hard to easy
            Current Counts in Question Bank:
            15 Hard Questions
            30 Easy Questions
            +*********************************************************************************+""",
        sv.printEndScreen(10, 2, 3, 15, 30));
  }
}