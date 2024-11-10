package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.data_access.HomeState;
import interface_adapter.data_access.HomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomeViewModel homeInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        //On success switch to the home view.
//        final HomeState homeState = homeViewModel.getState();
//        homeState.setusername(response.getUsername());
//        homeState.setemail(response.geteEmail());
//        this.homeViewModel.setState(homeState);
//        this.homeViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(homeViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
        //TEMP On success switch to the TempLoggedInView

    }
    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
