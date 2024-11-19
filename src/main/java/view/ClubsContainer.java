package view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.user.Club;
import interface_adapter.student_home.StudentHomeState;

/**
 * A container which stores all the clubs the user has joined.
 */
public class ClubsContainer extends JPanel {

    public ClubsContainer(StudentHomeState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Club club : state.getFollowedClubs()) {
            this.add(new JLabel(club.getUsername()));
        }
    }
}
