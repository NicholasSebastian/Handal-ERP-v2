package com.handalcargo.ui.pages;

import javax.swing.JPanel;

import com.handalcargo.ui.base.Updateable;

public class Payroll extends JPanel implements Updateable {

	public Payroll() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
