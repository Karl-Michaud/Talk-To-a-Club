package use_case.signup.club_signup;

import entity.user.Club;
import entity.user.ClubUserFactory;

/**
 * The Club Signup Interactor for the club sign up use case.
 */
public class ClubSignupInteractor implements ClubSignupInputBoundary {
    private final ClubSignupUserDataAccessInterface userDataAccessObject;
    private final ClubSignupOutputBoundary userPresenter;
    private final ClubUserFactory clubUserFactory;

    private final int minLengthUsername = 1;
    private final int maxLengthUsername = 64;

    private final int minLengthPassword = 8;
    private final int maxLengthPassword = 64;

    public ClubSignupInteractor(ClubSignupUserDataAccessInterface signupDataAccessInterface,
                                ClubSignupOutputBoundary clubSignupOutputBoundary,
                                ClubUserFactory clubUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = clubSignupOutputBoundary;
        this.clubUserFactory = clubUserFactory;
    }

    @Override
    public void execute(ClubSignupInputData clubSignupInputData) {
        final String onlyForCheckstyle = "characters.";
        if (userDataAccessObject.existsByNameClub(clubSignupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
        }
        else if (userDataAccessObject.existsByEmailClub(clubSignupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
        }
        else if (!clubSignupInputData.getPassword().equals(clubSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (clubSignupInputData.getUsername().length() <= minLengthUsername) {
            userPresenter.prepareFailView("Username must be at least " + minLengthUsername + onlyForCheckstyle);
        }
        else if (clubSignupInputData.getUsername().length() > maxLengthUsername) {
            userPresenter.prepareFailView("Username must be at most " + maxLengthUsername + onlyForCheckstyle);
        }
        else if (clubSignupInputData.getPassword().length() <= minLengthPassword) {
            userPresenter.prepareFailView("Password must be at least " + minLengthPassword + onlyForCheckstyle);
        }
        else if (clubSignupInputData.getPassword().length() > maxLengthPassword) {
            userPresenter.prepareFailView("Password must be at most " + maxLengthPassword + onlyForCheckstyle);
        }
        else if (!clubSignupInputData.getEmail().contentEquals("@")
                || !clubSignupInputData.getEmail().contentEquals(".")) {
            userPresenter.prepareFailView("Invalid email address.");
        }
        else {

            final Club user = clubUserFactory.create(clubSignupInputData.getUsername(),
                    clubSignupInputData.getEmail(),
                    clubSignupInputData.getPassword());

            userDataAccessObject.saveClub(user);

            final ClubSignupOutputData clubSignupOutputData = new ClubSignupOutputData(user.getEmail(),
                    false);
            userPresenter.prepareSuccessView(clubSignupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
