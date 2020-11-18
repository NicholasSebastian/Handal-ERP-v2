package com.handalcargo.ui.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import com.handalcargo.data.Palette;
import com.handalcargo.data.Session;
import com.handalcargo.ui.Application;

public class Header extends JPanel {
	
	private static final Font
		logoFont = new Font("Arial Black", Font.BOLD, 22),
		profileFont = new Font("Arial", Font.BOLD, 16);
	
	private static final int 
		height = 50,
		profileIconSize = 25;
	
	public Header() {
		setPreferredSize(new Dimension(0, height));
		setBackground(Palette.headerColor);
		setLayout(new BorderLayout());
		
		// Handal Cargo logo.
		JLabel logo = new JLabel("Handal Cargo");
		logo.setFont(logoFont);
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
			setFont(profileFont);
			setForeground(Color.WHITE);
			
			// Display avatar icon.
			ImageIcon icon = new ImageIcon(this.getClass().getResource("/user.png"));
			Image scaledImage = icon.getImage().getScaledInstance(profileIconSize, profileIconSize, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledImage));
			setIconTextGap(8);
			
			// Background color.
			setUI((ButtonUI) BasicButtonUI.createUI(this));
			setBackground(Palette.profileHoverColor);
			
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
