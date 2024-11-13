package interface_adapter.signup.club_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.club_login.ClubLoginState;
import interface_adapter.login.club_login.ClubLoginViewModel;
import use_case.signup.club_signup.ClubSignupOutputBoundary;
import use_case.signup.club_signup.ClubSignupOutputData;

/**
 * The Presenter for the Club Signup Use Case.
 */
public class ClubSignupPresenter implements ClubSignupOutputBoundary {

    private final ClubSignupViewModel clubSignupViewModel;
    private final ClubLoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubSignupPresenter(ViewManagerModel viewManagerModel,
                               ClubSignupViewModel clubSignupViewModel,
                               ClubLoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clubSignupViewModel = clubSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ClubSignupOutputData response) {
        // On success, switch to the login view.
        final ClubLoginState loginState = loginViewModel.getState();
        loginState.setEmail(response.getEmail());
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
