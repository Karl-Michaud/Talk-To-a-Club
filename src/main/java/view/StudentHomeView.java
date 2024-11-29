package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.logout.LogoutController;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsController;
import interface_adapter.student_logged_in.student_home.StudentHomeController;
import interface_adapter.student_logged_in.student_home.StudentHomeState;
import interface_adapter.student_logged_in.student_home.StudentHomeViewModel;
import interface_adapter.student_logged_in.student_home.dislike.StudentDislikeController;
import interface_adapter.student_logged_in.student_home.like.StudentLikeController;
import interface_adapter.student_logged_in.student_home.show_clubs.StudentShowClubsController;
import interface_adapter.student_logged_in.student_home.show_posts.StudentShowPostsController;

/**
 * This is the view for the homepage that the student will be greated with upon logging in. This will show all the clubs
 * the student follows, as well as current posts by these clubs, ordered by date.
 */
public class StudentHomeView extends JPanel implements PropertyChangeListener {
    private JPanel panelStudentHomeView;
    private JTextField textFieldClubSearch;
    private JButton buttonLogout;
    private JButton buttonProfile;
    private JButton buttonSearch;
    private JScrollPane pageScrollPane;
    private JLabel labelLogo;
    private JLabel labelSearch;

    private final String viewName = "student home";
    private final StudentHomeViewModel studentHomeViewModel;
    private StudentHomeController studentHomeController;
    private StudentShowPostsController studentShowPostsController;
    private StudentLikeController likeController;
    private StudentShowClubsController studentShowClubsController;
    private StudentDislikeController dislikeController;
    private LogoutController logoutController;
    private ExploreClubsController exploreClubsController;

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.studentHomeViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSearchListener();
        this.add(panelStudentHomeView);

        buttonLogout.addActionListener(
                evt -> logoutController.execute()
        );

        buttonProfile.addActionListener(
                evt -> studentHomeController.switchToProfileView()
        );

        buttonSearch.addActionListener(
                evt -> {
                    if (evt.getSource().equals(buttonSearch) && buttonSearch.getText().isEmpty()) {
                        final StudentHomeState currentState = studentHomeViewModel.getState();
                        exploreClubsController.execute(currentState.getCurrentUser());
                    }
                }
        );
    }

    private void addSearchListener() {
        textFieldClubSearch.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final StudentHomeState currentState = studentHomeViewModel.getState();
                currentState.setQuery(textFieldClubSearch.getText());
                studentHomeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final StudentHomeState currentState = studentHomeViewModel.getState();
            studentShowClubsController.execute(currentState.getCurrentUser());
            studentShowPostsController.execute(currentState.getCurrentUser());
        }
        else if (evt.getPropertyName().equals("show clubs") || evt.getPropertyName().equals("show posts")) {
            final StudentHomeState currentState = studentHomeViewModel.getState();
            final List<Map<String, String>> clubNames = currentState.getClubs();
            final Map<String, List<Map<String, Object>>> postData = currentState.getPostData();
            pageScrollPane.setViewportView(new PageView(new PostsContainer(postData, currentState.getCurrentUser(),
                    likeController, dislikeController), new ClubsContainer(clubNames)));
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setStudentHomeController(StudentHomeController studentHomeController) {
        this.studentHomeController = studentHomeController;
    }

    public void setShowPostsController(StudentShowPostsController showPostsController) {
        this.studentShowPostsController = showPostsController;
    }

    public void setLikeController(StudentLikeController likeController) {
        this.likeController = likeController;
    }

    public void setShowClubsController(StudentShowClubsController showClubsController) {
        this.studentShowClubsController = showClubsController;
    }

    public void setDislikeController(StudentDislikeController dislikeController) {
        this.dislikeController = dislikeController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setExploreClubsController(ExploreClubsController exploreClubsController) {
        this.exploreClubsController = exploreClubsController;
    }
}
