package main.java.barlocator.controller;

import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.barlocator.view.MainPage;
import main.java.barlocator.view.View;

public class Controller implements HomePage.Listener, MainPage.Listener {

    private Client client;
    private View view;

    public Controller() {
        this.client = new Client();
        this.view = new View();
        view.getHomePage().setListener(this);
        view.getMainPage().setListener(this);
    }

    @Override
    public void clientPageClick(boolean isAdmin) {
        view.getHomePage().getContentPane().setVisible(false);
        view.getMainPage().getContentPane().setVisible(true);
    }

    @Override
    public void adminPageClick(boolean isAdmin) {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void search() {

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
