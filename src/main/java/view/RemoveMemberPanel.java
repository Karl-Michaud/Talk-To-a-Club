package view;

import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel that contains a member's information, and a button to remove that member from the logged in club.
 * This takes in a RemoveMemberController to do the usecase.
 */
public class RemoveMemberPanel extends JPanel {
    private JLabel memberNameLabel;
    private JButton removeMemberButton;
    private JPanel memberPanel;

    public RemoveMemberPanel(ClubRemoveMemberController removeMemberController, String clubEmail,
                             String studentEmail, String studentUsername) {
        memberNameLabel.setText(studentUsername);

        removeMemberButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(removeMemberButton)) {
                            // Executes the Remove Member use case.
                            removeMemberController.execute(clubEmail, studentEmail);
                        }
                    }
                }
        );
    }
}
