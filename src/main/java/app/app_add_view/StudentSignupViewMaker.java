package app.app_add_view;

import interface_adapter.signup.student_signup.StudentSignupViewModel;
import view.StudentSignupView;

/**
 * Maker for the student signup view.
 */
public class StudentSignupViewMaker {
    private final StudentSignupViewModel studentSignupViewModel;
    private final StudentSignupView studentSignupView;

    public StudentSignupViewMaker() {
        studentSignupViewModel = new StudentSignupViewModel();
        studentSignupView = new StudentSignupView(studentSignupViewModel);
    }

    public StudentSignupView getStudentSignupView() {
        return studentSignupView;
    }

    public StudentSignupViewModel getStudentSignupViewModel() {
        return studentSignupViewModel;
    }
}
