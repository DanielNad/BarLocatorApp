package main.java.barlocator.controller;

import main.java.barlocator.model.Client;
import main.java.barlocator.view.View;

public class Controller {

    private MainPageController mainPageController;
    private HomePageController homePageController;
    private Client client;
    private View view;

    public Controller() {
        this.homePageController = new HomePageController();
        this.mainPageController = new MainPageController();
        this.client = new Client();
        this.view = new View();
    }
}
