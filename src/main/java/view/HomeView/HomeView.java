package view.HomeView;

import interface_adapter.data_access.HomeViewModel;
import interface_adapter.data_access.HomeState;
import interface_adapter.data_access.DataAccessController;


import javax.swing.*;

public class HomeView extends JPanel {
    private JPanel panel1;
    private JLabel labelUsername;
    private JLabel labelIcon;

    private final String viewName = "home";
    private final HomeViewModel homeViewModel;
    private DataAccessController dataAccessController;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panel1);
    }
}
