package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.student_profile.StudentProfileController;
import interface_adapter.student_profile.StudentProfileState;
import interface_adapter.student_profile.StudentProfileViewModel;

/**
 * The View for the student profile page.
 */
public class StudentProfileView extends JPanel implements PropertyChangeListener {
    private JButton backHomeButton;
    private JPanel panel1;

    private final String viewName = "student profile";
    private final StudentProfileViewModel studentProfileViewModel;
    private StudentProfileController studentProfileController;

    public StudentProfileView(StudentProfileViewModel studentProfileViewModel) {
        this.studentProfileViewModel = studentProfileViewModel;
        this.studentProfileViewModel.addPropertyChangeListener(this);

        backHomeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        studentProfileController.switchToHomeScreen();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panel1);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final StudentProfileState state = (StudentProfileState) evt.getNewValue();
        if (state.getStudentProfileError() != null) {
            JOptionPane.showMessageDialog(this, state.getStudentProfileError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setStudentProfileController(StudentProfileController studentProfileController) {
        this.studentProfileController = studentProfileController;
    }
}
