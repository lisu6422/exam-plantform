package exam.paperContext.domain.model.answersheet;

import java.util.List;

public interface AnswerSheetRepository {
	AnswerSheet find(AnswerSheetId paperId);

	void save(AnswerSheet answerSheet);

	List<AnswerSheet> getAll();
}