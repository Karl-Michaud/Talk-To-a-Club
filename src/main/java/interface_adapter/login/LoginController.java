package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.club_signup.ClubSignupState;
import interface_adapter.signup.club_signup.ClubSignupViewModel;
import interface_adapter.signup.student_signup.StudentSignupState;
import interface_adapter.signup.student_signup.StudentSignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import view.ClubSignupView;

/**
 * The Controller for the Login Use Case
 */
public class LoginController {
    /**
     *
     */
    private final StudentSignupViewModel studentSignupViewModel;
    private final LoginInputBoundary loginUseCaseInteractor;
    private final ClubSignupViewModel clubSignupViewModel;
    private final ViewManagerModel viewManagerModel;    /**
     * The Constructor for the Login controller
     */
    public LoginController(StudentSignupViewModel studentSignupViewModel, LoginInputBoundary loginUseCaseInteractor, ClubSignupViewModel clubSignupViewModel,
                           ViewManagerModel viewManagerModel){
        this.studentSignupViewModel = studentSignupViewModel;
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.clubSignupViewModel = clubSignupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Executes the Login Use Case.
     * @param email the email of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String email, String password){
        final LoginInputData loginInputData = new LoginInputData(email, password);


        loginUseCaseInteractor.execute(loginInputData);
    }

    public void switchToClubSignupView() {
        final ClubSignupState clubSignupState = clubSignupViewModel.getState();
        this.clubSignupViewModel.setState(clubSignupState);
        this.clubSignupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(clubSignupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void switchToStudentSignupView() {
        final StudentSignupState studentSignupState = studentSignupViewModel.getState();
        this.studentSignupViewModel.setState(studentSignupState);
        this.studentSignupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(studentSignupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
