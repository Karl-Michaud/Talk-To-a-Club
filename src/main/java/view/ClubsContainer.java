package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.user.Club;
import interface_adapter.student_home.StudentHomeState;

/**
 * A container which stores all the clubs the user has joined.
 */
public class ClubsContainer extends JPanel {

    public ClubsContainer(StudentHomeState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        for (Club club : state.getFollowedClubs()) {
//            this.add(new ClubMiniPanel(club));
//        }
        // This temporarily creates a list of fake clubs to populate as an example.
        //
        // for (int i = 0; i <= 10; i++) {
        //     this.add(new ClubMiniPanel(new Club("Example Club " + i, "", "",
        //             new ArrayList<Student>(), new ArrayList<Post>())));
        // }
    }
}
