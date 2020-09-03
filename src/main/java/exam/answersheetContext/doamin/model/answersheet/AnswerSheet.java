package exam.answersheetContext.doamin.model.answersheet;

import exam.paperContext.shared.Entity;
import exam.paperContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AnswerSheet implements Entity<AnswerSheet> {
  private AnswerSheetId answerSheetId;
  private String studentId;
  private String examinationId;
  private int score;
  private List<BlankQuiz> blankQuizList;
  private List<AnswerSheetItem> answerSheetItemList;

  public AnswerSheet(
      AnswerSheetId answerSheetId,
      String studentId,
      String examinationId,
      int score,
      List<BlankQuiz> blankQuizList,
      List<AnswerSheetItem> answerSheetItemList) {
    this.answerSheetId = answerSheetId;
    this.studentId = studentId;
    this.examinationId = examinationId;
    this.score = score;
    this.blankQuizList = blankQuizList;
    this.answerSheetItemList = answerSheetItemList;
  }

  @Override
  public boolean sameIdentityAs(AnswerSheet other) {
    return false;
  }

  public AnswerSheet assign(
      AnswerSheetId answerSheetId,
      String studentId,
      String examinationId,
      int score,
      List<BlankQuiz> blankQuizList,
      List<AnswerSheetItem> answerSheetItemList) {
    return new AnswerSheet(
        answerSheetId, studentId, examinationId, score, blankQuizList, answerSheetItemList);
  }

  public AnswerSheet(
      String studentId,
      String examinationId,
      int score,
      List<BlankQuiz> blankQuizList,
      List<AnswerSheetItem> answerSheetItemList) {
    this.studentId = studentId;
    this.examinationId = examinationId;
    this.score = score;
    this.blankQuizList = blankQuizList;
    this.answerSheetItemList = answerSheetItemList;
  }

  public static AnswerSheet submit(
      String studentId,
      String examinationId,
      int score,
      List<BlankQuiz> blankQuizList,
      List<AnswerSheetItem> answerSheetItemList) {
    return new AnswerSheet(studentId, examinationId, score, blankQuizList, answerSheetItemList);
  }

  public void handIn() {
    review();
  }

  private void review() {
    AtomicInteger totalScore = new AtomicInteger();
    answerSheetItemList.stream()
        .map(
            answerSheetItem -> {
              answerSheetItem.score = calculateAnswerSheetItemScore(answerSheetItem);
              totalScore.addAndGet(answerSheetItem.getScore());
              return totalScore;
            })
        .collect(Collectors.toList());
  }

  private int calculateAnswerSheetItemScore(AnswerSheetItem answerSheetItem) {
    return 0;
  }

  public AnswerSheetId getAnswerSheetId() {
    return answerSheetId;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getExaminationId() {
    return examinationId;
  }

  public int getScore() {
    return score;
  }

  public List<BlankQuiz> getBlankQuizList() {
    return blankQuizList;
  }

  public List<AnswerSheetItem> getAnswerSheetItemList() {
    return answerSheetItemList;
  }

  @Getter
  @AllArgsConstructor
  public static class AnswerSheetItem implements ValueObject<AnswerSheetItem> {
    private int number;
    private String answer;
    private int score;

    @Override
    public boolean sameValueAs(AnswerSheetItem other) {
      return false;
    }
  }

  @Getter
  @AllArgsConstructor
  public static class BlankQuiz implements ValueObject<BlankQuiz> {
    private int number;
    private String answer;
    private String referenceAnswer;
    private int score;

    @Override
    public boolean sameValueAs(BlankQuiz other) {
      return false;
    }
  }
}
