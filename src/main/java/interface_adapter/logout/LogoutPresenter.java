package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;

/**
 * Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogoutPresenter(LoginViewModel clubLoggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = clubLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoginView() {
        // creates a new empty login state and sets it to the login view model
        final LoginState loginState = loginViewModel.getState();
        loginState.setIdentifier("");
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // fires a property change to the view manager model to switch to the login view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
