package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_home.ClubHomeController;
import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.signup.club_signup.ClubSignupState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Club Home View.
 */
public class ClubHomeView extends JPanel implements PropertyChangeListener {
    private JPanel panelClubHome;
    private JLabel idk;
    private JButton createPostButton;

    private final String viewName = "club home";
    private final ClubHomeViewModel clubHomeViewModel;

    private ClubHomeController clubHomeController;

    public ClubHomeView(ClubHomeViewModel clubHomeViewModel) {
        this.clubHomeViewModel = clubHomeViewModel;
        this.clubHomeViewModel.addPropertyChangeListener(this);

        createPostButton.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createPostButton)) {
                            // Sets the state to be signing up a club before executing the signup use case
                            final ClubSignupState currentState = signupViewModel.getState();

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




        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelClubHome);

        // TODO add action listerners to the buttons here
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public void setClubHomeController(ClubHomeController clubHomeController) {
        this.clubHomeController = clubHomeController;
    }

    public String getViewName() {
        return viewName;
    }





}
