package view;

import javax.swing.*;
import java.awt.*;

public class TempLoggedInView extends JPanel {
    private JPanel panel1;
    private JLabel labelUsername;
    private JLabel labelIcon;

    public TempLoggedInView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panel1);
    }
}
