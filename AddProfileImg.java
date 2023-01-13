
//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class AddProfileImg extends JFrame {
    AddProfileImg(String userPicture,User user) {
        setTitle("Plz select an Image");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

    //Panels
        //Top Panel
        JPanel topPanel=new JPanel();
        JPanel topTextPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topTextPanel.setBackground(Color.decode("#"+Main.notDarkColor));
        JLabel topText = new JLabel("Select Your Image");
        topText.setForeground(Color.white);
        topText.setFont(new Font("Arial", Font.PLAIN, 25));
        topTextPanel.add(topText);
        topPanel.add(topTextPanel);
        //Middle Panel 
        JPanel middlePanel=new JPanel();
        // middlePanel.setBackground(Color.green);
        middlePanel.setLayout(new GridLayout(2,1,100,10));
        // Create an ImageIcon from a file
        JPanel imgPanel=new JPanel();
        JLabel Userimg = new JLabel();
        imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,40));
        ImageIcon imgIcon = new ImageIcon(userPicture);
        Image img = imgIcon.getImage();
        Image newImg = img.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
        Userimg.setIcon(new ImageIcon(newImg));
        imgPanel.add(Userimg);

        JPanel BtnPanel=new JPanel();
        BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        BtnPanel.setPreferredSize(new Dimension(100,50));
        JButton selectBtn = new JButton("Select");
        BtnPanel.add(selectBtn);

        middlePanel.add(imgPanel);
        middlePanel.add(BtnPanel);
        //Bottom Panel
        JPanel bottoJPanel=new JPanel();
        bottoJPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
        bottoJPanel.setBackground(Color.decode("#"+Main.DarkColor));
        JButton closeBtn = new JButton("Close");
        JButton finishBtn = new JButton("Finish");
        bottoJPanel.add(closeBtn);
        bottoJPanel.add(finishBtn);

        //Adding to main Frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottoJPanel, BorderLayout.SOUTH);

        //Action List er
        MyActionListener alA =new MyActionListener(user, userPicture);
        selectBtn.addActionListener(alA);
        finishBtn.addActionListener(alA);
        closeBtn.addActionListener(alA);
    }

    class MyActionListener implements ActionListener {
        User user;
        String str;

        MyActionListener() {

        }

        MyActionListener(User u,String st) {
            user = u;
            str = st;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Select") {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String name=selectedFile.getPath();
                    dispose();
                    new AddProfileImg(name,user);
                    // do something with the selected file
                }

            }else if(e.getActionCommand() == "Finish"){

                ArrayList<User> list;
                try {
                    list = Main_With_IO.getAllNodes("Data.txt");
                    int ind=User.FindIndexInList(list, user);
                    list.get(ind).setImageDir(str);
                    Main_With_IO.writeData(list, "Data.txt");
                    dispose();
                    new HomeWindow( list.get(ind));
                } catch (ClassNotFoundException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else if(e.getActionCommand() == "Close"){
                dispose();
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new AddProfileImg("user.png",Main_With_IO.getAllNodes("Data.txt").get(0));
    }
}
