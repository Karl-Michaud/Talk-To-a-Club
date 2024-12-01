package use_case.signup.club_signup;

import entity.user.Club;
import entity.user.ClubUserFactory;

/**
 * The Club Signup Interactor for the club sign up use case.
 */
public class ClubSignupInteractor implements ClubSignupInputBoundary {
    private final ClubSignupDataAccessInterface userDataAccessObject;
    private final ClubSignupOutputBoundary userPresenter;
    private final ClubUserFactory clubUserFactory;

    private final int minLengthUsername = 1;
    private final int maxLengthUsername = 64;

    private final int minLengthPassword = 8;
    private final int maxLengthPassword = 64;

    public ClubSignupInteractor(ClubSignupDataAccessInterface signupDataAccessInterface,
                                ClubSignupOutputBoundary clubSignupOutputBoundary,
                                ClubUserFactory clubUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = clubSignupOutputBoundary;
        this.clubUserFactory = clubUserFactory;
    }

    @Override
    public void execute(ClubSignupInputData clubSignupInputData) {

        // Calls helper to check if inputs are valid
        if (!hasEmptyInput(clubSignupInputData) && hasValidInputs(clubSignupInputData)) {
            // Creates a new club entity with the input data
            final Club user = clubUserFactory.create(clubSignupInputData.getUsername(),
                    clubSignupInputData.getEmail(),
                    clubSignupInputData.getPassword());

            // Tells the DAO to save the club user entity in the database
            userDataAccessObject.saveClub(user);

            // Prepares the output data. Tells the presenter to prepare the success view.
            final ClubSignupOutputData clubSignupOutputData = new ClubSignupOutputData(user.getEmail());
            userPresenter.prepareSuccessView(clubSignupOutputData);
        }
    }

    /**
     * Checks if any of the given data is empty. Returns an error to the presenter if so.
     * @param clubSignupInputData the input data
     * @return if there are empty inputs
     */
    private boolean hasEmptyInput(ClubSignupInputData clubSignupInputData) {
        boolean hasEmpty = false;

        if (clubSignupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("Username field is empty.");
            hasEmpty = true;
        }
        else if (clubSignupInputData.getEmail().isEmpty()) {
            userPresenter.prepareFailView("Email field is empty.");
            hasEmpty = true;
        }
        else if (clubSignupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password field is empty.");
            hasEmpty = true;
        }
        else if (clubSignupInputData.getRepeatPassword().isEmpty()) {
            userPresenter.prepareFailView("Repeat Password field is empty.");
            hasEmpty = true;
        }

        return hasEmpty;
    }

    /**
     * Checks if any of the given data is invalid. Returns an error to the presenter if so.
     * @param clubSignupInputData the input data
     * @return if all inputs are valid
     */
    private boolean hasValidInputs(ClubSignupInputData clubSignupInputData) {
        boolean hasValidInputs = true;

        final String onlyForCheckstyle = " character(s).";
        // Tests if any inputs were not valid. Prepares a fail view with a message of the issue if any conditions fail
        if (userDataAccessObject.existsByNameClub(clubSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
            hasValidInputs = false;
        }
        else if (userDataAccessObject.existsByEmailClub(clubSignupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
            hasValidInputs = false;
        }
        else if (!clubSignupInputData.getPassword().equals(clubSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            hasValidInputs = false;
        }
        else if (clubSignupInputData.getUsername().length() < minLengthUsername) {
            userPresenter.prepareFailView("Username must be at least " + minLengthUsername + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (clubSignupInputData.getUsername().length() > maxLengthUsername) {
            userPresenter.prepareFailView("Username must be at most " + maxLengthUsername + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (clubSignupInputData.getPassword().length() < minLengthPassword) {
            userPresenter.prepareFailView("Password must be at least " + minLengthPassword + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (clubSignupInputData.getPassword().length() > maxLengthPassword) {
            userPresenter.prepareFailView("Password must be at most " + maxLengthPassword + onlyForCheckstyle);
            hasValidInputs = false;
        }
        else if (!clubSignupInputData.getEmail().contains("@")
                || !clubSignupInputData.getEmail().contains(".")) {
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
