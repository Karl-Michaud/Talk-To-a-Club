package view;

import entity.user.Club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClubMiniPanel extends JPanel {
    private JPanel clubMiniPanel;
    private JLabel clubName;
    private JLabel description;
    private JButton viewMore;

    private final int descriptionLength = 50;

    public ClubMiniPanel(Club club) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(clubMiniPanel);
        this.clubName.setText(club.getUsername());

        // TODO replace with description of club.
        this.description.setText("Description: " + club.getEmail().substring(0,
                Math.min(club.getEmail().length(), descriptionLength)) + "...");
        this.setBorder(BorderFactory.createBevelBorder(1));

        viewMore.addActionListener(new ActionListener() {
            
        });
    }
}
