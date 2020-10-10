package main.java.barlocator.controller;

import main.java.barlocator.model.Client;
import main.java.barlocator.view.View;

public class Controller {

    private ClientController clientController;
    private HomeController homeController;
    private Client client;
    private View view;

    public Controller() {
        this.homeController = new HomeController();
        this.clientController = new ClientController();
        this.client = new Client();
        this.view = new View();
    }
}
