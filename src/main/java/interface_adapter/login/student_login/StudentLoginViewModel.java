package interface_adapter.login.student_login;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Student Signup View.
 */
public class StudentLoginViewModel extends ViewModel<StudentLoginState> {

    public StudentLoginViewModel() {
        super("student login");
        setState(new StudentLoginState());
    }
}