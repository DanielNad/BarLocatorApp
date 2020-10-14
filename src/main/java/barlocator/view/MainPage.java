package main.java.barlocator.view;

import com.locator.algorithms.datastructures.Graph;
import main.java.com.barlocator.dm.Bar;
import main.java.com.barlocator.dm.DistanceDict;
import main.java.com.barlocator.dm.Item;
import main.java.com.barlocator.dm.Menu;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

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
	private JButton deleteMenuJButton;
	private JButton deleteItemJButton;
	private JButton editBarJButton;
	private Listener listener;
	private JLabel errorJLabel;
	private JToggleButton algoToggleButton;
	private boolean isDijkstra;
	private JScrollPane scrollPane;
	private JButton addMenuJButton;
	private JButton addItemJButton;
	private JButton addEdgeJButton;
	private JLabel addEdgeJLabel;
	private JLabel editBarJLabel;
	private JLabel addBarJLabel;
	private JLabel addMenuJLabel;
	private JLabel addItemJLabel;
	private JLabel removeBarJLabel;
	private JLabel removeMenuJLabel;
	private JLabel removeItemJLabel;
	private JPanel viewPort;

	public interface Listener{
		void goBack();
		void search();
		void addBar();
		void deleteBar();
		void deleteMenu();
		void deleteItem();
		void editBar();
		void addMenu();
		void addItem();
		void addEdge();
		void addBarDialog(String barName , String barDes, int weight, String barTo);
		void editBarDescription(String description);
		void editItemPrice(double itemPrice,String menuName, String itemName);
		void addMenuDialog(String barName ,Menu menu);
		void addItemDialog(String barName, String menu,Item item);
		void addEdgeDialog(String barFrom ,String barTo,int weight);
		void openMenu(int i);
		void deleteMenuDialog(String barName,String subMenuName);
		void deleteItemDialog(String barName,String subMenuName ,String itemName);
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
		headerJPanel.setBounds(5, 5, 1137, 218);
		headerJPanel.setBackground(new Color(0, 191, 255));
		viewMainPage.add(headerJPanel);
		headerJPanel.setLayout(null);
		
		headingJLabel = new JLabel("Welcome to Bar Locator");
		headingJLabel.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/bar.png")));
		headingJLabel.setForeground(new Color(255, 250, 250));
		headingJLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
		headingJLabel.setBounds(342, 65, 378, 64);
		headerJPanel.add(headingJLabel);
		
		barSearchJButton = new JButton("Search");
		barSearchJButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		barSearchJButton.setBounds(663, 140, 100, 30);
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
		searchJComboBox.setBounds(283, 140, 213, 30);
		headerJPanel.add(searchJComboBox);
		
		errorJLabel = new JLabel("Sorry, an error has occurred");
		errorJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(new Color(255, 0, 0));
		errorJLabel.setBackground(new Color(255, 0, 0));
		errorJLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		errorJLabel.setBounds(57, 29, 980, 23);
		headerJPanel.add(errorJLabel);
		
		algoToggleButton = new JToggleButton("Basic Algorithm");
		algoToggleButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		algoToggleButton.setBounds(506, 140, 147, 30);
		algoToggleButton.addActionListener(e -> {
			isDijkstra = !isDijkstra;
		});
		headerJPanel.add(algoToggleButton);
		
		bodyJPanel = new JPanel();
		bodyJPanel.setBounds(5, 223, 1137, 486);
		bodyJPanel.setBackground(new Color(220, 220, 220));
		viewMainPage.add(bodyJPanel);
		bodyJPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 0, 890, 490);
		bodyJPanel.add(scrollPane);

		addBarJLabel = new JLabel("Add Bar");
		addBarJLabel.setBounds(8, 13, 50, 33);
		addBarJLabel.setVisible(false);
		addBarJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(addBarJLabel);
		
		addMenuJLabel = new JLabel("Add Menu");
		addMenuJLabel.setBounds(8, 56, 64, 33);
		addMenuJLabel.setVisible(false);
		addMenuJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(addMenuJLabel);
		
		addItemJLabel = new JLabel("Add Item");
		addItemJLabel.setBounds(8, 100, 59, 33);
		addItemJLabel.setVisible(false);
		addItemJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(addItemJLabel);
		
		removeBarJLabel = new JLabel("Remove Bar");
		removeBarJLabel.setBounds(8, 186, 79, 33);
		removeBarJLabel.setVisible(false);
		removeBarJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(removeBarJLabel);

		removeMenuJLabel = new JLabel("Remove Menu");
		removeMenuJLabel.setBounds(8, 229, 100, 33);
		removeMenuJLabel.setVisible(false);
		removeMenuJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(removeMenuJLabel);

		removeItemJLabel = new JLabel("Remove Item");
		removeItemJLabel.setBounds(8, 272, 100, 33);
		removeItemJLabel.setVisible(false);
		removeItemJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(removeItemJLabel);

		addEdgeJLabel = new JLabel("Add Connection");
		addEdgeJLabel.setBounds(8, 143, 100, 33);
		addEdgeJLabel.setVisible(false);
		addEdgeJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(addEdgeJLabel);
		
		editBarJLabel = new JLabel("Edit");
		editBarJLabel.setBounds(8, 315, 24, 33);
		editBarJLabel.setVisible(false);
		editBarJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bodyJPanel.add(editBarJLabel);
		
		addBarJButton = new JButton("");
		addBarJButton.setBounds(200, 13, 33, 33);
		addBarJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addBarJButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		bodyJPanel.add(addBarJButton);
		addBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/add.png")));
		addBarJButton.setContentAreaFilled(false);
		addBarJButton.setVisible(false);
		addBarJButton.addActionListener(e -> listener.addBar());
		
		deleteBarJButton = new JButton("");
		deleteBarJButton.setBounds(202, 186, 33, 33);
		deleteBarJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bodyJPanel.add(deleteBarJButton);
		deleteBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/remove.png")));
		deleteBarJButton.setBorder(BorderFactory.createEmptyBorder());
		deleteBarJButton.setContentAreaFilled(false);
		deleteBarJButton.setVisible(false);
		deleteBarJButton.addActionListener(e -> listener.deleteBar());

		deleteMenuJButton = new JButton("");
		deleteMenuJButton.setBounds(202, 229, 33, 33);
		deleteMenuJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bodyJPanel.add(deleteMenuJButton);
		deleteMenuJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/remove.png")));
		deleteMenuJButton.setBorder(BorderFactory.createEmptyBorder());
		deleteMenuJButton.setContentAreaFilled(false);
		deleteMenuJButton.setVisible(false);
		deleteMenuJButton.addActionListener(e -> listener.deleteMenu());

		deleteItemJButton = new JButton("");
		deleteItemJButton.setBounds(202, 272, 33, 33);
		deleteItemJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bodyJPanel.add(deleteItemJButton);
		deleteItemJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/remove.png")));
		deleteItemJButton.setBorder(BorderFactory.createEmptyBorder());
		deleteItemJButton.setContentAreaFilled(false);
		deleteItemJButton.setVisible(false);
		deleteItemJButton.addActionListener(e -> listener.deleteItem());
		
		editBarJButton = new JButton("");
		editBarJButton.setBounds(202, 315, 33, 33);
		editBarJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bodyJPanel.add(editBarJButton);
		editBarJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/edit.png")));
		editBarJButton.setBorder(BorderFactory.createEmptyBorder());
		editBarJButton.setContentAreaFilled(false);
		editBarJButton.setVisible(false);
		editBarJButton.addActionListener(e -> listener.editBar());
		
		addMenuJButton = new JButton("");
		addMenuJButton.setBounds(202, 56, 33, 33);
		addMenuJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addMenuJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/add-menu.png")));
		addMenuJButton.setContentAreaFilled(false);
		addMenuJButton.setBorder(BorderFactory.createEmptyBorder());
		addMenuJButton.setVisible(false);
		addMenuJButton.addActionListener(e -> listener.addMenu());
		bodyJPanel.add(addMenuJButton);
		
		addItemJButton = new JButton("");
		addItemJButton.setBounds(202, 100, 33, 33);
		addItemJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addItemJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/add-item.png")));
		addItemJButton.setContentAreaFilled(false);
		addItemJButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addItemJButton.setVisible(false);
		addItemJButton.addActionListener(e -> listener.addItem());
		bodyJPanel.add(addItemJButton);
		
		addEdgeJButton = new JButton("");
		addEdgeJButton.setBounds(202, 143, 33, 33);
		addEdgeJButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addEdgeJButton.setContentAreaFilled(false);
		addEdgeJButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/resources/images/edge.png")));
		addEdgeJButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addEdgeJButton.setVisible(false);
		addEdgeJButton.addActionListener(e -> listener.addEdge());
		bodyJPanel.add(addEdgeJButton);
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

	public JButton getAddMenuJButton() {
		return addMenuJButton;
	}

	public JButton getAddItemJButton() {
		return addItemJButton;
	}

	public JButton getAddEdgeJButton() {
		return addEdgeJButton;
	}

	public JLabel getAddEdgeJLabel() {
		return addEdgeJLabel;
	}

	public JLabel getEditBarJLabel() {
		return editBarJLabel;
	}

	public JLabel getAddBarJLabel() {
		return addBarJLabel;
	}

	public JLabel getAddMenuJLabel() {
		return addMenuJLabel;
	}

	public JLabel getAddItemJLabel() {
		return addItemJLabel;
	}

	public JLabel getRemoveBarJLabel() {
		return removeBarJLabel;
	}

	public JButton getDeleteMenuJButton() {
		return deleteMenuJButton;
	}

	public JButton getDeleteItemJButton() {
		return deleteItemJButton;
	}

	public JLabel getRemoveMenuJLabel() {
		return removeMenuJLabel;
	}

	public JLabel getRemoveItemJLabel() {
		return removeItemJLabel;
	}

	public JToggleButton getAlgoToggleButton() {
		return algoToggleButton;
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

		dialog.getContentPane().add(barNameJLabel);
		dialog.getContentPane().add(barNameJTextField);
		dialog.getContentPane().add(descriptionJLabel);
		dialog.getContentPane().add(descriptionJTextField);
		dialog.getContentPane().add(barToJLabel);
		dialog.getContentPane().add(barToComboBox);
		dialog.getContentPane().add(weightJLabel);
		dialog.getContentPane().add(weightJTextField);
		dialog.getContentPane().add(errorJLabel);
		dialog.getContentPane().add(submitButton);

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
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitButton.setBounds(195,y,60,20);
		submitButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,430);
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

	public void addMenu(){
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Add new Menu");
		JLabel menuNameJLabel = new JLabel("Please enter a menu name:");
		JTextField menuNameJTextField = new JTextField();
		JLabel errorJLabel = new JLabel("Text fields should not be empty!");
		JButton submitButton = new JButton();

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.getContentPane().add(menuNameJLabel);
		dialog.getContentPane().add(menuNameJTextField);
		dialog.getContentPane().add(errorJLabel);
		dialog.getContentPane().add(submitButton);

		menuNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		menuNameJTextField.setBounds(x,y,w,h);
		y += h + 10;
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitButton.setBounds(195,y,60,20);
		submitButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,220);
		dialog.setVisible(true);

		submitButton.addActionListener(e -> {
			if(!menuNameJTextField.getText().equals("")){
				listener.addMenuDialog(searchJComboBox.getSelectedItem().toString(),new Menu(menuNameJTextField.getText()));
			}else{
				invalidThread(errorJLabel,errorJLabel.getText());
			}
		});

	}

	public void addItem(Bar bar){
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Add item to menu");
		JLabel menuNameJLabel = new JLabel("Choose a menu:");
		JComboBox menuJComboBox = new JComboBox();
		JLabel itemNameJLabel = new JLabel("Please enter an item name:");
		JTextField itemNameJTextField = new JTextField();
		JLabel itemPriceJLabel = new JLabel("Please enter a price for the item:");
		JTextField itemPriceJTextField = new JTextField();
		JButton submitJButton = new JButton("Submit");
		JLabel errorJLabel = new JLabel("Text fields should not be empty!");

		menuNameJLabel.setVisible(false);
		menuJComboBox.setVisible(false);
		itemNameJLabel.setVisible(false);
		itemNameJTextField.setVisible(false);
		itemPriceJLabel.setVisible(false);
		itemPriceJTextField.setVisible(false);
		submitJButton.setVisible(false);
		errorJLabel.setVisible(false);


		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.getContentPane().add(menuNameJLabel);
		dialog.getContentPane().add(menuJComboBox);
		dialog.getContentPane().add(itemNameJLabel);
		dialog.getContentPane().add(itemNameJTextField);
		dialog.getContentPane().add(itemPriceJLabel);
		dialog.getContentPane().add(itemPriceJTextField);
		dialog.getContentPane().add(submitJButton);
		dialog.getContentPane().add(errorJLabel);

		if(!bar.getMenu().isEmpty()){
			renderBarComboBox(bar,menuJComboBox);
			menuNameJLabel.setVisible(true);
			menuJComboBox.setVisible(true);
			itemNameJLabel.setVisible(true);
			itemNameJTextField.setVisible(true);
			itemPriceJLabel.setVisible(true);
			itemPriceJTextField.setVisible(true);
			submitJButton.setVisible(true);
		} else{
			invalidThread(errorJLabel,"This bar does not have any menus");
		}

		menuNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		menuJComboBox.setBounds(x,y,w,h);
		y += h + 5;
		itemNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		itemNameJTextField.setBounds(x,y,w,h);
		y += h + 5;
		itemPriceJLabel.setBounds(x,y,w,h);
		y += h + 5;
		itemPriceJTextField.setBounds(x,y,w,h);
		y += h + 5;
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitJButton.setBounds(195,y,60,20);
		submitJButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,350);
		dialog.setVisible(true);

		validateNumberWithDot(itemPriceJTextField);

		submitJButton.addActionListener(e -> {
			if(!itemNameJTextField.getText().equals("") && !itemPriceJTextField.getText().equals("")){
				listener.addItemDialog(bar.getBarName(),menuJComboBox.getSelectedItem().toString(),new Item(itemNameJTextField.getText(),Double.parseDouble(itemPriceJTextField.getText())));
			}else {
				invalidThread(errorJLabel,"Text fields should not be empty!");
			}
		});
	}

	public void addEdge() {
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Connect bar");
		JLabel barFromNameJLabel = new JLabel("1st Bar:");
		JComboBox barFromNameJComboBox = new JComboBox();
		JLabel barToNameJLabel = new JLabel("2nd Bar:");
		JComboBox barToNameJComboBox = new JComboBox();
		JLabel weightJLabel = new JLabel("Enter distance:");
		JTextField weightJTextField = new JTextField();
		JButton submitJButton = new JButton("Submit");
		JLabel errorJLabel = new JLabel("Text fields should not be empty!");
		errorJLabel.setVisible(false);

		dialog.getContentPane().add(barFromNameJLabel);
		dialog.getContentPane().add(barFromNameJComboBox);
		dialog.getContentPane().add(barToNameJLabel);
		dialog.getContentPane().add(barToNameJComboBox);
		dialog.getContentPane().add(weightJLabel);
		dialog.getContentPane().add(weightJTextField);
		dialog.getContentPane().add(submitJButton);
		dialog.getContentPane().add(errorJLabel);

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		barFromNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		barFromNameJComboBox.setBounds(x,y,w,h);
		y += h + 5;
		barToNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		barToNameJComboBox.setBounds(x,y,w,h);
		y += h + 5;
		weightJLabel.setBounds(x,y,w,h);
		y += h + 5;
		weightJTextField.setBounds(x,y,w,h);
		y += h + 5;
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitJButton.setBounds(195,y,60,20);
		submitJButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,350);
		dialog.setVisible(true);

		for (int i = 0; i < searchJComboBox.getItemCount() ; i++) {
			barFromNameJComboBox.addItem(searchJComboBox.getItemAt(i));
			barToNameJComboBox.addItem(searchJComboBox.getItemAt(i));
		}

		validateNumber(weightJTextField);

		submitJButton.addActionListener(e -> {
			if(!weightJTextField.getText().equals("")){
				if (barFromNameJComboBox.getSelectedItem().toString().equals(barToNameJComboBox.getSelectedItem().toString())){
					invalidThread(errorJLabel,"Please choose two different bars");
				} else {
					listener.addEdgeDialog(barFromNameJComboBox.getSelectedItem().toString(),barToNameJComboBox.getSelectedItem().toString(),Integer.parseInt(weightJTextField.getText()));
					dialog.setVisible(false);
				}
			} else{
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

		dialog.getContentPane().add(barNameJLabel);
		dialog.getContentPane().add(descriptionJLabel);
		dialog.getContentPane().add(descriptionJTextField);
		dialog.getContentPane().add(descriptionSubmitJButton);
		dialog.getContentPane().add(menuJLabel);
		dialog.getContentPane().add(menuJComboBox);
		dialog.getContentPane().add(itemJLabel);
		dialog.getContentPane().add(itemJComboBox);
		dialog.getContentPane().add(itemJTextField);
		dialog.getContentPane().add(itemSubmitJButton);
		dialog.getContentPane().add(errorJLabel);

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
		y += h + 5;
		itemJLabel.setBounds(x,y,w,h);
		y += h + 5;
		itemJComboBox.setBounds(x,y,w,h);
		y += h + 5;
		itemJTextField.setBounds(x,y,w,h);
		itemSubmitJButton.setBounds(x + w + 5,y,100,h);
		y += h + 10;
		errorJLabel.setBounds(185,y,w,h);
		errorJLabel.setForeground(Color.RED);

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(550,400);
		dialog.setVisible(true);

		validateNumberWithDot(itemJTextField);

		descriptionSubmitJButton.addActionListener(e -> {
			if(!descriptionJTextField.getText().equals("")){
				listener.editBarDescription(descriptionJTextField.getText());
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
			String index = e.getItem().toString();
			Menu tempMenu = bar.getMenu().get(index);
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if (!tempMenu.getItems().isEmpty()) {
					itemJLabel.setVisible(true);
					itemJComboBox.setVisible(true);
					itemJTextField.setVisible(true);
					itemSubmitJButton.setVisible(true);
					renderMenuComboBox(tempMenu, itemJComboBox);
				} else {
					itemJLabel.setVisible(false);
					itemJComboBox.setVisible(false);
					itemJTextField.setVisible(false);
					itemSubmitJButton.setVisible(false);
					invalidThread(errorJLabel, "This menu has no items");
				}
			}
		});
		itemJComboBox.addItemListener(e -> {
			String index = e.getItem().toString();
			if(e.getStateChange() == ItemEvent.SELECTED) {
				Item tempItem = bar.getMenu().get(menuJComboBox.getSelectedItem().toString()).getItems().get(index);
				itemJTextField.setText(tempItem.getPrice() + "");
			}
		});
	}

	public void deleteMenuDialog(Bar bar) {
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Delete a Menu");
		JLabel menuNameJLabel = new JLabel("Please choose a menu :");
		JComboBox menuComboBox = new JComboBox();
		JLabel errorJLabel = new JLabel(bar.getBarName() + " has no menus.");
		JButton submitButton = new JButton();

		if(bar.getMenu().isEmpty()){
			invalidThread(errorJLabel,errorJLabel.getText());
		} else {
			renderBarComboBox(bar,menuComboBox);
			dialog.getContentPane().add(submitButton);
		}

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.getContentPane().add(menuNameJLabel);
		dialog.getContentPane().add(menuComboBox);
		dialog.getContentPane().add(errorJLabel);

		menuNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		menuComboBox.setBounds(x,y,w,h);
		y += h + 10;
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitButton.setBounds(195,y,60,20);
		submitButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,220);
		dialog.setVisible(true);

		submitButton.addActionListener(e ->{
			listener.deleteMenuDialog(bar.getBarName(),menuComboBox.getSelectedItem().toString());
			dialog.setVisible(false);
		});

	}

	public void deleteItemDialog(Bar bar) {
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		JDialog dialog = new JDialog(frame,"Delete an Item");
		JLabel menuNameJLabel = new JLabel("Please choose a menu :");
		JComboBox menuComboBox = new JComboBox();
		JLabel itemNameJLabel = new JLabel("Please choose an item to delete :");
		JComboBox itemComboBox = new JComboBox();
		JLabel errorJLabel = new JLabel(bar.getBarName() + " has no menus.");
		JButton submitButton = new JButton();

		if(!bar.getMenu().isEmpty()){
			renderBarComboBox(bar,menuComboBox);
			menuNameJLabel.setVisible(true);
			menuComboBox.setVisible(true);
			if(!bar.getMenu().get(menuComboBox.getSelectedItem().toString()).getItems().isEmpty()){
				renderMenuComboBox(bar.getMenu().get(menuComboBox.getSelectedItem().toString()),itemComboBox);
				itemNameJLabel.setVisible(true);
				itemComboBox.setVisible(true);
				submitButton.setVisible(true);
			} else{
				invalidThread(errorJLabel,"This menu has no items");
			}
		} else {
			invalidThread(errorJLabel,errorJLabel.getText());
		}

		int x = 10;
		int y = 10;
		int w = 400;
		int h = 30;

		dialog.getContentPane().add(menuNameJLabel);
		dialog.getContentPane().add(menuComboBox);
		dialog.getContentPane().add(itemNameJLabel);
		dialog.getContentPane().add(itemComboBox);
		dialog.getContentPane().add(submitButton);
		dialog.getContentPane().add(errorJLabel);

		menuNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		menuComboBox.setBounds(x,y,w,h);
		y += h + 5;
		itemNameJLabel.setBounds(x,y,w,h);
		y += h + 5;
		itemComboBox.setBounds(x,y,w,h);
		y += h + 10;
		errorJLabel.setBounds(158,y,w,h);
		errorJLabel.setVisible(false);
		errorJLabel.setForeground(Color.RED);
		y += h + 10;
		submitButton.setBounds(195,y,60,20);
		submitButton.setText("OK");

		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		dialog.setSize(450,300);
		dialog.setVisible(true);

		submitButton.addActionListener(e ->{
			listener.deleteItemDialog(bar.getBarName(),menuComboBox.getSelectedItem().toString(),itemComboBox.getSelectedItem().toString());
			dialog.setVisible(false);
		});
		menuComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String index = e.getItem().toString();
				Menu tempMenu = bar.getMenu().get(index);
				if(!tempMenu.getItems().isEmpty()){
					itemNameJLabel.setVisible(true);
					itemComboBox.setVisible(true);
					errorJLabel.setVisible(false);
					renderMenuComboBox(tempMenu,itemComboBox);
				} else {
					invalidThread(errorJLabel,errorJLabel.getText());
					itemNameJLabel.setVisible(false);
					itemComboBox.setVisible(false);
				}
			}
		});
	}

	public void search(Graph<Bar> graph, ArrayList<DistanceDict> distance){

		int x = 0;
		int yPanel = 0;
		int w = 870;
		int hPanel = 130;
		viewPort = new JPanel();
		scrollPane.setViewportView(viewPort);
		viewPort.setLayout(null);
		for (int i = 0; i < distance.size() ; i++) {
			JPanel panel = new JPanel(new GridLayout(1, 4, 20, 40));
			JLabel barNameJLabel = new JLabel(graph.getBars().get(distance.get(i).getIndex()).getBarName());
			JLabel distanceJLabel = new JLabel(distance.get(i).getDistance() + "");
			JLabel descriptionJLabel = new JLabel(graph.getBars().get(distance.get(i).getIndex()).getDescription());
			JButton openMenu = new JButton("Open Menu");
			barNameJLabel.setHorizontalAlignment(SwingConstants.CENTER);
			distanceJLabel.setHorizontalAlignment(SwingConstants.CENTER);
			descriptionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(barNameJLabel);
			panel.add(distanceJLabel);
			panel.add(descriptionJLabel);
			panel.add(openMenu);
			viewPort.add(panel);
			int finalI = i;
			openMenu.addActionListener(e -> listener.openMenu(distance.get(finalI).getIndex()));
			panel.setBounds(x, yPanel, w, hPanel);
			yPanel += 135;
			viewPort.setPreferredSize(new Dimension(w, yPanel));
			panel.setBorder(new LineBorder(new Color(0, 191, 255), 2, true));
		}
	}

	public void openMenuDialog(Bar bar) {
		JFrame frame = new JFrame();
		JDialog dialog = new JDialog(frame,bar.getBarName() + " Menu");
		JScrollPane scrollPane = new JScrollPane();
		JPanel viewPort = new JPanel(new GridLayout(0,1,10,10));
		JLabel errorJLabel = new JLabel("Oops... no menus to show");

		scrollPane.setViewportView(viewPort);
		if(bar.getMenu().isEmpty()){
			invalidThread(errorJLabel,errorJLabel.getText());
		}
		else {
			for (Menu menu : bar.getMenu().values()) {
				JPanel menuPanel = new JPanel(new GridLayout(1 + menu.getItems().size(),2));
				JLabel menuName = new JLabel("         " + menu.getSubMenuName());
				menuPanel.add(menuName);
				for (Item item : menu.getItems().values()) {
					JPanel itemPanel = new JPanel(new GridLayout(0,3));
					JLabel itemName = new JLabel("                - " + item.getItemName());
					JLabel itemPrice = new JLabel("... " + item.getPrice() + " ₪");
					itemPanel.add(itemName);
					itemPanel.add(itemPrice);
					menuPanel.add(itemPanel);
				}
				viewPort.add(menuPanel);
			}
		}

		dialog.getContentPane().add(scrollPane);
		viewPort.setPreferredSize(new Dimension(532,369));
		dialog.getContentPane().setLayout(null);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(this);
		scrollPane.setBounds(0,0,532,369);
		frame.setSize(700, 700);
		dialog.setSize(550,400);
		dialog.setVisible(true);
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
		String preText = label.getText();
		label.setText(text);
		label.setVisible(true);
		new Thread(() -> {
			try {
				Thread.sleep(3000);
				label.setVisible(false);
				label.setText(preText);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
