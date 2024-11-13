package interface_adapter.login.student_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.student_login.StudentLoginOutputBoundary;
import use_case.student_login.StudentLoginOutputData;

/**
 * The Presenter for the Student Login Use Case.
 */
public class StudentLoginPresenter implements StudentLoginOutputBoundary {

    private final StudentLoginViewModel studentLoginViewModel;
    private final StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentLoginPresenter(ViewManagerModel viewManagerModel,
                                 StudentHomeViewModel studentHomeViewModel,
                                 StudentLoginViewModel studentLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studentHomeViewModel = studentHomeViewModel;
        this.studentLoginViewModel = studentLoginViewModel;
    }

    @Override
    public void prepareSuccessView(StudentLoginOutputData response) {
        //On success switch to the home view.
        setHomePageState(response);

        this.viewManagerModel.setState(studentHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *  Helper method to prepare the home page view after logging in.
     * @param response the input data getting passed to the presenter
     */
    private void setHomePageState(StudentLoginOutputData response) {
        final StudentHomeState studentHomeState = studentHomeViewModel.getState();
        studentHomeState.setUsername(response.getUsername);
        this.studentHomeViewModel.setState(studentHomeState);
        this.studentHomeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final StudentLoginState studentLoginState = studentLoginViewModel.getState();
        studentLoginState.setLoginError(error);
        studentLoginViewModel.firePropertyChanged();
    }
}
