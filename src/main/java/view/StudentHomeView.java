package view;

import interface_adapter.student_home.StudentHomeViewModel;


import javax.swing.*;

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
