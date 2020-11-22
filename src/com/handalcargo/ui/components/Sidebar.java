package com.handalcargo.ui.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import com.handalcargo.ui.Styles;
import com.handalcargo.ui.Application;
import static com.handalcargo.ui.Application.promptExit;

public class Sidebar extends JPanel {
	
	private static final Font
		buttonFont = new Font("Arial", Font.BOLD, 16),
		subButtonFont = new Font("Arial", Font.PLAIN, 14);
	
	private final int 
		width = 260,
		buttonHeight = 40,
		buttonIconSize = 16,
		subButtonIconSize = 12;

	private Insets
		buttonPadding = new Insets(0, 20, 0, 0),
		subButtonPadding = new Insets(0, 30, 0, 0);

	private ArrayList<CategoryButton> buttons;
	
	public Sidebar() {
		setBackground(Styles.sideBarColor);
		setPreferredSize(new Dimension(width, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Subcategory names
		// note: these names must match their corresponding page class names for navigation to work.
		String
			shipping[] = {"Sea Freight", "Air Cargo", "Invoice Entry", "Payment"},
			master[] = {"Customers", "Staff", "Accounts"},
			references[] = {"Container Groups", "Shippers", "Routes", "Handlers", "Planes", "Currencies", "Product Details", "Expeditions"},
			reports[] = {"Dashboard", "Payroll"},
			settings[] = {"Staff Groups", "Company Setup", "Backup And Restore"};
		
		LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
		
		map.put("Shipping", shipping);
		map.put("Master", master);
		map.put("References", references);
		map.put("Reports", reports);
		map.put("Settings", settings);
					
		buttons = new ArrayList<>();
		map.forEach((categoryName, subcategories) -> {
			CategoryButton button = new CategoryButton(categoryName, subcategories);
			buttons.add(button);
			add(button);
		});
		add(new CategoryButton("Log Out and Exit", null));
	}
	
	private class CategoryButton extends JPanel {
		
		private GridLayout layout;
		private Dimension size;
		private JToggleButton headerButton;
		private String[] subcategories;
		private JButton subButtons[];
		
		private CategoryButton(String text, String[] subcategories) {
			
			// Panel properties.
			setOpaque(false);
			setAlignmentX(Component.RIGHT_ALIGNMENT);
			
			this.subcategories = subcategories;
			
			layout = new GridLayout(1, 1);
			setLayout(layout);
			
			size = new Dimension(width, buttonHeight);
			setMinimumSize(size);
			setMaximumSize(size);
			
			// Button properties.
			headerButton = new JToggleButton();
			
			headerButton.setText(text);
			headerButton.setFont(buttonFont);
			headerButton.setForeground(Color.WHITE);
			
			// Button icon.
			try {
				String iconPath = String.format("/%s.png", text.toLowerCase().replaceAll("\\s+", ""));
				ImageIcon icon = new ImageIcon(this.getClass().getResource(iconPath));
				Image scaledIcon = icon.getImage().getScaledInstance(buttonIconSize, buttonIconSize, Image.SCALE_SMOOTH);
				headerButton.setIcon(new ImageIcon(scaledIcon));
				headerButton.setIconTextGap(10);
			}
			catch(Exception e) {
				System.out.println(text + " icon not found.");
			}
	
			// Button color.
			headerButton.setUI((ButtonUI) BasicButtonUI.createUI(this));
			headerButton.setBackground(Styles.sideBarButtonHoverColor);
			
			headerButton.setHorizontalAlignment(SwingConstants.LEFT);
			headerButton.setMargin(buttonPadding);
			
			headerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			headerButton.setContentAreaFilled(false);
			headerButton.setBorderPainted(false);
			headerButton.setFocusPainted(false);
			
			// Button hover effects.
			headerButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					headerButton.setContentAreaFilled(true);
				}
				
				public void mouseExited(MouseEvent e) {
					headerButton.setContentAreaFilled(false);
				}
			});
			
			// Initialize subcategory buttons.
			if (subcategories != null) {
				subButtons = new JButton[subcategories.length];
				int i = 0;
				for (String subcategory : subcategories) {
					JButton subButton = new JButton();
					
					subButton.setText(subcategory);
					subButton.setFont(subButtonFont);
					subButton.setForeground(Color.WHITE);
					
					// SubButton icon.
					ImageIcon subIcon = new ImageIcon(this.getClass().getResource("/check.png"));
					Image scaledSubIcon = subIcon.getImage().getScaledInstance(subButtonIconSize, subButtonIconSize, Image.SCALE_SMOOTH);
					subButton.setIcon(new ImageIcon(scaledSubIcon));
					subButton.setIconTextGap(10);
					
					// SubButton color.
					subButton.setUI((ButtonUI) BasicButtonUI.createUI(CategoryButton.this));
					subButton.setBackground(Styles.sideBarSubButtonColor);
					
					subButton.setHorizontalAlignment(SwingConstants.LEFT);
					subButton.setMargin(subButtonPadding);
					
					subButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					subButton.setBorderPainted(false);
					subButton.setFocusPainted(false);
					
					// SubButton hover effects.
					subButton.addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent e) {
							subButton.setBackground(Styles.sideBarSubButtonHoverColor);
						}
						
						public void mouseExited(MouseEvent e) {
							subButton.setBackground(Styles.sideBarSubButtonColor);
						}
					});
					
					// SubButton functionality.
					subButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// The class name of every page is the same but with no spaces.
							String pageClassName = subcategory.replaceAll("\\s+", "");
							Application.displayPage(pageClassName);
						}
					});
					
					subButtons[i] = subButton;
					i++;
				}
				
				// HeaderButton functionality.
				headerButton.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (headerButton.isSelected()) {
							buttons.forEach(button -> {
								if (button != CategoryButton.this) {
									button.deselect();
								}
							});
							expand();
						}
						else {
							shrink();
						}
						// Re-render the component.
						CategoryButton.this.revalidate();
						CategoryButton.this.repaint();
					}
				});
			}
			else {
				// Regular button click functionality.
				headerButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						promptExit();
					}
				});
			}
			
			add(headerButton);
		}
		
		private void expand() {
			// Expand size and number of rows.
			int items = 1 + subcategories.length;
			size.setSize(width, buttonHeight * items);
			layout.setRows(items);
			CategoryButton.this.setMaximumSize(size);
			CategoryButton.this.setLayout(layout);
			
			// Add each subcategory button.
			for (JButton subButton : subButtons) {
				CategoryButton.this.add(subButton);
			}
		}
		
		private void shrink() {
			// Shrink size and number of rows.
			size.setSize(width, buttonHeight);
			layout.setRows(1);
			CategoryButton.this.setMaximumSize(size);
			CategoryButton.this.setLayout(layout);
			
			// Remove each subcategory button.
			for (JButton subButton : subButtons) {
				CategoryButton.this.remove(subButton);
			}
		}
		
		// To be called externally.
		private void deselect() {
			headerButton.setSelected(false);
			shrink();
		}
	}
}
