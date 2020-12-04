package com.handalcargo.ui.components;

import java.util.function.Consumer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import com.handalcargo.ui.Styles;

public class IconButton extends JButton {
	
	public IconButton(String iconPath, Color color, Color hoverColor, Dimension size, Consumer<ActionEvent> function) {
		
		// Set icon
		ImageIcon icon = new ImageIcon(this.getClass().getResource(iconPath));
		Image scaledIcon = icon.getImage().getScaledInstance(Styles.buttonIconSize, Styles.buttonIconSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledIcon));
		
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		// Background color
		setUI((ButtonUI) BasicButtonUI.createUI(this));
		setBackground(color);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(true);
		setBorderPainted(false);
		setFocusPainted(false);
		
		// Hover effects
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				IconButton.this.setBackground(hoverColor);
			}
			
			public void mouseExited(MouseEvent e) {
				IconButton.this.setBackground(color);
			}
		});
		
		// Functionality
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				function.accept(e);
			}
		});
	}
}
