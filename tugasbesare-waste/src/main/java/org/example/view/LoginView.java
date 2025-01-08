//package com.example.view;
//
//import com.example.controller.UserController;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LoginView extends JFrame {
//
//    private final UserController userController = new UserController();
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//
//    public LoginView() {
//        setTitle("Login");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new GridLayout(3, 2, 5, 5));
//        setLocationRelativeTo(null); // Center the window
//
//        JLabel usernameLabel = new JLabel("Username:");
//        usernameField = new JTextField();
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordField = new JPasswordField();
//        JButton loginButton = new JButton("Login");
//
//        add(usernameLabel);
//        add(usernameField);
//        add(passwordLabel);
//        add(passwordField);
//        add(new JLabel()); // Placeholder
//        add(loginButton);
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                if (userController.loginUser(username, password)) {
//                    JOptionPane.showMessageDialog(LoginView.this, "Login successful!");
//                    dispose();
//                } else {
//                    JOptionPane.showMessageDialog(LoginView.this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//    }
//
//    public void display() {
//        setVisible(true);
//    }
//}