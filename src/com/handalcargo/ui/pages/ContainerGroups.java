package com.handalcargo.ui.pages;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.handalcargo.ui.pages.Customers.Form;
import com.handalcargo.ui.pages.Customers.ModifyForm;

public class ContainerGroups extends Layout {

	public ContainerGroups() {
		super("Container Groups");
		
		Form addForm = new Form();
		ModifyForm modifyForm = new ModifyForm();
		
		setAddForm(addForm);
		setModifyForm(modifyForm);
		setModifyFormContent(modifyForm::setForm);
	}
	
	@Override
	protected void onDelete(Object selected) {
		Database.update("DELETE FROM kelcontainer WHERE containercode = ?", statement -> {
			try {
				statement.setString(1, selected.toString());
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		});
		System.out.println("Record Deleted");
	}
	
	@Override
	protected TableModel setTableModel(String filter) {
		
		final String[] columns = {"Container Code", "Description", "Size"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			String query = "SELECT `containercode`, `containerdesc`, `size` FROM kelcontainer";
			if (filter != null && filter.length() > 0) 
				query += String.format(" WHERE `containercode` LIKE '%%%s%%'", filter);
			
			ResultSet results = Database.query(query);
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
	
	class Form extends JPanel {
		
		protected JTextField codeField;
		protected JTextField descField;
		protected JTextField sizeField;
		
		public Form() {
			setBackground(Color.WHITE);
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 8, 5, 8);
			
			// Labels
			c.gridx = 0;	c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_END;
					
			c.gridy++;	add(new JLabel("Container Code"), c);
			c.gridy++;	add(new JLabel("Description"), c);
			c.gridy++;	add(new JLabel("Container Size"), c);
			
			// Fields
			c.gridx = 1;	c.weightx = 1.0;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridy = 0;
			
			c.gridy++;	codeField = new FormField();	
						add(codeField, c);
						
			c.gridy++;	descField = new FormField();	
						add(descField, c);
						
			c.gridy++;	sizeField = new FormField();	
						add(sizeField, c);
						
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
			Database.update("INSERT INTO kelcontainer VALUES (?, ?, ?)", 
			statement -> {
			try {
				statement.setInt(1, Integer.parseInt(codeField.getText()));
				statement.setString(2, descField.getText());
				statement.setString(3, sizeField.getText());				
			} 
			catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		});
			System.out.println("Record Added");
		}
	}
	
	class ModifyForm extends Form {
		
		private Object selected;
		
		public void setForm(Object selected) {
			this.selected = selected;
			codeField.setEditable(false);
			
			ResultSet results = Database.query(String.format("SELECT * FROM kelcontainer WHERE `containercode`='%s'", selected));
			try {
				while (results.next()) {
					codeField.setText(results.getString(1));
					descField.setText(results.getString(2));
					sizeField.setText(results.getString(3));
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void onSave() {
			Database.update("UPDATE kelcontainer SET containerdesc = ?, size = ? WHERE containercode = ? ", 
				statement -> {
				try {
					statement.setString(1, descField.getText());
					statement.setString(2, sizeField.getText());
					statement.setInt(3, Integer.valueOf(selected.toString()));
				} 
				catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			});
			System.out.println("Record Changed");
		}
	}
}
