package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.club_home.ClubHomeViewModel;

/**
 * Club Home View.
 */
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
