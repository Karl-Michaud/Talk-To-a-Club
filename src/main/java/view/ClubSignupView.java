package view;

import interface_adapter.signup.club_signup.ClubSignupController;
import interface_adapter.signup.club_signup.ClubSignupState;
import interface_adapter.signup.club_signup.ClubSignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClubSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "club sign up";

    private JButton switchToLogInButton;
    private JButton signUpAsClubButton;
    private JPasswordField repeatPasswordField;
    private JPasswordField passwordField;
    private JTextField clubEmailField;
    private JTextField clubNameField;
    private JPanel panelClubSignup;

    private final ClubSignupViewModel clubSignupViewModel;
    private ClubSignupController signupController;

    public ClubSignupView(ClubSignupViewModel clubSignupViewModel) {
        this.clubSignupViewModel = clubSignupViewModel;
        clubSignupViewModel.addPropertyChangeListener(this);

        signUpAsClubButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUpAsClubButton)) {
                            final ClubSignupState currentState = clubSignupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getEmail(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        switchToLogInButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        signupController.switchToLoginView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelClubSignup);

        addClubNameListener();
        addEmailListener();
        addPasswordListener();
        addRepeatPasswordListener();
    }

    private void addEmailListener() {
        clubEmailField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubSignupState currentState = clubSignupViewModel.getState();
                currentState.setEmail(clubEmailField.getText());
                clubSignupViewModel.setState(currentState);
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

    private void addClubNameListener() {
        clubNameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubSignupState currentState = clubSignupViewModel.getState();
                currentState.setUsername(clubNameField.getText());
                clubSignupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubSignupState currentState = clubSignupViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                clubSignupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubSignupState currentState = clubSignupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordField.getPassword()));
                clubSignupViewModel.setState(currentState);
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
        final ClubSignupState state = (ClubSignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(ClubSignupController controller) {
        this.signupController = controller;
    }
}
