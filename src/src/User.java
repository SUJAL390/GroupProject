import java.io.Serializable;
import java.util.ArrayList;
// User class for storing user information
class User implements Serializable {
    private static final long serialVersionUID = 2L; // Update this as needed
    private String username;
    private String password;
    private String userType;
    private String firstName;
    private String lastName;
    private String faculty;
    private String emailAddress;
    private String gender;
    private String phoneNo;
    private ArrayList<Survey> surveys; // Store surveys created by the user

    public User(String username, String password, String userType, String firstName, String lastName, String faculty, String emailAddress, String gender, String phoneNo) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.surveys = new ArrayList<>();
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public ArrayList<Survey> getSurveys() {
        return surveys;
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }
}