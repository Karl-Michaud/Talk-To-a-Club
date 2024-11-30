package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;

/**
 * View for the club panel.
 */
public class ClubMiniPanel extends JPanel {
    private JPanel clubMiniPanel;
    private JLabel clubName;
    private JLabel description;
    private JButton viewMore;
    private ExploreClubsController controller;

    private final int descriptionLength = 50;
    private final Map<String, String> club;

    public ClubMiniPanel(Map<String, String> club) {
        this.club = club;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(clubMiniPanel);
        this.clubName.setText(club.get("username"));

        this.description.setText("Description: " + club.get("description").substring(0,
                Math.min(club.get("description").length(), descriptionLength)) + "...");
        this.setBorder(BorderFactory.createBevelBorder(1));

        viewMore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                controller.switchToClubView(club);
            }
        });
    }

    public void setExploreClubsController(ExploreClubsController exploreClubsController) {
        this.controller = exploreClubsController;
    }
}
