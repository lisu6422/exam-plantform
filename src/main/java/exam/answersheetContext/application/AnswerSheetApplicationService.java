package exam.answersheetContext.application;

import exam.answersheetContext.doamin.model.answersheet.AnswerSheet;
import exam.answersheetContext.doamin.model.answersheet.AnswerSheetId;
import exam.answersheetContext.doamin.model.answersheet.AnswerSheetRepository;
import exam.answersheetContext.doamin.service.AnswerSheetItemDto;
import exam.answersheetContext.doamin.service.BlankQuizDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AnswerSheetApplicationService {

  private AnswerSheetRepository answerSheetRepository;

  public AnswerSheetId submit(SubmitAnswerSheetCommand command) {

    List<AnswerSheetItemDto> answerSheetItemDtos = answerSheetItemFrom(command);
    List<BlankQuizDto> blankQuizDtos = blankQuizFrom(command);
    AnswerSheet answerSheet =
        AnswerSheet.submit(
            command.getStudentId(),
            command.getExaminationId(),
            command.getScore(),
            blankQuizDtos.stream().map(BlankQuizDto::toBlankQuiz).collect(toList()),
            answerSheetItemDtos.stream()
                .map(AnswerSheetItemDto::toAnswerSheetItem)
                .collect(toList()));

    answerSheetRepository.save(answerSheet);
    return AnswerSheetId.nextId();
  }

  public void handIn(){

  }

  private List<AnswerSheetItemDto> answerSheetItemFrom(SubmitAnswerSheetCommand command) {
    return command.getAnswerSheetItemList().stream()
        .map(item -> new AnswerSheetItemDto(item.getNumber(), item.getAnswer(), item.getScore()))
        .collect(toList());
  }

  private List<BlankQuizDto> blankQuizFrom(SubmitAnswerSheetCommand command) {
    return command.getBlankQuizList().stream()
        .map(
            quiz ->
                new BlankQuizDto(
                    quiz.getNumber(), quiz.getAnswer(), quiz.getReferenceAnswer(), quiz.getScore()))
        .collect(toList());
  }

  public int handIn(String id) {
    AnswerSheet answerSheet = answerSheetRepository.find(new AnswerSheetId(id));
    answerSheet.handIn();
    return answerSheet.getScore();
  }
}
