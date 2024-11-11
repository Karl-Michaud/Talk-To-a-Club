package use_case.login;

import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import entity.user.User;

import java.util.Map;

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
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(),
                            ((Student) user).getJoinedClubs(), false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
                else {
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), user.getEmail(),
                            ((Club) user).getClubPosts(), false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
            }
        }
    }
}
