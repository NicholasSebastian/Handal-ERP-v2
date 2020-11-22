package com.handalcargo.ui.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import com.handalcargo.core.Database;
import com.handalcargo.ui.base.Layout;

public class Accounts extends Layout {

	public Accounts() {
		super("Accounts");
	}
	
	@Override
	protected TableModel createTableModel() {
		final String[] columns = {"Username", "Encrypted Password", "Full Name", "Group"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			ResultSet results = Database.query("SELECT * FROM accounts");
			
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
		
		String dataArray[][] = new String[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			dataArray[i] = data.get(i);
		}
		
		return new DefaultTableModel(dataArray, columns);
	}
	
	@Override
	protected JPanel createForm() {
		
		return new JPanel();
	}
	
	@Override
	protected JPanel setForm(Object selected) {
		
		return new JPanel();
	}
	
	@Override
	protected void onDelete(Object selected) {
		Database.update("DELETE FROM accounts WHERE username=?", 
			statement -> {
				try {
					statement.setString(1, selected.toString());
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			});
	}
}
