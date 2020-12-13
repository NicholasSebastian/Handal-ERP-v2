package com.handalcargo.ui.pages;

import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.handalcargo.core.Database;
import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.base.Updateable;
import com.handalcargo.ui.components.FormField;

public class Handlers extends Layout {

	private JTextField codeField;
	private JTextField nameField;
	
	public Handlers() {
		super ("Handlers");
	}
	
	@Override
	protected TableModel setTableModel(String filter) {
		
		final String[] columns = {"Handler Code", "Name"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			String query = "SELECT `penguruscode`, `pengurusname` FROM pengurus";
			if (filter != null && filter.length() > 0) 
				query += String.format(" WHERE `penguruscode` LIKE '%%%s%%'", filter);
			
			ResultSet results = Database.query(query);
			while (results.next()) {
				data.add(new String[] {
					results.getString(1), 
					results.getString(2)
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
		// TODO Auto-generated method stub
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Color.WHITE);
		formPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);
		
		// Labels
		c.gridx = 0;	c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
				
		c.gridy++;	formPanel.add(new JLabel("Handler Code"), c);
		c.gridy++;	formPanel.add(new JLabel("Name"), c);
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;
		
		c.gridy++;	codeField = new FormField();	
					formPanel.add(codeField, c);
					
		c.gridy++;	nameField = new FormField();	
					formPanel.add(nameField, c);
		
		return formPanel;
	}

	@Override
	protected void setForm(Object selected) {
		// TODO Auto-generated method stub
		ResultSet results = Database.query(String.format("SELECT * FROM pengurus WHERE `penguruscode`='%s'", selected));
		try {
			while (results.next()) {	// TODO
				codeField.setText(results.getString(1));
				nameField.setText(results.getString(2));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	protected void onAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onModify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDelete(Object selected) {
		// TODO Auto-generated method stub
		
	}
}
