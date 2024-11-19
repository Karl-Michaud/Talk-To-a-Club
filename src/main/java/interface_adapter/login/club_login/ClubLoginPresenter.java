package interface_adapter.login.club_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_home.ClubHomeState;
import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.club_signup.ClubSignupViewModel;
import use_case.login.club_login.ClubLoginOutputBoundary;
import use_case.login.club_login.ClubLoginOutputData;

/**
 * The Presenter for the Club Login Use Case.
 */
public class ClubLoginPresenter implements ClubLoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ClubHomeViewModel clubHomeViewModel;
    private final ClubSignupViewModel clubSignupViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubLoginPresenter(ViewManagerModel viewManagerModel,
                              ClubHomeViewModel clubHomeViewModel,
                              ClubSignupViewModel clubSignupViewModel,
                              LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clubHomeViewModel = clubHomeViewModel;
        this.clubSignupViewModel = clubSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view for the Login Use Case for Clubs.
     * @param response the output data
     */
    @Override
    public void prepareSuccessView(ClubLoginOutputData response) {
        // On success switch to the home view.
        setHomePageState(response);

        this.viewManagerModel.setState(clubHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *  Helper method to prepare the home page view after logging in.
     * @param response the input data getting passed to the presenter
     */
    private void setHomePageState(ClubLoginOutputData response) {
        final ClubHomeState clubHomeState = clubHomeViewModel.getState();
        clubHomeState.setUsername(response.getUsername());
        this.clubHomeViewModel.setState(clubHomeState);
        this.clubHomeViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Login Use Case.
     * @param error the explanation of the failure
     */
    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Switches to the Club Signup View.
     */
    @Override
    public void switchToClubSignupView() {
        viewManagerModel.setState(clubSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
