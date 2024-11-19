package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.post.Post;
import entity.user.Club;
import interface_adapter.student_home.StudentHomeState;

/**
 * Container for the list of posts on the student home view.
 */
public class PostsContainer extends JPanel {
    public PostsContainer(StudentHomeState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Club club : state.getFollowedClubs()) {
            for (Post post : club.getClubPosts()) {
                this.add(new PostPanel(post.getTitle(), post.getContent()));
            }
        }
    }
}
