package exam.answersheetContext.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
public class SubmitAnswerSheetCommand {
  private String studentId;
  private String examinationId;
  private int score;
  private List<BlankQuiz> blankQuizList;
  private List<AnswerSheetItem> answerSheetItemList;

  @Value
  public static class BlankQuiz {
    private int number;
    private String answer;
    private String referenceAnswer;
    private int score;
  }

  @Value
  public class AnswerSheetItem {
    private int number;
    private String answer;
    private int score;
  }
}
