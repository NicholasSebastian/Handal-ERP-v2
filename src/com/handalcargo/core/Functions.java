package com.handalcargo.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.handalcargo.data.Session;
import com.handalcargo.ui.Application;
import com.handalcargo.ui.Login;

public class Functions {
	
	public static void promptExit() {
		int exit = JOptionPane.showConfirmDialog(
			Application.getInstance(), 
			"Log Out and Exit?", 
			"Close Application", 
			JOptionPane.YES_NO_OPTION
		);
		if (exit == JOptionPane.YES_OPTION) {
			Application.getInstance().dispose();
			Database.closeConnection();
			System.exit(0);
		}
	}

	public static void onLogin(String username, String password) {
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
