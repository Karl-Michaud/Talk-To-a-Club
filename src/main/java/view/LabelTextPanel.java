package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A panel containing a label and a text field. Convenient for redundant code.
 */
public class LabelTextPanel extends JPanel {
    // Used for small text fields.
    protected LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }

    // Used for bigger text fields
    protected LabelTextPanel(JLabel label, JTextArea textArea) {
        this.add(label);
        this.add(textArea);
    }
}
