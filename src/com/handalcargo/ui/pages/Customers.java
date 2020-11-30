package com.handalcargo.ui.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import com.handalcargo.ui.base.Layout;

public class Customers extends Layout {
	
	private JTextField nameField;

	public Customers() {
		super("Customers");
	}
	
	@Override
	protected TableModel setTableModel() {
		
		return new DefaultTableModel();
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
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;
		
		c.gridy++;	nameField = new JTextField();	
					formPanel.add(nameField, c);
		
		return formPanel;
	}
	
	@Override
	protected void setForm(Object selected) {
		
	}
	
	@Override
	public void onAdd() {
		
	}
	
	@Override
	public void onModify() {
		
	}
	
	@Override
	public void onDelete(Object selected) {
		
	}
}
