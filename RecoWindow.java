import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class RecoWindow extends JFrame {
    RecoWindow(User user) throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Recommendation");
        setLayout(new BorderLayout());

        // Top Text
        JPanel topTxtPanel = new JPanel();
        topTxtPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        JLabel topTxt = new JLabel("Select one of these two shits");
        topTxt.setFont(new Font("Arial", Font.PLAIN, 30));
        topTxtPanel.add(topTxt);

        // Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        // Adding to left centre panel
        JPanel lmPanel = new JPanel();
        JButton distanceBtn = new JButton("Recommend by distance");
        distanceBtn.setPreferredSize(new Dimension(200, 120));
        // Adding img on buttons
        ImageIcon imgIcon = new ImageIcon("moaaz.jpg");
        Image img = imgIcon.getImage();
        Image newImg = img.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
        distanceBtn.setIcon(new ImageIcon(newImg));
        lmPanel.add(distanceBtn);
        add(topTxtPanel, BorderLayout.NORTH);
        // Adding to Right centre panel
        JPanel rmPanel = new JPanel();
        JButton frnBtn = new JButton("Friends of Friends");
        frnBtn.setPreferredSize(new Dimension(200, 120));
        // Adding img on buttons
        ImageIcon frnimg = new ImageIcon("moaaz.jpg");
        Image fimg = frnimg.getImage();
        Image newfimg = fimg.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
        frnBtn.setIcon(new ImageIcon(newfimg));
        rmPanel.add(frnBtn);

        // Adding to middle panel
        middlePanel.add(lmPanel);
        middlePanel.add(rmPanel);

        // Bottom Button Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton homeBtn = new JButton("Home");
        homeBtn.setPreferredSize(new Dimension(80, 40));
        btnPanel.add(homeBtn);
        // Adding to main panel
        topTxtPanel.setBackground(Color.red);
        middlePanel.setBackground(Color.green);
        btnPanel.setBackground(Color.yellow);
        add(topTxtPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        //Action Listeners
        ArrayList<User> allNodes=Main_With_IO.getAllNodes("Data.txt");
        MyActionListener alA=new MyActionListener(user,allNodes);
        homeBtn.addActionListener(alA);
        frnBtn.addActionListener(alA);
        distanceBtn.addActionListener(alA);

    }

    class MyActionListener implements ActionListener {
        User user;
        ArrayList<User> allNodes;

        MyActionListener() {

        }

        MyActionListener(User u, ArrayList<User> allNodes) {
            user = u;
            this.allNodes = allNodes;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Home") {
                dispose();
                new HomeWindow(user);
            } else if (e.getActionCommand() == "Friends of Friends") {
                dispose();
                try {
                    new RecommendationWindow(user, "fof");
                } catch (ClassNotFoundException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (e.getActionCommand() == "Recommend by distance") {
                dispose();
                try {
                    new RecommendationWindow(user, "distance");
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

}
