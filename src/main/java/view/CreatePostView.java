package view;

import java.awt.Component;
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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.club_create_post.ClubCreatePostController;
import interface_adapter.club_create_post.CreatePostState;
import interface_adapter.club_create_post.ClubCreatePostViewModel;

/**
 * The View for when a club wants to create a post.
 */
public class CreatePostView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "create post";
    private final ClubCreatePostViewModel clubCreatePostViewModel;

    private final JTextField titleTextField = new JTextField(20);
    private final JLabel titleErrorField = new JLabel();

    private final JTextArea descriptionTextField = new JTextArea(10, 20);
    private final JLabel descriptionErrorField = new JLabel();

    private final JButton createPostButton = new JButton("Create Post");
    private final JButton cancelButton = new JButton("Cancel");

    private ClubCreatePostController clubCreatePostController;

    public CreatePostView(ClubCreatePostViewModel clubCreatePostViewModel, ClubCreatePostController clubCreatePostController) {
        this.clubCreatePostViewModel = clubCreatePostViewModel;
        this.clubCreatePostController = clubCreatePostController;
        this.createPostButton.addActionListener(this);

        final JLabel title = new JLabel("Create Post Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel titleFieldInfo = new LabelTextPanel(new JLabel("Title"), titleTextField);
        final LabelTextPanel descriptionFieldInfo = new LabelTextPanel(new JLabel("Description"),
                descriptionTextField);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(createPostButton);
        buttonPanel.add(cancelButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreatePostState currentState = clubCreatePostViewModel.getState();
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

        descriptionTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreatePostState currentState = clubCreatePostViewModel.getState();
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

        cancelButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(cancelButton)) {
                        // TODO: Add code to go back to home page when button is clicked.
                        System.out.println("Temporary");
                    }
                }
        );

        createPostButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createPostButton)) {
                        final CreatePostState currentState = clubCreatePostViewModel.getState();
                        clubCreatePostController.execute(currentState.getEmail(), currentState.getTitle(),
                                currentState.getContent());
                    }
                }
        );
        this.add(title);
        this.add(titleFieldInfo);
        this.add(titleErrorField);
        this.add(descriptionFieldInfo);
        this.add(descriptionErrorField);
        this.add(buttonPanel);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("create post")) {
            JOptionPane.showMessageDialog(null, "Announcement successfully posted.");
        }
        else if (evt.getPropertyName().equals("state")) {
            final CreatePostState state = (CreatePostState) evt.getNewValue();
            titleErrorField.setText(state.getTitle());
            descriptionErrorField.setText(state.getContent());
            JOptionPane.showMessageDialog(null, state.getCreatePostError());
        }
    }

    public void setCreatePostController(ClubCreatePostController clubCreatePostController) {
        this.clubCreatePostController = clubCreatePostController;
    }
}
