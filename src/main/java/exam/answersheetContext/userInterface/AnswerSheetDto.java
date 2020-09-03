package exam.answersheetContext.userInterface;

import exam.answersheetContext.doamin.model.answersheet.AnswerSheetId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerSheetDto {
  private String uri;

  public static AnswerSheetDto from(AnswerSheetId answerSheetId) {
    return new AnswerSheetDto("answer-sheet/" + answerSheetId);
  }
  ;
}
