package com.handalcargo.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import com.handalcargo.ui.components.Header;
import com.handalcargo.ui.components.Sidebar;
import com.handalcargo.ui.pages.*;
import static com.handalcargo.core.Functions.promptExit;

public class Application extends JFrame {
	
	private static final Dimension defaultWindowSize = new Dimension(1280, 720);
	private static final Dimension minimumWindowSize = new Dimension(960, 540);
	
	private static Application instance = null;
	
	private Content content;
	
	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}
	
	public static void displayPage(String pageName) {
		instance.content.layout.show(instance.content, pageName);
		
		// Not the most efficient implementation...
		for (Component comp : instance.content.getComponents()) {
			if (comp.isVisible()) {
				if (comp instanceof Updateable) 
					((Updateable) comp).refresh();
			}
		}
	}
	
	private Application() {
		super("Handal Cargo - Enterprise System");
		setSize(defaultWindowSize);
		setMinimumSize(minimumWindowSize);
		// setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		// Close database connection and free memory on close.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				promptExit();
			}
		});
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		setIconImage(icon.getImage());
		
		JPanel header = new Header();
		JPanel sidebar = new Sidebar();
		content = new Content();
		
		JPanel notHeader = new JPanel(new BorderLayout());
		notHeader.add(sidebar, BorderLayout.WEST);
		notHeader.add(content, BorderLayout.CENTER);
		
		JPanel body = new JPanel(new BorderLayout());
		body.add(header, BorderLayout.NORTH);
		body.add(notHeader, BorderLayout.CENTER);
		
		add(body);
		setVisible(true);
	}
	
	private class Content extends JPanel {
		
		CardLayout layout;
		
		private Content() {
			layout = new CardLayout();
			setLayout(layout);
			
			// Initialize all the pages.
			JPanel pages[] = {
				new Dashboard(),
				new UserProfile(),
				new AirCargo(),
				new SeaFreight(),
				new InvoiceEntry(),
				new Payment(),
				new Customers(),
				new Staff(),
				new Accounts(),
				new Payroll(),
				new StaffGroups(),
				new CompanySetup(),
				new BackupAndRestore()
			};
			
			// Load them all into the card layout.
			for (JPanel page : pages) {
				add(page, page.getClass().getSimpleName());
			}
		}
	}
}
