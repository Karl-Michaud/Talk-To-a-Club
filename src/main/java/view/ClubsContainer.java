package view;

import java.util.List;
import java.util.Map;

import javax.swing.*;

/**
 * A container which stores all the clubs the user has joined.
 */
public class ClubsContainer extends JPanel {

    public ClubsContainer(List<Map<String, String>> clubs) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (clubs == null || clubs.isEmpty()) {
            this.add(new JLabel("You currently don't follow any clubs."));
        }
        else {
            for (Map<String, String> club : clubs) {
                this.add(new ClubMiniPanel(club));
            }
        }
    }
}
