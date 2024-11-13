package interface_adapter.login.club_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_home.ClubHomeState;
import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.login.club_login.ClubLoginOutputBoundary;
import use_case.login.club_login.ClubLoginOutputData;

/**
 * The Presenter for the Club Login Use Case.
 */
public class ClubLoginPresenter implements ClubLoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ClubHomeViewModel clubHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubLoginPresenter(ViewManagerModel viewManagerModel,
                              ClubHomeViewModel clubHomeViewModel,
                              LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clubHomeViewModel = clubHomeViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ClubLoginOutputData response) {
        //On success switch to the home view.
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

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToClubHomeView() {
        viewManagerModel.setState(clubHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
