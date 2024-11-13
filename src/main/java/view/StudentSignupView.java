package view;

import interface_adapter.signup.student_signup.StudentSignupController;
import interface_adapter.signup.student_signup.StudentSignupState;
import interface_adapter.signup.student_signup.StudentSignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StudentSignupView extends JPanel implements PropertyChangeListener {
    private final String viewName = "student sign up";

    private JButton switchToLogInButton;
    private JButton signUpButton;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JTextField emailField;
    private JPanel panelStudentSignup;
    private JLabel labelIcon;
    private JLabel labelEmail;
    private JLabel labelPassword;
    private JLabel labelRepeatPassword;
    private JLabel labelError;
    private JTextField usernameField;
    private JLabel labelUsername;

    private final StudentSignupViewModel signupViewModel;
    private StudentSignupController signupController;

    public StudentSignupView(StudentSignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        signUpButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUpButton)) {
                            // Sets the state to not be signing up a club before executing the signup use case
                            final StudentSignupState currentState = signupViewModel.getState();

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
        this.add(panelStudentSignup);

        addUsernameListener();
        addEmailListener();
        addPasswordListener();
        addRepeatPasswordListener();
    }

    private void addEmailListener() {
        emailField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final StudentSignupState currentState = signupViewModel.getState();
                currentState.setEmail(emailField.getText());
                signupViewModel.setState(currentState);
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

    private void addUsernameListener() {
        usernameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final StudentSignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameField.getText());
                signupViewModel.setState(currentState);
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
                final StudentSignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                signupViewModel.setState(currentState);
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
                final StudentSignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordField.getPassword()));
                signupViewModel.setState(currentState);
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
        final StudentSignupState state = (StudentSignupState) evt.getNewValue();
        if (state.getSignupError() != null) {
            JOptionPane.showMessageDialog(this, state.getSignupError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(StudentSignupController controller) {
        this.signupController = controller;
    }
}
