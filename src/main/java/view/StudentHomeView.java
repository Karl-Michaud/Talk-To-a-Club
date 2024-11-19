package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
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
    private JLabel labelSearch;
    private JLabel labelLogo;
    private JPanel panelJoinedClubs;
    private JButton buttonLogout;
    private JButton buttonProfile;
    private JScrollPane scrollPaneEvents;
    private JButton buttonSearch;

    private final String viewName = "student home";
    private final StudentHomeViewModel studentHomeViewModel;
    private StudentHomeController studentHomeController;

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.studentHomeViewModel.addPropertyChangeListener(this);

        buttonLogout.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        studentHomeController.switchToLoginView();
                    }
                }
        );

        buttonProfile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        studentHomeController.switchToLoginView();
                    }
                }
        );

        buttonSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonSearch)) {
                            final StudentHomeState currentState = studentHomeViewModel.getState();

                            studentHomeController.execute(
                                    currentState.getSearch()
                            );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelStudentHomeView);
    }

    private void addSearchListener() {
        textFieldClubSearch.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final StudentHomeState currentState = studentHomeViewModel.getState();
                currentState.setSearch(textFieldClubSearch.getText());
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
