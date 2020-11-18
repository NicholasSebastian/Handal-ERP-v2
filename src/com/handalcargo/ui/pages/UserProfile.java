package com.handalcargo.ui.pages;

import javax.swing.*;

import com.handalcargo.data.Session;

public class UserProfile extends JPanel {
	
	public UserProfile() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Session sessionRef = Session.getInstance();
		
		add(new JLabel(sessionRef.name));
		add(new JLabel(sessionRef.username));
		add(new JLabel(sessionRef.sex ? "Female" : "Male"));
		add(new JLabel(sessionRef.phone));
		add(new JLabel(sessionRef.mobile));
		add(new JLabel(sessionRef.address));
		add(new JLabel(sessionRef.kelurahan));
		add(new JLabel(sessionRef.city));
		add(new JLabel(sessionRef.employmentDate.toString()));
	}
}
