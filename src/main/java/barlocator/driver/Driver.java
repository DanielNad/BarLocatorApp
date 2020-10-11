package main.java.barlocator.driver;

import main.java.barlocator.model.Client;
import main.java.barlocator.view.HomePage;
import main.java.com.barlocator.server.BodyBuilder;

public class Driver {
    public static void main(String[] args) {
//        Client client = new Client();
//        client.sendRequest("GET",new BodyBuilder().type("graph").build());
//        System.out.println(client.getRes());
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
    }
}
