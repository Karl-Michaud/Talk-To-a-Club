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

import interface_adapter.student_home.student_dislike.StudentDislikeController;
import interface_adapter.student_home.student_like.StudentLikeController;

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
    private final Icon likeEmptyIcon;
    private final Icon likeFilledIcon;
    private final Icon dislikeEmptyIcon = new ImageIcon("src/main/resources/dislike.png");
    private final Icon dislikeFilledIcon = new ImageIcon("src/main/resources/dislike_filled.png");
    private final StudentLikeController studentLikeController;
    private final StudentDislikeController studentDislikeController;
    private final Map<String, Object> postData;

    public PostPanel(Map<String, Object> post, String clubName, String currentStudent,
                     StudentLikeController likeController, StudentDislikeController dislikeController) {
        // Set all the basic attributes.
        this.postData = post;
        this.likeEmptyIcon = new ImageIcon("src/main/resources/like.png");
        this.likeFilledIcon = new ImageIcon("src/main/resources/like_filled.png");
        this.studentLikeController = likeController;
        this.studentDislikeController = dislikeController;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(panelPost);
        this.labelPostTitle.setText("<html><p style=\"width:75px\">" + postData.get("title").toString()
                + "</p></html>");
        this.labelPostContent.setText("<html><p style=\"width:300px\">" + postData.get("content").toString()
                + "</p></html>");
        this.labelClub.setText(clubName);
        this.labelLikes.setText(String.valueOf(postData.get("likes")));
        this.labelDislikes.setText(String.valueOf(postData.get("dislikes")));
        this.setBorder(BorderFactory.createBevelBorder(1));

        // Set the button icons according to the liked/ disliked states.
        if (postData.get(LIKED).equals(true)) {
            this.buttonLike.setIcon(likeFilledIcon);
        }
        else {
            this.buttonLike.setIcon(likeEmptyIcon);
        }
        if (postData.get(DISLIKED).equals(true)) {
            this.buttonDislike.setIcon(dislikeFilledIcon);
        }
        else {
            this.buttonDislike.setIcon(dislikeEmptyIcon);
        }

        // Listener for the like button.
        buttonLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                likeClicked(currentStudent);
            }
        });

        // Listener for the dislike button.
        buttonDislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dislikeClicked(currentStudent);
            }
        });
    }

    private void likeClicked(String currentStudent) {
        if (postData.get(LIKED).equals(true)) {
            buttonLike.setIcon(likeEmptyIcon);
            int likedCount = Integer.parseInt(labelLikes.getText());
            likedCount--;
            labelLikes.setText(String.valueOf(likedCount));

            studentLikeController.changeLike(currentStudent, postData);
        }
        else {
            if (postData.get(DISLIKED).equals(true)) {
                buttonDislike.setIcon(dislikeEmptyIcon);
                int dislikedCount = Integer.parseInt(labelDislikes.getText());
                dislikedCount--;
                labelDislikes.setText(String.valueOf(dislikedCount));
                studentDislikeController.changeDislike(currentStudent, postData);
            }
            buttonLike.setIcon(likeFilledIcon);
            int likedCount = Integer.parseInt(labelLikes.getText());
            likedCount++;
            labelLikes.setText(String.valueOf(likedCount));
            studentLikeController.changeLike(currentStudent, postData);
        }
    }

    private void dislikeClicked(String currentStudent) {
        if (postData.get(DISLIKED).equals(true)) {
            buttonDislike.setIcon(dislikeEmptyIcon);
            int dislikedCount = Integer.parseInt(labelDislikes.getText());
            dislikedCount--;
            labelDislikes.setText(String.valueOf(dislikedCount));
            studentDislikeController.changeDislike(currentStudent, postData);
        }
        else {
            buttonDislike.setIcon(dislikeFilledIcon);
            if (postData.get(LIKED).equals(true)) {
                buttonLike.setIcon(likeEmptyIcon);
                int likedCount = Integer.parseInt(labelLikes.getText());
                likedCount--;
                labelLikes.setText(String.valueOf(likedCount));
                studentLikeController.changeLike(currentStudent, postData);
            }

            int dislikedCount = Integer.parseInt(labelDislikes.getText());
            dislikedCount++;
            labelDislikes.setText(String.valueOf(dislikedCount));
            studentDislikeController.changeDislike(currentStudent, postData);
        }
    }
}
