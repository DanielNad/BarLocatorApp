package main.java.barlocator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainPage extends JFrame {
	//Todo - add eventListeners to buttons.
	private JPanel viewMainPage;
	private JTextField searchJTextField;
	private JPanel headerJPanel;
	private JLabel headingJLabel;
	private JButton barSearchJButton;
	private JButton backJButton;
	private JPanel bodyJPanel;
	private Font mainFont = new Font("Montserrat Medium", Font.PLAIN, 16);

	public MainPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 744);
		viewMainPage = new JPanel();
		viewMainPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(viewMainPage);
		viewMainPage.setLayout(null);
		
		headerJPanel = new JPanel();
		headerJPanel.setBounds(5, 5, 1137, 213);
		headerJPanel.setBackground(new Color(0, 191, 255));
		viewMainPage.add(headerJPanel);
		headerJPanel.setLayout(null);
		
		headingJLabel = new JLabel("Welcome to Bar Locator");
		headingJLabel.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/bar.png")));
		headingJLabel.setForeground(new Color(255, 250, 250));
		headingJLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 25));
		headingJLabel.setBounds(296, 63, 378, 64);
		headerJPanel.add(headingJLabel);
		
		searchJTextField = new JTextField();
		searchJTextField.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		searchJTextField.setText("Enter a bar name");
		searchJTextField.setBounds(279, 137, 288, 40);
		headerJPanel.add(searchJTextField);
		searchJTextField.setColumns(10);
		
		barSearchJButton = new JButton("Search");
		barSearchJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		barSearchJButton.setBounds(594, 137, 96, 40);
		headerJPanel.add(barSearchJButton);
		
		backJButton = new JButton("");
		backJButton.setBorder(BorderFactory.createEmptyBorder());
		backJButton.setContentAreaFilled(false);
		backJButton.setBounds(1042, 10, 85, 21);
		headerJPanel.add(backJButton);
		
		bodyJPanel = new JPanel();
		bodyJPanel.setBounds(5, 218, 1137, 493);
		bodyJPanel.setBackground(new Color(220, 220, 220));
		viewMainPage.add(bodyJPanel);
		bodyJPanel.setLayout(null);
	}
}
