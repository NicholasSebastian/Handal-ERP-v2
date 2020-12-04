package com.handalcargo.ui.pages;

import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

import com.handalcargo.core.Database;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.base.Updateable;
import com.handalcargo.ui.components.IconButton;
import com.handalcargo.ui.components.ScrollPanel;
import com.handalcargo.ui.components.FormField;

public class StaffGroups extends JPanel implements Updateable {

	private JPanel contentPanel;
	private GridBagConstraints c;
	
	public StaffGroups() {
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout());
		
		// Title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Staff Groups");
		titleLabel.setFont(Styles.headerFont);
		titlePanel.add(titleLabel);
		
		// Content panel
		contentPanel = new JPanel();
		contentPanel.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Styles.headerColor), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10))
			);
		contentPanel.setLayout(new GridBagLayout());
		contentPanel.setBackground(Color.WHITE);
		
		JScrollPane scrollPanel = new ScrollPanel();
		scrollPanel.setViewportView(contentPanel);
		add(scrollPanel, BorderLayout.CENTER);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);
	}
	
	public void refresh() {
		for (Component comp : contentPanel.getComponents()) 
			contentPanel.remove(comp);
		
		c.gridy = 0;
		c.gridx = 0;	c.weightx = 0;	contentPanel.add(new JLabel("Group Code"), c);
		c.gridx = 1;	c.weightx = 1;	contentPanel.add(new JLabel("Job Title"), c);
		
		ResultSet results = Database.query("SELECT `stfgrcode`, `groupname` FROM staffgroup");
		try {
			while (results.next()) {
				c.gridy++;
				c.gridx = 0;	c.weightx = 0;	contentPanel.add(new FormField(results.getString(1)), c);
				c.gridx = 1;	c.weightx = 1;	contentPanel.add(new FormField(results.getString(2)), c);
				c.gridx = 2;	c.weightx = 0;	contentPanel.add(new IconButton("/delete.png", Styles.red, Styles.redHover, e -> System.out.println("click")), c);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO: add 'Save' button
	}
}
