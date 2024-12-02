package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_home.student_dislike.StudentDislikeController;
import interface_adapter.student_home.student_like.StudentLikeController;

/**
 * Container for the list of posts on the student home view.
 */
public class PostsContainer extends JPanel {
    private static final int WIDTH = 700;

    public PostsContainer(Map<String, List<Map<String, Object>>> posts, String currentStudent,
                          StudentLikeController likeController, StudentDislikeController dislikeController) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(WIDTH, Integer.MAX_VALUE));
        if (posts == null || posts.isEmpty()) {
            this.add(new JLabel("No posts found"));
        }
        else {
            for (String club : posts.keySet()) {
                for (Map<String, Object> post : posts.get(club)) {
                    this.add(new PostPanel(post, club, currentStudent, likeController, dislikeController));
                }
            }
        }
    }
}
