package main.java.barlocator.controller;

import com.locator.algorithms.datastructures.Graph;
import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.barlocator.view.MainPage;
import main.java.barlocator.view.View;
import main.java.com.barlocator.dm.Bar;
import main.java.com.barlocator.server.BodyBuilder;

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
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if(client.getRes().getBody().getStatus().equals("ok")){
            graph = client.getRes().getBody().getGraph();
            view.getMainPage().renderComboBox(graph);
            view.getHomePage().setVisible(false);
            view.getMainPage().setVisible(true);
        } else{
            view.invalidThread(view.getHomePage().getErrorJLabel());
        }
    }

    @Override
    public void adminPage(boolean isAdmin) {
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if(client.getRes().getBody().getStatus().equals("ok")){
            graph = client.getRes().getBody().getGraph();
            view.getMainPage().renderComboBox(graph);
            view.getHomePage().setVisible(false);
            view.getMainPage().setVisible(true);
            view.getMainPage().getAddBarJButton().setVisible(true);
            view.getMainPage().getDeleteBarJButton().setVisible(true);
            view.getMainPage().getEditBarJButton().setVisible(true);
        } else{
            view.invalidThread(view.getHomePage().getErrorJLabel());
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
        client.sendRequest("GET", new BodyBuilder().type("graph").build());
        if(client.getRes().getBody().getStatus().equals("ok")){
            graph = client.getRes().getBody().getGraph();
        } else {
            view.invalidThread(view.getMainPage().getErrorJLabel());
        }
    }

    @Override
    public void addBar() {

    }

    @Override
    public void deleteBar() {

    }

    @Override
    public void editBar() {

    }
}
