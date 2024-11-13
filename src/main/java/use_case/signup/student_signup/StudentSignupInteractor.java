package use_case.signup.student_signup;

import entity.user.StudentUserFactory;
import entity.user.User;

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
            final User user = studentUserFactory.create(studentSignupInputData.getUsername(),
                    studentSignupInputData.getEmail(),
                    studentSignupInputData.getPassword());
            userDataAccessObject.saveStudent(user);

            final StudentSignupOutputData studentSignupOutputData = new StudentSignupOutputData(user.getUsername(),
                    false);
            userPresenter.prepareSuccessView(studentSignupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
