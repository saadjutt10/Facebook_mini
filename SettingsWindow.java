import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsWindow extends JFrame{
	JButton UB,NB,AddB,PB,HB;
	JPanel TP,BP,HP;
	JLabel TPL,BPL;
	
	public SettingsWindow(User user) {
		setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Settings");
        setLayout(new GridLayout(3,1,30,50));
        
        //Panels
        TP=new JPanel(new FlowLayout(FlowLayout.CENTER, 20,50));
        add(TP);
        
        BP=new JPanel();
        add(BP);
        BP.setLayout(new FlowLayout(FlowLayout.CENTER, 30,0));
        
        HP=new JPanel();
        add(HP);
        
        //Buttons
        NB=new JButton("Name");
        BP.add(NB);
        UB=new JButton("Profile Pic");
        BP.add(UB);
        AddB=new JButton("Address");
        BP.add(AddB);
        PB=new JButton("Password");
        BP.add(PB);
        HB=new JButton("Home");
        HP.add(HB);
        
        //Labels
        TPL=new JLabel("Change User Info:");
        TPL.setForeground(Color.BLACK);
		TPL.setFont(new Font("Aharoni", Font.BOLD | Font.ITALIC, 30));
		TP.add(TPL);
        
		//ActionListener
		MyActionListener mal = new MyActionListener( user);
		HB.addActionListener(mal);
		NB.addActionListener(mal);
		UB.addActionListener(mal);
		AddB.addActionListener(mal);
		PB.addActionListener(mal);

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
			if(e.getActionCommand()==("Profile Pic")) {
				//dispose();
				JOptionPane.showMessageDialog(null, "Picture Button is pressed.");
				}
			if(e.getActionCommand()==("Password")) {
				dispose();
				new Password(user);
			}
		}
	}



}
