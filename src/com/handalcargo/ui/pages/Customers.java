package com.handalcargo.ui.pages;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
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
	
	public Customers() {
		super("Customers");

		Form addForm = new Form();
		ModifyForm modifyForm = new ModifyForm();
		
		setAddForm(addForm);
		setModifyForm(modifyForm);
		setModifyFormContent(modifyForm::setForm);
	}
	
	@Override
	public void onDelete(Object selected) {
		Database.update("DELETE FROM customers WHERE customerid = ?", statement -> {
			try {
				statement.setString(1, selected.toString());
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		});
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
	
	class Form extends JPanel {
		
		protected JTextField codeField;
		protected JTextField nameField;
		protected JTextField companyField;
		protected JTextArea address1Field;
		protected JTextField city1Field;
		protected JTextField postalcode1Field;
		protected JTextArea address2Field;
		protected JTextField city2Field;
		protected JTextField postalcode2Field;
		protected JTextField officephone1Field;
		protected JTextField officephone2Field;
		protected JTextField mobilephone1Field;
		protected JTextField mobilephone2Field;
		protected JTextField homephoneField;
		protected JTextField faxField;
		protected JTextField emailField;
		protected JTextField contact1Field;
		protected JTextField contact2Field;
		protected JTextField sizedescField;
		protected JTextField courierdescField;
		protected JTextArea othersField;
		protected DatePicker dateaddedField;
		protected SliderButton statusField;
		protected JTextField markingField;
		protected JButton courierButton;
		protected JButton markingButton;
		protected JTable markingTable;
		protected JButton historyButton;
		protected JButton deleteButton;
		protected JTextField packetdescField;
		protected JButton packetButton;
		protected JComboBox<String> byField;
		protected JTextField rutedescField;
		protected JButton ruteButton;
		protected JTextField priceField;
		protected JTable packetTable;
		protected JButton packetdeleteButton;
		
		public Form() {
			setBackground(Color.WHITE);
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 8, 5, 8);
			
			// Labels
			c.gridx = 0;	c.gridy = 0;
			c.anchor = GridBagConstraints.LINE_END;
			
			c.gridy++;	add(new JLabel("Code"), c);
			c.gridy++;	add(new JLabel("Name"), c);
			c.gridy++;	add(new JLabel("Company"), c);
			c.gridy++;	add(new JLabel("Address 1"), c);
			c.gridy++;	add(new JLabel("City"), c);
			c.gridy++;	add(new JLabel("Postal Code"), c);
			c.gridy++;	add(new JLabel("Address 2"), c);
			c.gridy++;	add(new JLabel("City"), c);
			c.gridy++;	add(new JLabel("Postal Code"), c);
			c.gridy++;	add(new JLabel("Office Phone 1"), c);
			c.gridy++;	add(new JLabel("Office Phone 2"), c);
			c.gridy++;	add(new JLabel("Mobile Phone 1"), c);
			c.gridy++;	add(new JLabel("Mobile Phone 2"), c);
			c.gridy++;	add(new JLabel("Home Phone"), c);
			c.gridy++;	add(new JLabel("Fax"), c);
			c.gridy++;	add(new JLabel("Email"), c);
			c.gridy++;	add(new JLabel("Contact Person 1"), c);
			c.gridy++;	add(new JLabel("Contact Person 2"), c);
			c.gridy++;	add(new JLabel("Size Description"), c);
			c.gridy++;	add(new JLabel("Courier Description"), c);
			c.gridy++;	add(new JLabel("Others"), c);
			c.gridy++;	add(new JLabel("Date Added"), c);
			c.gridy++;	add(new JLabel("Status"), c);
			c.gridy++;	add(new JLabel("Marking"), c);
			c.gridy = 27; add(new JLabel("Packet Description"), c);
			c.gridy = 28; add(new JLabel("By"), c);
			c.gridy = 29; add(new JLabel("Route"), c);
			c.gridy = 30; add(new JLabel("Price"), c);

			
			// Fields
			c.gridx = 1;	c.weightx = 1.0;	c.gridwidth = 2;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridy = 0;
			
			c.gridy++;	codeField = new FormField();	
						add(codeField, c);
			c.gridy++;	nameField = new FormField();	
						add(nameField, c);
			c.gridy++;	companyField = new FormField();	
						add(companyField, c);
			c.gridy++;	address1Field = new JTextArea();	
						add(address1Field, c);
			c.gridy++;	city1Field = new FormField();	
						add(city1Field, c);
			c.gridy++;	postalcode1Field = new FormField();	
						add(postalcode1Field, c);
			c.gridy++;	address2Field = new JTextArea();	
						add(address2Field, c);
			c.gridy++;	city2Field = new FormField();	
						add(city2Field, c);
			c.gridy++;	postalcode2Field = new FormField();	
						add(postalcode2Field, c);
			c.gridy++;	officephone1Field = new FormField();	
						add(officephone1Field, c);
			c.gridy++;	officephone2Field = new FormField();	
						add(officephone2Field, c);
			c.gridy++;	mobilephone1Field = new FormField();	
						add(mobilephone1Field, c);
			c.gridy++;	mobilephone2Field = new FormField();	
						add(mobilephone2Field, c);
			c.gridy++;	homephoneField = new FormField();	
						add(homephoneField, c);
			c.gridy++;	faxField = new FormField();	
						add(faxField, c);
			c.gridy++;	emailField = new FormField();	
						add(emailField, c);
			c.gridy++;	contact1Field = new FormField();	
						add(contact1Field, c);
			c.gridy++;	contact2Field = new FormField();	
						add(contact2Field, c);
			c.gridy++;	sizedescField = new FormField();	
						add(sizedescField, c);
			c.gridwidth = 1;
			c.gridy++;	courierdescField = new FormField();	
						add(courierdescField, c);
			c.gridx = 2;	c.weightx = 0.0;
						courierButton = new IconButton("/add.png", Styles.green, Styles.greenHover, 
							new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
								System.out.println("clicked on it");
							});
						add(courierButton, c);
			c.gridx = 1;	c.weightx = 1.0;
			c.gridy++;	othersField = new JTextArea();	
						add(othersField, c);
			c.gridy++;	dateaddedField = new DatePicker();	
						add(dateaddedField, c);
			c.gridy++;	statusField = new SliderButton();	
						add(statusField, c);
			c.gridy++;	markingField = new FormField();	
						add(markingField, c);
			c.gridx = 2;	c.weightx = 0.0;
						markingButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
							new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
								System.out.println("clicked on it");
							});
						add(markingButton, c);
			c.gridx = 1;    c.weightx = 1.0;	c.gridwidth = 2;
			c.gridy++;
						markingTable = new Table();
						markingTable.setModel(new DefaultTableModel(new String[] {"Markings"}, 0));
						markingTable.setRowHeight(Styles.tableRowHeight);

						JScrollPane scrollPanel = new ScrollPanel();
						scrollPanel.setViewportView(markingTable);
						scrollPanel.setPreferredSize(new Dimension(0, Styles.innerTableHeight));
						
						add(scrollPanel, c);
			c.gridy++;	c.gridwidth = 1;
						JPanel markingButtons = new JPanel();
						markingButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
						markingButtons.setOpaque(false);
						add(markingButtons, c);
			
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
			c.gridx = 1;
			c.gridy++;
						packetdescField = new FormField();	
						add(packetdescField, c);
			c.gridx = 2;	c.weightx = 0.0;	
						packetButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
							new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
								System.out.println("clicked on it");
							});
						add(packetButton, c);
			c.gridx = 1;	c.weightx = 1.0;	c.gridwidth = 2;
			c.gridy++;
						byField = new JComboBox<String>(new String[] {"Air", "Sea"});
						add(byField, c);
			c.gridy++;	c.gridwidth = 1;
						rutedescField = new FormField();	
						add(rutedescField, c);
			c.gridx = 2;	c.weightx = 0.0;	
						ruteButton = new IconButton("/add.png", Styles.green, Styles.greenHover,
							new Dimension(Styles.buttonSize, Styles.buttonSize), e -> {
								System.out.println("clicked on it");
							});
						add(ruteButton, c);
			c.gridx = 1;	c.weightx = 1.0;	c.gridwidth = 2;
			c.gridy++;
						priceField = new FormField();	
						add(priceField, c);			
			c.gridy++;
						packetTable = new Table();
						packetTable.setModel(new DefaultTableModel(new String[] {"Date", "Packet Description", "By", "Route", "Price", "Final Price", "User"}, 0));
						packetTable.setRowHeight(Styles.tableRowHeight);

						JScrollPane packetscrollPanel = new ScrollPanel();
						packetscrollPanel.setViewportView(packetTable);
						packetscrollPanel.setPreferredSize(new Dimension(0, Styles.innerTableHeight));
			
						add(packetscrollPanel, c);

			c.gridwidth = 1;
			c.gridy++;
						JPanel packetButtons = new JPanel();
						packetButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
						packetButtons.setOpaque(false);
						add(packetButtons, c);

						packetdeleteButton = new IconButton("/delete.png", Styles.red, Styles.redHover, 
								new Dimension(100, Styles.buttonSize), e -> {
									System.out.println("clicked on it");
								});
						packetdeleteButton.setText("Delete");
						packetButtons.add(packetdeleteButton);
						
			JPanel finishPanel = new FinishPanel(e -> onSave());
			c.gridx = 1;	c.gridwidth = 2;
			c.gridy++;	add(finishPanel, c);
		}
						
		public void onSave() {
			Database.update("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
			statement -> {
			try {
				Database.setNumber(statement, 1, Types.INTEGER, codeField.getText());
				statement.setString(2, nameField.getText());
				statement.setString(3, companyField.getText());
				statement.setString(4, address1Field.getText());
				statement.setString(5, city1Field.getText());
				Database.setNumber(statement, 6, Types.INTEGER, postalcode1Field.getText());
				statement.setString(7, address2Field.getText());
				statement.setString(8, city2Field.getText());
				Database.setNumber(statement, 9, Types.INTEGER, postalcode2Field.getText());
				statement.setString(10, officephone1Field.getText());
				statement.setString(11, officephone2Field.getText());
				statement.setString(12, mobilephone1Field.getText());
				statement.setString(13, mobilephone2Field.getText());
				statement.setString(14, homephoneField.getText());
				statement.setString(15, faxField.getText());
				statement.setString(16, emailField.getText());
				statement.setString(17, contact1Field.getText());
				statement.setString(18, contact2Field.getText());
				statement.setString(19, sizedescField.getText());
				statement.setString(20, courierdescField.getText());
				statement.setString(21, othersField.getText());
				statement.setDate(22, dateaddedField.getDate());
				statement.setBoolean(23, statusField.isSelected());
			} 
			catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		});
			System.out.println("Record Added");
		}
	}
	
	class ModifyForm extends Form {
		
		private Object selected;
		
		public void setForm(Object selected) {
			this.selected = selected;
			codeField.setEditable(false);
			
			ResultSet results = Database.query(String.format("SELECT * FROM customers WHERE `customerid`='%s'", selected));
			try {
				results.next();
				codeField.setText(results.getString(1));
				nameField.setText(results.getString(2));
				companyField.setText(results.getString(3));
				address1Field.setText(results.getString(4));
				city1Field.setText(results.getString(5));
				postalcode1Field.setText(results.getString(6));
				address2Field.setText(results.getString(7));
				city2Field.setText(results.getString(8));
				postalcode2Field.setText(results.getString(9));
				officephone1Field.setText(results.getString(10));
				officephone2Field.setText(results.getString(11));
				mobilephone1Field.setText(results.getString(12));
				mobilephone2Field.setText(results.getString(13));
				homephoneField.setText(results.getString(14));
				faxField.setText(results.getString(15));
				emailField.setText(results.getString(16));
				contact1Field.setText(results.getString(17));
				contact2Field.setText(results.getString(18));
				sizedescField.setText(results.getString(19));
				courierdescField.setText(results.getString(20)); 
				othersField.setText(results.getString(21));
				dateaddedField.setDate(results.getDate(22)); 
				statusField.setSelected(results.getBoolean(23));
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void onSave() {
			Database.update("UPDATE customers SET customername = ?, company = ?, address1 = ?, city1 = ?, postalcode1 = ?, address2 = ?, city2 = ?, postalcode2 = ?, officephone1 = ?, officephone2 = ?, mobilephone1= ?, mobilephone2 = ?, homephone = ?, fax = ?, email = ?, contactperson1 = ?, contactperson2 = ?, sizedesc = ?, courierdesc = ?, others = ?, dateadded = ?, status = ? WHERE customerid = ? ", 
				statement -> {
				try {
					statement.setString(1, nameField.getText());
					statement.setString(2, companyField.getText());
					statement.setString(3, address1Field.getText());
					statement.setString(4, city1Field.getText());
					statement.setInt(5, Integer.valueOf(postalcode1Field.getText()));
					statement.setString(6, address2Field.getText());
					statement.setString(7, city2Field.getText());
					statement.setInt(8, Integer.valueOf(postalcode2Field.getText()));
					statement.setString(9, officephone1Field.getText());
					statement.setString(10, officephone2Field.getText());
					statement.setString(11, mobilephone1Field.getText());
					statement.setString(12, mobilephone2Field.getText());
					statement.setString(13, homephoneField.getText());
					statement.setString(14, faxField.getText());
					statement.setString(15, emailField.getText());
					statement.setString(16, contact1Field.getText());
					statement.setString(17, contact2Field.getText());
					statement.setString(18, sizedescField.getText());
					statement.setString(19, courierdescField.getText());
					statement.setString(20, othersField.getText());
					statement.setDate(21, dateaddedField.getDate());
					statement.setBoolean(22, statusField.isSelected());
					statement.setInt(23, Integer.valueOf(selected.toString()));
				} 
				catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			});
			System.out.println("Record Changed");
		}
	}
}

