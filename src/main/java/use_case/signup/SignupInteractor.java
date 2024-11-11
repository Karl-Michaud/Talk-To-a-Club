package use_case.signup;

import entity.user.User;
import entity.user.ClubUserFactory;
import entity.user.StudentUserFactory;


/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final ClubUserFactory clubUserFactory;
    private final StudentUserFactory studentUserFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            ClubUserFactory clubUserFactory, StudentUserFactory studentUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.clubUserFactory = clubUserFactory;
        this.studentUserFactory = studentUserFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists.");
        }
        else if (userDataAccessObject.existsByEmail(signupInputData.getEmail())) {
            userPresenter.prepareFailView("Email address already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if (signupInputData.getIsClub()){
            final User user = clubUserFactory.create(signupInputData.getUsername(),
                    signupInputData.getEmail(),
                    signupInputData.getPassword());
            userDataAccessObject.saveClub(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getEmail(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
        else {
            final User user = studentUserFactory.create(signupInputData.getUsername(),
                    signupInputData.getEmail(),
                    signupInputData.getPassword());
            userDataAccessObject.saveStudent(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getEmail(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
