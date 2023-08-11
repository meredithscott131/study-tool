package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a study guide that summarizes information
 * from a given directory and is stored in the given output path.
 * Also, responsible for generating a question bank document with
 * relevant information from the same directory.
 */
public class StudyGuide {
  private final Path startingDirectory;
  private final Path outputPath;
  private final OrderingFlag ordering;

  /**
   * Instantiates a new Study guide.
   *
   * @param startingDirectory the starting directory
   * @param ordering          the ordering flag
   * @param outputPath        the output path
   */
  StudyGuide(String startingDirectory, String ordering, String outputPath) {

    Path sd = Paths.get(startingDirectory);
    Path op = Paths.get(outputPath);

    // checks if the given startingDirectory is valid
    // If not, throws new RuntimeException
    if (Files.exists(sd)) {
        this.startingDirectory = sd;
    } else {
      throw new RuntimeException("Directory doesn't exist.");
    }

    // checks if the given ordering is valid
    // If it is, creates the subsequent comparator
    switch (ordering) {
      case "filename" -> this.ordering = new FileNameComparator();
      case "created" -> this.ordering = new CreationComparator();
      case "modified" -> this.ordering = new ModifiedComparator();
      default -> throw new IllegalArgumentException("Ordering Flag doesn't exist");
    }

    // checks if the given output path is a markdown file
    if (op.toString().endsWith("md")) {
      this.outputPath = op;
    } else {
      throw new RuntimeException("Cannot use output path. Must be markdown file.");
    }
  }

  /**
   * This method creates a new StudyGuide from walking through the origin directory.
   * This builds a list of condensed markdown files called markdownFiles.
   * This list is first ordered based on the desired ordering flag, and
   * then written onto the new summary document.
   * This method also creates a questionBank sr file from gathering questions
   * found in md files in the origin directory.
   */
  public void createStudyFiles() {
    StudyGuideWalker study = new StudyGuideWalker();
    try {
      Files.walkFileTree(this.startingDirectory, study);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    study.getMarkdownFiles().sort(this.ordering);

    try {
      StudyFileWriter fw = new StudyFileWriter();
      fw.writeStudyGuideFile(study, this.outputPath);
      fw.writeQuestionBankFile(study, this.outputPath);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}