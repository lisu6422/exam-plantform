package exam.answersheetContext.doamin.service;

import exam.answersheetContext.doamin.model.answersheet.AnswerSheet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlankQuizDto {
  private int number;
  private String answer;
  private String referenceAnswer;
  private int score;

  public static AnswerSheet.BlankQuiz toBlankQuiz(BlankQuizDto blankQuizDto) {
    return new AnswerSheet.BlankQuiz(
        blankQuizDto.getNumber(),
        blankQuizDto.getAnswer(),
        blankQuizDto.getReferenceAnswer(),
        blankQuizDto.getScore());
  }
}
