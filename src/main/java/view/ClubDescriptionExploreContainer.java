package view;

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;

/**
 * Container for all explore page club mini panels.
 */
public class ClubDescriptionExploreContainer extends JPanel {
    public ClubDescriptionExploreContainer(ExploreClubsState exploreClubsState,
                                           ExploreClubsController exploreClubsController) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Map<String, String> club: exploreClubsState.getClubValues()) {
            final ClubMiniPanel clubMiniPanel = new ClubMiniPanel(club, exploreClubsState.getStudentEmail());
            clubMiniPanel.setExploreClubsController(exploreClubsController);

            this.add(clubMiniPanel);
        }
    }
}
