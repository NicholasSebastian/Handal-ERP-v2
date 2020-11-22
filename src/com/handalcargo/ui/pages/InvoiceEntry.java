package com.handalcargo.ui.pages;

import javax.swing.JPanel;

import com.handalcargo.ui.base.Updateable;

public class InvoiceEntry extends JPanel implements Updateable {

	public InvoiceEntry() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
