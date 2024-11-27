package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_home.like.StudentLikeController;

/**
 * A panel which will hold the data for a single post. A list of these in the student home view will be contained
 * within a scrollable field.
 */
public class PostPanel extends JPanel {
    private static final String LIKED = "Liked";
    private JLabel labelPostTitle;
    private JButton buttonLike;
    private JButton buttonDislike;
    private JPanel postPanel;
    private JLabel labelPostContent;
    private JLabel labelClub;
    private JLabel labelLikes;
    private JLabel labelDislikes;
    private final Icon likeEmptyIcon = new ImageIcon("src/main/resources/like.png");
    private final Icon likeFilledIcon = new ImageIcon("src/main/resources/like_filled.png");
    private final Icon dislikeEmptyIcon = new ImageIcon("src/main/resources/dislike.png");
    private final Icon dislikeFilledIcon = new ImageIcon("src/main/resources/dislike_filled.png");
    private StudentLikeController likeController;

    public PostPanel(Map<String, Object> post, String clubName, String currentStudent,
                     StudentLikeController likeController) {
        this.likeController = likeController;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(postPanel);
        this.labelPostTitle.setText(post.get("title").toString());
        this.labelPostContent.setText(post.get("content").toString());
        this.labelClub.setText(clubName);
        this.labelLikes.setText(String.valueOf(post.get("likes")));
        this.labelDislikes.setText(String.valueOf(post.get("dislikes")));
        this.setBorder(BorderFactory.createBevelBorder(1));
        if (post.get(LIKED).equals(true)) {
            this.buttonLike.setIcon(likeFilledIcon);
        }
        else {
            this.buttonLike.setIcon(likeEmptyIcon);
        }

        buttonLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                likeController.changeLike(currentStudent, post);
                if (post.get(LIKED).equals(true)) {
                    buttonLike.setIcon(likeEmptyIcon);
                    int likedCount = Integer.parseInt(labelLikes.getText());
                    likedCount--;
                    labelLikes.setText(String.valueOf(likedCount));
                    post.put(LIKED, false);
                }
                else {
                    buttonLike.setIcon(likeFilledIcon);
                    int likedCount = Integer.parseInt(labelLikes.getText());
                    likedCount++;
                    labelLikes.setText(String.valueOf(likedCount));
                    post.put(LIKED, true);
                }
            }
        });

        buttonDislike.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
