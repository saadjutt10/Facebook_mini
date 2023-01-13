
//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class FriendsFrame extends JFrame {
    ArrayList<User> list = new ArrayList<>();
    JPanel panels[];

    FriendsFrame(User user) throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Friends Window");
        setLayout(new BorderLayout(0, 20));

        ArrayList<User> allNodes;
        allNodes = Main_With_IO.getAllNodes("Data.txt");
        // System.out.println("Herererkenrkenkjnf" + user.getUsername());
        Main.getGraph();
        Main.V = allNodes.size();
        Main.setGraph(ConstructGraph.reconstructGraph(allNodes));
        // Getting which recommendation to run

        list = user.getFriends();

        int n = list.size();
        if (n < 4)
            n = 4;

        JPanel centerPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        centerPanel.setBackground(Color.decode("#" + Main.DarkColor));
        scrollPane.setPreferredSize(new Dimension(700, 400));
        centerPanel.setLayout(new GridLayout(n, 1, 30, 10));
        panels = new JPanel[list.size()];

        MyActionListener alA = new MyActionListener(user,list);
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
                cornerPanel.setLayout(new GridLayout(2, 1, 50, 0));
                JButton addBtn = new JButton("Remove");
                addBtn.setActionCommand(i + "Remove");
                addBtn.setPreferredSize(new Dimension(80, 30));

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

                // ConstructGraph.displayMatrix();

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
        JLabel topText = new JLabel("Friends list");
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
        ArrayList<User> flist;

        MyActionListener() {

        }

        MyActionListener(User u, ArrayList<User> list) throws ClassNotFoundException, IOException {
            user = u;
            allNodes = Main_With_IO.getAllNodes("Data.txt");
            flist = list;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Home") {
                dispose();
                new HomeWindow(user);

            } else {
                String command = e.getActionCommand();
                System.out.println(command);
                for (int i = 0; i < flist.size(); i++) {
                    if (command.equals(i + "Remove")) {
                        try {
                            allNodes = Main_With_IO.getAllNodes("Data.txt");
                            int ind = User.FindIndexInList(allNodes, user);
                            int index2 = User.FindIndexInList(allNodes, flist.get(i));
                            allNodes.get(ind).addFriend(allNodes, allNodes.get(index2).getUsername());
                            JOptionPane.showMessageDialog(null, "User is Unfriended.");
                            dispose();
                            new FriendsFrame(allNodes.get(ind));
                        } catch (NullPointerException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (command.equals(i + "Block")) {
                        try {
                            allNodes = Main_With_IO.getAllNodes("Data.txt");
                            int ind = User.FindIndexInList(allNodes, user);
                            int index2 = User.FindIndexInList(allNodes, list.get(i));
                            allNodes.get(ind).block(allNodes.get(index2), allNodes);
                            JOptionPane.showMessageDialog(null, "User is Blocked.");
                            dispose();
                            new FriendsFrame(allNodes.get(ind));
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        // System.out.println("Blocked");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ArrayList<User> re = Main_With_IO.getAllNodes("Data.txt");
        new FriendsFrame(re.get(0));
    }
}
