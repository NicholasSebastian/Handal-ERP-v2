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
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.SliderButton;
import com.mysql.cj.xdevapi.Statement;
import com.handalcargo.ui.components.DatePicker;

public class Staff extends Layout {

	private JTextField nameField;
	private JTextField aliasField;
	private JPasswordField passwordField;
	private JComboBox<String> levelPicker;
	private JTextArea addressField;
	private JTextField districtField;
	private JTextField cityField;
	private JTextField phoneField;
	private JComboBox<String> genderPicker;
	private DatePicker birthplaceField;
	private DatePicker birthdayField;
	private JTextField salaryField;
	private JTextField overtimeField;
	private JTextField allowanceField;
	private JTextField bonusField;
	private JComboBox<String> groupPicker;
	private SliderButton activeToggle;
	private DatePicker employDateField;
	
	public Staff() {
		super("Staff");
	}
	
	@Override
	protected TableModel setTableModel() {
		final String[] columns = {"Staff ID", "Name", "Access Level", "Group"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			ResultSet results = Database.query("SELECT `staffid`, `staffname`, `level` FROM staff");
			
			while (results.next()) {
				data.add(new String[] {
					results.getString(1), 
					results.getString(2),
					results.getString(3)
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
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Color.WHITE);
		formPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);
		
		// Labels
		c.gridx = 0;	c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		c.gridy++;	formPanel.add(new JLabel("Name"), c);
		c.gridy++;	formPanel.add(new JLabel("Alias"), c);
		c.gridy++;	formPanel.add(new JLabel("Password"), c);
		c.gridy++;	formPanel.add(new JLabel("Level"), c);
		c.gridy++;	formPanel.add(new JLabel("Address"), c);
		c.gridy++;	formPanel.add(new JLabel("District"), c);
		c.gridy++;	formPanel.add(new JLabel("City"), c);
		c.gridy++;	formPanel.add(new JLabel("Phone Number"), c);
		c.gridy++;	formPanel.add(new JLabel("Gender"), c);
		c.gridy++;	formPanel.add(new JLabel("Place of Birth"), c);
		c.gridy++;	formPanel.add(new JLabel("Date of Birth"), c);
		c.gridy++;	formPanel.add(new JLabel("Salary"), c);
		c.gridy++;	formPanel.add(new JLabel("Overtime Pay"), c);
		c.gridy++;	formPanel.add(new JLabel("Food Allowance"), c);
		c.gridy++;	formPanel.add(new JLabel("Bonus"), c);
		c.gridy++;	formPanel.add(new JLabel("Staff Group"), c);
		c.gridy++;	formPanel.add(new JLabel("Status"), c);
		c.gridy++;	formPanel.add(new JLabel("Date of Employment"), c);
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;

		// TODO: fix address text area.
		
		c.gridy++;	nameField = new JTextField();	
					formPanel.add(nameField, c);
		c.gridy++;	aliasField = new JTextField();
					formPanel.add(aliasField, c);
		c.gridy++;	passwordField = new JPasswordField();
					formPanel.add(passwordField, c);
		c.gridy++;	levelPicker = new JComboBox<String>();
					formPanel.add(levelPicker, c);
		c.gridy++;	addressField = new JTextArea();
					formPanel.add(addressField, c);
		c.gridy++;	districtField = new JTextField();
					formPanel.add(districtField, c);
		c.gridy++;	cityField = new JTextField();
					formPanel.add(cityField, c);
		c.gridy++;	phoneField = new JTextField();
					formPanel.add(phoneField, c);
		c.gridy++;	genderPicker = new JComboBox<String>(new String[] {"Male", "Female"});
					formPanel.add(genderPicker, c);
		c.gridy++;	birthplaceField = new DatePicker();
					formPanel.add(birthplaceField, c);
		c.gridy++;	birthdayField = new DatePicker();
					formPanel.add(birthdayField, c);
		c.gridy++;	salaryField = new JTextField();
					formPanel.add(salaryField, c);
		c.gridy++;	overtimeField = new JTextField();
					formPanel.add(overtimeField, c);
		c.gridy++;	allowanceField = new JTextField();
					formPanel.add(allowanceField, c);
		c.gridy++;	bonusField = new JTextField();
					formPanel.add(bonusField, c);
		c.gridy++;	groupPicker = new JComboBox<String>();
					formPanel.add(groupPicker, c);
		c.gridy++;	activeToggle = new SliderButton();
					formPanel.add(activeToggle, c);
		c.gridy++;	employDateField = new DatePicker();
					formPanel.add(employDateField, c);
		
		return formPanel;
	}
	
	@Override
	protected void setForm(Object selected) {
		ResultSet results = Database.query(String.format("SELECT * FROM staff WHERE `staffid`='%s'", selected));
		try {
			while (results.next()) {	// TODO
				aliasField.setText(results.getString(1));
				nameField.setText(results.getString(2));
				// levelPicker; 		// 4
				addressField.setText(results.getString(6));        
				districtField.setText(results.getString(7));      
				cityField.setText(results.getString(8));          
				phoneField.setText(results.getString(9));         
				// genderPicker;		// 10
				// birthplaceField;    	// 11
				// birthdayField;		// 12
				salaryField.setText(results.getString(14));        
				overtimeField.setText(results.getString(13));      
				allowanceField.setText(results.getString(15));     
				bonusField.setText(results.getString(16));         
				// groupPicker; 		// 5
				activeToggle.setSelected(results.getBoolean(18));
				// employDateField;		// 19
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	protected void onAdd() {
		Database.update("INSERT INTO staff VALUES (?, ?, ?, ?)", statement -> {
			
		});
	}
	
	@Override
	protected void onModify() {
		Database.update(
			"UPDATE staff SET "
			+ "`staffname`='?', "
			+ "`pwd`='?' "
			+ "WHERE `staffid`='?'", 
			statement -> {
				try {		// TODO
					statement.setString(1, nameField.getText());
					statement.setString(2, Encryption.encrypt(new String(passwordField.getPassword())));
					
					statement.setString(3, aliasField.getText());
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			});
	}
	
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
