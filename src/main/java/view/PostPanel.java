package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.post.Post;
import entity.user.User;

/**
 * A panel which will hold the data for a single post. A list of these in the student home view will be contained
 * within a scrollable field.
 */
public class PostPanel extends JPanel {
    private JLabel labelPostTitle;
    private JButton buttonLike;
    private JButton buttonDislike;
    private JPanel postPanel;
    private JLabel labelPostContent;
    private JLabel labelClub;

    public PostPanel(Post post, String clubName, User currUser) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(postPanel);
        this.labelPostTitle.setText(post.getTitle());
        this.labelPostContent.setText(post.getContent());
        this.labelClub.setText(clubName);
        this.setBorder(BorderFactory.createBevelBorder(1));

        buttonLike.addActionListener(new ActionListener() {
            private final Icon likeEmptyIcon = new ImageIcon("src/main/resources/like.png");
            private final Icon likeFilledIcon = new ImageIcon("src/main/resources/like_filled.png");
            @Override
            public void actionPerformed(ActionEvent e) {

                if (post.getLikes().contains(currUser)) {
                    post.removeLike(currUser);
                    buttonLike.setIcon(likeEmptyIcon);
                }
                else {
                    post.addLike(currUser);
                    buttonLike.setIcon(likeFilledIcon);
                }
            }
        });

        buttonDislike.addActionListener(new ActionListener() {
            private final Icon dislikeEmptyIcon = new ImageIcon("src/main/resources/dislike.png");
            private final Icon dislikeFilledIcon = new ImageIcon("src/main/resources/dislike_filled.png");
            @Override
            public void actionPerformed(ActionEvent e) {
                if (post.getDislikes().contains(currUser)) {
                    post.removeDislike(currUser);
                    buttonDislike.setIcon(dislikeEmptyIcon);
                }
                else {
                    post.addDislike(currUser);
                    buttonDislike.setIcon(dislikeFilledIcon);
                }
            }
        });
    }
}
