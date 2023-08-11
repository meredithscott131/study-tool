package cs3500.pa01;

/**
 * Represents the presentation of information in the study session application.
 */
public class SessionView {

  /**
   * @return the welcome screen to the study session application, asking for the sr file path
   */
  public String printWelcomeScreen() {
    return """
        +*********************************************************************************+
        Choose an SR Question Bank File:""";
  }

  /**
   * @return a screen asking the user for the number of questions they'd like to answer
   */
  public String printNumQuestions() {
    return """
        +*********************************************************************************+
        How many questions would you like to practice today?:""";
  }

  /**
   * @param q - the question to be printed
   * @return the given question's prompt and instructions on how to engage with it
   */
  public String printQuestion(Question q) {
    return "+*********************************************************************************+\n"
        + q.getPrompt()
        + "\nPress [H to change to hard] [E to change to easy] [S to see the answer] [Q to quit]";
  }

  /**
   * @param q - the question to be printed
   * @return the given question's answer
   */
  public String printAnswer(Question q) {
    return q.getAnswer();
  }

  /**
   * @param numQuestions -total number of questions the user has engaged with
   * @param easyToHard - total number of questions the user has changed from easy to hard
   * @param hardToEasy - total number of questions the user has changed from hard to easy
   * @param hardCount - total number of hard questions in the updated sr file
   * @param easyCount - total number of easy questions in the updated sr file
   * @return screen sharing the final data from the study session
   */
  public String printEndScreen(int numQuestions, int easyToHard,
                               int hardToEasy, int hardCount, int easyCount) {
    return "+*********************************************************************************+\n" +
        "Good Job!\n" +
        "You answered " + numQuestions + " questions.\n" +
        easyToHard + " questions went from easy to hard\n" +
        hardToEasy + " questions went from hard to easy\n" +
        "Current Counts in Question Bank:\n" +
        hardCount + " Hard Questions\n" +
        easyCount + " Easy Questions\n" +
        "+*********************************************************************************+";
  }
}