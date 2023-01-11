import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class NameWindow extends JFrame {
	JLabel Title, username, fname, lname, passlabel, password;
	JTextField UNF, FNF, LNF;
	JPasswordField passF;
	JButton SubmitButton, BackButton;

	public NameWindow(User user) {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Change Your Name");
		setLayout(null);

		// Labels
		Title = new JLabel("Change your Name");
		Title.setForeground(Color.BLUE);
		Title.setFont(new Font("Aharoni", Font.BOLD | Font.ITALIC, 20));
		Title.setBounds(100, 10, 300, 30);
		add(Title);

		username = new JLabel("Username:");
		username.setBounds(200, 60, 120, 20);
		add(username);

		fname = new JLabel("First Name:");
		fname.setBounds(200, 90, 120, 20);
		add(fname);

		lname = new JLabel("Last Name:");
		lname.setBounds(200, 120, 120, 20);
		add(lname);

		passlabel = new JLabel("Write your Password to Confirm Changes");
		passlabel.setBounds(200, 150, 320, 20);
		add(passlabel);

		password = new JLabel("Password:");
		password.setBounds(200, 190, 120, 20);
		add(password);

		// TextFields
		UNF = new JTextField("");
		UNF.setBounds(330, 60, 140, 20);
		add(UNF);

		FNF = new JTextField("");
		FNF.setBounds(330, 90, 140, 20);
		add(FNF);

		LNF = new JTextField("");
		LNF.setBounds(330, 120, 140, 20);
		add(LNF);

		// PasswordField
		passF = new JPasswordField("");
		passF.setBounds(330, 190, 140, 20);
		getContentPane().add(passF);

		// Buttons
		SubmitButton = new JButton("SUBMIT");
		SubmitButton.setBounds(220, 220, 100, 25);
		getContentPane().add(SubmitButton);

		BackButton = new JButton("BACK");
		BackButton.setBounds(350, 220, 100, 25);
		getContentPane().add(BackButton);

		// ActionListener
		MyActionListener mal = new MyActionListener(user);
		SubmitButton.addActionListener(mal);
		BackButton.addActionListener(mal);

	}

	public class MyActionListener implements ActionListener {
		User yu;

		MyActionListener(User user) {
			this.yu = user;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "SUBMIT") {
				try {
					ArrayList<User> list = Main_With_IO.getAllNodes("Data.txt");
					System.out.println(list.size());
					// yu.getName()
					for (User i : list) {
						System.out.println(i.getName());
						if (i.getUsername().equals(yu.getUsername())) {
							System.out.println("Found that mf");
							if(passF.getText().equals(i.getPassword())){
								i.setUsername(UNF.getText());
								i.setName(FNF.getText());
								i.setLastName(LNF.getText());
								Main_With_IO.writeData(list, "Data.txt");
								dispose();
								new HomeWindow(yu);
							}
							else{
								UNF.setText("Wrong Password");
							}
							yu=i;
						}
					}
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (e.getActionCommand() == ("BACK")) {
				dispose();
				new SettingsWindow(yu);
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		NameWindow n = new NameWindow(Main_With_IO.getAllNodes("Data.txt").get(0));
	}

}
