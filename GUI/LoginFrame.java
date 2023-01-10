package GUI;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LoginFrame extends JFrame {

    LoginFrame() {
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Login Iterface");
        // setBackground("yellow");
        // setBackground("green");
        // setLayout(new GridLayout()); 
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Create the image
        JLabel img = new JLabel();
        img.setIcon(new ImageIcon("moaaz.jpg"));
        JLabel imageLabel=new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon("moaaz.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        // Dimension s=img.getPreferredSize();
        // img.setBounds(50, 30, s.width, (int) s.getHeight());

        // JLabel imageLabel = new JLabel(new ImageIcon(image));

        // Add the image to the left column
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(imageLabel, constraints);

        // Create the labels and textfields for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordTextField = new JTextField(20);

        // Create the login and sign up buttons
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Create a panel to hold the textfields and labels
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(usernameLabel);
        textFieldPanel.add(usernameTextField);
        textFieldPanel.add(passwordLabel);
        textFieldPanel.add(passwordTextField);
        textFieldPanel.add(loginButton);
        textFieldPanel.add(signupButton);

        // Add the panel to the right column
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(textFieldPanel, constraints);
    }
    public static void main(String[] args) {
        LoginFrame temp=new LoginFrame();
    }

}
 