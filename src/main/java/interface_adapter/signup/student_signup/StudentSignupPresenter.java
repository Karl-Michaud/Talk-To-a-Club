package interface_adapter.signup.student_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.student_signup.StudentSignupOutputBoundary;
import use_case.signup.student_signup.StudentSignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class StudentSignupPresenter implements StudentSignupOutputBoundary {

    private final StudentSignupViewModel studentSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentSignupPresenter(ViewManagerModel viewManagerModel,
                                  StudentSignupViewModel studentSignupViewModel,
                                  LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studentSignupViewModel = studentSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(StudentSignupOutputData response) {
        // On success, switch to the login view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setEmail(response.getEmail());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final StudentSignupState studentSignupState = studentSignupViewModel.getState();
        studentSignupState.setUsernameError(error);
        studentSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
