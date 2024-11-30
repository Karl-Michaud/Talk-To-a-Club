package app.app_add_view;

import interface_adapter.student_logged_in.student_home.StudentHomeViewModel;
import view.StudentHomeView;

/**
 * Class makes the student home view.
 */
public class StudentHomeViewMaker {
    private final StudentHomeView studentHomeView;
    private final StudentHomeViewModel studentHomeViewModel;

    public StudentHomeViewMaker() {
        studentHomeViewModel = new StudentHomeViewModel();
        studentHomeView = new StudentHomeView(studentHomeViewModel);
    }

    public StudentHomeView getStudentHomeView() {
        return studentHomeView;
    }

    public StudentHomeViewModel getStudentHomeViewModel() {
        return studentHomeViewModel;
    }
}
