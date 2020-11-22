package com.handalcargo.ui.base;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.handalcargo.ui.Styles;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.IconButton;
import com.handalcargo.ui.components.Table;
import com.handalcargo.ui.components.ScrollPanel;

public abstract class Layout extends JPanel implements Updateable {

	protected abstract TableModel createTableModel();
	protected abstract JPanel createForm();
	protected abstract JPanel setForm(Object selected);
	protected abstract void onDelete(Object selected);
	
	protected JPanel contentPanel;
	private CardLayout content;
	private JTable table;
	
	protected void displayPage(String pageName) {
		content.show(contentPanel, pageName);
	}
	
	public Layout(String title) {
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
		contentPanel = new JPanel();
		content = new CardLayout();
		contentPanel.setLayout(content);
		contentPanel.setBorder(
			new CompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Styles.headerColor), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10))
			);
		contentPanel.setBackground(Color.WHITE);
		add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.add(new Overview(), "Overview");
		contentPanel.add(createForm(), "Add");
		contentPanel.add(createForm(), "Modify");
	}
	
	@Override 
	public void refresh() {
		table.setModel(createTableModel());
	}
	
	class Overview extends JPanel {
		
		private Overview() {
			setOpaque(false);
			setLayout(new BorderLayout());
			
			// Top panel.
			JPanel topPanel = new JPanel();
			topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			topPanel.setOpaque(false);
			topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			add(topPanel, BorderLayout.NORTH);
			
			JTextField searchField = new JTextField();
			searchField.setColumns(15);
			searchField.setFont(new Font("Arial", Font.PLAIN, 14));
			searchField.setMargin(new Insets(6, 6, 6, 6));
			topPanel.add(searchField);
			
			topPanel.add(Box.createHorizontalStrut(5));
			
			JButton searchButton = new IconButton("/search.png", e -> onSearch(searchField.getText()));
			topPanel.add(searchButton);
			
			// Right panel.
			JPanel rightPanel = new JPanel();
			rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			rightPanel.setOpaque(false);
			add(rightPanel, BorderLayout.EAST);
			
			Dimension buttonSize = new Dimension(Styles.buttonSize, Styles.buttonSize);
			
			JButton addButton = new Button("Add", Styles.green, Styles.greenHover, 
				buttonSize, true, Styles.buttonFont, e -> displayPage("Add"));
			rightPanel.add(addButton);
			
			rightPanel.add(Box.createVerticalStrut(10));
			
			JButton modifyButton = new Button("Modify", Styles.yellow, Styles.yellowHover, 
				buttonSize, true, Styles.buttonFont, e -> onModifyButton());
			rightPanel.add(modifyButton);
			
			rightPanel.add(Box.createVerticalStrut(10));
			
			JButton deleteButton = new Button("Delete", Styles.red, Styles.redHover, 
				buttonSize, true, Styles.buttonFont, e -> onDeleteButton());
			rightPanel.add(deleteButton);
			
			// Content table.
			table = new Table();
			JScrollPane scrollPane = new ScrollPanel();
			scrollPane.setViewportView(table);
			table.setRowHeight(Styles.tableRowHeight);
			
			JTableHeader header = table.getTableHeader();
			header.setOpaque(false);
			header.setBackground(Styles.headerColor);
			header.setForeground(Color.WHITE);
			
			add(scrollPane, BorderLayout.CENTER);
		}
		
		private void onSearch(String searchText) {
			System.out.println(searchText);
		}
		
		private void onModifyButton() {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				setForm(table.getValueAt(selectedRow, 0));
				displayPage("Modify");
			}
			else {
				JOptionPane.showMessageDialog(this, 
					"Select an entry to modify first!",
					"Modify Record",
					JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void onDeleteButton() {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int confirm = JOptionPane.showConfirmDialog(this, 
					String.format(
						"Are you sure you want to delete %s's record?", 
						table.getValueAt(selectedRow, 0)),
					"Delete Record",
					JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					onDelete(table.getValueAt(selectedRow, 0));
					JOptionPane.showMessageDialog(this, "Entry deleted successfully.");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, 
					"Select an entry to delete first!",
					"Delete Record",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
