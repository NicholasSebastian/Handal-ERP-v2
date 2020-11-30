package com.handalcargo.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

import com.handalcargo.core.Session;
import com.handalcargo.ui.Styles;

public class UserProfile extends JPanel {
	
	public UserProfile() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Profile");
		titleLabel.setFont(Styles.headerFont);
		titlePanel.add(titleLabel);
		
		// Content panel
		JPanel whitePanel = new JPanel();
		whitePanel.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Styles.headerColor), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10))
			);
		whitePanel.setBackground(Color.WHITE);
		whitePanel.setLayout(new BoxLayout(whitePanel, BoxLayout.Y_AXIS));
		add(whitePanel, BorderLayout.CENTER);
		
		// Content
		Session sessionRef = Session.getInstance();
		
		whitePanel.add(new JLabel(sessionRef.name));
		whitePanel.add(new JLabel(sessionRef.username));
		whitePanel.add(new JLabel(sessionRef.sex ? "Female" : "Male"));
		whitePanel.add(new JLabel(sessionRef.phone));
		whitePanel.add(new JLabel(sessionRef.address));
		whitePanel.add(new JLabel(sessionRef.kelurahan));
		whitePanel.add(new JLabel(sessionRef.city));
		whitePanel.add(new JLabel(sessionRef.employmentDate != null ? sessionRef.employmentDate.toString() : ""));
	}
}
