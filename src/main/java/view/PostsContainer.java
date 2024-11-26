package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.show_posts.ShowPostsState;

/**
 * Container for the list of posts on the student home view.
 */
public class PostsContainer extends JPanel {
    public PostsContainer(ShowPostsState state) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Creates sample posts and populates the view as a visual example.
        // final Post examplePost = new Announcement("Example post.", "This is an example post to "
        //         + "check what the post panel looks like and if the general layout works."
        // );
        // for (int i = 0; i <= 20; i++) {
        //     final PostPanel samplePostPanel = new PostPanel(examplePost, "Sample Club", state.getCurrentUser());
        //     this.add(samplePostPanel);
        // }
        for (String club : state.getPosts().keySet()) {
            for (Post post : state.getPosts().get(club)) {
                this.add(new PostPanel(post, club, likeState, dislikeState));
            }
        }
    }
}
