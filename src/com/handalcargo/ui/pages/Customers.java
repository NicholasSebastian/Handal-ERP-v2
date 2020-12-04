package com.handalcargo.ui.pages;

import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.handalcargo.core.Database;
import com.handalcargo.ui.Styles;
import com.handalcargo.ui.base.Layout;
import com.handalcargo.ui.components.DatePicker;
import com.handalcargo.ui.components.FormField;
import com.handalcargo.ui.components.IconButton;
import com.handalcargo.ui.components.ScrollPanel;
import com.handalcargo.ui.components.SliderButton;
import com.handalcargo.ui.components.Table;

public class Customers extends Layout {
	
	private JTextField codeField;
	private JTextField nameField;
	private JTextField companyField;
	private JTextArea address1Field;
	private JTextField city1Field;
	private JTextField postalcode1Field;
	private JTextArea address2Field;
	private JTextField city2Field;
	private JTextField postalcode2Field;
	private JTextField officephone1Field;
	private JTextField officephone2Field;
	private JTextField mobilephone1Field;
	private JTextField mobilephone2Field;
	private JTextField homephoneField;
	private JTextField faxField;
	private JTextField emailField;
	private JTextField contact1Field;
	private JTextField contact2Field;
	private JTextField sizedescField;
	private JTextField courierdescField;
	private JTextArea othersField;
	private DatePicker dateaddedField;
	private SliderButton statusField;
	private JTextField markingField;
	private JButton courierButton;
	private JButton markingButton;
	private JTable markingTable;
	private JButton historyButton;
	private JButton deleteButton;
	private JTextField packetdescField;
	private JButton packetButton;
	private JComboBox<String> byField;
	private JTextField rutedescField;
	private JButton ruteButton;
	private JTextField priceField;
	private JTable packetTable;
	private JButton packetdeleteButton;
	
	public Customers() {
		super("Customers");
	}
	
