package view;

import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.student_home.StudentHomeViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class ClubHomeView extends JPanel {
    private JPanel center;
    private JLabel idk;

    private final String viewName = "club home";
    private final ClubHomeViewModel clubHomeViewModel;
    // private DataAccessController clubHomeController;

    public ClubHomeView(ClubHomeViewModel clubHomeViewModel) {
        this.clubHomeViewModel = clubHomeViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(center);

    }

    public String getViewName() {
        return viewName;
    }
}
