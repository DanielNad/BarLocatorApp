package main.java.barlocator.view;

public class View {
    private HomePage homePage;
    private MainPage mainPage;

    public View() {
        this.homePage = new HomePage();
        this.mainPage = new MainPage();
        this.mainPage.getContentPane().setVisible(false);
        this.homePage.getContentPane().setVisible(true);
        homePage.getClientPageJButton().addActionListener(e -> this.showMainPage(false));
        homePage.getAdminPageJButton().addActionListener(e -> this.showMainPage(true));
    }

    public void showMainPage(boolean isAdmin){
        this.homePage.setVisible(false);
        this.mainPage.setVisible(true);
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
