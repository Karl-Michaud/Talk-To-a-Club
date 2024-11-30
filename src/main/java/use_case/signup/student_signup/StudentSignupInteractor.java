package use_case.signup.student_signup;

import entity.user.Student;
import entity.user.StudentUserFactory;

/**
 * The Student Signup Interactor.
 */
public class StudentSignupInteractor implements StudentSignupInputBoundary {
    private final StudentSignupDataAccessInterface userDataAccessObject;
    private final StudentSignupOutputBoundary userPresenter;
    private final StudentUserFactory studentUserFactory;

    private final int minLengthUsername = 1;
    private final int maxLengthUsername = 64;

    private final int minLengthPassword = 8;
    private final int maxLengthPassword = 64;

    public StudentSignupInteractor(StudentSignupDataAccessInterface signupDataAccessInterface,
                                   StudentSignupOutputBoundary studentSignupOutputBoundary,
                                   StudentUserFactory studentUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = studentSignupOutputBoundary;
        this.studentUserFactory = studentUserFactory;
    }

    @Override
    public void execute(StudentSignupInputData studentSignupInputData) {
        final String onlyForCheckstyle = " character(s).";
        // Tests if any inputs were not valid. Prepares a fail view with a message of the issue if any conditions fail
        if (userDataAccessObject.existsByNameStudent(studentSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
        }
        else if (userDataAccessObject.existsByEmailStudent(studentSignupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
        }
        else if (!studentSignupInputData.getPassword().equals(studentSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (studentSignupInputData.getUsername().length() < minLengthUsername) {
            userPresenter.prepareFailView("Username must be at least " + minLengthUsername + onlyForCheckstyle);
        }
        else if (studentSignupInputData.getUsername().length() > maxLengthUsername) {
            userPresenter.prepareFailView("Username must be at most " + maxLengthUsername + onlyForCheckstyle);
        }
        else if (studentSignupInputData.getPassword().length() < minLengthPassword) {
            userPresenter.prepareFailView("Password must be at least " + minLengthPassword + onlyForCheckstyle);
        }
        else if (studentSignupInputData.getPassword().length() > maxLengthPassword) {
            userPresenter.prepareFailView("Password must be at most " + maxLengthPassword + onlyForCheckstyle);
        }
        else if (!studentSignupInputData.getEmail().contains("@")
                || !studentSignupInputData.getEmail().contains(".")) {
            userPresenter.prepareFailView("Invalid email address.");
        }
        else {
            // Creates a student entity with the input data
            final Student user = studentUserFactory.create(studentSignupInputData.getUsername(),
                    studentSignupInputData.getEmail(),
                    studentSignupInputData.getPassword());

            // Save the student to the database
            userDataAccessObject.saveStudent(user);

            // Prepares the output data. Tells the presenter to prepare the success view.
            final StudentSignupOutputData studentSignupOutputData = new StudentSignupOutputData(user.getEmail());
            userPresenter.prepareSuccessView(studentSignupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
