package view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A container which stores all the clubs the user has joined.
 */
public class ClubsContainer extends JPanel {

    public ClubsContainer(List<String> clubs) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (clubs == null || clubs.isEmpty()) {
            this.add(new ClubMiniPanel("You currently don't follow any clubs."));
        }
        else {
            for (String club : clubs) {
                this.add(new ClubMiniPanel(club));
            }
        }
        // This temporarily creates a list of fake clubs to populate as an example.
        //
        // for (int i = 0; i <= 10; i++) {
        //     this.add(new ClubMiniPanel(new Club("Example Club " + i, "", "",
        //             new ArrayList<Student>(), new ArrayList<Post>())));
        // }
    }
}
