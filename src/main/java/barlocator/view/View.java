package main.java.barlocator.view;

public class View {
    private HomePage homePage;
    private MainPage mainPage;

    public View() {
        this.homePage = new HomePage();
        this.mainPage = new MainPage();
        this.mainPage.setVisible(false);
        this.homePage.setVisible(true);
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
