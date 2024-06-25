import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class LoginForm extends JFrame implements ActionListener {
    private JButton loginBtn, registerBtn;
    private JTextField userField, firstNameField, lastNameField, facultyField, emailField, genderField, phoneNoField;
    private JPasswordField passField;
    private JComboBox<String> userTypeBox;
    private JPanel registrationPanel;
    private User loggedInUser; // Store the currently logged in user

    LoginForm() {
        setTitle("Login Form");
        setLayout(new GridLayout(5, 2));

        userField = new JTextField();
        passField = new JPasswordField();
        userTypeBox = new JComboBox<>(new String[]{"User", "Survey Creator", "Admin"});

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        add(new JLabel("Username:"));
        add(userField);
        add(new JLabel("Password:"));
        add(passField);
        add(new JLabel("User Type:"));
        add(userTypeBox);
        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String userType = (String) userTypeBox.getSelectedItem();
            try {
                if (userType.equals("User") || userType.equals("Survey Creator") || userType.equals("Admin")) {
                    if (validateUserLogin(username, password, userType)) {
                        JOptionPane.showMessageDialog(this, userType + " Login Successful!");
                        // Load dashboard based on user type
                        switch (userType) {
                            case "Admin":
                                openAdminDashboard();
                                break;
                            case "User":
                                openUserDashboard();
                                break;
                            case "Survey Creator":
                                openSurveyCreatorDashboard();
                                break;
                            default:
                                JOptionPane.showMessageDialog(this, "Invalid User Type");
                                break;
                        }
                        this.dispose(); // Close login form
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Credentials");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid User Type");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
            }
        } else if (e.getSource() == registerBtn) {
            String userType = (String) userTypeBox.getSelectedItem();
            if (userType.equals("User")) {
                showRegistrationForm("User");
            } else if (userType.equals("Survey Creator")) {
                showRegistrationForm("Survey Creator");
            } else if (userType.equals("Admin")) {
                showRegistrationForm("Admin");
            }
        }
    }

    private void showRegistrationForm(String userType) {
        registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(9, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel facultyLabel = new JLabel("Faculty:");
        facultyField = new JTextField();
        JLabel emailLabel = new JLabel("Email Address:");
        emailField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        genderField = new JTextField();
        JLabel phoneNoLabel = new JLabel("Phone Number:");
        phoneNoField = new JTextField();

        registrationPanel.add(usernameLabel);
        registrationPanel.add(usernameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(firstNameLabel);
        registrationPanel.add(firstNameField);
        registrationPanel.add(lastNameLabel);
        registrationPanel.add(lastNameField);
        registrationPanel.add(facultyLabel);
        registrationPanel.add(facultyField);
        registrationPanel.add(emailLabel);
        registrationPanel.add(emailField);
        registrationPanel.add(genderLabel);
        registrationPanel.add(genderField);
        registrationPanel.add(phoneNoLabel);
        registrationPanel.add(phoneNoField);

        int option = JOptionPane.showConfirmDialog(null, registrationPanel, "Registration Form - " + userType, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String faculty = facultyField.getText();
            String emailAddress = emailField.getText();
            String gender = genderField.getText();
            String phoneNo = phoneNoField.getText();

            try {
                registerUser(username, password, userType, firstName, lastName, faculty, emailAddress, gender, phoneNo);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration failed: " + ex.getMessage());
            }
        }
    }

//    private void openAdminDashboard() {
//        JFrame adminFrame = new JFrame("Admin Dashboard");
//        adminFrame.setLayout(new GridLayout(5, 1));
//
//        JButton manageUserDataBtn = new JButton("Manage User Data");
//        JButton manageSurveyCreatorDataBtn = new JButton("Manage Survey Creator Data");
//        JButton createSurveyBtn = new JButton("Create Survey");
//        JButton backBtn = new JButton("Back");
//
//        manageUserDataBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manageUserData();
//            }
//        });
//
//        manageSurveyCreatorDataBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manageSurveyCreatorData();
//            }
//        });
//
//        createSurveyBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                createSurvey();
//            }
//        });
//
//        backBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new LoginForm();
//                adminFrame.dispose();
//            }
//        });
//
//        adminFrame.add(manageUserDataBtn);
//        adminFrame.add(manageSurveyCreatorDataBtn);
//        adminFrame.add(createSurveyBtn);
//        adminFrame.add(backBtn);
//
//        adminFrame.setSize(400, 300);
//        adminFrame.setVisible(true);
//        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
private void openAdminDashboard() {
    JFrame adminFrame = new JFrame("Admin Dashboard");
    adminFrame.setLayout(new GridLayout(5, 1));

    JButton manageUserDataBtn = new JButton("Manage User Data");
    JButton manageSurveyCreatorDataBtn = new JButton("Manage Survey Creator Data");
    JButton viewAnswersBtn = new JButton("View Answers");
    JButton createSurveyBtn = new JButton("Create Survey");
    JButton backBtn = new JButton("Back");

    manageUserDataBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            manageUserData();
        }
    });

    manageSurveyCreatorDataBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            manageSurveyCreatorData();
        }
    });

    viewAnswersBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewAnswers();
        }
    });

    createSurveyBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            createSurvey();
        }
    });

    backBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginForm();
            adminFrame.dispose();
        }
    });

    adminFrame.add(manageUserDataBtn);
    adminFrame.add(manageSurveyCreatorDataBtn);
    adminFrame.add(viewAnswersBtn);
    adminFrame.add(createSurveyBtn);
    adminFrame.add(backBtn);

    adminFrame.setSize(400, 300);
    adminFrame.setVisible(true);
    adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    private void viewAnswers() {
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        try {
            File file = new File("answers.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                br.close();
            } else {
                textArea.setText("No answers found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            textArea.setText("Error reading answers: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(null, scrollPane, "View Answers", JOptionPane.PLAIN_MESSAGE);
    }
    private void manageUserData() {
        try {
            ArrayList<User> users = readUsers("User");
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            for (User user : users) {
                textArea.append("Username: " + user.getUsername() + "\n");
                textArea.append("First Name: " + user.getFirstName() + "\n");
                textArea.append("Last Name: " + user.getLastName() + "\n");
                textArea.append("Faculty: " + user.getFaculty() + "\n");
                textArea.append("Email: " + user.getEmailAddress() + "\n");
                textArea.append("Gender: " + user.getGender() + "\n");
                textArea.append("Phone Number: " + user.getPhoneNo() + "\n");
                textArea.append("--------------------\n");
            }

            JOptionPane.showMessageDialog(null, scrollPane, "User Data", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading user data: " + ex.getMessage());
        }
    }

    private void manageSurveyCreatorData() {
        try {
            ArrayList<User> surveyCreators = readUsers("Survey Creator");
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            for (User surveyCreator : surveyCreators) {
                textArea.append("Username: " + surveyCreator.getUsername() + "\n");
                textArea.append("First Name: " + surveyCreator.getFirstName() + "\n");
                textArea.append("Last Name: " + surveyCreator.getLastName() + "\n");
                textArea.append("Faculty: " + surveyCreator.getFaculty() + "\n");
                textArea.append("Email: " + surveyCreator.getEmailAddress() + "\n");
                textArea.append("Gender: " + surveyCreator.getGender() + "\n");
                textArea.append("Phone Number: " + surveyCreator.getPhoneNo() + "\n");
                textArea.append("--------------------\n");
            }

            JOptionPane.showMessageDialog(null, scrollPane, "Survey Creator Data", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading survey creator data: " + ex.getMessage());
        }
    }

    private void createSurvey() {
        JPanel surveyPanel = new JPanel(new GridLayout(4, 2));

        JTextField surveyIdField = new JTextField();
        JTextField surveyTitleField = new JTextField();
        JTextField scIdField = new JTextField();
        JTextField creatorNameField = new JTextField();

        surveyPanel.add(new JLabel("Survey ID:"));
        surveyPanel.add(surveyIdField);
        surveyPanel.add(new JLabel("Survey Title:"));
        surveyPanel.add(surveyTitleField);
        surveyPanel.add(new JLabel("Survey Creator ID:"));
        surveyPanel.add(scIdField);
        surveyPanel.add(new JLabel("Creator Name:"));
        surveyPanel.add(creatorNameField);

        int option = JOptionPane.showConfirmDialog(null, surveyPanel, "Create Survey", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String surveyId = surveyIdField.getText();
            String surveyTitle = surveyTitleField.getText();
            String scId = scIdField.getText();
            String creatorName = creatorNameField.getText();

            // Save the survey data (this is a placeholder, implement actual saving logic)
            JOptionPane.showMessageDialog(null, "Survey Created Successfully!");
        }
    }

    private void openUserDashboard() {
        JFrame userFrame = new JFrame("User Dashboard");
        userFrame.setLayout(new GridLayout(2, 1));

        JButton answerSurveyBtn = new JButton("Answer Survey");
        JButton backBtn = new JButton("Back");

        answerSurveyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerSurvey();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                userFrame.dispose();
            }
        });

        userFrame.add(answerSurveyBtn);
        userFrame.add(backBtn);

        userFrame.setSize(400, 300);
        userFrame.setVisible(true);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openSurveyCreatorDashboard() {
        JFrame surveyCreatorFrame = new JFrame("Survey Creator Dashboard");
        surveyCreatorFrame.setLayout(new GridLayout(3, 1));

        JButton createSurveyBtn = new JButton("Create Survey");
        JButton addQuestionBtn = new JButton("Add Question");
        JButton backBtn = new JButton("Back");

        createSurveyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSurvey();
            }
        });

        addQuestionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                surveyCreatorFrame.dispose();
            }
        });

        surveyCreatorFrame.add(createSurveyBtn);
        surveyCreatorFrame.add(addQuestionBtn);
        surveyCreatorFrame.add(backBtn);

        surveyCreatorFrame.setSize(400, 300);
        surveyCreatorFrame.setVisible(true);
        surveyCreatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addQuestion() {
        JPanel questionPanel = new JPanel(new GridLayout(4, 2));

        JTextField questionIdField = new JTextField();
        JTextField questionPositionField = new JTextField();
        JTextField questionTextField = new JTextField();
        JTextField surveyIdField = new JTextField();

        questionPanel.add(new JLabel("Question ID:"));
        questionPanel.add(questionIdField);
        questionPanel.add(new JLabel("Question Position:"));
        questionPanel.add(questionPositionField);
        questionPanel.add(new JLabel("Question Text:"));
        questionPanel.add(questionTextField);
        questionPanel.add(new JLabel("Survey ID:"));
        questionPanel.add(surveyIdField);

        int option = JOptionPane.showConfirmDialog(null, questionPanel, "Add Question", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String questionId = questionIdField.getText();
            String questionPosition = questionPositionField.getText();
            String questionText = questionTextField.getText();
            String surveyId = surveyIdField.getText();

            // Save the question data (this is a placeholder, implement actual saving logic)
            JOptionPane.showMessageDialog(null, "Question Added Successfully!");
        }
    }

