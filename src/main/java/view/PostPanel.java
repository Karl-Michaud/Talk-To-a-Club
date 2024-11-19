package view;

import javax.swing.*;

/**
 * A panel which will hold the data for a single post. A list of these in the student home view will be contained
 * within a scrollable field.
 */
public class PostPanel extends JPanel {
    private JLabel labelPostTitle;
    private JTextArea textAreaPostContent;
    private JButton buttonLike;
    private JButton buttonDislike;
    private JPanel postPanel;

    public PostPanel(String postTitle, String postContent) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(postPanel);
        this.labelPostTitle.setText(postTitle);
        this.textAreaPostContent.setText(postContent);
    }
}
