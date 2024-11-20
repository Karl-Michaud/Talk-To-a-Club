package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.user.Club;

/**
 * A small Panel to populate the container for the joined clubs.
 */
public class ClubMiniPanel extends JPanel {
    private JLabel clubName;
    private JPanel clubMiniPanel;

    public ClubMiniPanel(Club club) {
        this.clubName.setText(club.getUsername());
        this.setBorder(BorderFactory.createBevelBorder(1));
        clubMiniPanel.setLayout(new BoxLayout(clubMiniPanel, BoxLayout.Y_AXIS));
        this.add(clubMiniPanel);
    }
}
