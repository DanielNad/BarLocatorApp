package main.java.barlocator.view;

import com.locator.algorithms.datastructures.Graph;
import main.java.com.barlocator.dm.Bar;
import sun.misc.Request;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JLabel errorJLabel;
	private JToggleButton algoToggleButton;
	private boolean isDijkstra;

	public interface Listener{
		void goBack();
		void search();
		void addBar();
		void deleteBar();
		void editBar();
		void addBarDialog(String barName , String barDes, int weight, String barTo);
	}

	public MainPage() {
		isDijkstra = true;

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
		barSearchJButton.setBounds(701, 139, 100, 31);
		barSearchJButton.addActionListener(e -> listener.search());
		headerJPanel.add(barSearchJButton);
		
		backJButton = new JButton("");
		backJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/back.png")));
		backJButton.setBorder(BorderFactory.createEmptyBorder());
		backJButton.setContentAreaFilled(false);
		backJButton.setBounds(12, 13, 33, 33);
		backJButton.addActionListener(e -> listener.goBack());
		headerJPanel.add(backJButton);
		
		searchJComboBox = new JComboBox();
		barSearchJButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		searchJComboBox.setBounds(328, 139, 349, 31);
		headerJPanel.add(searchJComboBox);
		
		addBarJButton = new JButton("");
		addBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/add.png")));
		addBarJButton.setBorder(BorderFactory.createEmptyBorder());
		addBarJButton.setContentAreaFilled(false);
		addBarJButton.setBounds(328, 183, 17, 20);
		addBarJButton.setVisible(false);
		addBarJButton.addActionListener(e -> listener.addBar());
		headerJPanel.add(addBarJButton);
		
		deleteBarJButton = new JButton("");
		deleteBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/remove.png")));
		deleteBarJButton.setBorder(BorderFactory.createEmptyBorder());
		deleteBarJButton.setContentAreaFilled(false);
		deleteBarJButton.setBounds(371, 183, 17, 20);
		deleteBarJButton.setVisible(false);
		deleteBarJButton.addActionListener(e -> listener.deleteBar());
		headerJPanel.add(deleteBarJButton);
		
		editBarJButton = new JButton("");
		editBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/edit.png")));
		editBarJButton.setBorder(BorderFactory.createEmptyBorder());
		editBarJButton.setContentAreaFilled(false);
		editBarJButton.setBounds(414, 183, 17, 20);
		editBarJButton.setVisible(false);
		editBarJButton.addActionListener(e -> listener.editBar());
		headerJPanel.add(editBarJButton);
		
		errorJLabel = new JLabel("Sorry, an error has occurred");
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(new Color(255, 0, 0));
		errorJLabel.setBackground(new Color(255, 0, 0));
		errorJLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		errorJLabel.setBounds(426, 29, 227, 23);
		headerJPanel.add(errorJLabel);
		
		algoToggleButton = new JToggleButton("Basic Algorithm");
		algoToggleButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		algoToggleButton.setBounds(654, 179, 147, 29);
		algoToggleButton.addActionListener(e -> {
			if(isDijkstra)
				isDijkstra = false;
			else
				isDijkstra = true;
		});
		headerJPanel.add(algoToggleButton);
		
		bodyJPanel = new JPanel();
		bodyJPanel.setBounds(5, 218, 1137, 493);
		bodyJPanel.setBackground(new Color(220, 220, 220));
		viewMainPage.add(bodyJPanel);
		bodyJPanel.setLayout(null);

	}

	public void renderComboBox(Graph<Bar> graph , JComboBox comboBox){
		for (Bar bar : graph.getBars().values()) {
			comboBox.addItem(bar.getBarName());
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

	public JComboBox getSearchJComboBox() {
		return searchJComboBox;
	}

	public JButton getAddBarJButton() {
		return addBarJButton;
	}

	public JButton getDeleteBarJButton() {
		return deleteBarJButton;
	}

	public JButton getEditBarJButton() {
		return editBarJButton;
	}

	public JLabel getErrorJLabel() {
		return errorJLabel;
	}

	public JToggleButton getAlgoToggleButton() {
		return algoToggleButton;
	}

	public JPanel getBodyJPanel() {
		return bodyJPanel;
	}

	public boolean isDijkstra() {
		return isDijkstra;
	}

	public void addBar(Graph graph){
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Add new Bar");
		JLabel barNameJLabel = new JLabel("Please enter a bar name:");
		JTextField barNameJTextField = new JTextField();
		JLabel descriptionJLabel = new JLabel("Please enter a bar description:");
		JTextField descriptionJTextField = new JTextField();
		JLabel barToJLabel = new JLabel("Please enter a bar to connect:");
		JComboBox barToComboBox = new JComboBox();
		JLabel weightJLabel = new JLabel("Please enter a distance between the bars:");
		JTextField weightJTextField = new JTextField();
		JLabel errorJLabel = new JLabel("Text fields should not be empty!");
		JButton submitButton = new JButton();

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.add(barNameJLabel);
		dialog.add(barNameJTextField);
		dialog.add(descriptionJLabel);
		dialog.add(descriptionJTextField);
		dialog.add(barToJLabel);
		dialog.add(barToComboBox);
		dialog.add(weightJLabel);
		dialog.add(weightJTextField);
		dialog.add(errorJLabel);
		dialog.add(submitButton);

		barNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		barNameJTextField.setBounds(x,y,w,h);
		y += h + 5;
		descriptionJLabel.setBounds(x,y,w,h);
		y += h + 5;
		descriptionJTextField.setBounds(x,y,w,h);
		y += h + 5;
		barToJLabel.setBounds(x,y,w,h);
		y += h + 5;
		barToComboBox.setBounds(x,y,w,h);
		renderComboBox(graph,barToComboBox);
		y += h + 5;
		weightJLabel.setBounds(x,y,w,h);
		y += h + 5;
		weightJTextField.setBounds(x,y,w,h);
		y += h + 10;
		errorJLabel.setBounds(x,y,w,h);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitButton.setBounds(220,y,60,20);
		submitButton.setText("OK");

		dialog.setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(500,430);
		dialog.setVisible(true);

		validateNumber(weightJTextField);

		submitButton.addActionListener(e -> {
			if(!barNameJTextField.getText().equals("") && !descriptionJTextField.getText().equals("") && !weightJTextField.getText().equals("")){
				listener.addBarDialog(barNameJTextField.getText(),descriptionJTextField.getText(),Integer.parseInt(weightJTextField.getText()),barToComboBox.getSelectedItem().toString());
				dialog.setVisible(false);
			}else {
				invalidThread(errorJLabel);
			}
		});

	}

	public void validateNumber(JTextField jTextField){
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				jTextField.getText();
				jTextField.setEditable((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8);
			}
		});
	}

	public void invalidThread(JLabel label){
		label.setVisible(true);
		new Thread(() -> {
			try {
				Thread.sleep(3000);
				label.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
