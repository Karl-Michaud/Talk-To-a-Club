package use_case.signup.student_signup;

import entity.user.Student;
import entity.user.StudentUserFactory;

/**
 * The Student Signup Interactor.
 */
public class StudentSignupInteractor implements StudentSignupInputBoundary {
    private final StudentSignupUserDataAccessInterface userDataAccessObject;
    private final StudentSignupOutputBoundary userPresenter;
    private final StudentUserFactory studentUserFactory;

    public StudentSignupInteractor(StudentSignupUserDataAccessInterface signupDataAccessInterface,
                                   StudentSignupOutputBoundary studentSignupOutputBoundary,
                                   StudentUserFactory studentUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = studentSignupOutputBoundary;
        this.studentUserFactory = studentUserFactory;
    }

    @Override
    public void execute(StudentSignupInputData studentSignupInputData) {
        if (userDataAccessObject.existsByName(studentSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
        }
        else if (userDataAccessObject.existsByEmail(studentSignupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
        }
        else if (!studentSignupInputData.getPassword().equals(studentSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final Student user = studentUserFactory.create(studentSignupInputData.getUsername(),
                    studentSignupInputData.getEmail(),
                    studentSignupInputData.getPassword());
            // Save user to database
            userDataAccessObject.saveStudent(user);

            final StudentSignupOutputData studentSignupOutputData = new StudentSignupOutputData(user.getEmail(),
                    false);
            userPresenter.prepareSuccessView(studentSignupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
