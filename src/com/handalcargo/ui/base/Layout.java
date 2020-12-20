package com.handalcargo.ui.base;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.table.TableModel;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.components.Button;
import com.handalcargo.ui.components.IconButton;
import com.handalcargo.ui.components.Table;
import com.handalcargo.ui.components.ScrollPanel;

public abstract class Layout extends JPanel implements Updateable {

	protected abstract void onDelete(Object selected);
	protected abstract TableModel setTableModel(String filter);
	
	protected JPanel contentPanel;
	private CardLayout content;
	private JTable table;
	private Consumer<Object> setForm;
	
	protected void displayPage(String pageName) {
		content.show(contentPanel, pageName);
	}
	
	protected void setAddForm(JPanel addForm) {
		contentPanel.add(new ScrollPanel(addForm), "Add");
	}
	
	protected void setModifyForm(JPanel modifyForm) {
		contentPanel.add(new ScrollPanel(modifyForm), "Modify");
	}
	
	protected void setModifyFormContent(Consumer<Object> setForm) {
		this.setForm = setForm;
	}
	
	public Layout(String title) {
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout());
		
		// Title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(Styles.headerFont);
		titlePanel.add(titleLabel);
		
		// Content panel
		contentPanel = new JPanel();
		content = new CardLayout();
		contentPanel.setLayout(content);
		contentPanel.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Styles.headerColor), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10))
			);
		contentPanel.setBackground(Color.WHITE);
		add(contentPanel, BorderLayout.CENTER);
		
		// Form pages
		contentPanel.add(new Overview(), "Overview");
	}
	
	@Override 
	public void refresh() {
		displayPage("Overview");
		table.setModel(setTableModel(null));

		// Resize column widths
		if (table.getColumnModel().getColumnCount() > 8) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(Styles.tableColumnWidth);
			}
		}
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
			
			JButton searchButton = new IconButton("/search.png", Styles.blue, Styles.blueHover, 
					new Dimension(Styles.buttonSize, Styles.buttonSize), e -> onSearch(searchField.getText()));
			topPanel.add(searchButton);
			
			// Right panel.
			JPanel rightPanel = new JPanel();
			rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			rightPanel.setOpaque(false);
			add(rightPanel, BorderLayout.EAST);
			
			JButton addButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
					new Dimension(Styles.buttonSize, Styles.buttonSize), e -> displayPage("Add"));
			rightPanel.add(addButton);
			
			rightPanel.add(Box.createVerticalStrut(10));
			
			JButton modifyButton = new IconButton("/modify.png", Styles.yellow, Styles.yellowHover, 
					new Dimension(Styles.buttonSize, Styles.buttonSize), e -> onModifyButton());
			rightPanel.add(modifyButton);
			
			rightPanel.add(Box.createVerticalStrut(10));
			
			JButton deleteButton = new IconButton("/delete.png", Styles.red, Styles.redHover, 
					new Dimension(Styles.buttonSize, Styles.buttonSize), e -> onDeleteButton());
			rightPanel.add(deleteButton);
			
			// Content table.
			table = new Table();
			JScrollPane scrollPane = new ScrollPanel();
			scrollPane.setViewportView(table);
			table.setRowHeight(Styles.tableRowHeight);
			
			add(scrollPane, BorderLayout.CENTER);
		}
		
		private void onSearch(String searchText) {
			table.setModel(setTableModel(searchText));
		}
		
		private void onModifyButton() {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				setForm.accept(table.getValueAt(selectedRow, 0));
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
					refresh();
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
	
	public class FinishPanel extends JPanel {
		public FinishPanel(Consumer<ActionEvent> onSave) {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			setOpaque(false);
			add(new Button("Save", Styles.green, Styles.greenHover, new Dimension(100, 40), true, 
				e -> {
					onSave.accept(e);
					refresh();
				}
			));
							
			add(new Button("Cancel", Styles.red, Styles.redHover, new Dimension(100, 40), true, 
				e -> {
					displayPage("Overview");
					refresh();
				}
			));
		}
	}
}
