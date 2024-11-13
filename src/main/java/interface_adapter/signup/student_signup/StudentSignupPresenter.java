package interface_adapter.signup.student_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.student_login.StudentLoginState;
import interface_adapter.login.student_login.StudentLoginViewModel;
import use_case.signup.student_signup.StudentSignupOutputBoundary;
import use_case.signup.student_signup.StudentSignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class StudentSignupPresenter implements StudentSignupOutputBoundary {

    private final StudentSignupViewModel studentSignupViewModel;
    private final StudentLoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentSignupPresenter(ViewManagerModel viewManagerModel,
                                  StudentSignupViewModel studentSignupViewModel,
                                  StudentLoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studentSignupViewModel = studentSignupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(StudentSignupOutputData response) {
        // On success, switch to the login view.
        final StudentLoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final StudentSignupState studentSignupState = studentSignupViewModel.getState();
        studentSignupState.setSignupError(error);
        studentSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
