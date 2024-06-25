import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CreateQuestionForm extends JFrame {
    JTextField questionIdField, questionPositionField, questionTextField, surveyIdField;
    JComboBox<String> questionTypeBox;
    JButton saveQuestionBtn;

    CreateQuestionForm() {
        setTitle("Create Question");
        setLayout(new GridLayout(6, 2));

        questionIdField = new JTextField();
        questionPositionField = new JTextField();
        questionTextField = new JTextField();
        surveyIdField = new JTextField();
        questionTypeBox = new JComboBox<>(new String[] {"MCQ", "Open-ended"});

        saveQuestionBtn = new JButton("Save Question");

        add(new JLabel("Question ID:"));
        add(questionIdField);
        add(new JLabel("Question Position:"));
        add(questionPositionField);
        add(new JLabel("Question Text:"));
        add(questionTextField);
        add(new JLabel("Survey ID:"));
        add(surveyIdField);
        add(new JLabel("Question Type:"));
        add(questionTypeBox);
        add(saveQuestionBtn);

        saveQuestionBtn.addActionListener(e -> saveQuestion());

        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void saveQuestion() {
        try {
            String questionId = questionIdField.getText();
            String questionPosition = questionPositionField.getText();
            String questionText = questionTextField.getText();
            String surveyId = surveyIdField.getText();
            String questionType = (String) questionTypeBox.getSelectedItem();

            Question question = new Question(questionId, questionPosition, questionText, surveyId, questionType);
            ArrayList<Question> questions = new ArrayList<>();
            File file = new File("questions.ser");
            if (file.exists()) {
                questions = readQuestions();
            }
            questions.add(question);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(questions);
            oos.close();
            JOptionPane.showMessageDialog(this, "Question Created Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Question> readQuestions() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("questions.ser"));
        return (ArrayList<Question>) ois.readObject();
    }

    public static void main(String[] args) {
        new CreateQuestionForm();
    }
}
