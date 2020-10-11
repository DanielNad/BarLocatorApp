package main.java.barlocator.view;

public class View {
    private HomePage homePage;
    private MainPage mainPage;

    public View() {
        this.homePage = new HomePage();
        this.mainPage = new MainPage();
        this.mainPage.getContentPane().setVisible(false);
        this.homePage.getContentPane().setVisible(true);
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
