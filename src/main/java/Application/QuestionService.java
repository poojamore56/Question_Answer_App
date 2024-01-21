
package Application;

import java.util.List;

public interface QuestionService {

    int addQuestions(Question newQuestion);
    int removeQuestions( int questionId ) ;

    List<Question> getAllQuestions();

    int modifyQuestion(int questionId, String ques);

    int modifyOption(Question newQuestion, int questionId);

    List<Question> getAllQuestions(int questionId);

    List<Question> getQuestionById(int questionId);
}