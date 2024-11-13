package interface_adapter.login.student_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.data_access.HomeState;
import interface_adapter.data_access.HomeViewModel;

/**
 * The Presenter for the Student Login Use Case.
 */
public class StudentLoginPresenter implements StudentLoginOutputBoundary {

    private final StudentLoginViewModel studentLoginViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentLoginPresenter(ViewManagerModel viewManagerModel,
                                 HomeViewModel homeInViewModel,
                                 StudentLoginViewModel studentLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeInViewModel;
        this.studentLoginViewModel = studentLoginViewModel;
    }

    @Override
    public void prepareSuccessView(StudentLoginOutputData response) {
        //On success switch to the home view.
        setHomePageState(response);

        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *  Helper method to prepare the home page view after logging in.
     * @param response the input data getting passed to the presenter
     */
    private void setHomePageState(StudentLoginOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        homeState.setUsername(response.getUsername);
        homeState.setIsClub(response.getIsClub);
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final StudentLoginState studentLoginState = studentLoginViewModel.getState();
        studentLoginState.setLoginError(error);
        studentLoginViewModel.firePropertyChanged();
    }
}
