package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.student_home.StudentHomeController;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import interface_adapter.student_home.like.StudentLikeController;
import interface_adapter.student_home.show_clubs.ShowClubsController;
import interface_adapter.student_home.show_clubs.ShowClubsState;
import interface_adapter.student_home.show_clubs.ShowClubsViewModel;
import interface_adapter.student_home.show_posts.ShowPostsController;
import interface_adapter.student_home.show_posts.ShowPostsState;
import interface_adapter.student_home.show_posts.ShowPostsViewModel;

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
    private final ShowPostsViewModel showPostsViewModel;
    private final ShowClubsViewModel showClubsViewModel;
    private StudentHomeController studentHomeController;
    private ShowPostsController showPostsController;
    private StudentLikeController studentLikeController;
    private ShowClubsController showClubsController;

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel,
                           ShowPostsViewModel showPostsViewModel, ShowClubsViewModel showClubsViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.showPostsViewModel = new ShowPostsViewModel();
        this.showClubsViewModel = new ShowClubsViewModel();
        this.studentHomeViewModel.addPropertyChangeListener(this);
        this.showPostsViewModel.addPropertyChangeListener(this);

        buttonLogout.addActionListener(
                evt -> studentHomeController.switchToLoginView()
        );

        buttonProfile.addActionListener(
                evt -> studentHomeController.switchToProfileView()
        );

        buttonSearch.addActionListener(
                evt -> {
                    if (evt.getSource().equals(buttonSearch)) {
                        final StudentHomeState currentState = studentHomeViewModel.getState();

                        studentHomeController.execute(
                                currentState.getQuery(),
                                currentState.getCurrentUser()
                        );
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSearchListener();
        this.add(panelStudentHomeView);
        final ShowPostsState showPostsState = showPostsViewModel.getState();
        final ShowClubsState showClubsState = showClubsViewModel.getState();
        this.pageScrollPane.setViewportView(new PageView(new PostsContainer(showPostsState.getPosts(),
                showPostsState.getCurrentUser(), studentLikeController),
                new ClubsContainer(showClubsState.getClubNames())));
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
        final StudentHomeState state = (StudentHomeState) evt.getNewValue();
        showPostsController.execute(state.getCurrentUser());
        showClubsController.execute(state.getCurrentUser());
        final ShowPostsState showPostsState = showPostsViewModel.getState();
        final ShowClubsState showClubsState = showClubsViewModel.getState();
        this.pageScrollPane.setViewportView(new PageView(new PostsContainer(showPostsState.getPosts(),
                state.getCurrentUser(), studentLikeController), new ClubsContainer(showClubsState.getClubNames())));
        if (state.getStudentHomeError() != null) {
            JOptionPane.showMessageDialog(this, state.getStudentHomeError());
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
        this.studentLikeController = likeController;
    }

    public void setShowClubsController(ShowClubsController showClubsController) {
        this.showClubsController = showClubsController;
    }
}
