package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public LoginViewModel() {
        super("login");
        setState(new LoginState());
    }
}