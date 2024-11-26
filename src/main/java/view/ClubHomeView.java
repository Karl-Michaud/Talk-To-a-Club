package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.club_logged_in.ClubLoggedInViewModel;

/**
 * Club Home View.
 */
public class ClubHomeView extends JPanel {
    private JPanel center;
    private JLabel idk;

    private final String viewName = "club home";
    private final ClubLoggedInViewModel clubHomeViewModel;
    // private DataAccessController clubHomeController;

    public ClubHomeView(ClubLoggedInViewModel clubHomeViewModel) {
        this.clubHomeViewModel = clubHomeViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(center);

    }

    public String getViewName() {
        return viewName;
    }
}
