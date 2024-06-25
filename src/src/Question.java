import java.io.Serializable;

public class Question implements Serializable {
    private String questionId;
    private String questionPosition; // Position in the survey
    private String questionText;
    private String surveyId; // Associated Survey ID
    private String questionType;

    public Question(String questionId, String questionPosition, String questionText, String surveyId, String questionType) {
        this.questionId = questionId;
        this.questionPosition = questionPosition;
        this.questionText = questionText;
        this.surveyId = surveyId;
        this.questionType = questionType;
    }

    // Getters and setters

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionPosition() {
        return questionPosition;
    }

    public void setQuestionPosition(String questionPosition) {
        this.questionPosition = questionPosition;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
