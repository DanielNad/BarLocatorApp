package main.java.barlocator.controller;

import com.locator.algorithms.datastructures.Graph;
import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.barlocator.view.MainPage;
import main.java.barlocator.view.View;
import main.java.com.barlocator.dm.Bar;
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
        validateRequest(view.getMainPage().getErrorJLabel());

    }

    @Override
    public void editBar() {

    }

    @Override
    public void addBarDialog(String barName, String barDes, int weight, String barTo) {
        client.sendRequest("POST",new BodyBuilder().type("bar").bar(new Bar(barName,barDes)).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        client.sendRequest("POST", new BodyBuilder().type("edge").barName(barName).barTo(barTo).weight(weight).build());
        validateRequest(view.getMainPage().getErrorJLabel());
        renderGraph(view.getMainPage().getErrorJLabel());
    }

    public boolean renderGraph(JLabel label) {
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if (client.getRes().getBody().getStatus().equals("ok")) {
            graph = client.getRes().getBody().getGraph();
            view.getMainPage().getSearchJComboBox().removeAllItems();
            view.getMainPage().renderComboBox(graph,view.getMainPage().getSearchJComboBox());
            return  true;
        } else {
            view.getHomePage().invalidThread(label);
            return  false;
        }
    }

    public boolean renderMenu(JLabel label){
        return false;
    }

    public boolean renderItems(){
        return false;
    }

    public void validateRequest(JLabel label){
        if(!client.getRes().getBody().getStatus().equals("ok")){
            view.getHomePage().invalidThread(label);
        }
    }
}
