package cs3500.pa01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents the writer for the studyGuide md file and the questionBank sr file
 */
public class StudyFileWriter {
  /**
   * This method writes the given summary document by iterating through the list of markdownFiles,
   * adding their content onto the desired output path.
   *
   * @param sg - The StudyGuide to be written onto the new summary document
   *
   * @throws IOException - thrown when an IO error occurs when reading and writing files
   */
  public void writeStudyGuideFile(StudyGuideWalker sg, Path op) throws IOException {
    String filePath = op.toString();
    File file = new File(filePath);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    for (int i = 0; i < sg.getMarkdownFiles().size(); i++) {
      String f = sg.shortenFile(sg.getMarkdownFiles().get(i));
      writer.write(f);
    }
    writer.close();
  }

  /**
   * This method writes a new question bank sr file that automatically sets
   * all questions to hard.
   * @param sg - the StudyGuideWalker that accumulates the sr questionList
   * @param op - the output path for the new questionBank sr file
   * @throws IOException - when an error occurs with accessing a file
   */
  public void writeQuestionBankFile(StudyGuideWalker sg, Path op) throws IOException {
    String path = op.toString();
    String filePath = path.substring(0, path.length() - 2) + "sr";
    File file = new File(filePath);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    for (int i = 0; i < sg.getQuestionList().size(); i++) {
      String question = sg.getQuestionList().get(i) + "---HARD\n";
      writer.write(question);
    }
    writer.close();
  }

  /**
   * This method rewrites a question bank sr file, updating its questions difficulty.
   * @param p - the path to rewrite the question data onto
   * @param questions - the list of questions to rewrite onto the file
   * @throws IOException - when an error occurs with accessing a file
   */
  public void rewriteQuestionBank(Path p, ArrayList<Question> questions) throws IOException {
    File file = new File(p.toString());
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    for (Question q : questions) {
      String question = q.toString() + "\n";
      writer.write(question);
    }
    writer.close();
  }
}