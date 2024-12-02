package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_explore_clubs.ExploreClubsController;
import interface_adapter.student_explore_clubs.ExploreClubsState;
import interface_adapter.student_explore_clubs.ExploreClubsViewModel;
import interface_adapter.student_home.student_join_club.JoinClubController;
import interface_adapter.student_home.student_leave_club.LeaveClubController;
import interface_adapter.student_home.student_show_clubs.StudentShowClubsController;
import interface_adapter.student_home.student_show_posts.StudentShowPostsController;

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
    private StudentShowClubsController studentShowClubsController;
    private StudentShowPostsController studentShowPostsController;

    private ExploreClubsViewModel exploreClubsViewModel;

    // TODO: Change all string panel views to constants form CONSTANTS file
    private final String viewName = "ClubPageView";

    public ClubPageView(ExploreClubsViewModel exploreClubsViewModel) {
        this.exploreClubsViewModel = exploreClubsViewModel;
        exploreClubsViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(contentPanel);
        final ExploreClubsState exploreClubsState = exploreClubsViewModel.getState();
        this.clubNameLabel.setText(exploreClubsState.getCurrentClubName());
        this.description.setText("<html><p style=\"width:350px\">" + exploreClubsState.getCurrentClubDescription()
                + "</p></html>");
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

        // Add listener to join button such that it calls the join use case if the student is not a member of the club,
        // and similarly the leave use case if a member is a part of the club.
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                final boolean isMemberCheck = exploreClubsState.getJoinedClubEmails()
                        .contains(exploreClubsState.getCurrentClubEmail());
                // Check if student is in club or not
                if (isMemberCheck) {
                    // Update the member count on the view and run leave club usecase.
                    final Integer intMembers = Integer.valueOf(exploreClubsState.getCurrentNumberOfMembersString()) - 1;
                    exploreClubsState.setCurrentNumberOfMembersString(intMembers.toString());
                    exploreClubsViewModel.setState(exploreClubsState);
                    leaveClubController.leaveClub(exploreClubsState.getStudentEmail(),
                            exploreClubsState.getCurrentClubEmail());
                    // Update the student home page
                    studentShowClubsController.execute(exploreClubsState.getStudentEmail());
                    studentShowPostsController.execute(exploreClubsState.getStudentEmail());
                }
                else {
                    // Update the member count on the view.
                    final Integer intMembers = Integer.valueOf(exploreClubsState.getCurrentNumberOfMembersString()) + 1;
                    exploreClubsState.setCurrentNumberOfMembersString(intMembers.toString());
                    // Run the join club use case
                    joinClubController.joinClub(exploreClubsState.getStudentEmail(),
                            exploreClubsState.getCurrentClubEmail());
                    // Update the student home page
                    studentShowClubsController.execute(exploreClubsState.getStudentEmail());
                    studentShowPostsController.execute(exploreClubsState.getStudentEmail());
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

    public void setStudentShowClubsController(StudentShowClubsController studentShowClubsController) {
        this.studentShowClubsController = studentShowClubsController;
    }

    public void setStudentShowPostsController(StudentShowPostsController studentShowPostsController) {
        this.studentShowPostsController = studentShowPostsController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ExploreClubsState state = (ExploreClubsState) evt.getNewValue();

        final String memberNumberMessage = "Number of members: ";

        if (evt.getPropertyName().equals("state")) {
            this.clubNameLabel.setText(state.getCurrentClubName());
            this.description.setText("<html><p style=\"width:350px\">" + state.getCurrentClubDescription()
                    + "</p></html>");
            this.email.setText("Contact info: " + state.getCurrentClubEmail());
            this.numMembers.setText(memberNumberMessage + state.getCurrentNumberOfMembersString());

            // Check to see if the student is a member of the club.
            // The purpose is to locally check if the student is a member and set the text of the button reducing the
            // strain on the database and improves speed.
            final boolean isMember = state.getJoinedClubEmails().contains(state.getCurrentClubEmail());
            if (isMember) {
                joinButton.setText("Leave Club");
                this.numMembers.setText(memberNumberMessage + state.getCurrentNumberOfMembersString());
            }
            else {
                joinButton.setText("Join Club");
                this.numMembers.setText(memberNumberMessage + state.getCurrentNumberOfMembersString());
            }
        }
    }
}
