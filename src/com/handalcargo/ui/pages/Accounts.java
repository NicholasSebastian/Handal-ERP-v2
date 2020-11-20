package com.handalcargo.ui.pages;

import javax.swing.JPanel;
import com.handalcargo.ui.Updateable;

public class Accounts extends JPanel implements Updateable {

	public Accounts() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
