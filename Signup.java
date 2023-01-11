import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Signup extends JFrame {

		JLabel TitleLabel, IDLabel, NameLabel, LNameLabel,PassLabel, GenderLabel, AddressLabel, AgeLabel, AHomeLabel, AStreetLabel, ACityLabel;
		JButton SubmitButton,BackButton;
		JTextField IDTF, NTF, LNTF,GTF, ATF, HTF, STF, CTF;
		JPasswordField PTF;
		
		public Signup() {
			setTitle("User Registeration");
			setSize(800,500);
			setBounds(10,10,550,550);
			//setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			getContentPane().setLayout(null);
			this.getContentPane().setBackground(new java.awt.Color(103, 106, 230));
			//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ammar Cheema\\eclipse-workspace\\Oop Project\\Comsats Logo.jpg"));
			
			TitleLabel = new JLabel("Sign Up");
			TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			TitleLabel.setForeground(Color.WHITE);
			TitleLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 30));
			TitleLabel.setBounds(50, 15, 250, 30);
			getContentPane().add(TitleLabel);
			
			NameLabel = new JLabel("First Name:");
			NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			NameLabel.setBounds(200, 60, 120, 20);
			getContentPane().add(NameLabel);
			
			LNameLabel = new JLabel("Last Name:");
			LNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			LNameLabel.setBounds(200, 90, 120, 20);
			getContentPane().add(LNameLabel);
			
			IDLabel = new JLabel("User Name:");
			IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
			IDLabel.setBounds(200, 120, 120, 20);
			getContentPane().add(IDLabel);
			
			GenderLabel = new JLabel("Gender:");
			GenderLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GenderLabel.setBounds(200, 150, 120, 20);
			getContentPane().add(GenderLabel);
			
			PassLabel = new JLabel("Password:");
			PassLabel.setHorizontalAlignment(SwingConstants.CENTER);
			PassLabel.setBounds(200, 180, 120, 20);
			getContentPane().add(PassLabel);
			
			AgeLabel = new JLabel("Age:");
			AgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			AgeLabel.setBounds(200, 210, 120, 20);
			getContentPane().add(AgeLabel);
			
			AddressLabel = new JLabel("Address");
			AddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			AddressLabel.setBounds(200, 240, 120, 20);
			AddressLabel.setFont(new Font("Century", Font.BOLD, 15));
			getContentPane().add(AddressLabel);
			
			AHomeLabel = new JLabel("House.no:");
			AHomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			AHomeLabel.setBounds(200, 270, 120, 20);
			getContentPane().add(AHomeLabel);
			
			AStreetLabel = new JLabel("Street.no:");
			AStreetLabel.setHorizontalAlignment(SwingConstants.CENTER);
			AStreetLabel.setBounds(200, 293, 120, 20);
			getContentPane().add(AStreetLabel);
			
			ACityLabel = new JLabel("City:");
			ACityLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ACityLabel.setBounds(200, 315, 120, 20);
			getContentPane().add(ACityLabel);
			
			
			//Buttons
			SubmitButton = new JButton("SUBMIT");
			SubmitButton.setHorizontalAlignment(SwingConstants.CENTER);
			SubmitButton.setBounds(200, 360, 100, 25);
			getContentPane().add(SubmitButton);
			
			BackButton = new JButton("Home");
			BackButton.setHorizontalAlignment(SwingConstants.CENTER);
			BackButton.setBounds(350, 360, 100, 25);
			getContentPane().add(BackButton);
			
			//TextFields
			NTF = new JTextField("");
			NTF.setHorizontalAlignment(SwingConstants.CENTER);
			NTF.setBounds(330, 60, 140, 20);
			getContentPane().add(NTF);
			
			LNTF = new JTextField("");
			LNTF.setHorizontalAlignment(SwingConstants.CENTER);
			LNTF.setBounds(330, 90, 140, 20);
			getContentPane().add(LNTF);
			
			IDTF = new JTextField("");
			IDTF.setHorizontalAlignment(SwingConstants.CENTER);
			IDTF.setBounds(330, 120, 140, 20);
			getContentPane().add(IDTF);

			GTF = new JTextField("");
			GTF.setHorizontalAlignment(SwingConstants.CENTER);
			GTF.setBounds(330, 150, 140, 20);
			getContentPane().add(GTF);
			
			PTF = new JPasswordField("");
			PTF.setHorizontalAlignment(SwingConstants.CENTER);
			PTF.setBounds(330, 180, 140, 20);
			getContentPane().add(PTF);
			
			ATF = new JTextField("");
			ATF.setHorizontalAlignment(SwingConstants.CENTER);
			ATF.setBounds(330, 210, 140, 20);
			getContentPane().add(ATF);
			
			HTF = new JTextField("");
			HTF.setHorizontalAlignment(SwingConstants.CENTER);
			HTF.setBounds(330, 270, 140, 20);
			getContentPane().add(HTF);
			
			STF = new JTextField("");
			STF.setHorizontalAlignment(SwingConstants.CENTER);
			STF.setBounds(330, 293, 140, 20);
			getContentPane().add(STF);
			
			CTF = new JTextField("");
			CTF.setHorizontalAlignment(SwingConstants.CENTER);
			CTF.setBounds(330, 315, 140, 20);
			getContentPane().add(CTF);
			
			// ActionListener
			MyActionListener mal = new MyActionListener();
			SubmitButton.addActionListener(mal);
			BackButton.addActionListener(mal);

			
	}
		public class MyActionListener implements ActionListener{
			@Override
			
			public void actionPerformed(ActionEvent e) {
				ArrayList<User> list=new ArrayList<>();
				if(e.getActionCommand()=="SUBMIT") {
					System.out.println("Here ");
					if(NTF.getText().isEmpty()|| LNTF.getText().isEmpty() || IDTF.getText().isEmpty() || PTF.getText().isEmpty() || GTF.getText().isEmpty() || ATF.getText().isEmpty()  || HTF.getText().isEmpty() || STF.getText().isEmpty() || CTF.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (PTF.getText().length() >= 8) {
						Address a = new Address(CTF.getText(),Integer.parseInt(STF.getText()), Integer.parseInt(HTF.getText()));
						User d = new User(null,NTF.getText(),LNTF.getText(), Integer.parseInt(ATF.getText()), GTF.getText(), null, PTF.getText(),IDTF.getText(), a);
						
						try {
							list = Main_With_IO.getAllNodes("Data.txt");
							list.add(d);
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							Main_With_IO.writeData(list,"Data.txt");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Sign Up Successfully");
						// System.out.println(readFromFile());
						dispose();
						new Login();
						// return;
					} 
				}else if(e.getActionCommand()=="Home"){
					dispose();
					new Login();
				}
			}
		}
		public static void main(String[] args) {
			Signup u=new Signup();
	}
}
