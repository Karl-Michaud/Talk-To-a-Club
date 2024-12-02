package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.student_explore_clubs.ExploreClubsController;
import interface_adapter.student_explore_clubs.ExploreClubsState;
import interface_adapter.student_explore_clubs.ExploreClubsViewModel;

/**
 * Main view for the Explore clubs use case.
 */
public class ExploreClubsView extends JPanel implements PropertyChangeListener {
    private JPanel explorePanel;
    private JButton backButton;
    private JScrollPane scrollPanel;
    private JLabel explorePageTitle;

    private final String viewName = "explore clubs";
    private ExploreClubsController exploreClubsController;
    private ExploreClubsViewModel exploreClubsViewModel;

    public ExploreClubsView(ExploreClubsViewModel exploreClubsViewModel) {
        this.exploreClubsViewModel = exploreClubsViewModel;
        this.exploreClubsViewModel.addPropertyChangeListener(this);
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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ExploreClubsState state = exploreClubsViewModel.getState();
            scrollPanel.setViewportView(new ClubDescriptionExploreContainer(state,
                    exploreClubsController));
        }
        if (evt.getPropertyName().equals("fail join")) {
            JOptionPane.showMessageDialog(this, "Failed to join club");
        }
        if (evt.getPropertyName().equals("fail leave")) {
            JOptionPane.showMessageDialog(this, "Failed to leave club");
        }
    }

    public void setExploreClubsController(ExploreClubsController exploreClubsController) {
        this.exploreClubsController = exploreClubsController;
    }

    public String getViewName() {
        return viewName;
    }
}
