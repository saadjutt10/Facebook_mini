

import javax.imageio.ImageIO;
import javax.swing.*;

public class GridFrame extends JFrame {

    public GridFrame() {
        // Set the layout manager for the frame
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Create the image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("image.jpg"));
        } catch (IOException e) {
            // Handle the exception
        }
        JLabel imageLabel = new JLabel(new ImageIcon(image));

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

        // Set the size of the frame and make it visible
        setSize(500, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridFrame();
    }
}