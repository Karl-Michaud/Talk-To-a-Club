package view;

import entity.user.Club;

import javax.swing.*;

public class ClubMiniPanel extends JPanel {
    private JLabel clubName;
    private JPanel clubMiniPanel;

    public ClubMiniPanel(Club club) {
        this.clubName.setText(club.getUsername());
        this.setBorder(BorderFactory.createBevelBorder(1));
        clubMiniPanel.setLayout(new BoxLayout(clubMiniPanel, BoxLayout.Y_AXIS));
        this.add(clubMiniPanel);
    }
}
