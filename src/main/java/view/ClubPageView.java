package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.signup.club_signup.ClubSignupState;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsViewModel;
import interface_adapter.student_logged_in.join_club.JoinClubController;
import interface_adapter.student_logged_in.leave_club.LeaveClubController;

/**
 * The view for the Club popup page when exploring clubs.
 */
public class ClubPageView extends JPanel implements PropertyChangeListener {
    private JPanel contentPanel;
    private JButton joinButton;
    private JButton backButton;
    private JLabel clubNameLabel;
    private JLabel description;
    private JLabel email;
    private JLabel numMembers;
    private JLabel logoLabel;
    private ExploreClubsController exploreClubsController;
    private JoinClubController joinClubController;
    private LeaveClubController leaveClubController;

    // TODO: Change all string panel views to constants form CONSTANTS file
    private final String viewName = "ClubPageView";

    public ClubPageView(ExploreClubsViewModel exploreClubsViewModel) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(contentPanel);
        final ExploreClubsState exploreClubsState = exploreClubsViewModel.getState();
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
                final ExploreClubsState state = exploreClubsViewModel.getState();
                exploreClubsController.execute(state.getStudentEmail());
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
                    leaveClubController.leaveClub(exploreClubsState.getStudentEmail(),
                            exploreClubsState.getCurrentClubEmail());
                    // Change the join button text
                    joinButton.setText("Join Club");
                    // Update the member count on the view.
                    final Integer intMembers = Integer.valueOf(exploreClubsState.getCurrentNumberOfMembersString()) - 1;
                    numMembers.setText(intMembers.toString());
                }
                else {
                    // Run the join club use case
                    joinClubController.joinClub(exploreClubsState.getStudentEmail(),
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
        return this.viewName;
    }

    public void setExploreClubsController(ExploreClubsController exploreClubsController) {
        this.exploreClubsController = exploreClubsController;
    }

    public void setJoinClubController(JoinClubController joinClubController) {
        this.joinClubController = joinClubController;
    }

    public void setLeaveClubController(LeaveClubController leaveClubController) {
        this.leaveClubController = leaveClubController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ExploreClubsState state = (ExploreClubsState) evt.getNewValue();
        if (evt.getPropertyName().equals("state")) {
            this.clubNameLabel.setText(state.getCurrentClubName());
            this.description.setText(state.getCurrentClubDescription());
            this.email.setText("Contact info: " + state.getCurrentClubEmail());
            this.numMembers.setText("Number of members: " + state.getCurrentNumberOfMembersString());
        }
    }
}
