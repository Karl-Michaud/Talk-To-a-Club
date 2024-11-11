package use_case.login;

import entity.user.Student;
import entity.user.User;

public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByEmail(email)) {
            loginPresenter.prepareFailView(email + ": Account does not exist.");
        } else {
            final String pwd = userDataAccessObject.get(email).getPassword();
            if (!pwd.equals(password)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + email + "\".");
            } else {
                final User user = userDataAccessObject.get(email);
                if (user instanceof Student) {
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
                else {
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getPassword(), user.getEmail(),
                            false);
                }
            }
        }
    }
}
