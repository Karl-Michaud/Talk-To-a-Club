package view;

import interface_adapter.home.HomeViewModel;


import javax.swing.*;

public class HomeView extends JPanel {
    private JPanel panel1;
    private JLabel labelUsername;
    private JLabel labelIcon;

    private final String viewName = "home";
    private final HomeViewModel homeViewModel;
    // private DataAccessController HomeController;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panel1);
    }

    public String getViewName() {
        return viewName;
    }
}