	@Override
	protected TableModel setTableModel(String filter) {
		
		final String[] columns = {"Customer ID", "Name", "Marking", "Company", "Address 1", "Kota 1", "Kode Pos 1", "Contact Person 1", "Office Phone 1", "Address 2", "Kota 2", "Kode Pos 2", "Contact Person 2", "Office Phone 2", "Home Phone", "Email", "Status"};
		ArrayList<String[]> data = new ArrayList<>();
		
		try {
			String query = "SELECT customers.`customerid`, `customername`, `marking`, `company`, `address1`, `city1`, `postalcode1`, `contactperson1`, `officephone1`, `address2`, `city2`, `postalcode2`, `contactperson2`, `officephone2`, `homephone`, `email`, `status` FROM customers LEFT JOIN customermarking ON customers.`customerid` = customermarking.`customerid`";
			if (filter != null && filter.length() > 0) 
				query += String.format(" WHERE `customerid` LIKE '%%%s%%'", filter);
			
			ResultSet results = Database.query(query);
			while (results.next()) {
				data.add(new String[] {
					results.getString(1), 
					results.getString(2),
					results.getString(3),
					results.getString(4),
					results.getString(5),
					results.getString(6),
					results.getString(7),
					results.getString(8),
					results.getString(9),
					results.getString(10),
					results.getString(11),
					results.getString(12),
					results.getString(13),
					results.getString(14),
					results.getString(15),
					results.getString(16),
					results.getString(17)
				});
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String dataArray[][] = new String[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			dataArray[i] = data.get(i);
		}
		
		return new DefaultTableModel(dataArray, columns);
	}
	
	@Override
	protected JPanel createForm() {
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Color.WHITE);
		formPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);
		
		// Labels
		c.gridx = 0;	c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		c.gridy++;	formPanel.add(new JLabel("Code"), c);
		c.gridy++;	formPanel.add(new JLabel("Name"), c);
		c.gridy++;	formPanel.add(new JLabel("Company"), c);
		c.gridy++;	formPanel.add(new JLabel("Address 1"), c);
		c.gridy++;	formPanel.add(new JLabel("City"), c);
		c.gridy++;	formPanel.add(new JLabel("Postal Code"), c);
		c.gridy++;	formPanel.add(new JLabel("Address 2"), c);
		c.gridy++;	formPanel.add(new JLabel("City"), c);
		c.gridy++;	formPanel.add(new JLabel("Postal Code"), c);
		c.gridy++;	formPanel.add(new JLabel("Office Phone 1"), c);
		c.gridy++;	formPanel.add(new JLabel("Office Phone 2"), c);
		c.gridy++;	formPanel.add(new JLabel("Mobile Phone 1"), c);
		c.gridy++;	formPanel.add(new JLabel("Mobile Phone 2"), c);
		c.gridy++;	formPanel.add(new JLabel("Home Phone"), c);
		c.gridy++;	formPanel.add(new JLabel("Fax"), c);
		c.gridy++;	formPanel.add(new JLabel("Email"), c);
		c.gridy++;	formPanel.add(new JLabel("Contact Person 1"), c);
		c.gridy++;	formPanel.add(new JLabel("Contact Person 2"), c);
		c.gridy++;	formPanel.add(new JLabel("Size Description"), c);
		c.gridy++;	formPanel.add(new JLabel("Courier Description"), c);
		c.gridy++;	formPanel.add(new JLabel("Others"), c);
		c.gridy++;	formPanel.add(new JLabel("Date Added"), c);
		c.gridy++;	formPanel.add(new JLabel("Status"), c);
		c.gridy++;	formPanel.add(new JLabel("Marking"), c);
		
		// Fields
		c.gridx = 1;	c.weightx = 1.0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;
		
		c.gridy++;	codeField = new FormField();	
					formPanel.add(codeField, c);
		c.gridy++;	nameField = new FormField();	
					formPanel.add(nameField, c);
		c.gridy++;	companyField = new FormField();	
					formPanel.add(companyField, c);
		c.gridy++;	address1Field = new JTextArea();	
					formPanel.add(address1Field, c);
		c.gridy++;	city1Field = new FormField();	
					formPanel.add(city1Field, c);
		c.gridy++;	postalcode1Field = new FormField();	
					formPanel.add(postalcode1Field, c);
		c.gridy++;	address2Field = new JTextArea();	
					formPanel.add(address2Field, c);
		c.gridy++;	city2Field = new FormField();	
					formPanel.add(city2Field, c);
		c.gridy++;	postalcode2Field = new FormField();	
					formPanel.add(postalcode2Field, c);
		c.gridy++;	officephone1Field = new FormField();	
					formPanel.add(officephone1Field, c);
		c.gridy++;	officephone2Field = new FormField();	
					formPanel.add(officephone2Field, c);
		c.gridy++;	mobilephone1Field = new FormField();	
					formPanel.add(mobilephone1Field, c);
		c.gridy++;	mobilephone2Field = new FormField();	
					formPanel.add(mobilephone2Field, c);
		c.gridy++;	homephoneField = new FormField();	
					formPanel.add(homephoneField, c);
		c.gridy++;	faxField = new FormField();	
					formPanel.add(faxField, c);
		c.gridy++;	emailField = new FormField();	
					formPanel.add(emailField, c);
		c.gridy++;	contact1Field = new FormField();	
					formPanel.add(contact1Field, c);
		c.gridy++;	contact2Field = new FormField();	
					formPanel.add(contact2Field, c);
		c.gridy++;	sizedescField = new FormField();	
					formPanel.add(sizedescField, c);
		c.gridy++;	courierdescField = new FormField();	
					formPanel.add(courierdescField, c);
		c.gridx = 2;	c.weightx = 0.0;
					courierButton = new IconButton("/add.png", Styles.green, Styles.greenHover, 
						new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					formPanel.add(courierButton, c);
		c.gridx = 1;	c.weightx = 1.0;
		c.gridy++;	othersField = new JTextArea();	
					formPanel.add(othersField, c);
		c.gridy++;	dateaddedField = new DatePicker();	
					formPanel.add(dateaddedField, c);
		c.gridy++;	statusField = new SliderButton();	
					formPanel.add(statusField, c);
		c.gridy++;	markingField = new FormField();	
					formPanel.add(markingField, c);
		c.gridx = 2;	c.weightx = 0.0;
					markingButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
						new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					formPanel.add(markingButton, c);
		c.gridx = 0;    c.weightx = 1.0;	c.gridwidth = 3;
		c.gridy++;
					markingTable = new Table();
					markingTable.setModel(new DefaultTableModel(new String[] {"Markings"}, 0));
					markingTable.setRowHeight(Styles.tableRowHeight);

					JScrollPane scrollPanel = new ScrollPanel();
					scrollPanel.setViewportView(markingTable);
					scrollPanel.setPreferredSize(new Dimension(0, Styles.innerTableHeight));
					
					formPanel.add(scrollPanel, c);
		c.gridy++;
					JPanel markingButtons = new JPanel();
					markingButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
					markingButtons.setOpaque(false);
					formPanel.add(markingButtons, c);
		
					historyButton = new IconButton("/history.png", Styles.yellow, Styles.yellowHover, 
						new Dimension(100, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					historyButton.setText("History");
					markingButtons.add(historyButton);
					
					deleteButton = new IconButton("/delete.png", Styles.red, Styles.redHover,
							new Dimension(100, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					deleteButton.setText("Delete");
					markingButtons.add(deleteButton);
		c.gridy++;
		c.gridx = 0;
					formPanel.add(new JLabel("Packet Description"), c);
		c.gridx = 1;
					packetdescField = new FormField();	
					formPanel.add(packetdescField, c);
		c.gridx = 2;	c.weightx = 0.0;	
					packetButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
						new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					formPanel.add(packetButton, c);
		c.weightx = 1.0;
		c.gridy++;
		c.gridx = 0;
					formPanel.add(new JLabel("By"), c);
		c.gridx = 1;
					byField = new JComboBox<String>(new String[] {"Air", "Sea"});
					formPanel.add(byField, c);
		c.gridy++;
		c.gridx = 0;
					formPanel.add(new JLabel("Route"), c);
		c.gridx = 1;
					rutedescField = new FormField();	
					formPanel.add(rutedescField, c);
		c.gridx = 2;	c.weightx = 0.0;	
					ruteButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
						new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
							System.out.println("clicked on it");
						});
					formPanel.add(ruteButton, c);
		c.weightx = 1.0;
		c.gridy++;
		c.gridx = 0;
					formPanel.add(new JLabel("Price"), c);
		c.gridx = 1;
					priceField = new FormField();	
					formPanel.add(priceField, c);			
		c.gridy++;
		c.gridx = 0;	c.gridwidth = 3;
					packetTable = new Table();
					packetTable.setModel(new DefaultTableModel(new String[] {"Date", "Packet Description", "By", "Route", "Price", "Final Price", "User"}, 0));
					packetTable.setRowHeight(Styles.tableRowHeight);

					JScrollPane packetscrollPanel = new ScrollPanel();
					packetscrollPanel.setViewportView(packetTable);
					packetscrollPanel.setPreferredSize(new Dimension(0, Styles.innerTableHeight));
		
					formPanel.add(packetscrollPanel, c);
		c.gridy++;
					JPanel packetButtons = new JPanel();
					packetButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
					packetButtons.setOpaque(false);
					formPanel.add(packetButtons, c);

					packetdeleteButton = new IconButton("/delete.png", Styles.red, Styles.redHover, 
							new Dimension(100, Styles.buttonSize), e -> {
								System.out.println("clicked on it");
							});
					packetdeleteButton.setText("Delete");
					packetButtons.add(packetdeleteButton);
					
		return formPanel;
	}
	
	@Override
	protected void setForm(Object selected) {
		
	}
	
	@Override
	public void onAdd() {
		
	}
	
	@Override
	public void onModify() {
		
	}
	
	@Override
	public void onDelete(Object selected) {
		
	}
}
