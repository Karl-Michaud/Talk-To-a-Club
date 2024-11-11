package interface_adapter.signup.student_signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Student Signup View.
 */
public class StudentSignupViewModel extends ViewModel<StudentSignupState> {

    public StudentSignupViewModel() {
        super("student sign up");
        setState(new StudentSignupState());
    }

}
