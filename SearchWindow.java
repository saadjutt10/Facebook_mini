
//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class SearchWindow extends JFrame {
    SearchWindow(User user, String name) throws ClassNotFoundException, IOException {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Search");
        ArrayList<User> allNodes = Main_With_IO.getAllNodes("Data.txt");
        Main.getGraph();
        Main.V=allNodes.size();
        Main.setGraph(ConstructGraph.reconstructGraph(allNodes));
        ArrayList<User> list = user.searching_Breadth(allNodes, name);
        System.out.println(list.size()+"-----"+name);
        System.out.println(list.size());

        int n = list.size();
        if (n == 0) {
            n = 1;
        }
        setLayout(new GridLayout(n, 1));
        JPanel panels[] = new JPanel[n];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, 4));
            panels[i].setBackground(Color.red);
            panels[i].add(new JLabel(new ImageIcon(list.get(i).getImageDir())));
            JPanel info = new JPanel();
            {
                info.setLayout(new GridLayout(5, 1));
                JLabel uName = new JLabel("Name :" + list.get(i).getName() + " " +  list.get(i).getLastName());
                JLabel uAge = new JLabel("Age :" +  list.get(i).getAge());
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
            {
                cornerPanel.setLayout(new GridLayout(4, 1, 50, 30));
                JButton addBtn = new JButton("Add");
                addBtn.setPreferredSize(new Dimension(100, 30));
                JPanel addBtnPanel = new JPanel();
                addBtnPanel.add(addBtn);
                addBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton settingBtn = new JButton("Block");
                settingBtn.setPreferredSize(new Dimension(100, 30));
                JPanel settingBtnPanel = new JPanel();
                settingBtnPanel.add(settingBtn);
                settingBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
                cornerPanel.add(new JLabel(""));
                cornerPanel.add(addBtnPanel);
                cornerPanel.add(settingBtnPanel);
                cornerPanel.add(new JLabel(""));
            }
            
            panels[i].add(cornerPanel);
        }
        for(int i =0 ; i< panels.length ; i++){
            add(panels[i]);
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ArrayList<User> re=Main_With_IO.getAllNodes("Data.txt");
        new SearchWindow(re.get(0), "Bakr");
    }
}
