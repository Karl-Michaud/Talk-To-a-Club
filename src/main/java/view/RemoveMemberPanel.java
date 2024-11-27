package view;

import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel that contains a member's email, the club they're in, and a button to remove that member from the club.
 */
public class RemoveMemberPanel extends JPanel {
    private JLabel memberNameLabel;
    private JButton removeMemberButton;
    private JPanel memberPanel;
    // the club who has the student to be deleted
    private String clubEmail;
    // The student who gets deleted after the button press
    private String studentUsername;

    private ClubRemoveMemberController removeMemberController;

    public RemoveMemberPanel(ClubRemoveMemberController removeMemberController, String clubEmail,
                             String studentUsername) {
        this.removeMemberController = removeMemberController;
        this.clubEmail = clubEmail;
        this.studentUsername = studentUsername;

        removeMemberButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(removeMemberButton)) {
                            // Executes the Logout use case.
                            removeMemberController.execute(clubEmail, studentUsername);
                            // TODO make Karl change it so it takes in the username and not email for student
                        }
                    }
                }
        );
    }
}
