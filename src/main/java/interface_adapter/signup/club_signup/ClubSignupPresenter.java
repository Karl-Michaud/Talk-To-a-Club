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
        // Gets the login state and sets the email field to the newly registered email
        final LoginState loginState = loginViewModel.getState();
        loginState.setIdentifier(response.getEmail());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // Switch to the login view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Sets the signup state to have an error message and fires a property change to display the message
        final ClubSignupState signupState = clubSignupViewModel.getState();
        signupState.setSignupError(error);
        clubSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        // Clears the LoginState
        final LoginState state = loginViewModel.getState();
        state.setLoginError(null);
        state.setPassword("");
        state.setIdentifier("");
        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();

        // Switch to the login view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
