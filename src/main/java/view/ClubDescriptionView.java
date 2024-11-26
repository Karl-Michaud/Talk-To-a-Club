package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.user.Club;
import entity.user.Student;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsPresenter;
import interface_adapter.student_logged_in.join_club.JoinClubController;
import interface_adapter.student_logged_in.leave_club.LeaveClubController;

/**
 * Club description view.
 */
public class ClubDescriptionView extends JPanel {
    private JPanel contentPane;
    private JButton joinLeave;
    private JLabel clubName;
    private JLabel clubDescription;
    private JLabel clubEmail;
    private JButton backToExplore;

    private final String viewName = "ClubDescriptionView";

    public ClubDescriptionView(ExploreClubsState exploreClubsState, ExploreClubsPresenter exploreClubsPresenter,
                               JoinClubController joinController, LeaveClubController leaveController) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(contentPane);
        this.clubName.setText(exploreClubsState.getSelectedClub().getUsername());
        this.clubEmail.setText(exploreClubsState.getSelectedClub().getEmail());

        // TODO add the the get club description
        this.clubDescription.setText(exploreClubsState.getSelectedClub().getUsername());

        final boolean isMember = exploreClubsState.getSelectedClub().getClubMembers()
                .contains(exploreClubsState.getCurrentStudent());

        if (isMember) {
            joinLeave.setText("Leave Club");
        }
        else {
            joinLeave.setText("Join Club");
        }
        this.backToExplore.setText("Back");

        this.setBorder(BorderFactory.createBevelBorder(1));

        backToExplore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exploreClubsPresenter.switchToHomeView();
            }
        });

        joinLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exploreClubsState.getSelectedClub() != null) {
                    final Student student = exploreClubsState.getCurrentStudent();
                    final Club club = exploreClubsState.getSelectedClub();

                    if (club.getClubMembers().contains(student)) {
                        leaveController.leaveClub(student.getEmail(), club.getEmail());
                        joinLeave.setText("Join Club");
                    }
                    else {
                        joinController.joinClub(student.getEmail(),
                                club.getEmail());
                        joinLeave.setText("Leave Club");
                    }
                }
            }
        });
    }

    public String getViewName() {
        return viewName;
    }
}
