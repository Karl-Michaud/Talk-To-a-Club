package interface_adapter.student_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.student_homepage.StudentHomeOutputBoundary;
import use_case.student_homepage.StudentHomeOutputData;

public class StudentHomePresenter implements StudentHomeOutputBoundary {

    private final StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public StudentHomePresenter(StudentHomeViewModel viewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.studentHomeViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(StudentHomeOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final StudentHomeState studentHomeState = studentHomeViewModel.getState();
        studentHomeState.setStudentHomeError(errorMessage);
        studentHomeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView() {

    }
}
