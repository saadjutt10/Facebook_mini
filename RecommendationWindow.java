import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class RecommendationWindow extends JFrame {
    ArrayList<User> allNodes;
    ArrayList<User> list;
    JPanel panels[];

    RecommendationWindow(User user ,String ac) throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Recommendation Window ha vro");
        setLayout(new BorderLayout(0, 20));
        allNodes = Main_With_IO.getAllNodes("Data.txt");
        Main.getGraph();
        Main.V = allNodes.size();
        Main.setGraph(ConstructGraph.reconstructGraph(allNodes));
        // Getting which recommendation to run
        if (ac.equals("distance")) {
            list = user.distanceSuggestions(allNodes);
        } else if (ac.equals("fof")) {
            list = user.friendsOfFriends(allNodes);
        }
        int n = list.size();
        if (n < 4)
            n = 4;

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(n, 1, 30, 10));
        panels = new JPanel[list.size()];

        MyActionListener alA = new MyActionListener(user, allNodes, list);
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, 4));
            // panels[i].setBackground(Color.red);
            JLabel searchimg = new JLabel();
            ImageIcon sicon = new ImageIcon(list.get(i).getImageDir());
            Image simg = sicon.getImage();
            Image sNewimg = simg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
            searchimg.setIcon(new ImageIcon(sNewimg));
            panels[i].add(searchimg);
            JPanel info = new JPanel();
            {
                info.setLayout(new GridLayout(5, 1));
                JLabel uName = new JLabel("Name :" + list.get(i).getName() + " " + list.get(i).getLastName());
                JLabel uAge = new JLabel("Age :" + list.get(i).getAge());
                ArrayList<User> mutuaList = user.getMutualFriendsList(allNodes, list.get(i));
                JLabel uMutualNum = new JLabel("Mutual Friends :" + mutuaList.size());
                info.add(new JLabel(""));
                info.add(uName);
                info.add(uAge);
                info.add(uMutualNum);
                info.add(new JLabel(""));
            }
            panels[i].add(info);
            panels[i].add(new JLabel(""));
            JPanel cornerPanel = new JPanel();
            {// Buttons
                cornerPanel.setLayout(new GridLayout(2, 1, 50, 0));
                JButton addBtn = new JButton("Add");
                addBtn.setActionCommand(i + "Add");
                addBtn.setPreferredSize(new Dimension(80, 30));
                if (User.getUser(allNodes, user.getUsername()).getFriends().contains(list.get(i))) {// Condition to
                                                                                                    // check if they are
                                                                                                    // already friends
                    addBtn.setEnabled(false);
                }
                JPanel addBtnPanel = new JPanel();
                addBtnPanel.add(addBtn);
                addBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton settingBtn = new JButton("Block");
                settingBtn.setActionCommand(i + "Block");
                settingBtn.setPreferredSize(new Dimension(80, 30));
                JPanel settingBtnPanel = new JPanel();
                settingBtnPanel.add(settingBtn);
                settingBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                // cornerPanel.add(new JLabel(""));
                cornerPanel.add(addBtnPanel);
                cornerPanel.add(settingBtnPanel);
                // cornerPanel.add(new JLabel(""));
                // Adding actionListener
                addBtn.addActionListener(alA);
                settingBtn.addActionListener(alA);
            }

            panels[i].add(cornerPanel);
        }
        for (int i = 0; i < panels.length; i++) {
            centerPanel.add(panels[i]);
        }
        for (int i = list.size(); i < n; i++) {
            centerPanel.add(new JLabel(""));
        }
        // TOp Text Panel
        JPanel topPanel = new JPanel();

        // text
        JPanel topTextPanel = new JPanel();
        topTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel topText = new JLabel("Here are your recommendations");
        topText.setFont(new Font("Arial", Font.PLAIN, 25));
        topTextPanel.add(topText);

        // Adding panels to top panel
        topPanel.add(topTextPanel);
        // Bottom Button Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton homeBtn = new JButton("Home");
        homeBtn.setPreferredSize(new Dimension(80, 40));
        btnPanel.add(homeBtn);
        // Adding in Main frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners
        homeBtn.addActionListener(alA);

    }

    class MyActionListener implements ActionListener {
        User user;
        ArrayList<User> allNodes;
        ArrayList<User> list;

        MyActionListener() {

        }

        MyActionListener(User u, ArrayList<User> allNodes, ArrayList<User> lis) {
            user = u;
            this.allNodes = allNodes;
            list = lis;
        }

        public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "Home") {
                    dispose();
                    new HomeWindow(user);
                
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ArrayList<User> re = Main_With_IO.getAllNodes("Data.txt");
        new RecommendationWindow(re.get(0),"distance");
    }
}
