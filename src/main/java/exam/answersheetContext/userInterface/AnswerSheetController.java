package exam.answersheetContext.userInterface;

import exam.answersheetContext.application.AnswerSheetApplicationService;
import exam.answersheetContext.application.SubmitAnswerSheetCommand;
import exam.answersheetContext.doamin.model.answersheet.AnswerSheetId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer-sheet")
public class AnswerSheetController {

  private final AnswerSheetApplicationService answerSheetApplicationService;

  public AnswerSheetController(AnswerSheetApplicationService answerSheetApplicationService) {
    this.answerSheetApplicationService = answerSheetApplicationService;
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  AnswerSheetDto submit(@RequestBody SubmitAnswerSheetCommand command) {
    final AnswerSheetId answerSheetId = answerSheetApplicationService.submit(command);
    return AnswerSheetDto.from(answerSheetId);
  }

  @RequestMapping(method = RequestMethod.PUT)
  int handIn(String id) {
    return answerSheetApplicationService.handIn(id);
  }
}
