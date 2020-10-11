package main.java.barlocator.view;

import javax.swing.*;

public class View {
    private HomePage homePage;
    private MainPage mainPage;

    public View() {
        this.homePage = new HomePage();
        this.mainPage = new MainPage();
        this.mainPage.setVisible(false);
        this.homePage.setVisible(true);
    }

    public void invalidThread(JLabel label){
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

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
