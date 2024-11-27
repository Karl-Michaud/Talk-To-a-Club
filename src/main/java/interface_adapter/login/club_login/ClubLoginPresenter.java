package interface_adapter.login.club_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.club_signup.ClubSignupState;
import interface_adapter.signup.club_signup.ClubSignupViewModel;
import use_case.login.club_login.ClubLoginOutputBoundary;
import use_case.login.club_login.ClubLoginOutputData;

/**
 * The Presenter for the Club Login Use Case.
 */
public class ClubLoginPresenter implements ClubLoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ClubSignupViewModel clubSignupViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubLoginPresenter(ViewManagerModel viewManagerModel,
                              ClubLoggedInViewModel clubLoggedInViewModel,
                              ClubSignupViewModel clubSignupViewModel,
                              LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.clubSignupViewModel = clubSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ClubLoginOutputData response) {
        // On success switch to the club logged in view.
        setHomePageState(response);

        this.viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *  Helper method to prepare the home page view after logging in.
     * @param response the input data getting passed to the presenter
     */
    private void setHomePageState(ClubLoginOutputData response) {
        final ClubLoggedInState clubLoggedInState = clubLoggedInViewModel.getState();
        clubLoggedInState.setClubName(response.getUsername());
        clubLoggedInState.setEmail(response.getEmail());
        clubLoggedInState.setDescriptionTextArea(response.getDescription());
        this.clubLoggedInViewModel.setState(clubLoggedInState);
        this.clubLoggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToClubSignupView() {
        // Clears the ClubSignupState
        final ClubSignupState state = clubSignupViewModel.getState();
        state.setEmail("");
        state.setPassword("");
        state.setUsername("");
        state.setRepeatPassword("");
        state.setSignupError(null);
        this.clubSignupViewModel.setState(state);
        this.clubSignupViewModel.firePropertyChanged();

        // Switches to the club signup state
        viewManagerModel.setState(clubSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
