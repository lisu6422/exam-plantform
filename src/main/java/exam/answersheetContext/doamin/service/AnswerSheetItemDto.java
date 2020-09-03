package exam.answersheetContext.doamin.service;

import exam.answersheetContext.doamin.model.answersheet.AnswerSheet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerSheetItemDto {
  private int number;
  private String answer;
  private int score;

  public static AnswerSheet.AnswerSheetItem toAnswerSheetItem(
      AnswerSheetItemDto answerSheetItemDto) {
    return new AnswerSheet.AnswerSheetItem(
        answerSheetItemDto.getNumber(),
        answerSheetItemDto.getAnswer(),
        answerSheetItemDto.getScore());
  }
}
