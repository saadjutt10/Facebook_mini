import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AddressGUI extends JFrame {
	JLabel Title,username,passlabel,password,AddressLabel,AHomeLabel, AStreetLabel, ACityLabel;
	JTextField UNF,HTF, STF, CTF;
	JPasswordField passF;
	JButton SubmitButton,BackButton;
	public AddressGUI() {
		setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Change Your Address");
        setLayout(null);
        
        //Labels
        Title = new JLabel("Change your Address");
		Title.setForeground(Color.BLUE);
		Title.setFont(new Font("Aharoni", Font.BOLD | Font.ITALIC, 30));
		Title.setBounds(100, 10, 400, 40);
		add(Title);
		
		username = new JLabel("Username:");
		username.setBounds(200, 60, 120, 20);
		add(username);
		
		AddressLabel = new JLabel("Address");
		AddressLabel.setBounds(200, 90, 120, 20);
		AddressLabel.setFont(new Font("Century", Font.BOLD, 20));
		getContentPane().add(AddressLabel);
		
		AHomeLabel = new JLabel("House.no:");
		AHomeLabel.setBounds(200, 120, 120, 20);
		getContentPane().add(AHomeLabel);
		
		AStreetLabel = new JLabel("Street.no:");
		AStreetLabel.setBounds(200, 150, 120, 20);
		getContentPane().add(AStreetLabel);
		
		ACityLabel = new JLabel("City:");
		ACityLabel.setBounds(200, 180, 120, 20);
		getContentPane().add(ACityLabel);
		
		passlabel = new JLabel("Write your Password to Confirm Changes");
		passlabel.setBounds(200, 210, 320, 20);
		add(passlabel);
		
		password = new JLabel("Password:");
		password.setBounds(200, 245, 120, 20);
		add(password);
		
		
		//TextFields
		UNF = new JTextField("");
		UNF.setBounds(330, 60, 140, 20);
		add(UNF);
		
		HTF = new JTextField("");
		HTF.setBounds(330, 125, 140, 20);
		getContentPane().add(HTF);
		
		STF = new JTextField("");
		STF.setBounds(330, 155, 140, 20);
		getContentPane().add(STF);
		
		CTF = new JTextField("");
		CTF.setBounds(330, 185, 140, 20);
		getContentPane().add(CTF);
		
		//PasswordField
		passF = new JPasswordField("");
		passF.setBounds(330, 250, 140, 20);
		getContentPane().add(passF);
		
		//Buttons
		SubmitButton = new JButton("SUBMIT");
		SubmitButton.setBounds(220, 280, 100, 25);
		getContentPane().add(SubmitButton);
				
		BackButton = new JButton("BACK");
		BackButton.setBounds(350, 280, 100, 25);
		getContentPane().add(BackButton);	
		//ActionListener
		MyActionListener mal = new MyActionListener();
		SubmitButton.addActionListener(mal);
		BackButton.addActionListener(mal);
	}
	public class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==("SUBMIT")) {
	//Agay Daal diin edar file handling
	// k kis tarah update hoo ga sbb
				}
			if(e.getActionCommand()==("BACK")) {
			dispose();
			new SettingsWindow();
			}
		}
	}
	public static void main(String[] args) {
		AddressGUI a=new AddressGUI();
	}
}
