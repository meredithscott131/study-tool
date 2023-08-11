package cs3500.pa01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents the model of a study session and is
 * responsible for creation and updating of relevant data
 */
public class StudySession {
  private Path questionBankPath;
  private ArrayList<Question> questionList = new ArrayList<>();
  private int hardToEasy;
  private int easyToHard;
  private int easyQuestions = 0;
  private int hardQuestions = 0;

  public void setQuestionBankPath(Path p) {
    if (Files.exists(p) && p.toString().endsWith(".sr")) {
        this.questionBankPath = p;
    } else {
      throw new IllegalArgumentException("Not a valid path");
    }
  }

  /**
   * Creates a list of questions to study from
   */
  public void createQuestionList(Random rand) {
      questionList = this.fullList();
      Collections.shuffle(questionList, rand);
      questionList.sort(new DifficultyComparator());
  }

  public ArrayList<Question> getQuestionList() {
    return questionList;
  }

  /**
   * @return - the full list of questions from the set question bank path unordered
   */
  private ArrayList<Question> fullList() {
    ArrayList<Question> tempList = new ArrayList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(this.questionBankPath.toString()));
      String line = reader.readLine();
      while (line != null) {
        tempList.add(new Question(line));
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return tempList;
  }

  /**
   * @param q - the question to be updated
   * @param d - the new difficulty of the question
   */
  public void updateDifficulty(Question q, Difficulty d) {
      if (d.equals(Difficulty.HARD) && q.getDifficulty().equals(Difficulty.EASY)) {
        easyToHard++;
      } else if (d.equals(Difficulty.EASY) && q.getDifficulty().equals(Difficulty.HARD)) {
        hardToEasy++;
      }
      q.updateQuestion(d);
  }

  /**
   * @return the count of the easy questions converted into hard questions during the session
   */
  public int getEasyToHard() {
    return easyToHard;
  }

  /**
   * @return the count of the hard questions converted into easy questions during the session
   */
  public int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * @throws IOException when an error occurs in accessing a file
   */
  public void rewriteQuestionBank() throws IOException {
    StudyFileWriter fw = new StudyFileWriter();
    fw.rewriteQuestionBank(questionBankPath, questionList);
  }

  /**
   * @return the count of hard questions found in a sr questionBank file
   */
  public int getEasyQuestions() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(this.questionBankPath.toString()));
    String line = reader.readLine();

    while (line != null) {
      if (line.endsWith("EASY")) {
        easyQuestions++;
      }
      line = reader.readLine();
    }
    reader.close();
    return easyQuestions;
  }

  /**
   * @return the count of easy questions found in a sr questionBank file
   */
  public int getHardQuestions() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(this.questionBankPath.toString()));
    String line = reader.readLine();

    while (line != null) {
      if (line.endsWith("HARD")) {
        hardQuestions++;
      }
      line = reader.readLine();
    }
    reader.close();
    return hardQuestions;
  }
}