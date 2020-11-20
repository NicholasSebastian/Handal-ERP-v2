package com.handalcargo.ui.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.handalcargo.ui.Updateable;
import com.handalcargo.ui.Styles;

public class Staff extends JPanel implements Updateable {

	public Staff() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout());
		
		// Title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Staff");
		titleLabel.setFont(Styles.headerFont);
		titlePanel.add(titleLabel);
		
		// Content panel
		JPanel contentPanel = new JPanel();
		CardLayout content = new CardLayout();
		contentPanel.setLayout(content);
		contentPanel.setBorder(
			new CompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Styles.headerColor), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10))
			);
		contentPanel.setBackground(Color.WHITE);
		add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.add(new Overview(), "Overview");
		contentPanel.add(new AddView(), "Add");
		contentPanel.add(new ModifyView(), "Modify");
	}
	
	public void refresh() {
		System.out.println(this.getClass().getSimpleName());
	}
	
	class Overview extends JPanel {
		
		private Overview() {
			setOpaque(false);
			setLayout(new BorderLayout());
			
			
		}
	}
	
	class AddView extends JPanel {
		
		private AddView() {
			setOpaque(false);
			
		}
	}
	
	class ModifyView extends JPanel {
		
		private ModifyView() {
			setOpaque(false);
			
		}
	}
}
