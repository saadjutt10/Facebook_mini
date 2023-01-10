import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class FileDirectory extends JFrame {
    FileDirectory() {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JButton b1 = new JButton("Button");
        add(b1);
        MyActionListener al=new MyActionListener();
        b1.addActionListener(al);
    }

    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Button") {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String dir=selectedFile.getAbsolutePath();
                    System.out.println(dir);
                    // Do something with the selected file, such as reading its contents
                    // or using it in some other way in your project
                }
            }
        }
    }
    public static void main(String[] args) {
        new FileDirectory();
    }
}
