package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberController;

/**
 * A JPanel that contains a member's information, and a button to remove that member from the logged in club.
 * This takes in a RemoveMemberController to do the usecase.
 */
public class RemoveMemberPanel {
    private JButton removeMemberButton;
    private JPanel memberPanel;
    private JTextPane memberName;

    public RemoveMemberPanel(ClubRemoveMemberController removeMemberController, String clubEmail,
                             String studentEmail, String studentUsername) {
        memberName.setText(studentUsername);

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

    public JPanel getMemberPanel() {
        return this.memberPanel;
    }
}
