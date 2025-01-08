package org.example.view;

import org.example.controller.UserController;
import org.example.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends JFrame {

    private final UserController userController = new UserController();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField otpField;
    private boolean otpSent = false;
    private String savedOtp;


    public RegistrationView() {
        setTitle("Registration");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        //Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(usernameField, gbc);
        //Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        gbc.gridy++;
        add(emailLabel, gbc);
        gbc.gridy++;
        add(emailField, gbc);

        //OTP
        JLabel otpLabel = new JLabel("OTP Code:");
        otpField = new JTextField();
        otpField.setEnabled(false);
        gbc.gridy++;
        add(otpLabel, gbc);
        gbc.gridy++;
        add(otpField, gbc);
        //Kirim OTP Button
        gbc.gridy++;
        JButton sendOtpButton = new JButton("Kirim OTP");
        add(sendOtpButton, gbc);
        //Register Button
        gbc.gridy++;
        JButton registerButton = new JButton("Register");
        registerButton.setEnabled(false);
        add(registerButton, gbc);

        sendOtpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                User user = new User(username, password, email);

                if (userController.sendOTP(user)) {
                    if (userController.saveUser(user, user.getOtp())) {
                        JOptionPane.showMessageDialog(RegistrationView.this, "OTP Send To Email, Please Check Your Email");
                        otpField.setEnabled(true);
                        registerButton.setEnabled(true);
                        sendOtpButton.setEnabled(false);
                        savedOtp = user.getOtp();
                    } else {
                        JOptionPane.showMessageDialog(RegistrationView.this, "Save User Failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(RegistrationView.this, "Send OTP Failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String otp = otpField.getText();
                User user = new User(username, password, email);
                if (userController.registerUser(user, otp)) {
                    JOptionPane.showMessageDialog(RegistrationView.this, "Registration successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegistrationView.this, "Registration failed, OTP Invalid or Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void display() {
        setVisible(true);
    }
}