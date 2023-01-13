import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class SettingsWindow extends JFrame{
	JButton UB,NB,PB,HB,BlockBtn;
	JPanel TP,BP,HP;
	JLabel TPL,BPL;
	
	public SettingsWindow(User user) {
		setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Settings");
        setLayout(new GridLayout(3,1,30,50));
        setBackground(Color.decode("#"+Main.DarkColor));
        //Panels
        TP=new JPanel(new FlowLayout(FlowLayout.CENTER, 20,50));
        TP.setBackground(Color.decode("#"+Main.DarkColor));
        add(TP);
        
        BP=new JPanel();
        add(BP);
        BP.setLayout(new FlowLayout(FlowLayout.CENTER, 30,0));
        // BP.setBackground(Color.decode("#"+Main.notDarkColor));
        
        HP=new JPanel();
        // HP.setBackground(Color.decode("#"+Main.notDarkColor));
        add(HP);
        
        //Buttons
        NB=new JButton("Name");
        BP.add(NB);
        UB=new JButton("Profile Pic");
        BP.add(UB);
        PB=new JButton("Password");
        BP.add(PB);
        HB=new JButton("Home");
        HP.add(HB);
        BlockBtn=new JButton("Blocked Users");
		BP.add(BlockBtn);
        //Labels
        TPL=new JLabel("Change User Info Section");
        TPL.setForeground(Color.white);
		TPL.setFont(new Font("Aharoni", Font.BOLD | Font.ITALIC, 30));
		TP.add(TPL);
        
		//ActionListener
		MyActionListener mal = new MyActionListener( user);
		HB.addActionListener(mal);
		NB.addActionListener(mal);
		UB.addActionListener(mal);
		PB.addActionListener(mal);
		BlockBtn.addActionListener(mal);

	}
	public class MyActionListener implements ActionListener{
		User user;
		MyActionListener(User user){
			this.user=user;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==("Home")) {
				dispose();
				new HomeWindow(user);
				}
			if(e.getActionCommand()==("Name")) {
			dispose();
			new NameWindow(user);
				}
			// if(e.getActionCommand()==("Address")) {
			// 	dispose();
			// 	new AddressGUI(user);
			// 	}
			else if(e.getActionCommand()==("Profile Pic")) {
				dispose();
				new AddProfileImg(user.getImageDir(), user);
				}
			else if(e.getActionCommand()==("Password")) {
				dispose();
				new Password(user);
			}else if(e.getActionCommand()==("Blocked Users")) {
				try {
					dispose();
					new BlockedWindow(user);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}


public static void main(String[] args) throws ClassNotFoundException, IOException {
	new SettingsWindow(Main_With_IO.getAllNodes("Data.txt").get(8));
}
}
