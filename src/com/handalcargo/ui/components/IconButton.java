package com.handalcargo.ui.components;

import java.util.function.Consumer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import com.handalcargo.ui.Styles;

public class IconButton extends JButton {
	
	public IconButton(String iconPath, Consumer<ActionEvent> function) {
		
		// Set icon
		ImageIcon icon = new ImageIcon(this.getClass().getResource(iconPath));
		Image scaledIcon = icon.getImage().getScaledInstance(Styles.buttonIconSize, Styles.buttonIconSize, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledIcon));
		
		Dimension size = new Dimension(Styles.buttonSize, Styles.buttonSize);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		// Background color
		setUI((ButtonUI) BasicButtonUI.createUI(this));
		setBackground(Styles.blue);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(true);
		setBorderPainted(false);
		setFocusPainted(false);
		
		// Hover effects
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				IconButton.this.setBackground(Styles.blueHover);
			}
			
			public void mouseExited(MouseEvent e) {
				IconButton.this.setBackground(Styles.blue);
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
