package app.app_add_view;

import interface_adapter.student_profile.StudentProfileViewModel;
import view.StudentProfileView;

/**
 * Class makes the student profile view.
 */
public class StudentProfileViewMaker {
    private final StudentProfileViewModel studentProfileViewModel;
    private final StudentProfileView studentProfileView;

    public StudentProfileViewMaker() {
        studentProfileViewModel = new StudentProfileViewModel();
        studentProfileView = new StudentProfileView(studentProfileViewModel);
    }

    public StudentProfileView getStudentProfileView() {
        return studentProfileView;
    }

    public StudentProfileViewModel getStudentProfileViewModel() {
        return studentProfileViewModel;
    }
}
