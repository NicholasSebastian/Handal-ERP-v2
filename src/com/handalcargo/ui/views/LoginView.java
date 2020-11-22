package com.handalcargo.ui.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

import com.handalcargo.core.Database;
import com.handalcargo.core.Encryption;
import com.handalcargo.data.Session;
import com.handalcargo.ui.Application;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.Login;
import com.handalcargo.ui.components.Button;

public class LoginView extends JPanel {
	
	private static LoginView instance = null;
	
	private final int iconSize = 20;
	private final Font
		titleFont = new Font("Arial", Font.BOLD, 18),
		formFont = new Font("Arial", Font.PLAIN, 14),
		buttonFont = new Font("Arial", Font.BOLD, 14);
	
	public static LoginView getInstance() {
		if (instance == null) {
			instance = new LoginView();
		}
		return instance;
	}
	
	private LoginView() {
		setLayout(new BorderLayout());
		
		// Top panel.
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Styles.sideBarColor);
		topPanel.setPreferredSize(new Dimension(0, 50));
		add(topPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("HANDAL CARGO");
		titleLabel.setFont(titleFont);
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
		Image scaledUsernameImage = usernameIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
		JLabel usernameLabel = new JLabel(new ImageIcon(scaledUsernameImage));
		JTextField usernameField = new JTextField();
		usernameLabel.setOpaque(true);
		usernameLabel.setBackground(Styles.gray);
		usernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		usernameField.setColumns(25);
		usernameField.setFont(formFont);
		usernameField.setMargin(new Insets(6, 6, 6, 6));
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
		Image scaledPasswordImage = passwordIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
		JLabel passwordLabel = new JLabel(new ImageIcon(scaledPasswordImage));
		JPasswordField passwordField = new JPasswordField();
		passwordLabel.setOpaque(true);
		passwordLabel.setBackground(Styles.gray);
		passwordLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		passwordField.setColumns(25);
		passwordField.setFont(formFont);
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
			"Login", Styles.blue, Styles.blueHover, new Dimension(100, 40), true, buttonFont, 
			e -> onLogin(usernameField.getText(), String.valueOf(passwordField.getPassword())));
		contentPanel.add(loginButton);
	}
	
	private static void onLogin(String username, String password) {
		String encryptedPassword = Encryption.encrypt(password);
		
		// Authentication.
		String query = "SELECT EXISTS ( SELECT * FROM accounts WHERE username = ? AND password = ? )";
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