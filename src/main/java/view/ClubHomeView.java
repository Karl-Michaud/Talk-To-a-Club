package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_create_post.CreatePostController;
import interface_adapter.club_get_posts.ClubGetPostsController;
import interface_adapter.club_home.ClubHomeController;
import interface_adapter.club_home.ClubLoggedInViewModel;
import interface_adapter.club_update_desc.ClubUpdateDescController;
import interface_adapter.logout.LogoutController;
import interface_adapter.signup.club_signup.ClubSignupState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Club Home View.
 */
public class ClubHomeView extends JPanel implements PropertyChangeListener {
    private JPanel panelClubHome;
    private JButton createPostButton;
    private JPanel membersPanel;
    private JPanel postsPanel;
    private JPanel descriptionPanel;
    private JButton updateDescriptionButton;
    private JTextArea descriptionTextArea;
    private JPanel optionsPanel;
    private JButton logoutButton;
    private JButton refreshButton;
    private JPanel logoPanel;
    private JLabel logoLabel;
    private JLabel message;
    private JScrollPane membersScrollPane;
    private JScrollPane descScrollPane;
    private JScrollPane postsScrollPane;

    private final String viewName = "club home";
    private final ClubLoggedInViewModel clubLoggedInViewModel;

    private ClubHomeController clubHomeController;
    private CreatePostController createPostController;  // TODO might change to one to swap to the view first
    private LogoutController logoutController;
    private ClubGetPostsController clubGetPostsController;
    private ClubGetMembersController clubGetMembersController;
    private ClubUpdateDescController clubUpdateDescController;

    public ClubHomeView(ClubLoggedInViewModel clubLoggedInViewModel) {
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.clubLoggedInViewModel.addPropertyChangeListener(this);

        createPostButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createPostButton)) {
                            // Sets the state to be signing up a club before executing the signup use case
                            final ClubSignupState currentState = signupViewModel.getState();

                            signupController.execute( // TODO
                                    currentState.getUsername(),
                                    currentState.getEmail(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        logoutButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logoutButton)) {
                            // Executes the Logout use case.
                            logoutController.execute();
                        }
                    }
                }
        );

        refreshButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refreshButton)) {
                            // Executes the Logout use case.
                            final ClubLoggedInState currentState = clubLoggedInViewModel.getState();
                            clubGetPostsController.execute(currentState.getClubEmail);
                            clubGetMembersController.execute(currentState.getClubEmail);
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

            // TODO call the controllers for getmembers and getposts
        }
        else if (evt.getPropertyName().equals("create post")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            // TODO call the controller for getposts
        }
        else if (evt.getPropertyName().equals("")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            // TODO call the controller for getmembers and fill in the .equals call
        }
        else if (evt.getPropertyName().equals("reload message")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            // make this.message into the one in loggedinstate
        }

    }

    /**
     * Adds a listener to the descriptionTextArea to update the club logged in state. TODO
     */
    private void addDescriptionTextAreaListener() {
        descriptionTextArea.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubSignupState currentState = clubLoggedInViewModel.getState();
                currentState.setEmail(descriptionTextArea.getText());
                clubLoggedInViewModel.setState(currentState);
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


    public void setClubHomeController(ClubHomeController clubHomeController) {
        this.clubHomeController = clubHomeController;
    }

    public void setCreatePostController(CreatePostController createPostController) {
        this.createPostController = createPostController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setClubGetPostsController(ClubGetPostsController clubGetPostsController) {
        this.clubGetPostsController = clubGetPostsController;
    }

    public void setClubGetMembersController(ClubGetMembersController clubGetMembersController) {
        this.clubGetMembersController = clubGetMembersController;
    }

    public void setClubUpdateDescController(ClubUpdateDescController clubUpdateDescController) {
        this.clubUpdateDescController = clubUpdateDescController;
    }

    public String getViewName() {
        return viewName;
    }
}
