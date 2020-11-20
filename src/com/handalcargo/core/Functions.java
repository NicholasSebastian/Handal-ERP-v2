package com.handalcargo.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static ArrayList<String[]> getStaff() {
		ArrayList<String[]> data = new ArrayList<>();
		try {
			ResultSet results = Database.query("SELECT * FROM staff");
			
			while (results.next()) {
				data.add(new String[] {		// Add to this
					results.getString(""),
					results.getString(""),
					results.getString("")
				});
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static ArrayList<String[]> getAccounts() {
		ArrayList<String[]> data = new ArrayList<>();
		try {
			ResultSet results = Database.query("SELECT `staffId`, `username`, `password` FROM accounts");
			
			while (results.next()) {
				String staffId = results.getString("staffid");
				ResultSet details = Database.query("SELECT `name`, `group` FROM staff WHERE staffId=" + staffId);
				details.next();
				
				data.add(new String[] {
					results.getString("username"), 
					results.getString("password"), 
					details.getString("name"),
					details.getString("group")
				});
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
