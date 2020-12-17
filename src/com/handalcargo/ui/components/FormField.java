package com.handalcargo.ui.components;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import com.handalcargo.ui.Styles;

public class FormField extends JTextField {
	
	public FormField() {
		setFont(Styles.fieldFont);
		setMargin(new Insets(6, 6, 6, 6));
	}
	
	public FormField(String text) {
		this();
		setText(text);
	}
}
