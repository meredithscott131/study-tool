package cs3500.pa01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * This program offers a way to either condense markdown files within a
 * directory into a single studyGuide file and a corresponding questionBank sr file,
 * or a way to review questions found in a pre-existing questionBank file.
 * ---------------------------------------------------------------------------------------
 * TO GENERATE FILES:
 * This program takes in 3 command-line arguments:
 * a relative/absolute path to a directory of markdown files,
 * an ordering flag representing how the summary document should be ordered,
 * and a relative/absolute output path of where to write the summary document
 * generated in this program
 * ---------------------------------------------------------------------------------------
 * TO REVIEW QUESTIONS:
 * This program takes in no command-line arguments and generates a console application.
 * Within the application, the user has the ability to select a questionBank file
 * path and review the questions contained in it. Users can change question
 * difficulty and override the questionBank file.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - To generate new files, this takes in a starting directory path,
   *               an ordering flag, and the output path. To review questions,
   *               this takes in no arguments.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      StudyGuide sg = new StudyGuide(args[0], args[1], args[2]);
      sg.createStudyFiles();
    } else if (args.length == 0) {
      SessionController session =
         new SessionController(new InputStreamReader(System.in), System.out);
     session.startStudy();
    }
  }
}