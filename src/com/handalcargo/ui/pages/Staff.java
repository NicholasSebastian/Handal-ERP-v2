package com.handalcargo.ui.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import com.handalcargo.core.Database;
import com.handalcargo.core.Encryption;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.components.SliderButton;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.DatePicker;
import com.handalcargo.ui.components.FormField;

public class Staff extends Layout {

	private ModifyForm modifyform;
	
	public Staff() {
		super("Staff");
	}
	
	@Override
	protected TableModel setTableModel(String filter) {
		final String[] columns = {"Staff ID", "Name", "Access Level", "Group"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			String query = "SELECT `staffid`, `staffname`, `level`, `groupcode` FROM staff";
			if (filter != null && filter.length() > 0) 
				query += String.format(" WHERE `staffid` LIKE '%%%s%%'", filter);
			
			ResultSet results = Database.query(query);
			while (results.next()) {
				data.add(new String[] {
					results.getString(1), 
					results.getString(2),
					results.getString(3),
					results.getString(4)
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
	
	class Form extends JPanel {
		
		protected JTextField codeField;
		protected JTextField nameField;
		protected JPasswordField passwordField;
		protected JComboBox<String> levelPicker;
		protected JTextArea addressField;
		protected JTextField districtField;
		protected JTextField cityField;
		protected JTextField phoneField;
		protected JComboBox<String> genderPicker;
		protected JTextField birthplaceField;
		protected DatePicker birthdayField;
		protected JTextField salaryField;
		protected JTextField overtimeField;
		protected JTextField allowanceField;
		protected JTextField bonusField;
		protected JComboBox<String> groupPicker;
		protected SliderButton activeToggle;
		protected DatePicker employDateField;
		
		public Form() {
			setBackground(Color.WHITE);
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 8, 5, 8);
			
			// Labels
			c.gridx = 0;	c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_END;
			
			c.gridy++;	add(new JLabel("Staff ID"), c);
			c.gridy++;	add(new JLabel("Name"), c);
			c.gridy++;	add(new JLabel("Password"), c);
			c.gridy++;	add(new JLabel("Level"), c);
			c.gridy++;	add(new JLabel("Address"), c);
			c.gridy++;	add(new JLabel("District"), c);
			c.gridy++;	add(new JLabel("City"), c);
			c.gridy++;	add(new JLabel("Phone Number"), c);
			c.gridy++;	add(new JLabel("Gender"), c);
			c.gridy++;	add(new JLabel("Place of Birth"), c);
			c.gridy++;	add(new JLabel("Date of Birth"), c);
			c.gridy++;	add(new JLabel("Salary"), c);
			c.gridy++;	add(new JLabel("Overtime Pay"), c);
			c.gridy++;	add(new JLabel("Food Allowance"), c);
			c.gridy++;	add(new JLabel("Bonus"), c);
			c.gridy++;	add(new JLabel("Staff Group"), c);
			c.gridy++;	add(new JLabel("Status"), c);
			c.gridy++;	add(new JLabel("Date of Employment"), c);
			
			// Fields
			c.gridx = 1;	c.weightx = 1.0;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridy = 0;

			// TODO: fix address text area.
			
			c.gridy++;	codeField = new FormField();	
						add(codeField, c);
			c.gridy++;	nameField = new FormField();
						add(nameField, c);
			c.gridy++;	passwordField = new JPasswordField();
						passwordField.setFont(Styles.fieldFont);
						passwordField.setMargin(new Insets(6, 6, 6, 6));
						add(passwordField, c);
			c.gridy++;	levelPicker = new JComboBox<String>();
						add(levelPicker, c);
			c.gridy++;	addressField = new JTextArea();
						add(addressField, c);
			c.gridy++;	districtField = new FormField();
						add(districtField, c);
			c.gridy++;	cityField = new FormField();
						add(cityField, c);
			c.gridy++;	phoneField = new FormField();
						add(phoneField, c);
			c.gridy++;	genderPicker = new JComboBox<String>(new String[] {"Male", "Female"});
						add(genderPicker, c);
			c.gridy++;	birthplaceField = new FormField();
						add(birthplaceField, c);
			c.gridy++;	birthdayField = new DatePicker();
						add(birthdayField, c);
			c.gridy++;	salaryField = new FormField();
						add(salaryField, c);
			c.gridy++;	overtimeField = new FormField();
						add(overtimeField, c);
			c.gridy++;	allowanceField = new FormField();
						add(allowanceField, c);
			c.gridy++;	bonusField = new FormField();
						add(bonusField, c);
			c.gridy++;	groupPicker = new JComboBox<String>();
						add(groupPicker, c);
			c.gridy++;	activeToggle = new SliderButton();
						add(activeToggle, c);
			c.gridy++;	employDateField = new DatePicker();
						add(employDateField, c);
						
			JPanel finishPanel = new JPanel();
			finishPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			finishPanel.setOpaque(false);
						
			finishPanel.add(
				new Button("Save", Styles.green, Styles.greenHover, new Dimension(100, 40), true, 
				e -> {
					onSave();
					refresh();
				}
			));
						
			finishPanel.add(
				new Button("Cancel", Styles.red, Styles.redHover, new Dimension(100, 40), true, 
				e -> {
					displayPage("Overview");
					refresh();
				}
			));
						
			c.gridx = 1;	c.gridwidth = 2;
			c.gridy++;	add(finishPanel, c);
		}
		
		public void onSave() {
			System.out.println("from add form: " + codeField.getText());
		}
	}
	
	class ModifyForm extends Form {
		public void setForm(Object selected) {
			ResultSet results = Database.query(String.format("SELECT * FROM staff WHERE `staffid`='%s'", selected));
			try {
				while (results.next()) {	// TODO
					codeField.setText(results.getString(1));
					nameField.setText(results.getString(2));
					passwordField.setText(results.getString(3));
					//levelPicker.set"SOMETHING"(results.getString(4)); COMBOBOX
					addressField.setText(results.getString(6));        
					districtField.setText(results.getString(7));      
					cityField.setText(results.getString(8));          
					phoneField.setText(results.getString(9));         
					//genderPicker.set"SOMETHING"(reuslts.getString(10)); COMBOBOX	
					birthplaceField.setText(results.getString(11));   
					birthdayField.setDate(results.getDate(12));;		
					overtimeField.setText(results.getString(13)); 
					salaryField.setText(results.getString(14));        
					allowanceField.setText(results.getString(15));     
					bonusField.setText(results.getString(16));         
					activeToggle.setSelected(results.getBoolean(18));
					employDateField.setDate(results.getDate(19));;		
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	protected JPanel createAddForm() {
		return new Form();
	}
	
	@Override
	protected JPanel createModifyForm() {
		modifyform = new ModifyForm();
		return modifyform;
	}
	
	@Override
	protected void setForm(Object selected) {
		modifyform.setForm(selected);
	}
	
//	Database.update("INSERT INTO staff VALUES (?, ?, ?, ?)", statement -> {
//		
//	});
	
//	Database.update(
//			"UPDATE staff SET "
//			+ "`staffname`='?', "
//			+ "`pwd`='?' "
//			+ "WHERE `staffid`='?'", 
//			statement -> {
//				try {			// TODO
//					statement.setString(1, nameField.getText());
//					statement.setString(2, Encryption.encrypt(new String(passwordField.getPassword())));
//					
//					statement.setString(3, aliasField.getText());
//				}
//				catch (SQLException e) {
//					e.printStackTrace();
//				}
//			});
	
	@Override
	protected void onDelete(Object selected) {
		Database.update("DELETE FROM staff WHERE name=?", 
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
