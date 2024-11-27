package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The view for the Club popup page when exploring clubs.
 */
public class ClubPageView {
    private JPanel contentPanel;
    private JButton joinButton;
    private JButton backButton;
    private JLabel clubNameLabel;
    private JLabel description;
    private JLabel email;
    private JLabel numMembers;

//    // TODO: Change all string panel views to constants form CONSTANTS file
//    private final String viewName = "ClubDescriptionView";
//
//    public ClubPageView(ExploreClubsState exploreClubsState, ExploreClubsPresenter exploreClubsPresenter,
//                               JoinClubController joinController, LeaveClubController leaveController) {
//        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        this.add(contentPanel);
//        this.clubNameLabel.setText(exploreClubsState.getSelectedClub().getUsername());
//        this.email.setText(exploreClubsState.getSelectedClub().getEmail());
//        this.numMembers.setText("Number of members: " + exploreClubsState.getClubs().size().toString());
//
//        // TODO add the the get club description
//        this.description.setText(exploreClubsState.getSelectedClub().getUsername());
//
//        final boolean isMember = exploreClubsState.getSelectedClub().getClubMembers()
//                .contains(exploreClubsState.getCurrentStudent());
//
//        if (isMember) {
//            joinButton.setText("Leave Club");
//        }
//        else {
//            joinButton.setText("Join Club");
//        }
//        this.backButton.setText("Back");
//
//        this.setBorder(BorderFactory.createBevelBorder(1));
//
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                exploreClubsPresenter.switchToHomeView();
//            }
//        });
//
//        // Join / Leave button action.
//        joinButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (exploreClubsState.getSelectedClub() != null) {
//                    final Student student = exploreClubsState.getCurrentStudent();
//                    final Club club = exploreClubsState.getSelectedClub();
//
//                    if (club.getClubMembers().contains(student)) {
//                        leaveController.leaveClub(student.getEmail(), club.getEmail());
//                        joinButton.setText("Join Club");
//                    }
//                    else {
//                        joinController.joinClub(student.getEmail(),
//                                club.getEmail());
//                        joinButton.setText("Leave Club");
//                    }
//                }
//            }
//        });
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
}
