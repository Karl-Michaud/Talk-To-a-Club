package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class of a JPanel which has a Title and Body for the text of a post.
 */
public class PostTextPanel extends JPanel {
    private JPanel postTextPanel;
    private JLabel postTitleText;
    private JLabel postBodyText;

    public PostTextPanel(String title, String body) {
        this.postTitleText.setText(title);
        this.postBodyText.setText(body);
    }
}
