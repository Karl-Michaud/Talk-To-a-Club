package view;

import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import interface_adapter.student_home.like.StudentLikeController;

/**
 * Container for the list of posts on the student home view.
 */
public class PostsContainer extends JPanel {
    public PostsContainer(Map<String, List<Map<String, Object>>> posts, String currentStudent, StudentLikeController likeController) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Creates sample posts and populates the view as a visual example.
        // final Post examplePost = new Announcement("Example post.", "This is an example post to "
        //         + "check what the post panel looks like and if the general layout works."
        // );
        // for (int i = 0; i <= 20; i++) {
        //     final PostPanel samplePostPanel = new PostPanel(examplePost, "Sample Club", state.getCurrentUser());
        //     this.add(samplePostPanel);
        // }
        for (String club : posts.keySet()) {
            for (Map<String, Object> post : posts.get(club)) {
                this.add(new PostPanel(post, club, currentStudent, likeController));
            }
        }
    }
}
