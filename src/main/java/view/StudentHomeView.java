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

import interface_adapter.student_home.StudentHomeController;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import interface_adapter.student_home.dislike.StudentDislikeController;
import interface_adapter.student_home.like.StudentLikeController;
import interface_adapter.student_home.show_clubs.ShowClubsController;
import interface_adapter.student_home.show_posts.ShowPostsController;

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
    private ShowPostsController showPostsController;
    private StudentLikeController likeController;
    private ShowClubsController showClubsController;
    private StudentDislikeController dislikeController;

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.studentHomeViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSearchListener();
        this.add(panelStudentHomeView);

        buttonLogout.addActionListener(
                evt -> studentHomeController.switchToLoginView()
        );

        buttonProfile.addActionListener(
                evt -> studentHomeController.switchToProfileView()
        );

        buttonSearch.addActionListener(
                evt -> {
                    if (evt.getSource().equals(buttonSearch)) {
                        // TODO: link to explore page.
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
            showClubsController.execute(currentState.getCurrentUser());
            showPostsController.execute(currentState.getCurrentUser());
        }
        else if (evt.getPropertyName().equals("show clubs") || evt.getPropertyName().equals("show posts")) {
            final StudentHomeState currentState = studentHomeViewModel.getState();
            showClubsController.execute(currentState.getCurrentUser());
            showPostsController.execute(currentState.getCurrentUser());
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

    public void setShowPostsController(ShowPostsController showPostsController) {
        this.showPostsController = showPostsController;
    }

    public void setLikeController(StudentLikeController likeController) {
        this.likeController = likeController;
    }

    public void setShowClubsController(ShowClubsController showClubsController) {
        this.showClubsController = showClubsController;
    }

    public void setDislikeController(StudentDislikeController dislikeController) {
        this.dislikeController = dislikeController;
    }
}
