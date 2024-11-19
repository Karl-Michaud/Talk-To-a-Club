package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import interface_adapter.student_home.StudentHomeState;

import java.util.ArrayList;

/**
 * A container which stores all the clubs the user has joined.
 */
public class ClubsContainer extends JPanel {

    public ClubsContainer(StudentHomeState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Club club : state.getFollowedClubs()) {
            this.add(new ClubMiniPanel(club));
        }
        for (int i = 0; i <= 10; i++) {
            this.add(new ClubMiniPanel(new Club("Example Club " + i, "", "",
                    new ArrayList<Student>(), new ArrayList<Post>())));
        }

    }
}
