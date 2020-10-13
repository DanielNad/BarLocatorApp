package main.java.barlocator.controller;


import com.locator.algorithms.datastructures.Graph;
import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.barlocator.view.MainPage;
import main.java.barlocator.view.View;
import main.java.com.barlocator.dm.Bar;
import main.java.com.barlocator.dm.DistanceDict;
import main.java.com.barlocator.dm.Item;
import main.java.com.barlocator.dm.Menu;
import main.java.com.barlocator.server.BodyBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements HomePage.Listener, MainPage.Listener {

    private Client client;
    private View view;
    private Graph<Bar> graph;
    private String algo;
    private ArrayList<DistanceDict> distance;

    public Controller() {
        this.client = new Client();
        this.view = new View();
        view.getHomePage().setListener(this);
        view.getMainPage().setListener(this);
    }

    @Override
    public void clientPage(boolean isAdmin) {
        if(renderGraph(view.getHomePage().getErrorJLabel())){
            view.getHomePage().setVisible(false);
            view.getMainPage().setVisible(true);
        }
    }

    @Override
    public void adminPage(boolean isAdmin) {
        if(renderGraph(view.getHomePage().getErrorJLabel())){
            view.getHomePage().setVisible(false);
            view.getMainPage().setVisible(true);
            view.getMainPage().getAddBarJLabel().setVisible(true);
            view.getMainPage().getAddMenuJLabel().setVisible(true);
            view.getMainPage().getAddItemJLabel().setVisible(true);
            view.getMainPage().getRemoveBarJLabel().setVisible(true);
            view.getMainPage().getEditBarJLabel().setVisible(true);
            view.getMainPage().getAddEdgeJLabel().setVisible(true);
            view.getMainPage().getAddBarJButton().setVisible(true);
            view.getMainPage().getDeleteBarJButton().setVisible(true);
            view.getMainPage().getEditBarJButton().setVisible(true);
            view.getMainPage().getAddMenuJButton().setVisible(true);
            view.getMainPage().getAddItemJButton().setVisible(true);
            view.getMainPage().getAddEdgeJButton().setVisible(true);
        }
    }

    @Override
    public void goBack() {
        view.getHomePage().setVisible(true);
        view.getMainPage().setVisible(false);
        view.setMainPage(new MainPage());
        view.getMainPage().setListener(this);
    }

    @Override
    public void search() {
        if(view.getMainPage().getAlgoToggleButton().getModel().isPressed())
            algo = "basic";
        else
            algo = "dijkstra";
        client.sendRequest("GET", new BodyBuilder().type(algo).i(view.getMainPage().getSearchJComboBox().getSelectedIndex()).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        distance = client.getRes().getBody().getDistance();
        view.getMainPage().search(graph,distance);
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void addBar() {
        view.getMainPage().addBar(graph);
    }

    @Override
    public void deleteBar() {
        client.sendRequest("DELETE", new BodyBuilder().type("bar").barName(view.getMainPage().getSearchJComboBox().getSelectedItem().toString()).build());
        if(validateRequest(view.getMainPage().getErrorJLabel())){
            view.getMainPage().invalidThread(view.getMainPage().getErrorJLabel(),"Deleted " + client.getReq().getBody().getBarName());
            renderGraph(view.getMainPage().getErrorJLabel());
        } else {
            view.getMainPage().invalidThread(view.getMainPage().getErrorJLabel(),"Sorry, an error has occurred");
        }
    }

    @Override
    public void editBar() {
        view.getMainPage().editBar(graph.getBars().get(view.getMainPage().getSearchJComboBox().getSelectedIndex()));
    }

    @Override
    public void addMenu() {
        view.getMainPage().addMenu();
    }

    @Override
    public void addItem() {
        view.getMainPage().addItem(graph.getBars().get(view.getMainPage().getSearchJComboBox().getSelectedIndex()));
    }

    @Override
    public void addEdge() {
        view.getMainPage().addEdge();
    }
    @Override
    public void addBarDialog(String barName, String barDes, int weight, String barTo) {
        client.sendRequest("POST",new BodyBuilder().type("bar").bar(new Bar(barName,barDes)).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        client.sendRequest("POST", new BodyBuilder().type("edge").barName(barName).barTo(barTo).weight(weight).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void editBarDescription(String description) {
        Bar editedBar = graph.getBars().get(view.getMainPage().getSearchJComboBox().getSelectedIndex());
        editedBar.setDescription(description);
        client.sendRequest("POST",new BodyBuilder().type("bar").bar(editedBar).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void editItemPrice(double itemPrice, String menuName, String itemName) {
        Bar tempBar = graph.getBars().get(view.getMainPage().getSearchJComboBox().getSelectedIndex());
        Menu tempMenu = tempBar.getMenu().get(menuName);
        Item editedItem = tempMenu.getItems().get(itemName);
        editedItem.setPrice(itemPrice);
        client.sendRequest("POST",new BodyBuilder().type("item").barName(tempBar.getBarName()).menuName(tempMenu.getSubMenuName()).item(editedItem).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void addMenuDialog(String barName, Menu menu) {
        client.sendRequest("POST",new BodyBuilder().type("menu").barName(barName).menu(menu).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void addItemDialog(String barName, String menu, Item item) {
        client.sendRequest("POST",new BodyBuilder().type("item").barName(barName).menuName(menu).item(item).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void addEdgeDialog(String barFrom, String barTo, int weight) {
        client.sendRequest("POST", new BodyBuilder().type("edge").barName(barFrom).barTo(barTo).weight(weight).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    @Override
    public void openMenu(int i) {

    }

    public boolean renderGraph(JLabel label) {
        int selectedItem = view.getMainPage().getSearchJComboBox().getSelectedIndex();
        selectedItem = selectedItem == -1 ? 0 : selectedItem;
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if (client.getRes().getBody().getStatus().equals("ok")) {
            graph = client.getRes().getBody().getGraph();
            view.getMainPage().renderGraphComboBox(graph,view.getMainPage().getSearchJComboBox());
            view.getMainPage().getSearchJComboBox().setSelectedIndex(selectedItem);
            return  true;
        } else {
            view.getHomePage().invalidThread(label);
            return  false;
        }
    }

    public boolean validateRequest(JLabel label){
        if(!client.getRes().getBody().getStatus().equals("ok")){
            view.getHomePage().invalidThread(label);
            return false;
        } else {
            return true;
        }


    }
}
