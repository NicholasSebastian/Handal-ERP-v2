package com.handalcargo.ui.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.pages.Customers.Form;
import com.handalcargo.ui.pages.Customers.ModifyForm;

public class AirCargo extends Layout {

	public AirCargo() {
		super("Air Cargo");
		
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
}
