import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {
    private String surveyId;
    private String surveyTitle;
    private String scId; // Survey Creator ID
    private String creatorName;
    private ArrayList<Question> questions;

    public Survey(String surveyId, String surveyTitle, String scId, String creatorName) {
        this.surveyId = surveyId;
        this.surveyTitle = surveyTitle;
        this.scId = scId;
        this.creatorName = creatorName;
        this.questions = new ArrayList<>();
    }

    // Getters and setters

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