//    private void answerSurvey() {
//        // Dummy surveys data
//        String[] surveys = {"Survey 1", "Survey 2", "Survey 3"};
//
//        String selectedSurvey = (String) JOptionPane.showInputDialog(
//                null,
//                "Select Survey to Answer",
//                "Answer Survey",
//                JOptionPane.PLAIN_MESSAGE,
//                null,
//                surveys,
//                surveys[0]);
//
//        if (selectedSurvey != null) {
//            // Load questions from question.ser and display them for the selected survey
//            displaySurveyQuestions(selectedSurvey);
//        }
//    }

    private void answerSurvey() {
        String[] questions = {
                "What do you think about the academic environment at Tribhuvan University?",
                "How satisfied are you with the facilities provided by Tribhuvan University?",
                "What improvements would you suggest for student life at Tribhuvan University?",
                "How would you rate the quality of education at Tribhuvan University?",
                "Do you think Tribhuvan University adequately prepares students for the job market?"
        };

        ArrayList<String> answers = new ArrayList<>();

        for (String question : questions) {
            String answer = JOptionPane.showInputDialog(null, question);
            if (answer != null) {
                answers.add(answer);
            } else {
                // Handle case where user cancels or closes the dialog
                JOptionPane.showMessageDialog(null, "Answering survey canceled.");
                return; // Exit method if user cancels
            }
        }

        try {
            writeAnswersToFile(answers);
            JOptionPane.showMessageDialog(null, "Answers saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving answers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void writeAnswersToFile(ArrayList<String> answers) throws IOException {
        FileWriter writer = new FileWriter("answers.txt");

        for (int i = 0; i < answers.size(); i++) {
            writer.write("Question " + (i + 1) + ": " + answers.get(i) + "\n");
        }

        writer.close();
    }



    private void displaySurveyQuestions(String surveyTitle) {
        try {
            // Read the questions from question.ser
            ArrayList<Question> questions = readQuestions();
            StringBuilder questionsText = new StringBuilder();

            for (Question question : questions) {
                if (question.getSurveyId().equalsIgnoreCase(surveyTitle.replaceAll("\\D+", ""))) {
                    questionsText.append("Question ID: ").append(question.getQuestionId()).append("\n");
//                    questionsText.append("Position: ").append(question.getPosition()).append("\n");
                    questionsText.append("Question: ").append(question.getQuestionText()).append("\n");
                    questionsText.append("--------------------\n");
                }
            }

            JTextArea textArea = new JTextArea(questionsText.toString(), 10, 30);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "Survey Questions", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading survey questions: " + ex.getMessage());
        }
    }

    private boolean validateUserLogin(String username, String password, String userType) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        if (userType.equals("Admin")) {
            // Hardcoded admin credentials
            if (username.equals("admin") && password.equals("admin")) {
                return true;
            }
        } else {
            ArrayList<User> users = readUsers(userType);
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(encryptPassword(password))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void registerUser(String username, String password, String userType, String firstName, String lastName, String faculty, String emailAddress, String gender, String phoneNo) throws IOException, NoSuchAlgorithmException {
        User newUser = new User(username, encryptPassword(password), userType, firstName, lastName, faculty, emailAddress, gender, phoneNo);
        ArrayList<User> users = new ArrayList<>();
        File file = new File(userType.toLowerCase() + "s.ser");
        try {
            if (file.exists()) {
                users = readUsers(userType);
            }
            users.add(newUser);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(users);
            oos.close();
            JOptionPane.showMessageDialog(this, "Registration Successful!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    private ArrayList<User> readUsers(String userType) throws IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        File file = new File(userType.toLowerCase() + "s.ser");

        // Print out information for debugging
        System.out.println("Attempting to read users from: " + file.getAbsolutePath());

        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            ois.close();

            if (obj instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<?> objList = (ArrayList<?>) obj;
                // Check if the list contains User objects
                if (!objList.isEmpty() && objList.get(0) instanceof User) {
                    users = (ArrayList<User>) objList;
                } else {
                    System.err.println("File does not contain ArrayList<User>");
                    throw new ClassNotFoundException("File does not contain ArrayList<User>");
                }
            } else {
                System.err.println("File does not contain ArrayList<User>");
                throw new ClassNotFoundException("File does not contain ArrayList<User>");
            }
        } else {
            System.err.println("File does not exist: " + file.getAbsolutePath());
        }

        return users;
    }


    private ArrayList<Question> readQuestions() throws IOException, ClassNotFoundException {
        ArrayList<Question> questions = new ArrayList<>();
        File file = new File("question.ser");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            questions = (ArrayList<Question>) ois.readObject();
            ois.close();
        }
        return questions;
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
