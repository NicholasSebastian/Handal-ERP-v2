package com.handalcargo.ui.pages;

import javax.swing.JPanel;
import com.handalcargo.ui.Updateable;

public class StaffGroups extends JPanel implements Updateable {

	public StaffGroups() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
