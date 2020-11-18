package com.handalcargo.ui.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import java.util.function.Consumer;

public class Button extends JButton {
	
	public Button(
			String text, Color backgroundColor, Color hoverColor, 
			Dimension size, Consumer<ActionEvent> function
	) {
		super(text);
		
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		// Background color
		setUI((ButtonUI) BasicButtonUI.createUI(this));
		setBackground(backgroundColor);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(true);
		setBorderPainted(false);
		setFocusPainted(false);
		
		// Hover effects
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Button.this.setBackground(hoverColor);
			}
			
			public void mouseExited(MouseEvent e) {
				Button.this.setBackground(backgroundColor);
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
	
	public Button(
			String text, Color backgroundColor, Color hoverColor, 
			Dimension size, boolean whiteText, Consumer<ActionEvent> function
	) {
		this(text, backgroundColor, hoverColor, size, function);
		setForeground(whiteText ? Color.WHITE : Color.BLACK);
	}
	
	public Button(
			String text, Color backgroundColor, Color hoverColor, 
			Dimension size, Font font, Consumer<ActionEvent> function
	) {
		this(text, backgroundColor, hoverColor, size, function);
		setFont(font);
	}
	
	public Button(
			String text, Color backgroundColor, Color hoverColor, 
			Dimension size, boolean whiteText, Font font, 
			Consumer<ActionEvent> function
	) {
		this(text, backgroundColor, hoverColor, size, whiteText, function);
		setFont(font);
	}
}
