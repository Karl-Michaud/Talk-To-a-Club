package app.app_add_view;

import interface_adapter.login.LoginViewModel;
import view.LoginView;

/**
 * Class makes the LoginView.
 */
public class LoginViewMaker {
    private final LoginView loginView;
    private final LoginViewModel loginViewModel;

    public LoginViewMaker() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
