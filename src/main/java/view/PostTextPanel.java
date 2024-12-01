package view;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class of a JPanel which has a Title and Body for the text of a post.
 */
public class PostTextPanel {
    private JPanel postPanel;
    private JTextArea postTitleText;
    private JTextArea postBodyText;

    public PostTextPanel(String title, String body) {
        this.postTitleText.setText(title);
        this.postBodyText.setText(body);
    }

    public JPanel getPostPanel() {
        return this.postPanel;
    }
}
