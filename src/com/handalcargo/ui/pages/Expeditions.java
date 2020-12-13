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

public class Expeditions extends Layout {
	
	private JTextField expeditioncodeField;
	private JTextField expeditionnameField;
	private JTextField routenameField;
	private JTextField addressField;
	private JTextField phone1Field;
	private JTextField phone2Field;
	private JTextField faxField;
	private JTextField descField;

	public Expeditions() {
		super ("Expeditions");
	}
	
	@Override
	protected TableModel setTableModel(String filter) {
		
		final String[] columns = {"Expedition Code", "Expedition Name", "Route Name", "Address", "Phone 1", "Phone 2", "Fax", "Description"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			String query = "SELECT `expedisicode`, `expedisiname`, `rutename`, `alamat`, `phone1`, `phone2`, `fax`, `keterangan` FROM expedisi";
			if (filter != null && filter.length() > 0) 
				query += String.format(" WHERE `expedisicode` LIKE '%%%s%%'", filter);
			
			ResultSet results = Database.query(query);
			while (results.next()) {
				data.add(new String[] {
					results.getString(1), 
					results.getString(2),
					results.getString(3),
					results.getString(4),
					results.getString(5),
					results.getString(6),
					results.getString(7),
					results.getString(8)
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
				
		c.gridy++;	formPanel.add(new JLabel("Expedition Code"), c);
		c.gridy++;	formPanel.add(new JLabel("Expedition Name"), c);
		c.gridy++;	formPanel.add(new JLabel("Route Name"), c);
		c.gridy++;	formPanel.add(new JLabel("Address"), c);
		c.gridy++;	formPanel.add(new JLabel("Phone 1"), c);
		c.gridy++;	formPanel.add(new JLabel("Phone 2"), c);
		c.gridy++;	formPanel.add(new JLabel("Fax"), c);
		c.gridy++;	formPanel.add(new JLabel("Description"), c);
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;
		
		c.gridy++;	expeditioncodeField = new FormField();	
					formPanel.add(expeditioncodeField, c);
		c.gridy++;	expeditionnameField = new FormField();	
					formPanel.add(expeditionnameField, c);
		c.gridy++;	routenameField = new FormField();	
					formPanel.add(routenameField, c);
		c.gridy++;	addressField = new FormField();	
					formPanel.add(addressField, c);
		c.gridy++;	phone1Field = new FormField();	
					formPanel.add(phone1Field, c);
		c.gridy++;	phone2Field = new FormField();	
					formPanel.add(phone2Field, c);
		c.gridy++;	faxField = new FormField();	
					formPanel.add(faxField, c);
		c.gridy++;	descField = new FormField();	
					formPanel.add(descField, c);
		
		return formPanel;
	}

	@Override
	protected void setForm(Object selected) {
		// TODO Auto-generated method stub
		ResultSet results = Database.query(String.format("SELECT * FROM expedisi WHERE `expedisicode`='%s'", selected));
		try {
			while (results.next()) {	// TODO
				expeditioncodeField.setText(results.getString(1));
				expeditionnameField.setText(results.getString(2));
				routenameField.setText(results.getString(3));
				addressField.setText(results.getString(4));
				phone1Field.setText(results.getString(5));
				phone2Field.setText(results.getString(6));
				faxField.setText(results.getString(7));
				descField.setText(results.getString(8));
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
