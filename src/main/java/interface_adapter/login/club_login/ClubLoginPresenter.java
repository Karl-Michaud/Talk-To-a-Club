package interface_adapter.login.club_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.data_access.HomeState;
import interface_adapter.data_access.HomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Club Login Use Case.
 */
public class ClubLoginPresenter implements LoginOutputBoundary {

    private final ClubLoginViewModel clubLoginViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubLoginPresenter(ViewManagerModel viewManagerModel,
                              HomeViewModel homeInViewModel,
                              ClubLoginViewModel clubLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeInViewModel;
        this.clubLoginViewModel = clubLoginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        //On success switch to the home view.
        setHomePageState(response);

        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    private void setHomePageState(LoginOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        homeState.setUsername(response.getUsername);
        homeState.setIsClub(response.getIsClub);
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ClubLoginState clubLoginState = clubLoginViewModel.getState();
        clubLoginState.setLoginError(error);
        clubLoginViewModel.firePropertyChanged();
    }
}
