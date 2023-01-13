
//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class BlockedWindow extends JFrame {
    ArrayList<User> list = new ArrayList<>();
    JPanel panels[];

    BlockedWindow(User user) throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Blocked Users");
        setLayout(new BorderLayout(0, 20));

        ArrayList<User> allNodes;
        allNodes = Main_With_IO.getAllNodes("Data.txt");
        // System.out.println("Herererkenrkenkjnf" + user.getUsername());
        Main.getGraph();
        Main.V = allNodes.size();
        Main.setGraph(ConstructGraph.reconstructGraph(allNodes));
        // Getting which recommendation to run

        list = user.getBlockedUsers();

        int n = list.size();
        if (n < 4)
            n = 4;

        JPanel centerPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        centerPanel.setLayout(new GridLayout(n, 1, 30, 10));
        centerPanel.setBackground(Color.decode("#"+Main.DarkColor));
        panels = new JPanel[list.size()];

        MyActionListener alA = new MyActionListener(user, list);
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, 4));
            // panels[i].setBackground(Color.red);
            JLabel searchimg = new JLabel();
            ImageIcon sicon = new ImageIcon(list.get(i).getImageDir());
            Image simg = sicon.getImage();
            Image sNewimg = simg.getScaledInstance(130, 100, java.awt.Image.SCALE_SMOOTH);
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
                cornerPanel.setLayout(new GridLayout(1, 1, 50, 0));
                JButton addBtn = new JButton("UnBlock");
                addBtn.setActionCommand(i + "UnBlock");
                addBtn.setPreferredSize(new Dimension(80, 30));

                JPanel addBtnPanel = new JPanel();
                addBtnPanel.add(addBtn);
                addBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton settingBtn = new JButton("Block");
                settingBtn.setVisible(false);
                settingBtn.setActionCommand(i + "Block");
                settingBtn.setPreferredSize(new Dimension(80, 30));
                JPanel settingBtnPanel = new JPanel();
                settingBtnPanel.add(settingBtn);
                settingBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                // cornerPanel.add(new JLabel(""));
                cornerPanel.add(addBtnPanel);
                cornerPanel.add(settingBtnPanel);

                // ConstructGraph.displayMatrix();

                // cornerPanel.add(new JLabel(""));
                // Adding actionListener
                addBtn.addActionListener(alA);
                // settingBtn.addActionListener(alA);
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
        JLabel topText = new JLabel("Blocked Users list");
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
        centerPanel.setAutoscrolls(true);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        topPanel.setBackground(Color.decode("#" + Main.DarkColor));
        btnPanel.setBackground(Color.decode("#" + Main.DarkColor));

        // Action Listeners
        homeBtn.addActionListener(alA);
    }

    class MyActionListener implements ActionListener {
        User user;
        ArrayList<User> allNodes;
        ArrayList<User> list;

        MyActionListener(User u, ArrayList<User> li) throws ClassNotFoundException, IOException {
            user = u;
            allNodes = Main_With_IO.getAllNodes("Data.txt");
            list = li;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Home") {
                dispose();
                new HomeWindow(user);

            } else {
                String command = e.getActionCommand();
                System.out.println(command);
                for (int i = 0; i < list.size(); i++) {
                    if (command.equals(i + "UnBlock")) {
                        try {
                            allNodes = Main_With_IO.getAllNodes("Data.txt");
                            int ind = User.FindIndexInList(allNodes, user);
                            int index2 = User.FindIndexInList(allNodes, list.get(i));
                            allNodes.get(ind).unblock(allNodes.get(index2), allNodes);
                            JOptionPane.showMessageDialog(null, "Selected user is Unblocked.");
                            dispose();
                            new BlockedWindow( allNodes.get(ind));
                            
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ArrayList<User> re = Main_With_IO.getAllNodes("Data.txt");
        new BlockedWindow(re.get(0));
    }
}
