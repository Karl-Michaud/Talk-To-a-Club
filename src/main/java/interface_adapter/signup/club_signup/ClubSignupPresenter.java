package interface_adapter.signup.club_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.club_signup.ClubSignupOutputBoundary;
import use_case.signup.club_signup.ClubSignupOutputData;

/**
 * The Presenter for the Club Signup Use Case.
 */
public class ClubSignupPresenter implements ClubSignupOutputBoundary {

    private final ClubSignupViewModel clubSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubSignupPresenter(ViewManagerModel viewManagerModel,
                               ClubSignupViewModel clubSignupViewModel,
                               LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clubSignupViewModel = clubSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ClubSignupOutputData response) {
        // On success, switch to the login view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setIdentifier(response.getEmail());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ClubSignupState signupState = clubSignupViewModel.getState();
        signupState.setSignupError(error);
        clubSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
