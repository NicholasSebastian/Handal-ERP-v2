package com.handalcargo.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.handalcargo.ui.Styles;

public class Table extends JTable {

	public Table() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectionBackground(Styles.tableSelectColor);
		setSelectionForeground(Color.WHITE);
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		setFillsViewportHeight(true);
		
		// Remove cell highlight border.
		setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column
		    ) {
		    	hasFocus = false;
		        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		    }
		});
		
		// Disable table dragging.
		getTableHeader().setReorderingAllowed(false);
		
		// Remove table header borders and set color.
		getTableHeader().setDefaultRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus, int row, int column
			) {
				JPanel returnComp = new JPanel();
				returnComp.setBackground(Styles.tableHeaderColor);
				JLabel text = new JLabel(value.toString());
				text.setForeground(Color.WHITE);
				returnComp.add(text);
				return returnComp;
			}
		});
	}
	
	@Override	// Allows deselecting of selected rows.
	public void changeSelection(
		int rowIndex, int columnIndex, boolean toggle, boolean extend
    ) {
        super.changeSelection(rowIndex, columnIndex, true, false);
	}
	
	@Override	// Disable cell editing.
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override	// Alternating row colors.
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component returnComp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = Styles.tableAlternateColor;
        Color whiteColor = Color.WHITE;
        if (!returnComp.getBackground().equals(getSelectionBackground())){
            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
            returnComp.setBackground(bg);
            bg = null;
        }
        return returnComp;
	}
}
