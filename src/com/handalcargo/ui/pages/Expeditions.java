package com.handalcargo.ui.pages;

import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.handalcargo.core.Database;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.base.Updateable;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.FormField;
import com.handalcargo.ui.pages.AirCargo.Form;
import com.handalcargo.ui.pages.AirCargo.ModifyForm;

public class Expeditions extends Layout {

	private ModifyForm modifyform;
	
	public Expeditions() {
		super ("Expeditions");
		
		Form addForm = new Form();
		ModifyForm modifyForm = new ModifyForm();
		
		setAddForm(addForm);
		setModifyForm(modifyForm);
		setModifyFormContent(modifyForm::setForm);
	}
	
	@Override
	protected void onDelete(Object selected) {
		
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
	
	class Form extends JPanel {
		
		protected JTextField expeditioncodeField;
		protected JTextField expeditionnameField;
		protected JTextField routenameField;
		protected JTextField addressField;
		protected JTextField phone1Field;
		protected JTextField phone2Field;
		protected JTextField faxField;
		protected JTextField descField;
		
		public Form() {
			setBackground(Color.WHITE);
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 8, 5, 8);
			
			// Labels
			c.gridx = 0;	c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_END;
					
			c.gridy++;	add(new JLabel("Expedition Code"), c);
			c.gridy++;	add(new JLabel("Expedition Name"), c);
			c.gridy++;	add(new JLabel("Route Name"), c);
			c.gridy++;	add(new JLabel("Address"), c);
			c.gridy++;	add(new JLabel("Phone 1"), c);
			c.gridy++;	add(new JLabel("Phone 2"), c);
			c.gridy++;	add(new JLabel("Fax"), c);
			c.gridy++;	add(new JLabel("Description"), c);
			
			// Fields
			c.gridx = 1;	c.weightx = 1.0;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridy = 0;
			
			c.gridy++;	expeditioncodeField = new FormField();	
						add(expeditioncodeField, c);
			c.gridy++;	expeditionnameField = new FormField();	
						add(expeditionnameField, c);
			c.gridy++;	routenameField = new FormField();	
						add(routenameField, c);
			c.gridy++;	addressField = new FormField();	
						add(addressField, c);
			c.gridy++;	phone1Field = new FormField();	
						add(phone1Field, c);
			c.gridy++;	phone2Field = new FormField();	
						add(phone2Field, c);
			c.gridy++;	faxField = new FormField();	
						add(faxField, c);
			c.gridy++;	descField = new FormField();	
						add(descField, c);
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
		System.out.println("from add form: " + expeditioncodeField.getText());
		}
	}
	
	class ModifyForm extends Form {
		public void setForm(Object selected) {
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
	}
}
