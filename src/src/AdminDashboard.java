import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
class AdminDashboard extends JFrame {
    JButton manageDataBtn, createSurveyBtn;

    AdminDashboard() {
        setTitle("Admin Dashboard");
        setLayout(new GridLayout(2, 1));

        manageDataBtn = new JButton("Manage Data");
        createSurveyBtn = new JButton("Create Survey");

        add(manageDataBtn);
        add(createSurveyBtn);

        manageDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement data management functionality
            }
        });

        createSurveyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement survey creation functionality
            }
        });

        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}