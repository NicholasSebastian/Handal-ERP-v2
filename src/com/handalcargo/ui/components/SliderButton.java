package com.handalcargo.ui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;

public class SliderButton extends JPanel {
	
	private static final Dimension buttonSize = new Dimension(40, 25);
	
	private TheActualButton button;

	public SliderButton() {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		button = new TheActualButton();
		add(button, BorderLayout.WEST);
	}
	
	public boolean isSelected() {
		return button.isSelected();
	}
	
	public void setSelected(boolean state) {
		button.setSelected(state);
	}
	
	class TheActualButton extends JToggleButton {
		public TheActualButton() {
			setContentAreaFilled(false);
			setBorderPainted(false);
			
			setSize(buttonSize);
			setPreferredSize(buttonSize);
			setMaximumSize(buttonSize);
			
			ImageIcon offIcon = new ImageIcon(this.getClass().getResource("/togglebutton_off.png"));
			Image scaledOffIcon = offIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
			ImageIcon onIcon = new ImageIcon(this.getClass().getResource("/togglebutton_on.png"));
			Image scaledOnIcon = onIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledOffIcon));
			setSelectedIcon(new ImageIcon(scaledOnIcon));
		}
	}
}