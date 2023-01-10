//  package GUI;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class SearchWindow extends JFrame{
    SearchWindow(User user){
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Login Iterface");
        // setBackground("yellow");
        // setBackground("green");
        // setLayout(new GridLayout());
        setLayout(new GridLayout(1, 2));
    }
}
