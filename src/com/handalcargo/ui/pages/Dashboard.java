package com.handalcargo.ui.pages;

import javax.swing.JPanel;

import com.handalcargo.ui.base.Updateable;;

public class Dashboard extends JPanel implements Updateable {

	public Dashboard() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
