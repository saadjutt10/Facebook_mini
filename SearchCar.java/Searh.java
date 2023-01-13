
//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Searh<T> extends JFrame {
    JPanel panels[];
    ArrayList<T> list;

    Searh() throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Search");
        setLayout(new BorderLayout(0, 20));
        // setBackground(Color.decode("#" + Main.DarkColor));

        JPanel centerPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        // scrollPane.setForeground(Color.decode("#"+Main.DarkColor));
        // centerPanel.setBackground(Color.decode("#"+Main.DarkColor));
        scrollPane.setPreferredSize(new Dimension(700, 400));

        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        panels = new JPanel[3/* list.size()*/];

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, 3));
            // panels[i].setBackground(Color.red);
            JLabel searchimg = new JLabel();

            ImageIcon sicon = new ImageIcon("bakr.jpg");
            Image simg = sicon.getImage();
            Image sNewimg = simg.getScaledInstance(130, 100, java.awt.Image.SCALE_SMOOTH);
            searchimg.setIcon(new ImageIcon(sNewimg));
            panels[i].add(searchimg);
            JPanel info = new JPanel();
            // info.setBackground(Color.decode("#" + Main.DarkColor));
            {
                info.setLayout(new GridLayout(5, 1));
                JLabel uName = new JLabel("Name :"/* plus your info */);
                JLabel model = new JLabel("Model :" /* plus your info */);
                JLabel numPlate = new JLabel("Number Plate" /* plus your info */);
                // uMutualNum.setForeground(Color.white);
                info.add(new JLabel(""));
                info.add(uName);
                info.add(model);
                info.add(numPlate);
                info.add(new JLabel(""));
            }
            panels[i].add(info);
            // panels[i].add(new JLabel(""));
            JPanel cornerPanel = new JPanel();

            // cornerPanel.setBackground(Color.decode("#" + Main.DarkColor));
            {// Buttons
                cornerPanel.setLayout(new GridLayout(3, 1, 10, 5));
                JButton updBtn = new JButton("Update");
                // addBtn.setForeground(Color.white);
                // addBtn.setBackground(Color.decode("#" + Main.notDarkColor));
                updBtn.setActionCommand(i + "Update");
                updBtn.setPreferredSize(new Dimension(80, 30));
                // Panels to contain buttons and to adjust their placement
                JPanel updBtnPanel = new JPanel();
                // updBtnPanel.setBackground(Color.decode("#" + Main.DarkColor));
                updBtnPanel.add(updBtn);
                updBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton removeBtn = new JButton("Remove");
                // removeBtn.setBackground(Color.decode("#" + Main.notDarkColor));
                removeBtn.setActionCommand(i + "Remove");
                removeBtn.setPreferredSize(new Dimension(80, 30));
                JPanel removeBtnPanel = new JPanel();
                removeBtnPanel.add(removeBtn);
                removeBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                // removeBtnPanel.setBackground(Color.decode("#" + Main.DarkColor));

                // cornerPanel.add(new JLabel(""));
                JButton viewBtn = new JButton("View");
                viewBtn.setActionCommand(i + "View");
                viewBtn.setPreferredSize(new Dimension(80, 30));
                // Panels to contain buttons and to adjust their placement
                JPanel viewBtnPanel = new JPanel();
                // viewBtnPanel.setBackground(Color.decode("#" + Main.DarkColor));
                viewBtnPanel.add(viewBtn);
                viewBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                cornerPanel.add(updBtnPanel);
                cornerPanel.add(removeBtnPanel);
                cornerPanel.add(viewBtnPanel);
                // cornerPanel.add(new JLabel(""));
                // Adding actionListener
                // updBtn.addActionListener(alA);
                // settingBtn.addActionListener(alA);
            }

            panels[i].add(cornerPanel);
        }
        for (int i = 0; i < panels.length; i++) {
            centerPanel.add(panels[i]);
        }
        
        // TOp Text Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        // searchbar
        JPanel seachPanel = new JPanel();
        seachPanel.setLayout(new GridLayout(1,3,10, 10));
        JLabel searchimg;
        // Combo Boxes 
        String opt1[]={"Client", "Car","Booking"};
        JComboBox comb1=new JComboBox<>(opt1);

        String opt2[]={"Whatver ", "The","Shit"};
        JComboBox comb2=new JComboBox<>(opt2);

        JButton searchBtn=new JButton("Search");

        
        JButton searchButton = new JButton("Search");
        seachPanel.add(comb1);
        seachPanel.add(comb2);
        seachPanel.add(searchButton);
        seachPanel.setMaximumSize(new Dimension(700,1300));

        // text
        JPanel topTextPanel = new JPanel();
        topTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel topText = new JLabel("Search here");
        topText.setFont(new Font("Arial", Font.PLAIN, 25));
        topTextPanel.add(topText);

        // Adding panels to top panel
        topPanel.add(seachPanel);
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
        // Printing Graph
        // Action Listeners
        // searchButton.addActionListener(alA);
        // homeBtn.addActionListener(alA);
        // Design
        // topPanel.setBackground(Color.decode("#" + Main.DarkColor));
        // btnPanel.setBackground(Color.decode("#" + Main.DarkColor));

    }

    class MyActionListener implements ActionListener {
        // User user;
        // ArrayList<User> list;

        MyActionListener() {

        }

       /*  MyActionListener(User u) {
            user = u;
            this.allNodes = allNodes;
            list = lis;
        } */

        public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                for (int i = 0; i < 5; i++) {
                    if (command.equals(i + "Add")) {
                      /*   try {
                            // user.addFriend(allNodes, list.get(i).getUsername());
                            // new SearchWindow(user, "");

                        } */
                    } else if (command.equals(i + "Block")) {
                        /* try {
                            // user.block(list.get(i), allNodes);
                        }  */
                        System.out.println("Blocked");
                    }
                }
            }
        }
    

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new Searh();
    }
}
