package view;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.club_login.ClubLoginController;
import interface_adapter.login.student_login.StudentLoginController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class
LoginView extends JPanel implements PropertyChangeListener {
    private final String viewName = "login";

    private JButton buttonLogin;
    private JButton studentSignupButton;
    private JTextField userIdentifierField;
    private JPasswordField passwordField;
    private JPanel panelLogin;
    private JLabel labelPassword;
    private JLabel labelUserIdentifier;
    private JLabel labelError;
    private JLabel labelLogo;
    private JButton clubSignupButton;
    private JCheckBox clubLoginCheckBox;

    private final LoginViewModel loginViewModel;
    private ClubLoginController clubLoginController;
    private StudentLoginController studentLoginController;

    public LoginView(LoginViewModel loginViewModel) {

        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        clubLoginCheckBox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clubLoginCheckBox)) {
                            final LoginState currentState = loginViewModel.getState();
                            currentState.setIdentifier(currentState.getIdentifier());
                            loginViewModel.setState(currentState);
                        }
                    }
                }
        );

        buttonLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonLogin)) {
                            final LoginState currentState = loginViewModel.getState();

                            if (clubLoginCheckBox.isSelected()) {
                                clubLoginController.execute(
                                        currentState.getIdentifier(),
                                        currentState.getPassword()
                                );
                            }
                            else {
                                studentLoginController.execute(
                                        currentState.getIdentifier(),
                                        currentState.getPassword()
                                );
                            }
                        }
                    }
                }
        );

        studentSignupButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        studentLoginController.switchToStudentSignupView();
                    }
                }
        );

        clubSignupButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        clubLoginController.switchToClubSignupView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelLogin);

        addEmailListener();
        addPasswordListener();
    }

    private void addPasswordListener() {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                loginViewModel.setState(currentState);
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

    private void addEmailListener() {
        userIdentifierField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setIdentifier(userIdentifierField.getText());
                loginViewModel.setState(currentState);
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
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        labelError.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        userIdentifierField.setText(state.getIdentifier());
        passwordField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setClubLoginController(ClubLoginController clubLoginController) {
        this.clubLoginController = clubLoginController;
    }

    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
}
