package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_logged_in.club_create_post.ClubCreatePostController;
import interface_adapter.club_get_posts.ClubGetPostsController;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import interface_adapter.club_logged_in.club_get_members.ClubGetMembersController;
import interface_adapter.club_update_desc.ClubUpdateDescController;
import interface_adapter.logout.LogoutController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The Club Logged View.
 */
public class ClubLoggedInView extends JPanel implements PropertyChangeListener {
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

    private final String viewName = "club logged in";
    private final ClubLoggedInViewModel clubLoggedInViewModel;

    private ClubCreatePostController clubCreatePostController;
    private LogoutController logoutController;
    private ClubGetPostsController clubGetPostsController;
    private ClubGetMembersController clubGetMembersController;
    private ClubUpdateDescController clubUpdateDescController;

    public ClubLoggedInView(ClubLoggedInViewModel clubLoggedInViewModel) {
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.clubLoggedInViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelClubHome);

        createPostButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createPostButton)) {
                            // createPostController.switchToCreatePost(); // TODO By Karl
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
                            // Executes both the Get Posts and Get Members usecase to get an updated version of them
                            final ClubLoggedInState currentState = clubLoggedInViewModel.getState();
                            clubGetPostsController.execute(currentState.getEmail());
                            clubGetMembersController.execute(currentState.getEmail());
                        }
                    }
                }
        );

        updateDescriptionButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(updateDescriptionButton)) {
                            // Gets the current view state containing the new description in the TextArea
                            final ClubLoggedInState currentState = clubLoggedInViewModel.getState();

                            // Executes the update description usecase
                            clubUpdateDescController.execute(currentState.getEmail(),
                                    currentState.getDescriptionTextArea());
                        }
                    }
                }
        );

        addDescriptionTextAreaListener();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();
            clubGetMembersController.execute(state.getEmail());
            clubGetPostsController.execute(state.getEmail());
        }
        else if (evt.getPropertyName().equals("create post")) {
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();
            clubGetPostsController.execute(state.getEmail());
            // TODO add panels into the postsScrollPane of each post
        }
        else if (evt.getPropertyName().equals("get members")) {
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();
            clubGetMembersController.execute(state.getEmail());
            // TODO add panels into the membersScrollPane of each student
        }
        else if (evt.getPropertyName().equals("reload message")) {
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();
            message.setText(state.getMessage());
        }
    }

    /**
     * Adds a listener to the descriptionTextArea to update the club logged in state.
     */
    private void addDescriptionTextAreaListener() {
        descriptionTextArea.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ClubLoggedInState currentState = clubLoggedInViewModel.getState();
                currentState.setDescriptionTextArea(descriptionTextArea.getText());
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

    public void setCreatePostController(ClubCreatePostController clubCreatePostController) {
        this.clubCreatePostController = clubCreatePostController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setClubGetPostsController(ClubGetPostsController clubGetPostsController) {
        this.clubGetPostsController = clubGetPostsController;
    }

    public void setGetMembersController(ClubGetMembersController clubGetMembersController) {
        this.clubGetMembersController = clubGetMembersController;
    }

    public void setClubUpdateDescController(ClubUpdateDescController clubUpdateDescController) {
        this.clubUpdateDescController = clubUpdateDescController;
    }

    public String getViewName() {
        return viewName;
    }
}
