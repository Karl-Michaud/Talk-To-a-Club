package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;
import interface_adapter.student_logged_in.join_club.JoinClubController;
import interface_adapter.student_logged_in.leave_club.LeaveClubController;

/**
 * The view for the Club popup page when exploring clubs.
 */
public class ClubPageView extends JPanel {
    private JPanel contentPanel;
    private JButton joinButton;
    private JButton backButton;
    private JLabel clubNameLabel;
    private JLabel description;
    private JLabel email;
    private JLabel numMembers;

    // TODO: Change all string panel views to constants form CONSTANTS file
    private final String viewName = "ClubPageView";

    public ClubPageView(ExploreClubsState exploreClubsState, ExploreClubsController exploreClubsContoller,
                        JoinClubController joinController, LeaveClubController leaveController) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(contentPanel);

        this.clubNameLabel.setText(exploreClubsState.getCurrentClubName());
        this.description.setText(exploreClubsState.getCurrentClubDescription());
        this.email.setText(exploreClubsState.getCurrentClubEmail());
        this.numMembers.setText(exploreClubsState.getCurrentNumberOfMembersString());

        this.setBorder(BorderFactory.createBevelBorder(1));

        // Add listener to back button such that when it is clicked, it reloads the student home view.
        this.backButton.setText("Back");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exploreClubsContoller.switchToHomeView();
            }
        });

        // Check to see if the student is a member of the club.
        // The purpose is to locally check if the student is a member and set the text of the button reducing the
        // strain on the database and improves speed.
        final boolean isMember = exploreClubsState.getJoinedClubEmails()
                .contains(exploreClubsState.getCurrentClubEmail());

        if (isMember) {
            joinButton.setText("Leave Club");
        }
        else {
            joinButton.setText("Join Club");
        }

        // Add listener to join button such that it calls the join use case if the student is not a member of the club,
        // and similarly the leave use case if a member is a part of the club.
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                final boolean isMemberCheck = exploreClubsState.getJoinedClubEmails()
                        .contains(exploreClubsState.getCurrentClubEmail());
                // Check if student is in club or not
                if (isMemberCheck) {
                    // Run the leave club use case
                    leaveController.leaveClub(exploreClubsState.getStudentEmail(),
                            exploreClubsState.getCurrentClubEmail());
                    // Change the join button text
                    joinButton.setText("Join Club");
                    // Update the member count on the view.
                    final Integer intMembers = Integer.valueOf(exploreClubsState.getCurrentNumberOfMembersString()) - 1;
                    numMembers.setText(intMembers.toString());
                }
                else {
                    // Run the join club use case
                    joinController.joinClub(exploreClubsState.getStudentEmail(),
                            exploreClubsState.getCurrentClubEmail());
                    // Change the join button text
                    joinButton.setText("Leave Club");
                    // Update the member count on the view
                    final Integer intMembers = Integer.valueOf(exploreClubsState.getCurrentNumberOfMembersString()) + 1;
                    numMembers.setText(intMembers.toString());
                }
            }
        });

    }

    public String getViewName() {
        return viewName;
    }
}
