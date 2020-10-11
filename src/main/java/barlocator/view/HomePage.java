package main.java.barlocator.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class HomePage extends JFrame {
	//Todo - add eventListeners to buttons.
	private JPanel viewHomePage;
	private JPanel homePageJPanel;
	private JPanel headerJPanel;
	private JLabel headerJLabel;
	private JPanel footerJPanel;
	private JLabel rightsJLabel;
	private JLabel heading2JLabel;
	private JButton clientPageJButton;
	private JButton adminPageJButton;
	private Listener listener;
	private JLabel errorJLabel;

	public interface Listener {
		void clientPage(boolean isAdmin);
		void adminPage(boolean isAdmin);
	}

	public HomePage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 672);
		viewHomePage = new JPanel();
		viewHomePage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(viewHomePage);
		viewHomePage.setLayout(null);
		
		homePageJPanel = new JPanel();
		homePageJPanel.setBackground(new Color(0, 191, 255));
		homePageJPanel.setBounds(-20, 0, 954, 648);
		viewHomePage.add(homePageJPanel);
		homePageJPanel.setLayout(null);
		
		headerJPanel = new JPanel();
		headerJPanel.setBackground(new Color(220, 220, 220));
		headerJPanel.setBounds(10, 0, 944, 133);
		homePageJPanel.add(headerJPanel);
		
		headerJPanel.setLayout(null);
		headerJLabel = new JLabel("Bar Locator");
		headerJLabel.setIcon(new ImageIcon(HomePage.class.getResource("/main/resources/images/bar.png")));
		headerJLabel.setBounds(359, 44, 203, 64);
		headerJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerJLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 23));
		headerJPanel.add(headerJLabel);
		
		footerJPanel = new JPanel();
		footerJPanel.setBackground(new Color(220, 220, 220));
		footerJPanel.setBounds(10, 515, 944, 133);
		homePageJPanel.add(footerJPanel);
		footerJPanel.setLayout(null);
		
		rightsJLabel = new JLabel("\u00A9 All rights reserved to Bar Locator Inc.");
		rightsJLabel.setBounds(330, 59, 278, 18);
		rightsJLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		footerJPanel.add(rightsJLabel);
		
		heading2JLabel = new JLabel("Please choose a login type");
		heading2JLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heading2JLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 23));
		heading2JLabel.setBounds(320, 210, 311, 29);
		homePageJPanel.add(heading2JLabel);
		
		clientPageJButton = new JButton("Client");
		clientPageJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 23));
		clientPageJButton.setBounds(420, 284, 111, 37);
		clientPageJButton.addActionListener(e -> listener.clientPage(false));
		homePageJPanel.add(clientPageJButton);
		
		adminPageJButton = new JButton("Admin");
		adminPageJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 23));
		adminPageJButton.setBounds(420, 377, 111, 37);
		adminPageJButton.addActionListener(e -> listener.adminPage(true));
		homePageJPanel.add(adminPageJButton);
		
		errorJLabel = new JLabel("Sorry, an error has occurred");
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(new Color(255, 0, 0));
		errorJLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		errorJLabel.setBounds(352, 447, 246, 24);
		homePageJPanel.add(errorJLabel);
	}

	public JButton getClientPageJButton() {
		return clientPageJButton;
	}

	public JButton getAdminPageJButton() {
		return adminPageJButton;
	}

	public JLabel getErrorJLabel() {
		return errorJLabel;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
