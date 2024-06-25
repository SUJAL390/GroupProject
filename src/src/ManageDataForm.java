
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
class ManageDataForm extends JFrame {
    JTextArea dataArea;

    ManageDataForm() {
        setTitle("Manage Data");
        setLayout(new BorderLayout());

        dataArea = new JTextArea();
        add(new JScrollPane(dataArea), BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadData();
    }

    private void loadData() {
        try {
            dataArea.append("Users:\n");
            ArrayList<User> users = readUsers("User");
            for (User user : users) {
                dataArea.append(user.getUsername() + " - " + user.getPassword() + "\n");
            }
            dataArea.append("\nSurvey Creators:\n");
            ArrayList<User> surveyCreators = readUsers("Survey Creator");
            for (User sc : surveyCreators) {
                dataArea.append(sc.getUsername() + " - " + sc.getPassword()+ "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<User> readUsers(String userType) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userType + "s.ser"));
        return (ArrayList<User>) ois.readObject();
    }
}
