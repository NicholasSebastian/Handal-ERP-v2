package com.handalcargo.ui.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import com.handalcargo.core.Database;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.SliderButton;
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
		formPanel.setOpaque(false);
		formPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);
		
		// Labels
		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		int i = 0;
		
		c.gridy = i++;	formPanel.add(new JLabel("Name"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Alias"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Password"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Level"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Address"), c);
		c.gridy = i++;	formPanel.add(new JLabel("District"), c);
		c.gridy = i++;	formPanel.add(new JLabel("City"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Phone Number"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Gender"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Place of Birth"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Date of Birth"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Salary"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Overtime Pay"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Food Allowance"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Bonus"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Staff Group"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Status"), c);
		c.gridy = i++;	formPanel.add(new JLabel("Date of Employment"), c);
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		i = 0;
		
		c.gridy = i++;	nameField = new JTextField();	
						formPanel.add(nameField, c);
		c.gridy = i++;	aliasField = new JTextField();
						formPanel.add(aliasField, c);
		c.gridy = i++;	passwordField = new JPasswordField();
						formPanel.add(passwordField, c);
		c.gridy = i++;	levelPicker = new JComboBox<String>();
						formPanel.add(levelPicker, c);
		c.gridy = i++;	addressField = new JTextArea();
						formPanel.add(addressField, c);
		c.gridy = i++;	districtField = new JTextField();
						formPanel.add(districtField, c);
		c.gridy = i++;	cityField = new JTextField();
						formPanel.add(cityField, c);
		c.gridy = i++;	phoneField = new JTextField();
						formPanel.add(phoneField, c);
		c.gridy = i++;	genderPicker = new JComboBox<String>(new String[] {"Male", "Female"});
						formPanel.add(genderPicker, c);
		c.gridy = i++;	birthplaceField = new DatePicker();
						formPanel.add(birthplaceField, c);
		c.gridy = i++;	birthdayField = new DatePicker();
						formPanel.add(birthdayField, c);
		c.gridy = i++;	salaryField = new JTextField();
						formPanel.add(salaryField, c);
		c.gridy = i++;	overtimeField = new JTextField();
						formPanel.add(overtimeField, c);
		c.gridy = i++;	allowanceField = new JTextField();
						formPanel.add(allowanceField, c);
		c.gridy = i++;	bonusField = new JTextField();
						formPanel.add(bonusField, c);
		c.gridy = i++;	groupPicker = new JComboBox<String>();
						formPanel.add(groupPicker, c);
		c.gridy = i++;	activeToggle = new SliderButton();
						formPanel.add(activeToggle, c);
		c.gridy = i++;	employDateField = new DatePicker();
						formPanel.add(employDateField, c);
		
		// Finish button
		JPanel finishPanel = new JPanel();
		finishPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		finishPanel.setOpaque(false);
		finishPanel.add(new Button("Save", Styles.green, Styles.greenHover, new Dimension(100, 40), true, e -> {
			
			// TODO: fix address text area.
			// TODO: separate add page submit functionality and modify page submit functionality.
			
			Database.update("INSERT INTO staff (`staffid`, `staffname`) VALUES (?, ?)", statement -> {
				try {
					statement.setString(1, aliasField.getText());	// TODO
					statement.setString(2, nameField.getText());
				} 
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			});
		}));
		finishPanel.add(new Button("Cancel", Styles.red, Styles.redHover, new Dimension(100, 40), true, e -> displayPage("Overview")));
		
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy = i++;	
		formPanel.add(finishPanel, c);
		
		return formPanel;
	}
	
	@Override
	protected void setForm(Object selected) {
		ResultSet results = Database.query(String.format("SELECT * FROM staff WHERE staffid=", selected));
		try {
			while (results.next()) {
				aliasField.setText(results.getString(0));	// TODO
				nameField.setText(results.getString(1));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
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
