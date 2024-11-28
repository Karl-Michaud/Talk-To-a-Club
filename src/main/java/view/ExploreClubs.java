package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.student_home.StudentHomeController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsViewModel;

/**
 * Main view for the Explore clubs use case.
 */
public class ExploreClubs extends JPanel {
    private JPanel explorePanel;
    private JButton backButton;
    private JScrollPane scrollPanel;

    private final String viewName = "explore clubs";
    private ExploreClubsController exploreClubsController;
    private StudentHomeController studentHomeViewController;
    private ExploreClubsViewModel exploreClubsViewModel;

    public ExploreClubs(ExploreClubsViewModel exploreClubsViewModel) {
        this.exploreClubsViewModel = exploreClubsViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(explorePanel);

        backButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(backButton)) {
                        exploreClubsController.switchToHomeView();
                    }
                }
            }
        );
        final ExploreClubsState state = exploreClubsViewModel.getState();
        final ClubDescriptionExploreContainer clubDescriptionExploreContainer =
                new ClubDescriptionExploreContainer(state, exploreClubsController);

        this.scrollPanel.setViewportView(new JScrollPane(clubDescriptionExploreContainer));
    }

    public void setExploreClubsController(ExploreClubsController exploreClubsController) {
        this.exploreClubsController = exploreClubsController;
    }

    public void setStudentHomeViewController(StudentHomeController studentHomeController) {
        this.studentHomeViewController = studentHomeViewController;
    }

    public String getViewName() {
        return viewName;
    }

}
