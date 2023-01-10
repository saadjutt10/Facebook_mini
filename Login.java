//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import javafx.scene.control.Label;

// import javafx.scene.paint.Color;
public class Login extends JFrame {
    
JTextField usernameTextField , passwordTextField;
    Login() {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Login Iterface");
        // setBackground("yellow");
        // setBackground("green");
        // setLayout(new GridLayout());
        setLayout(new GridLayout(1, 2));
        // GridBagConstraints constraints = new GridBagConstraints();

        // Create the image
        // JLabel img = new JLabel();
        // img.setIcon(new ImageIcon("moaaz.jpg"));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("moaaz.jpg"));
        // imageLabel.setIcon(new ImageIcon(new
        // ImageIcon("moaaz.jpg").getImage().getScaledInstance(200, 200,
        // Image.SCALE_DEFAULT)));
        // Dimension s=img.getPreferredSize();
        // img.setBounds(50, 30, s.width, (int) s.getHeight());

        // JLabel imageLabel = new JLabel(new ImageIcon(image));

        // Add the image to the left column
        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // constraints.insets = new Insets(10, 10, 10, 10);

        // Create the labels and textfields for username and password
        JLabel usernameLabel = new JLabel("Username:");
         usernameTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
         passwordTextField = new JTextField(20);

        // Create the login and sign up buttons
        JButton loginButton = new JButton("Login");
        JPanel b1=new JPanel();
        b1.add(loginButton);
        b1.setPreferredSize(new Dimension(120,80));
        //Signup
        JButton signupButton = new JButton("Sign Up");
        JPanel b2=new JPanel();
        b2.add(signupButton);
        b2.setPreferredSize(new Dimension(120,80));

        // Create a panel to hold the textfields and labels
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setBackground(Color.BLACK);
    
        //Panel for 2nd row rightPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(150,0));
        mainPanel.setBackground(Color.RED);

        //Compo Panel to be added in centre of mainPanel
        JPanel compoPanel = new JPanel();
        compoPanel.setLayout(new GridLayout(3,1));

        JPanel fieldsJPanel =new JPanel();
        fieldsJPanel.setLayout(new FlowLayout());
        fieldsJPanel.add(usernameLabel);
        fieldsJPanel.add(usernameTextField);

        JPanel fieldsJPanel2 =new JPanel();
        fieldsJPanel2.setLayout(new FlowLayout());
        fieldsJPanel2.add(passwordLabel);
        fieldsJPanel2.add(passwordTextField);

        JPanel btnPanel=new JPanel();
        btnPanel.setLayout(new GridLayout(1,4));
        btnPanel.add(new JLabel(""));
        btnPanel.add(new JLabel(""));
        btnPanel.add(b1);
        btnPanel.add(b2);

        //Adding panels to compoPanel
        compoPanel.add(fieldsJPanel);
        compoPanel.add(fieldsJPanel2);
        compoPanel.add(btnPanel);


        //Making a panel for Welcome msg
        JPanel txtOuter = new JPanel();
        txtOuter.setLayout(new GridLayout(3,1));

        JPanel centreTextPanel=new JPanel();
        centreTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel welcomeText=new JPanel();
        JLabel wtxt=new JLabel("Welcome to Home Window");
        wtxt.setFont(new Font("Arial", Font.PLAIN, 17));
        welcomeText.add(wtxt);
        txtOuter.add(new JLabel(" "));
        txtOuter.add(welcomeText);
        txtOuter.add(new JLabel(""));
        // welcomeText.setPreferredSize(new Dimension(500,30));
        //Adding componenet Panel to main in centre
        centreTextPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""),BorderLayout.NORTH); 
        mainPanel.add(compoPanel, BorderLayout.CENTER);
        mainPanel.add(new JLabel(""), BorderLayout.SOUTH);
        //Adding main Panel to rightPanel
        // Add the panels to the right column
        rightPanel.add(txtOuter); // Adding empty label on top tp contain all our compo in centre
        rightPanel.add(mainPanel);
        rightPanel.add(new JLabel(""));
        
        add(imageLabel);
        add(rightPanel);

        //Adding ActionListeners
        MyActionListener al=new MyActionListener();
        loginButton.addActionListener(al);
        signupButton.addActionListener(al);
    }
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand()== "Login"){
                         try {
                            ArrayList<User> allNodes=Main_With_IO.getAllNodes("Data.txt");
                            if(User.userExists(allNodes, usernameTextField.getText())){
                                if(User.correctPassword(allNodes, passwordTextField.getText(),usernameTextField.getText() )){
                                    dispose();
                                    System.out.println("Logging in");
                                }else{
                                    passwordTextField.setForeground(Color.red);
                                    passwordTextField.setText("InCorrect Password");
                                    // passwordTextField.setForeground(Color.black);
                                }
                            }
                            else{
                                usernameTextField.setForeground(Color.red);
                                usernameTextField.setText("User does't exist");
                                // usernameTextField.setForeground(Color.black);
                            }
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
        
             

        }
    }
    public static void main(String[] args) {
        new Login();
    }

}
