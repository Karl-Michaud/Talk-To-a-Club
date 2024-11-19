package view;

import javax.swing.*;

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

    public PostPanel(String postTitle, String postContent, String clubName) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(postPanel);
        this.labelPostTitle.setText(postTitle);
        this.labelPostContent.setText(postContent);
        this.labelClub.setText(clubName);
        this.setBorder(BorderFactory.createBevelBorder(1));
    }
}
