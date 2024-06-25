import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CreateSurveyForm extends JFrame {
    JTextField surveyIdField, surveyTitleField, scIdField, creatorNameField;
    JButton saveSurveyBtn;

    CreateSurveyForm() {
        setTitle("Create Survey");
        setLayout(new GridLayout(5, 2));

        surveyIdField = new JTextField();
        surveyTitleField = new JTextField();
        scIdField = new JTextField();
        creatorNameField = new JTextField();

        saveSurveyBtn = new JButton("Save Survey");

        add(new JLabel("Survey ID:"));
        add(surveyIdField);
        add(new JLabel("Survey Title:"));
        add(surveyTitleField);
        add(new JLabel("Survey Creator ID:"));
        add(scIdField);
        add(new JLabel("Creator Name:"));
        add(creatorNameField);
        add(saveSurveyBtn);

        saveSurveyBtn.addActionListener(e -> saveSurvey());

        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void saveSurvey() {
        try {
            String surveyId = surveyIdField.getText();
            String surveyTitle = surveyTitleField.getText();
            String scId = scIdField.getText();
            String creatorName = creatorNameField.getText();

            Survey survey = new Survey(surveyId, surveyTitle, scId, creatorName);
            ArrayList<Survey> surveys = new ArrayList<>();
            File file = new File("surveys.ser");
            if (file.exists()) {
                surveys = readSurveys();
            }
            surveys.add(survey);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(surveys);
            oos.close();
            JOptionPane.showMessageDialog(this, "Survey Created Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Survey> readSurveys() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("surveys.ser"));
        return (ArrayList<Survey>) ois.readObject();
    }

    public static void main(String[] args) {
        new CreateSurveyForm();
    }
}
