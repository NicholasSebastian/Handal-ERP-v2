package com.handalcargo.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import com.handalcargo.core.Database;
import com.handalcargo.core.Encryption;
import com.handalcargo.core.Session;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.FormField;

public class Login extends JFrame {

	private static Login instance = null;
	
	public static Login getInstance() {
		if (instance == null) {
			instance = new Login();
		}
		return instance;
	}
	
	private Login() {
		super("Handal Cargo - Login");
		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// Close database connection and free memory on close.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				Database.closeConnection();
				System.exit(0);
			}
		});
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		setIconImage(icon.getImage());
		
		
		Exception connectionError = Database.initialize();
		if (connectionError == null) {
			Encryption.initialize();
			JPanel portalView = new LoginView();
			add(portalView);
			setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(
				this, 
				connectionError.toString(), 
				"Connection Error", 
				JOptionPane.WARNING_MESSAGE
			);
		}
	}
	
	class LoginView extends JPanel {
		
		private LoginView() {
			setLayout(new BorderLayout());
			
			// Top panel.
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			topPanel.setBackground(Styles.sideBarColor);
			topPanel.setPreferredSize(new Dimension(0, 50));
			add(topPanel, BorderLayout.NORTH);
			
			JLabel titleLabel = new JLabel("HANDAL CARGO");
			titleLabel.setFont(Styles.loginTitleFont);
			titleLabel.setForeground(Color.WHITE);
			titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			topPanel.add(titleLabel, BorderLayout.WEST);
			
			// Content panel.
			JPanel contentPanel = new JPanel();
			contentPanel.setBackground(Color.WHITE);
			add(contentPanel, BorderLayout.CENTER);
			
			contentPanel.add(Box.createRigidArea(new Dimension(400, 30)));
			
			// Username.
			ImageIcon usernameIcon = new ImageIcon(this.getClass().getResource("/username.png"));
			Image scaledUsernameImage = usernameIcon.getImage().getScaledInstance(Styles.loginIconSize, Styles.loginIconSize, Image.SCALE_SMOOTH);
			JLabel usernameLabel = new JLabel(new ImageIcon(scaledUsernameImage));
			JTextField usernameField = new FormField();
			usernameLabel.setOpaque(true);
			usernameLabel.setBackground(Styles.gray);
			usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			usernameField.setColumns(25);
			usernameField.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					usernameLabel.setBackground(Styles.blue);
				}

				@Override
				public void focusLost(FocusEvent e) {
					usernameLabel.setBackground(Styles.gray);
				}
			});
			contentPanel.add(usernameLabel);
			contentPanel.add(usernameField);
			
			contentPanel.add(Box.createRigidArea(new Dimension(400, 10)));
			
			// Password.
			ImageIcon passwordIcon = new ImageIcon(this.getClass().getResource("/password.png"));
			Image scaledPasswordImage = passwordIcon.getImage().getScaledInstance(Styles.loginIconSize, Styles.loginIconSize, Image.SCALE_SMOOTH);
			JLabel passwordLabel = new JLabel(new ImageIcon(scaledPasswordImage));
			JPasswordField passwordField = new JPasswordField();
			passwordLabel.setOpaque(true);
			passwordLabel.setBackground(Styles.gray);
			passwordLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			passwordField.setColumns(25);
			passwordField.setFont(Styles.fieldFont);
			passwordField.setMargin(new Insets(6, 6, 6, 6));
			passwordField.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					passwordLabel.setBackground(Styles.blue);
				}
				@Override
				public void focusLost(FocusEvent e) {
					passwordLabel.setBackground(Styles.gray);
				}
			});
			contentPanel.add(passwordLabel);
			contentPanel.add(passwordField);
			
			contentPanel.add(Box.createRigidArea(new Dimension(400, 10)));

			// Login Button and functionality.
			JButton loginButton = new Button(
				"Login", Styles.blue, Styles.blueHover, new Dimension(100, 40), true, Styles.loginButtonFont, 
				e -> onLogin(usernameField.getText(), String.valueOf(passwordField.getPassword())));
			contentPanel.add(loginButton);
		}
		
		private void onLogin(String username, String password) {
			String encryptedPassword = Encryption.encrypt(password);
			
			// Authentication.
			String query = "SELECT EXISTS ( SELECT * FROM staff WHERE staffid = ? AND pwd = ? )";
			ResultSet results = Database.query(query, statement -> {
				try {
					statement.setString(1, username);
					statement.setString(2, encryptedPassword);
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			});
			try {
				results.next();
				if (results.getInt(1) == 1) {
					Session.getInstance();
					Session.initialize(username);
					Application.getInstance();
					Login.getInstance().dispose();
				}
				else {
					JOptionPane.showMessageDialog(
						Login.getInstance(), 
						"Invalid Credentials.", 
						"Alert", 
						JOptionPane.WARNING_MESSAGE);
				}
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
