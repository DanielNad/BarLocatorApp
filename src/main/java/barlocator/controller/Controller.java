package main.java.barlocator.controller;

import com.locator.algorithms.datastructures.Graph;
import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.barlocator.view.MainPage;
import main.java.barlocator.view.View;
import main.java.com.barlocator.dm.Bar;
import main.java.com.barlocator.dm.Item;
import main.java.com.barlocator.dm.Menu;
import main.java.com.barlocator.server.BodyBuilder;

import javax.swing.*;

public class Controller implements HomePage.Listener, MainPage.Listener {

    private Client client;
    private View view;
    private Graph<Bar> graph;
    private int[] distance;

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
            view.getMainPage().getAddBarJButton().setVisible(true);
            view.getMainPage().getDeleteBarJButton().setVisible(true);
            view.getMainPage().getEditBarJButton().setVisible(true);
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

    public boolean renderGraph(JLabel label) {
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if (client.getRes().getBody().getStatus().equals("ok")) {
            graph = client.getRes().getBody().getGraph();
            view.getMainPage().renderGraphComboBox(graph,view.getMainPage().getSearchJComboBox());
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
