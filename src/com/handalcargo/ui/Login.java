package com.handalcargo.ui;

import java.awt.event.*;
import javax.swing.*;

import com.handalcargo.core.Database;
import com.handalcargo.core.Encryption;
import com.handalcargo.ui.views.LoginView;

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
			JPanel portalView = LoginView.getInstance();
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
}
