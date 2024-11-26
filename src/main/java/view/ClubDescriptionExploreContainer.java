package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.user.Club;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsPresenter;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;

/**
 * Container for all explore page club mini panels.
 */
public class ClubDescriptionExploreContainer extends JPanel {
    public ClubDescriptionExploreContainer(ExploreClubsState exploreClubsState,
                                           ExploreClubsPresenter exploreClubsPresenter) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Club club : exploreClubsState.getClubs().getAll()) {
            this.add(new ClubMiniPanel(club, exploreClubsPresenter));
        }
    }
}
