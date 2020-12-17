package com.handalcargo.ui.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.handalcargo.ui.base.Layout;

public class AirCargo extends Layout {
	
	private ModifyForm modifyForm;

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
	
	class Form extends JPanel {
		
		public Form() {
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			add(new JLabel("Hello"), c);
		}
	}
	
	class ModifyForm extends Form {
		
		public void setForm(Object selected) {
			
		}
	}

	@Override
	protected JPanel createAddForm() {
		return new Form();
	}

	@Override
	protected JPanel createModifyForm() {
		modifyForm = new ModifyForm();
		return modifyForm;
	}

	@Override
	protected void setForm(Object selected) {
		modifyForm.setForm(selected);
	}

	@Override
	protected void onDelete(Object selected) {
		
	}
}
