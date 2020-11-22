package com.handalcargo.ui.pages;

import javax.swing.JPanel;

import com.handalcargo.ui.base.Updateable;

public class SeaFreight extends JPanel implements Updateable {

	public SeaFreight() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
