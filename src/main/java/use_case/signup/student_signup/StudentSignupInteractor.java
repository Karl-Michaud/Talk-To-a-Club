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

        // Calls helper to check if inputs are valid
        if (!hasEmptyInput(studentSignupInputData) && hasValidInputs(studentSignupInputData)) {
            // Creates a new club entity with the input data
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

    /**
     * Checks if any of the given data is empty. Returns an error to the presenter if so.
     * @param studentSignupInputData the input data
     * @return if an input is empty
     */
    private boolean hasEmptyInput(StudentSignupInputData studentSignupInputData) {
        boolean hasEmpty = false;

        if (studentSignupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("Username field is empty.");
            hasEmpty = true;
        }
        else if (studentSignupInputData.getEmail().isEmpty()) {
            userPresenter.prepareFailView("Email field is empty.");
            hasEmpty = true;
        }
        else if (studentSignupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password field is empty.");
            hasEmpty = true;
        }
        else if (studentSignupInputData.getRepeatPassword().isEmpty()) {
            userPresenter.prepareFailView("Repeat Password field is empty.");
            hasEmpty = true;
        }
        return hasEmpty;
    }

    /**
     * Checks if any of the given data is invalid. Returns an error to the presenter if so.
     * @param studentSignupInputData the input data
     * @return if all inputs are valid
     */
    private boolean hasValidInputs(StudentSignupInputData studentSignupInputData) {
        boolean hasValidInputs = true;

        final String onlyForCheckstyle = " character(s).";
        // Tests if any inputs were not valid. Prepares a fail view with a message of the issue if any conditions fail
        if (userDataAccessObject.existsByNameStudent(studentSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
            hasValidInputs = false;
        }
        else if (userDataAccessObject.existsByEmailStudent(studentSignupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
            hasValidInputs = false;
        }
        else if (!studentSignupInputData.getPassword().equals(studentSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            hasValidInputs = false;
        }
        else if (studentSignupInputData.getUsername().length() < minLengthUsername) {
            userPresenter.prepareFailView("Username must be at least " + minLengthUsername + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (studentSignupInputData.getUsername().length() > maxLengthUsername) {
            userPresenter.prepareFailView("Username must be at most " + maxLengthUsername + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (studentSignupInputData.getPassword().length() < minLengthPassword) {
            userPresenter.prepareFailView("Password must be at least " + minLengthPassword + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (studentSignupInputData.getPassword().length() > maxLengthPassword) {
            userPresenter.prepareFailView("Password must be at most " + maxLengthPassword + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (!studentSignupInputData.getEmail().contains("@")
                || !studentSignupInputData.getEmail().contains(".")) {
            userPresenter.prepareFailView("Invalid email address.");
            hasValidInputs = false;
        }
        return hasValidInputs;
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
