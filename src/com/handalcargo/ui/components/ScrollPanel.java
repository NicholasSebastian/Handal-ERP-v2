package com.handalcargo.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import com.handalcargo.ui.Styles;

public class ScrollPanel extends JScrollPane {

	public ScrollPanel() {
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getHorizontalScrollBar().setBackground(Color.WHITE);
		getVerticalScrollBar().setBackground(Color.WHITE);
		getVerticalScrollBar().setUnitIncrement(16);
		
		getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
	        protected JButton createDecreaseButton(int orientation) {
				return new InvisibleButton();
	        }
	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	        	return new InvisibleButton();
	        }
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Styles.scrollBarColor;
			}
		});
		
		getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
	        protected JButton createDecreaseButton(int orientation) {
				return new InvisibleButton();
	        }
	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	        	return new InvisibleButton();
	        }
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Styles.scrollBarColor;
			}
		});
	}
	
	public ScrollPanel(Component content) {
		this();
		setViewportView(content);
	}
	
	class InvisibleButton extends JButton {
		public InvisibleButton() {
			setPreferredSize(new Dimension(0, 0));
			setMinimumSize(new Dimension(0, 0));
			setMaximumSize(new Dimension(0, 0));
		}
	}
}
