package org.example.view;

import org.example.controller.UserController;
import org.example.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class EditProfileView extends JFrame {

    private JTextField imageField;
    private JTextArea addressArea;
    private JTextField dateOfBirthField;
    private String userEmail;


    public EditProfileView(String userEmail) {
        this.userEmail = userEmail;
        setTitle("Edit Profile");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        //Image
        JLabel imageLabel = new JLabel("Foto Profil (URL/Path):");
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(imageLabel,gbc);
        gbc.gridy++;
        imageField = new JTextField(20);
        imageField.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(10,1), // Custom round border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)) // padding
        );
        imageField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(imageField,gbc);


        //Address
        gbc.gridy++;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(addressLabel,gbc);
        gbc.gridy++;
        addressArea = new JTextArea(3,20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        addressArea.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(10,1), // Custom round border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)) // padding
        );
        addressArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(new JScrollPane(addressArea), gbc);

        //Date of birth
        gbc.gridy++;
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        dobLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(dobLabel, gbc);
        gbc.gridy++;
        dateOfBirthField = new JTextField(20);
        dateOfBirthField.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(10,1), // Custom round border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)) // padding
        );
        dateOfBirthField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(dateOfBirthField, gbc);


        //Save Button
        gbc.gridy++;
        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setBackground(new Color(0,128,0)); //Green color
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(10,1),
                new EmptyBorder(10, 10, 10, 10) // padding
        ));
        mainPanel.add(saveButton, gbc);
        UserController userController = new UserController();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = addressArea.getText();
                String dob = dateOfBirthField.getText();
                String image = imageField.getText();
                String otp ="";
                User user = new User("","",userEmail,otp,true,image, address, dob);

                if (userController.updateUser(user)){
                    JOptionPane.showMessageDialog(EditProfileView.this,
                            "Profile Updated!\nAddress: " + address + "\nDate of Birth: " + dob);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(EditProfileView.this,
                            "Update profile failed!");
                }
            }
        });
        add(mainPanel, BorderLayout.CENTER);

    }
    static class RoundBorder extends LineBorder {
        private int radius;

        public RoundBorder(int radius, int thickness) {
            super(Color.LIGHT_GRAY,thickness,true);
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape round = new RoundRectangle2D.Float(x, y, width-1, height-1, radius, radius);
            g2d.setColor(super.getLineColor());
            g2d.setStroke(new BasicStroke(super.getThickness()));
            g2d.draw(round);

            g2d.dispose();
        }
    }

    public void display() {
        setVisible(true);
    }
}