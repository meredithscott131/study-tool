package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

/**
 * Representation of the data flow in a StudySession.
 */
public class SessionController {

  private final StudySession model = new StudySession();
  private final SessionView view = new SessionView();
  private final Readable in;
  private final Appendable out;
  private final Random rand; // used for testing sorting
  private final Scanner scan;

  /**
   * Instantiates a new Session controller.
   *
   * @param in  the in
   * @param out the out
   */
  public SessionController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    this.rand = new Random();
    this.scan = new Scanner(this.in);
  }

  /**
   * Instantiates a new Session controller with a desired random
   * value for sorting the question list.
   *
   * @param in   the in
   * @param out  the out
   * @param rand the rand
   */
  public SessionController(Readable in, Appendable out, Random rand) {
    this.in = in;
    this.out = out;
    this.rand = rand;
    this.scan = new Scanner(this.in);
  }

  /**
   * Launches the study session sequence.
   *
   * @throws IOException - when an error occurs when accessing a file or stream
   */
  public void startStudy() throws IOException {
    this.out.append(view.printWelcomeScreen()).append("\n");
    String path = scan.next();
    this.model.setQuestionBankPath(Paths.get(path));
    this.model.createQuestionList(this.rand);

    this.out.append(this.view.printNumQuestions()).append("\n");
    int num;
    try {
      num = Integer.parseInt(scan.next());
    } catch (NumberFormatException ex) {
      throw new RuntimeException("Question amount must be a non-negative integer.");
    }
    int numQuestions = Math.min(num, this.model.getQuestionList().size());

    int numQ = 0;
    loop: for (int i = 0; i < numQuestions; i++) {
      numQ++;
      Question curQ = this.model.getQuestionList().get(i);
      this.out.append(view.printQuestion(curQ)).append("\n");
      String response = scan.next();
      switch (response) {
        case "H" -> this.model.updateDifficulty(curQ, Difficulty.HARD);
        case "E" -> this.model.updateDifficulty(curQ, Difficulty.EASY);
        case "S" -> this.out.append(view.printAnswer(this.model.getQuestionList().get(i)))
            .append("\n");
        case "Q" -> {
            numQ--;
            break loop; }
        default -> throw new RuntimeException("Invalid Command");
      }
    }
    this.model.rewriteQuestionBank();
    this.out.append(view.printEndScreen(numQ,
        this.model.getEasyToHard(), this.model.getHardToEasy(),
        this.model.getHardQuestions(), this.model.getEasyQuestions())).append("\n");
  }
}