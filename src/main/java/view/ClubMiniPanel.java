package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.user.Club;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsPresenter;

/**
 * View for the club panel.
 */
public class ClubMiniPanel extends JPanel {
    private JPanel clubMiniPanel;
    private JLabel clubName;
    private JLabel description;
    private JButton viewMore;

    private final int descriptionLength = 50;

    public ClubMiniPanel(Club club, ExploreClubsPresenter presenter) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(clubMiniPanel);
        this.clubName.setText(club.getUsername());

        // TODO replace with description of club.
        this.description.setText("Description: " + club.getEmail().substring(0,
                Math.min(club.getEmail().length(), descriptionLength)) + "...");
        this.setBorder(BorderFactory.createBevelBorder(1));

        viewMore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                presenter.switchToClubView(club);
            }
        });
    }
}
