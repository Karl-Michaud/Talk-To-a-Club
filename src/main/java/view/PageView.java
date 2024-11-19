package view;

import javax.swing.*;

public class PageView extends JPanel {
    private JPanel pagePanel;
    private JLabel labelClubsJoined;
    private JPanel panelClubsJoined;
    private JPanel panelPosts;

    public PageView(PostsContainer postsContainer, ClubsContainer clubsContainer) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(pagePanel);
        this.panelPosts.setLayout(new BoxLayout(this.panelPosts, BoxLayout.Y_AXIS));
        this.panelClubsJoined.setLayout(new BoxLayout(this.panelClubsJoined, BoxLayout.Y_AXIS));
        this.panelPosts.add(postsContainer);
        this.panelClubsJoined.add(clubsContainer);
    }
}
