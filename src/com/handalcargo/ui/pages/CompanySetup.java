package com.handalcargo.ui.pages;

import javax.swing.JPanel;

import com.handalcargo.ui.base.Updateable;

public class CompanySetup extends JPanel implements Updateable {

	public CompanySetup() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
