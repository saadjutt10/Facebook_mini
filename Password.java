import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Password extends JFrame {
	JLabel Title, username, oldpassword, newpassword, confirmpassword;
	JPasswordField oldpass, newpass, conPass;
	JTextField UNF;
	JButton SubmitButton, BackButton;

	public Password(User user) {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Change Your Address");
		setLayout(null);

		// Labels
		Title = new JLabel("Change your Address");
		Title.setForeground(Color.BLUE);
		Title.setFont(new Font("Aharoni", Font.BOLD | Font.ITALIC, 30));
		Title.setBounds(100, 10, 400, 40);
		add(Title);

		username = new JLabel("Username:");
		username.setBounds(200, 60, 120, 20);
		add(username);

		oldpassword = new JLabel("Old Password:");
		oldpassword.setBounds(200, 90, 120, 20);
		add(oldpassword);

		newpassword = new JLabel("New Password:");
		newpassword.setBounds(200, 120, 120, 20);
		add(newpassword);

		confirmpassword = new JLabel("Confirm Password:");
		confirmpassword.setBounds(200, 150, 120, 20);
		add(confirmpassword);

		// PasswordField
		oldpass = new JPasswordField("");
		oldpass.setBounds(330, 90, 140, 20);
		getContentPane().add(oldpass);

		newpass = new JPasswordField("");
		newpass.setBounds(330, 120, 140, 20);
		getContentPane().add(newpass);

		conPass = new JPasswordField("");
		conPass.setBounds(330, 150, 140, 20);
		getContentPane().add(conPass);

		// Buttons
		SubmitButton = new JButton("SUBMIT");
		SubmitButton.setBounds(220, 180, 100, 25);
		getContentPane().add(SubmitButton);

		BackButton = new JButton("BACK");
		BackButton.setBounds(350, 180, 100, 25);
		getContentPane().add(BackButton);

		// TextFields
		UNF = new JTextField("");
		UNF.setBounds(330, 60, 140, 20);
		add(UNF);

		// ActionListener
		MyActionListener mal = new MyActionListener(user);
		SubmitButton.addActionListener(mal);
		BackButton.addActionListener(mal);

	}

	public class MyActionListener implements ActionListener {
		User user;

		MyActionListener(User user) {
			user = user;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == ("SUBMIT")) {
				ArrayList<User> list;
				try {
					list = Main_With_IO.getAllNodes("Data.txt");

					for (User i : list) {
						if (i.equals(user)) {
							if(UNF.getText().equals(user.getUsername())){
								if(oldpass.getText().equals(user.getPassword())){
									if(newpass.getText().equals(conPass.getText())){
										user.setPassword(newpass.getText());
										JOptionPane.showMessageDialog(null, "Password is changed.");
										i.setPassword(newpass.getText());
										user=i;
										dispose();
										new HomeWindow(user);
									}else{
										newpass.setText("");
										conPass.setText("");
									}
								}else{
									oldpass.setText("Incorrect Password");
								}
							}
							else{
								UNF.setText("Incorrect User Name");
							}
						}
					}
					Main_With_IO.writeData(list, "Data.txt");
					dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new HomeWindow(user);
			}
			if (e.getActionCommand() == ("BACK")) {
				dispose();
				new SettingsWindow(user);
			}
		}
	}

}
