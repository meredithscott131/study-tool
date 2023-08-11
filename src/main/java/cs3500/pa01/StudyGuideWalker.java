package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the content stored in the study guide or the summary document.
 * It is responsible for walking the file tree, formatting each
 * markdown file visited, and adding that formatted file to the list markdownFiles.
 */
public class StudyGuideWalker implements FileVisitor<Path> {
  private final ArrayList<Path> markdownFiles = new ArrayList<>();
  private final ArrayList<String> questionList = new ArrayList<>();

  /**
   * This method is invoked for a directory before
   * entries in the directory are visited.
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return - CONTINUE to continue the traversal.
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * This method is invoked for a visitable file in the directory.
   * It adds the current file to the list markdownFiles if it is a markdown file.
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return - CONTINUE to continue the traversal.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (attrs.isRegularFile()) {
      String fileName = (file.getFileName()).toString();

      // Checks if the file is a markdown file
      if (fileName.endsWith("md")) {
        markdownFiles.add(file);
      }
    }
    return CONTINUE;
  }

  /**
   * This method is invoked for a file that could not be visited.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return - CONTINUE to continue the traversal.
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    if (file == null) {
      throw new RuntimeException("Cannot visit file");
    }
    return CONTINUE;
  }

  /**
   * This method is invoked for a directory after all entries in the directory
   *
   * @param dir a reference to the directory
   * @param exc the I/O exception that prevented the file from being visited
   * @return - CONTINUE to continue the traversal.
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return CONTINUE;
  }

  /**
   * This method takes in a path of a markdown file and reads it line by line.
   * Each line is formatted to fit the summary document structure and added to
   * a growing StringBuilder. Also finds questions and adds them onto the growing questionList.
   *
   * @param path - a path to a file that will be properly formatted/condensed
   * @return - a String representation of the condensed file
   * @throws IOException - when an error occurs with reading/accessing the given path
   */
  public String shortenFile(Path path) throws IOException {
    ArrayList<String> lines = fileByLine(path);
    StringBuilder headerContent = new StringBuilder();
    StringBuilder fileString = new StringBuilder();
    Pattern pattern = Pattern.compile("\\[\\[(.*?)]]");

    for (String line : lines) {
      if (isHeader(line)) {
        Matcher matcher = pattern.matcher(headerContent.toString());
        while (matcher.find()) {
          if (matcher.group().contains(":::")) {
            questionList.add(matcher.group().substring(2, matcher.group().length() - 2));
          } else {
            fileString.append("\n- ")
                .append(matcher.group(), 2, matcher.group().length() - 2);
          }
        }
        headerContent.setLength(0);
        fileString.append("\n\n").append(line);
      } else {
        headerContent.append(line);
      }
    }
    Matcher matcher = pattern.matcher(headerContent.toString());
    while (matcher.find()) {
      if (matcher.group().contains(":::")) {
        questionList.add(matcher.group().substring(2, matcher.group().length() - 2));
      } else {
        fileString.append("\n- ")
            .append(matcher.group(), 2, matcher.group().length() - 2);
      }
    }
    return fileString.toString();
  }

  /**
   * This method is responsible for converting the given path to a list
   * of Strings representing its lines.
   *
   * @param path - the path to the file that will be converted
   * @return - the list of lines from the given file
   * @throws IOException - when an error occurs with reading/accessing the given path
   */
  public ArrayList<String> fileByLine(Path path) throws IOException {
    ArrayList<String> fileLines = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
    String line;

    while ((line = br.readLine()) != null) {
      if (line.startsWith("#") || line.contains("[[")) {
        fileLines.add(line);
      } else {
        fileLines.add(" " + line);
      }

    }
    return fileLines;
  }

  /**
   * Determines whether a given line is a header.
   *
   * @param line the line
   * @return the boolean
   */
  public boolean isHeader(String line){
    return line.startsWith("#");
  }

  /**
   * Add the given question to the question list.
   *
   * @param question - String representation of a question to be added to the growing questionList
   */
  public void addQuestion(String question){
    questionList.add(question + "---HARD");
  }

  /**
   * @return the list of markdown files.
   */
  public ArrayList<Path> getMarkdownFiles() {
    return this.markdownFiles;
  }

  /**
   * @return the list of questions.
   */
  public ArrayList<String> getQuestionList() {
    return this.questionList;
  }
}