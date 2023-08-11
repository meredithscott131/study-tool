package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * The StudyGuideTest class holds all tests relating to the StudyGuide class
 * and its associated methods.
 */
class StudyGuideWalkerTest {

  /**
   * Tests the filterFile method given a path.
   *
   * @throws IOException - thrown when an IO error occurs when reading and writing files
   */
  @Test
  public void testFileByLine() throws IOException {

    StudyGuideWalker sg = new StudyGuideWalker();
    ArrayList<String> lines = sg.fileByLine(Paths.get("sampleInput/weekdays.md"));

    ArrayList<String> expLines = new ArrayList<>(Arrays.asList("# Weekdays",
                                 "- [[Monday]] [[Tuesday]]",
                                 "- [[Wednesday]] [[Thursday]]",
                                 " - Friday!"));
    assertEquals(expLines, lines);
  }

  /**
   * Tests the shortenFile method given a path.
   *
   * @throws IOException - thrown when an IO error occurs when reading and writing files
   */
  @Test
  public void testShortenFileWeekdays() throws IOException {
    Path p = Paths.get("sampleInput/weekdays.md");
    StudyGuideWalker sg = new StudyGuideWalker();
    assertEquals("""
        
        
        # Weekdays
        - Monday
        - Tuesday
        - Wednesday
        - Thursday""", sg.shortenFile(p));
  }

  /**
   * Test shorten file method given a path.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testShortenFileVectors() throws IOException {
    Path p = Paths.get("sampleInput/vectors.md");
    StudyGuideWalker sg = new StudyGuideWalker();
    assertEquals("""
                 
                 
        # Vectors
        - Vectors act like resizable arrays
               
        ## Declaring a vector
        - General Form: Vector<type> v = new Vector();
        - type needs to be a valid reference type
               
        ## Adding an element to a vector
        - v.add(object of type);""", sg.shortenFile(p));
  }

  /**
   * Test shorten file method given a path.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testShortenFileArrays() throws IOException {
    Path p = Paths.get("sampleInput/arrays.md");
    StudyGuideWalker sg = new StudyGuideWalker();
    assertEquals("""
                 
                 
        # Java Arrays
        - An **array** is a collection of variables of the same type
                
        ## Declaring an Array
        - General Form: type[] arrayName;
                
        ## Creating an Array (Instantiation)
        - General form:  arrayName = new type[numberOfElements];
        - numberOfElements must be a positive Integer.
        - Gotcha: Array size is not modifiable once instantiated.""", sg.shortenFile(p));
  }

  /**
   * Test shorten file method given a path.
   * NOTE: this file was taken from the official PA01
   * sample files to test specific edge cases.
   *
   * @throws IOException the io exception
   */
  @Test
  public void testShortenFileBrackets() throws IOException {
    Path p = Paths.get("sampleInput/brackets.md");
    StudyGuideWalker sg = new StudyGuideWalker();
    assertEquals("""
                 
                 
        # Some [[important [brackets]]]
        - works
        -  { as expected! }\s
        - CS3500
        - ==
        - OOD.
        - important phrase""", sg.shortenFile(p));
  }

  /**
   * Test if a line is a header.
   */
  @Test
  public void testIsHeader() {
    StudyGuideWalker sg = new StudyGuideWalker();
    assertTrue(sg.isHeader("# Heading"));
    assertTrue(sg.isHeader("## Heading"));
    assertTrue(sg.isHeader("### Heading"));
  }

  /**
   * Test add question.
   */
  @Test
  public void testAddQuestion() {
    StudyGuideWalker sg = new StudyGuideWalker();
    sg.addQuestion("this is a question:::yes");
    assertEquals(1, sg.getQuestionList().size());
  }

  /**
   * Test get question list.
   */
  @Test
  public void testGetQuestionList() {
    StudyGuideWalker sg = new StudyGuideWalker();
    assertNotNull(sg.getQuestionList());
  }

  /**
   * Test get markdown files.
   */
  @Test
  public void testGetMarkdownFiles() {
    StudyGuideWalker sg = new StudyGuideWalker();
    assertNotNull(sg.getMarkdownFiles());
  }
}