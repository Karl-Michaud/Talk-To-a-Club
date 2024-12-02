package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_logged_in.club_create_post.ClubCreatePostController;
import interface_adapter.club_logged_in.club_create_post.ClubCreatePostState;
import interface_adapter.club_logged_in.club_create_post.ClubCreatePostViewModel;

/**
 * The View for when a club wants to create a post.
 */
public class ClubCreatePostView extends JPanel implements PropertyChangeListener {
    private int titleFontSize;

    // Initialize the necessary instance variables
    private final String viewName = "create post";
    private final ClubCreatePostViewModel clubCreatePostViewModel;

    // Text fields
    private final JTextArea titleTextField = new JTextArea(1, 40);
    private final JTextArea descriptionTextField = new JTextArea(10, 40);

    // Buttons
    private final JButton createPostButton = new JButton("Create Post");
    private final JButton cancelButton = new JButton("Cancel");

    // Controller to execute the use case
    private ClubCreatePostController clubCreatePostController;

    public ClubCreatePostView(ClubCreatePostViewModel clubCreatePostViewModel) {
        // Add property listener to the view model of club create post.
        this.clubCreatePostViewModel = clubCreatePostViewModel;
        this.clubCreatePostViewModel.addPropertyChangeListener(this);

        // Title at the top of the view.
        final int fontSize = 16;
        setTitleFontSize(fontSize);
        final JLabel title = new JLabel("New Post");
        title.setFont(new Font("Default", Font.BOLD, titleFontSize));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set line wrap to true (enables automatic back to line when you reach the end of the text area)
        this.descriptionTextField.setLineWrap(true);
        this.titleTextField.setLineWrap(true);

        // Set wrap style. When you reach the end of the line, if a word is cut off, then the whole word goes to
        // the new line.
        this.titleTextField.setWrapStyleWord(true);
        this.descriptionTextField.setWrapStyleWord(true);

        // Create the LabelText panel for the titleTexField and descriptionTextField.
        final LabelTextPanel titleFieldInfo = new LabelTextPanel(new JLabel("Title: "), titleTextField);
        final LabelTextPanel descriptionFieldInfo = new LabelTextPanel(new JLabel("Description: "),
                descriptionTextField);

        // Align vertically
        titleFieldInfo.setAlignmentY(Component.CENTER_ALIGNMENT);
        descriptionFieldInfo.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add buttons to the buttons panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(createPostButton);
        buttonPanel.add(cancelButton);

        // Set the main panel layout to vertical
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add document listeners to the title and description text fields through helper methods.
        setTitleFieldDocumentListner();
        setDescriptionTextFieldDocumentListner();

        // Add action listener to the cancel button
        cancelButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(cancelButton)) {
                        clubCreatePostController.switchToClubLoggedInView();
                        this.titleTextField.setText("");
                        this.descriptionTextField.setText("");
                    }
                }
        );

        // Add action listener to the create post button.
        createPostButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createPostButton)) {
                            // Get the current state
                            final ClubCreatePostState currentState = clubCreatePostViewModel.getState();

                            // Check if the title and content fields is null
                            String titleInputText = currentState.getTitle();
                            String descriptionInputText = currentState.getContent();
                            if (titleInputText == null) {
                                titleInputText = "";
                            }
                            else if (descriptionInputText == null) {
                                descriptionInputText = "";
                            }
                            // Execute the use case
                            clubCreatePostController.execute(currentState.getEmail(), titleInputText,
                                    descriptionInputText);
                        }
                    }
                }
        );

        // Add all panels to the main panel.
        this.add(title);
        this.add(titleFieldInfo);
        this.add(descriptionFieldInfo);
        this.add(buttonPanel);
    }

    // Helper functions for the constructor
    private void setDescriptionTextFieldDocumentListner() {
        descriptionTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ClubCreatePostState currentState = clubCreatePostViewModel.getState();
                currentState.setContent(descriptionTextField.getText());
                clubCreatePostViewModel.setState(currentState);
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

    private void setTitleFieldDocumentListner() {
        titleTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ClubCreatePostState currentState = clubCreatePostViewModel.getState();
                currentState.setTitle(titleTextField.getText());
                clubCreatePostViewModel.setState(currentState);
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
        if (evt.getPropertyName().equals("create post")) {
            JOptionPane.showMessageDialog(this, "Announcement successfully posted.");
            titleTextField.setText("");
            descriptionTextField.setText("");
        }
        else if (evt.getPropertyName().equals("create post error")) {
            final ClubCreatePostState state = (ClubCreatePostState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getCreatePostError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setClubCreatePostController(ClubCreatePostController controller) {
        this.clubCreatePostController = controller;
    }

    public void setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
}
