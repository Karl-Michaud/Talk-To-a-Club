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

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.studentHomeViewModel.addPropertyChangeListener(this);

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
                                currentState.getEmail()
                        );
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSearchListener();
        this.add(panelStudentHomeView);
        final StudentHomeState state = studentHomeViewModel.getState();
        this.pageScrollPane.setViewportView(new PageView(new PostsContainer(state), new ClubsContainer(state)));
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
}
