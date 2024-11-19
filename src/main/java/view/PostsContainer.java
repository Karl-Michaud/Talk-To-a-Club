package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.post.Post;
import entity.user.Club;
import interface_adapter.student_home.StudentHomeState;

import java.awt.*;

/**
 * Container for the list of posts on the student home view.
 */
public class PostsContainer extends JPanel {
    public PostsContainer(StudentHomeState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i <= 20; i++) {
            final PostPanel samplePostPanel = new PostPanel("Example post.",
                    "This is an example post to check what the post panel looks like and"
                            + " if the general layout works.", "Sample Club");
            this.add(samplePostPanel);
        }

        for (Club club : state.getFollowedClubs()) {
            for (Post post : club.getClubPosts()) {
                this.add(new PostPanel(post.getTitle(), post.getContent(), club.getUsername()));
            }
        }
    }
}
