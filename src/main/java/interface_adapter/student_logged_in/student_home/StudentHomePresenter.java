package interface_adapter.student_logged_in.student_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.student_profile.StudentProfileViewModel;
import use_case.student_homepage.StudentHomeOutputBoundary;

/**
 * The Presenter for the Student Home usecase.
 */
public class StudentHomePresenter implements StudentHomeOutputBoundary {

    private final StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final StudentProfileViewModel studentProfileViewModel;

    public StudentHomePresenter(StudentHomeViewModel viewModel, ViewManagerModel viewManagerModel,
                                LoginViewModel loginViewModel, StudentProfileViewModel studentProfileViewModel) {
        this.studentHomeViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.studentProfileViewModel = studentProfileViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final StudentHomeState studentHomeState = studentHomeViewModel.getState();
        studentHomeState.setStudentHomeError(errorMessage);
        studentHomeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView() {
        viewManagerModel.setState(studentProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
