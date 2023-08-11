package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * The type Question test.
 */
class QuestionTest {

  /**
   * Tests the creation of an easy question from a given string
   */
  @Test
  public void testCreatingEasyQuestion() {
    Question q = new Question("What time is it?:::Coding time!---EASY");

    assertEquals("What time is it?", q.getPrompt());
    assertEquals("Coding time!", q.getAnswer());
    assertEquals(q.getDifficulty(), Difficulty.EASY);
  }

  /**
   * Tests the creation of an easy question from a given string
   */
  @Test
  public void testCreatingHardQuestion() {
    Question q = new Question("What time is it?:::Coding time!---HARD");

    assertEquals("What time is it?", q.getPrompt());
    assertEquals("Coding time!", q.getAnswer());
    assertEquals(q.getDifficulty(), Difficulty.HARD);
  }

  /**
   * Tests the creation of an invalid question from a given string
   */
  @Test
  public void testCreatingInvalidQuestion() {
    assertThrows(RuntimeException.class,
        () -> new Question("What time is it?:::Coding time!---MEDIUM"));
  }

  /**
   * Test creating new question.
   */
  @Test
  public void testCreatingNewQuestion() {
    Question q = new Question("What time is it?", "Coding time!");
    assertEquals("What time is it?", q.getPrompt());
    assertEquals("Coding time!", q.getAnswer());
    assertEquals(q.getDifficulty(), Difficulty.HARD);
  }

  /**
   * Test hard question to string.
   */
  @Test
  public void testHardToString() {
    Question q = new Question("What time is it?", "Coding time!");
    assertEquals("What time is it?:::Coding time!---HARD", q.toString());
  }

  /**
   * Test easy question to string.
   */
  @Test
  public void testEasyToString() {
    Question q = new Question("What time is it?", "Coding time!");
    q.updateQuestion(Difficulty.EASY);
    assertEquals("What time is it?:::Coding time!---EASY", q.toString());
  }

  /**
   * Test update question difficulty.
   */
  @Test
  public void testUpdateQuestion() {
    Question q = new Question("What time is it?:::Coding time!---EASY");
    q.updateQuestion(Difficulty.HARD);
    assertEquals("What time is it?:::Coding time!---HARD", q.toString());
  }

  /**
   * Tests getting the prompt from a question.
   */
  @Test
  public void testGetPrompt() {
    Question q = new Question("What time is it?:::Coding time!---HARD");
    assertEquals(q.getPrompt(), "What time is it?");
  }

  /**
   * Tests getting the answer from a question.
   */
  @Test
  public void testGetAnswer() {
    Question q = new Question("What time is it?:::Coding time!---HARD");
    assertEquals(q.getAnswer(), "Coding time!");
  }

  /**
   * Tests getting the difficulty from a question.
   */
  @Test
  public void testGetDifficulty() {
    Question q = new Question("What time is it?:::Coding time!---HARD");
    assertEquals(q.getDifficulty(), Difficulty.HARD);
  }

  /**
   * Tests comparing two questions.
   */
  @Test
  public void testCompareTo() {
    Question q1 = new Question("What time is it?:::Coding time!---HARD");
    Question q2 = new Question("What time is it?:::Coding time!---EASY");
    assertEquals(-1, q1.compareTo(q2));
  }
}