package com.handalcargo.ui.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import com.handalcargo.core.Database;
import com.handalcargo.ui.base.Layout;

public class Staff extends Layout {
	
	public Staff() {
		super("Staff");
	}
	
	@Override
	protected TableModel createTableModel() {
		
		return new DefaultTableModel();
	}
	
	@Override
	protected JPanel createForm() {
		
		return new JPanel();
	}
	
	@Override
	protected JPanel setForm(Object selected) {
		
		return new JPanel();
	}
	
	@Override
	protected void onDelete(Object selected) {
		
	}
}
