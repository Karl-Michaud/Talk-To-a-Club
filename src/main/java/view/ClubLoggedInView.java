package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_create_post.ClubCreatePostController;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import interface_adapter.club_logged_in.club_get_members.ClubGetMembersController;
import interface_adapter.club_logged_in.club_get_posts.ClubGetPostsController;
import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberController;
import interface_adapter.club_logged_in.club_update_desc.ClubUpdateDescController;
import interface_adapter.logout.LogoutController;

/**
 * The Club Logged View.
 */
public class ClubLoggedInView extends JPanel implements PropertyChangeListener {
    // Instance Variables of Displayed Elements
    private JPanel panelClubHome;
    private JPanel membersPanel;
    private JPanel postsPanel;
    private JPanel descriptionPanel;
    private JPanel logoPanel;
    private JPanel optionsPanel;
    private JButton updateDescriptionButton;
    private JButton logoutButton;
    private JButton refreshButton;
    private JButton createPostButton;
    private JLabel logoLabel;
    private JLabel message;
    private JScrollPane membersScrollPane;
    private JScrollPane descScrollPane;
    private JScrollPane postsScrollPane;
    private JTextArea descriptionTextArea;
    private JLabel clubName;

    private final String viewName = "club logged in";
    private final ClubLoggedInViewModel clubLoggedInViewModel;

    private ClubCreatePostController clubCreatePostController;
    private LogoutController logoutController;
    private ClubGetPostsController clubGetPostsController;
    private ClubGetMembersController clubGetMembersController;
    private ClubUpdateDescController clubUpdateDescController;
    private ClubRemoveMemberController clubRemoveMemberController;

    public ClubLoggedInView(ClubLoggedInViewModel clubLoggedInViewModel) {
        // Saves the view model to this view.
        this.clubLoggedInViewModel = clubLoggedInViewModel;

        // Makes this View listen to property changes from the saved view model
        this.clubLoggedInViewModel.addPropertyChangeListener(this);

        // Sets this view's layout and sets the view's main panel to be displayed
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelClubHome);

        // Adds a text listener to the description text area
        addDescriptionTextAreaListener();

        createPostButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createPostButton)) {
                            clubCreatePostController.switchToCreatePostView();
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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();

            // Clears the message from the view
            state.setMessage("");

            // Displays the club name in the view
            clubName.setText(state.getClubName());

            // Sets the text area to have the club's current description
            descriptionTextArea.setText(state.getDescriptionTextArea());

            // Calls the controller to get the club's members and posts
            clubGetMembersController.execute(state.getEmail());
            clubGetPostsController.execute(state.getEmail());
        }
        else if (evt.getPropertyName().equals("reload posts")) {
            // Gets the updated state
            final ClubLoggedInState updatedState = (ClubLoggedInState) evt.getNewValue();

            // Stores the list of post titles and post bodies
            final List<String> postTitles = updatedState.getPostTitles();
            final List<String> postBodies = updatedState.getPostBodies();

            // Creates a panel for every post body and title retrieved and adds it to a new JPanel called posts
            // Counts backwards to create the latest posts first
            final JPanel posts = new JPanel();
            posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));

            for (int i = postTitles.size() - 1; i >= 0; i--) {
                final PostTextPanel postPanel = new PostTextPanel(postTitles.get(i), postBodies.get(i));

                posts.add(postPanel.getPostPanel());
            }
            // Sets the Viewport of the postsScrollPane to the new JPanel posts
            postsScrollPane.setViewportView(posts);
        }
        else if (evt.getPropertyName().equals("reload members")) {
            // Gets the updated current state
            final ClubLoggedInState updatedState = (ClubLoggedInState) evt.getNewValue();

            // Stores the list of member names and emails
            final List<String> memberNames = updatedState.getMembersName();
            final List<String> memberEmails = updatedState.getMembersEmail();

            // Creates a panel for every member retrieved and adds it to a new JPanel called members
            final JPanel members = new JPanel();
            members.setLayout(new BoxLayout(members, BoxLayout.Y_AXIS));

            for (int i = 0; i < memberNames.size(); i++) {
                final RemoveMemberPanel memberPanel = new RemoveMemberPanel(clubRemoveMemberController,
                        updatedState.getEmail(), memberEmails.get(i), memberNames.get(i));
                members.add(memberPanel.getMemberPanel());
            }
            // Sets the Viewport of the membersScrollPane to the new JPanel members
            membersScrollPane.setViewportView(members);
        }
        else if (evt.getPropertyName().equals("show message")) {
            // gets the state's message and displays it in a message popup
            final ClubLoggedInState state = (ClubLoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, state.getMessage());
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

    public void setClubCreatePostController(ClubCreatePostController controller) {
        this.clubCreatePostController = controller;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setClubGetPostsController(ClubGetPostsController controller) {
        this.clubGetPostsController = controller;
    }

    public void setClubGetMembersController(ClubGetMembersController controller) {
        this.clubGetMembersController = controller;
    }

    public void setClubUpdateDescController(ClubUpdateDescController controller) {
        this.clubUpdateDescController = controller;
    }

    public void setClubRemoveMemberController(ClubRemoveMemberController controller) {
        this.clubRemoveMemberController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
