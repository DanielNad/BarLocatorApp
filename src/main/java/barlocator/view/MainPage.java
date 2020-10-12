package main.java.barlocator.view;

import com.locator.algorithms.datastructures.Graph;
import main.java.com.barlocator.dm.Bar;
import main.java.com.barlocator.dm.Item;
import main.java.com.barlocator.dm.Menu;

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
		void editBarDescription(String description);
		void editItemPrice(double itemPrice,String menuName, String itemName);
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

	public void renderGraphComboBox(Graph<Bar> graph , JComboBox jComboBox){
		jComboBox.removeAllItems();
		for (Bar bar : graph.getBars().values()) {
			jComboBox.addItem(bar.getBarName());
		}
	}

	public void renderBarComboBox(Bar bar, JComboBox jComboBox){
		jComboBox.removeAllItems();
		for ( Menu menu : bar.getMenu().values()) {
			jComboBox.addItem(menu.getSubMenuName());
		}
	}

	public  void renderMenuComboBox(Menu menu, JComboBox jComboBox){
		jComboBox.removeAllItems();
		for (Item item : menu.getItems().values()) {
			jComboBox.addItem(item.getItemName());
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
		renderGraphComboBox(graph,barToComboBox);
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
				invalidThread(errorJLabel,errorJLabel.getText());
			}
		});
	}

	public void editBar(Bar bar){
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Edit Bar Info");
		JLabel barNameJLabel = new JLabel("Edit " + bar.getBarName());
		JLabel descriptionJLabel = new JLabel("Please enter a new bar description:");
		JTextField descriptionJTextField = new JTextField();
		JButton descriptionSubmitJButton = new JButton("Submit");
		JLabel menuJLabel = new JLabel("Choose a menu:");
		JComboBox menuJComboBox = new JComboBox();
		JLabel itemJLabel = new JLabel("Choose an item to edit:");
		JComboBox itemJComboBox = new JComboBox();
		JTextField itemJTextField = new JTextField();
		JButton itemSubmitJButton = new JButton("Submit");
		JLabel errorJLabel = new JLabel("Text fields should not be empty!");

		menuJLabel.setVisible(false);
		menuJComboBox.setVisible(false);
		itemJLabel.setVisible(false);
		itemJComboBox.setVisible(false);
		itemJTextField.setVisible(false);
		itemSubmitJButton.setVisible(false);
		errorJLabel.setVisible(false);

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.add(barNameJLabel);
		dialog.add(descriptionJLabel);
		dialog.add(descriptionJTextField);
		dialog.add(descriptionSubmitJButton);
		dialog.add(menuJLabel);
		dialog.add(menuJComboBox);
		dialog.add(itemJLabel);
		dialog.add(itemJComboBox);
		dialog.add(itemJTextField);
		dialog.add(itemSubmitJButton);
		dialog.add(errorJLabel);

		if(!bar.getMenu().isEmpty()){
			renderBarComboBox(bar,menuJComboBox);
			menuJLabel.setVisible(true);
			menuJComboBox.setVisible(true);
			if(!bar.getMenu().get(menuJComboBox.getSelectedItem().toString()).getItems().isEmpty()){
				renderMenuComboBox(bar.getMenu().get(menuJComboBox.getSelectedItem().toString()),itemJComboBox);
				itemJLabel.setVisible(true);
				itemJComboBox.setVisible(true);
				itemJTextField.setVisible(true);
				itemSubmitJButton.setVisible(true);
				itemJTextField.setText(bar.getMenu().get(menuJComboBox.getSelectedItem().toString()).getItems().get(itemJComboBox.getSelectedItem().toString()).getPrice() + "");
			}
		}

		barNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		descriptionJLabel.setBounds(x,y,w,h);
		y += h + 5;
		descriptionJTextField.setBounds(x,y,w,h);
		descriptionSubmitJButton.setBounds(x + w + 5,y,100,h);
		y += h + 5;
		menuJLabel.setBounds(x,y,w,h);
		y += h + 5;
		menuJComboBox.setBounds(x,y,w,h);
		renderBarComboBox(bar,menuJComboBox);
		y += h + 5;
		itemJLabel.setBounds(x,y,w,h);
		y += h + 5;
		itemJComboBox.setBounds(x,y,w,h);
		y += h + 5;
		itemJTextField.setBounds(x,y,w,h);
		itemSubmitJButton.setBounds(x + w + 5,y,100,h);
		y += h + 10;
		errorJLabel.setBounds(175,y,w,h);
		errorJLabel.setForeground(Color.RED);

		dialog.setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(550,400);
		dialog.setVisible(true);

		validateNumberWithDot(itemJTextField);

		descriptionSubmitJButton.addActionListener(e -> {
			if(!descriptionJTextField.getText().equals("")){
				listener.editBarDescription(descriptionJTextField.getText().toString());
			}else {
				invalidThread(errorJLabel,errorJLabel.getText());
			}
		});
		itemSubmitJButton.addActionListener(e -> {
			if(!itemJTextField.getText().equals("")){
				listener.editItemPrice(Double.parseDouble(itemJTextField.getText()),menuJComboBox.getSelectedItem().toString(),itemJComboBox.getSelectedItem().toString());
			} else {
				invalidThread(errorJLabel,errorJLabel.getText());
			}
		});
		menuJComboBox.addItemListener(e -> {
			int index = e.getStateChange() - 1;
			Menu tempMenu = bar.getMenu().get(menuJComboBox.getItemAt(index).toString());
			if(!tempMenu.getItems().isEmpty()){
				itemJLabel.setVisible(true);
				itemJComboBox.setVisible(true);
				itemJTextField.setVisible(true);
				itemSubmitJButton.setVisible(true);
				errorJLabel.setVisible(true);
				renderMenuComboBox(tempMenu,itemJComboBox);
			} else {
				itemJLabel.setVisible(false);
				itemJComboBox.setVisible(false);
				itemJTextField.setVisible(false);
				itemSubmitJButton.setVisible(false);
				errorJLabel.setVisible(false);
			}
		});
		itemJComboBox.addItemListener(e -> {
			int index = e.getStateChange() - 1;
			Item tempItem = bar.getMenu().get(menuJComboBox.getSelectedItem().toString()).getItems().get(itemJComboBox.getItemAt(index).toString());
			itemJTextField.setText(tempItem.getPrice() + "");
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

	public void validateNumberWithDot(JTextField jTextField){
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				jTextField.getText();
				jTextField.setEditable((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8 || ke.getKeyChar() == '.');
			}
		});
	}

	public void invalidThread(JLabel label,String text){
		label.setText(text);
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
