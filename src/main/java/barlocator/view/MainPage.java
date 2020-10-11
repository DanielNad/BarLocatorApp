package main.java.barlocator.view;

import com.locator.algorithms.datastructures.Graph;
import main.java.com.barlocator.dm.Bar;

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
import javax.swing.JComboBox;

public class MainPage extends JFrame {

	private JPanel viewMainPage;
	private JPanel headerJPanel;
	private JLabel headingJLabel;
	private JButton barSearchJButton;
	private JButton backJButton;
	private JPanel bodyJPanel;
	private JComboBox searchJComboBox;
	private JButton addBarJButton;
	private JButton deleteBarJButton;
	private JButton editBarJButton;
	private Listener listener;

	public interface Listener{
		void goBack();
		void search();
		void addBar();
		void deleteBar();
		void editBar();
	}

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
		headingJLabel.setBounds(403, 65, 378, 64);
		headerJPanel.add(headingJLabel);
		
		barSearchJButton = new JButton("Search");
		barSearchJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		barSearchJButton.setBounds(701, 139, 85, 31);
		headerJPanel.add(barSearchJButton);
		
		backJButton = new JButton("");
		backJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/back.png")));
		backJButton.setBorder(BorderFactory.createEmptyBorder());
		backJButton.setContentAreaFilled(false);
		backJButton.setBounds(12, 13, 33, 33);
		headerJPanel.add(backJButton);
		
		searchJComboBox = new JComboBox();
		barSearchJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		searchJComboBox.setBounds(328, 139, 349, 31);
		headerJPanel.add(searchJComboBox);
		
		addBarJButton = new JButton("");
		addBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/add.png")));
		addBarJButton.setBorder(BorderFactory.createEmptyBorder());
		addBarJButton.setContentAreaFilled(false);
		addBarJButton.setBounds(518, 180, 17, 20);
		addBarJButton.setVisible(false);
		headerJPanel.add(addBarJButton);
		
		deleteBarJButton = new JButton("");
		deleteBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/remove.png")));
		deleteBarJButton.setBorder(BorderFactory.createEmptyBorder());
		deleteBarJButton.setContentAreaFilled(false);
		deleteBarJButton.setBounds(561, 180, 17, 20);
		deleteBarJButton.setVisible(false);
		headerJPanel.add(deleteBarJButton);
		
		editBarJButton = new JButton("");
		editBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/edit.png")));
		editBarJButton.setBorder(BorderFactory.createEmptyBorder());
		editBarJButton.setContentAreaFilled(false);
		editBarJButton.setBounds(604, 180, 17, 20);
		editBarJButton.setVisible(false);
		headerJPanel.add(editBarJButton);
		
		bodyJPanel = new JPanel();
		bodyJPanel.setBounds(5, 218, 1137, 493);
		bodyJPanel.setBackground(new Color(220, 220, 220));
		viewMainPage.add(bodyJPanel);
		bodyJPanel.setLayout(null);
	}

	public void renderMainPage(boolean isAdmin, Graph<Bar> graph) {
		for (Bar bar : graph.getBars().values()) {
			searchJComboBox.addItem(bar.getBarName());
		}
	}

	public JButton getBarSearchJButton() {
		return barSearchJButton;
	}

	public JButton getBackJButton() {
		return backJButton;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
