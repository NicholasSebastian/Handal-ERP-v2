package com.handalcargo.ui.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.handalcargo.ui.base.Layout;

public class AirCargo extends Layout {

	public AirCargo() {
		super("Air Cargo");
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}

	@Override
	protected TableModel setTableModel(String filter) {
		
		return new DefaultTableModel();
	}

	@Override
	protected JPanel createForm() {
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		formPanel.add(new JLabel("Hello"), c);
		
		return formPanel;
	}

	@Override
	protected void setForm(Object selected) {
		// TODO Auto-generated method stub
		
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
