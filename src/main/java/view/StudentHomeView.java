package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.student_home.StudentHomeViewModel;

/**
 * View for the student home.
 */
public class StudentHomeView extends JPanel {
    private JPanel panel1;
    private JLabel labelUsername;
    private JLabel labelIcon;

    private final String viewName = "student home";
    private final StudentHomeViewModel studentHomeViewModel;
    // private DataAccessController HomeController;

    public StudentHomeView(StudentHomeViewModel studentHomeViewModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panel1);
    }

    public String getViewName() {
        return viewName;
    }
}
