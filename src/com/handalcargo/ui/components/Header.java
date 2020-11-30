package com.handalcargo.ui.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import com.handalcargo.ui.Styles;
import com.handalcargo.core.Session;
import com.handalcargo.ui.Application;

public class Header extends JPanel {
	
	public Header() {
		setPreferredSize(new Dimension(0, Styles.headerHeight));
		setBackground(Styles.headerColor);
		setLayout(new BorderLayout());
		
		// Handal Cargo logo.
		JLabel logo = new JLabel("Handal Cargo");
		logo.setFont(Styles.headerLogoFont);
		logo.setForeground(Color.WHITE);
		logo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		add(logo, BorderLayout.WEST);
		
		// Profile.
		add(new Profile(), BorderLayout.EAST);
	}
	
	private class Profile extends JButton {
		
		private Profile() {
			
			// Display username.
			setText(Session.getInstance().username);
			setFont(Styles.headerProfileFont);
			setForeground(Color.WHITE);
			
			// Display avatar icon.
			ImageIcon icon = new ImageIcon(this.getClass().getResource("/user.png"));
			Image scaledImage = icon.getImage().getScaledInstance(Styles.headerProfileIconSize, Styles.headerProfileIconSize, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledImage));
			setIconTextGap(8);
			
			// Background color.
			setUI((ButtonUI) BasicButtonUI.createUI(this));
			setBackground(Styles.profileHoverColor);
			
			// Styling.
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
			
			// Hover effects.
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Profile.this.setContentAreaFilled(true);
				}
				
				public void mouseExited(MouseEvent e) {
					Profile.this.setContentAreaFilled(false);
				}
			});
			
			// On click event.
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Application.displayPage("UserProfile");
				}
			});
		}
	}
}
