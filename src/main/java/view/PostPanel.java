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

import interface_adapter.student_logged_in.student_home.dislike.StudentDislikeController;
import interface_adapter.student_logged_in.student_home.like.StudentLikeController;

/**
 * A panel which will hold the data for a single post. A list of these in the student home view will be contained
 * within a scrollable field.
 */
public class PostPanel extends JPanel {
    private static final String LIKED = "liked";
    private static final String DISLIKED = "disliked";
    private JLabel labelPostTitle;
    private JButton buttonLike;
    private JButton buttonDislike;
    private JPanel panelPost;
    private JLabel labelPostContent;
    private JLabel labelClub;
    private JLabel labelLikes;
    private JLabel labelDislikes;
    private final Icon likeEmptyIcon = new ImageIcon("src/main/resources/like.png");
    private final Icon likeFilledIcon = new ImageIcon("src/main/resources/like_filled.png");
    private final Icon dislikeEmptyIcon = new ImageIcon("src/main/resources/dislike.png");
    private final Icon dislikeFilledIcon = new ImageIcon("src/main/resources/dislike_filled.png");
    private final StudentLikeController likeController;
    private final StudentDislikeController dislikeController;

    public PostPanel(Map<String, Object> post, String clubName, String currentStudent,
                     StudentLikeController likeController, StudentDislikeController dislikeController) {
        this.likeController = likeController;
        this.dislikeController = dislikeController;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(panelPost);
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
        if (post.get(DISLIKED).equals(true)) {
            this.buttonDislike.setIcon(dislikeFilledIcon);
        }
        else {
            this.buttonDislike.setIcon(dislikeEmptyIcon);
        }

        buttonLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (post.get(LIKED).equals(true)) {
                    buttonLike.setIcon(likeEmptyIcon);
                    int likedCount = Integer.parseInt(labelLikes.getText());
                    likedCount--;
                    labelLikes.setText(String.valueOf(likedCount));
                    post.put(LIKED, false);
                    likeController.changeLike(currentStudent, post);
                }
                else {
                    buttonLike.setIcon(likeFilledIcon);
                    buttonLike.setIcon(likeEmptyIcon);
                    int likedCount = Integer.parseInt(labelLikes.getText());
                    likedCount++;
                    labelLikes.setText(String.valueOf(likedCount));
                    post.put(LIKED, true);
                    likeController.changeLike(currentStudent, post);
                    dislikeController.changeDislike(currentStudent, post);
                }
            }
        });

        buttonDislike.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (post.get(DISLIKED).equals(true)) {
                    buttonDislike.setIcon(dislikeEmptyIcon);
                    int dislikedCount = Integer.parseInt(labelDislikes.getText());
                    dislikedCount--;
                    labelDislikes.setText(String.valueOf(dislikedCount));
                    post.put(DISLIKED, false);
                    dislikeController.changeDislike(currentStudent, post);
                }
                else {
                    buttonDislike.setIcon(dislikeFilledIcon);
                    buttonLike.setIcon(likeEmptyIcon);
                    int dislikedCount = Integer.parseInt(labelDislikes.getText());
                    dislikedCount++;
                    labelDislikes.setText(String.valueOf(dislikedCount));
                    post.put(DISLIKED, true);
                    likeController.changeLike(currentStudent, post);
                    dislikeController.changeDislike(currentStudent, post);
                }
            }
        });
    }
}
