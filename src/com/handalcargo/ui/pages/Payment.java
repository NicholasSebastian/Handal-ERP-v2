package com.handalcargo.ui.pages;

import javax.swing.JPanel;
import com.handalcargo.ui.Updateable;

public class Payment extends JPanel implements Updateable {

	public Payment() {
		
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
}
