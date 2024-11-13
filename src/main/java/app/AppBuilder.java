package app;

import data_access.UserDataAccessObject;
import entity.user.ClubUserFactory;
import entity.user.StudentUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.club_login.ClubLoginController;
import interface_adapter.login.club_login.ClubLoginPresenter;
import interface_adapter.login.student_login.StudentLoginController;
import interface_adapter.login.student_login.StudentLoginPresenter;
import interface_adapter.signup.club_signup.ClubSignupController;
import interface_adapter.signup.club_signup.ClubSignupPresenter;
import interface_adapter.signup.club_signup.ClubSignupViewModel;
import interface_adapter.signup.student_signup.StudentSignupController;
import interface_adapter.signup.student_signup.StudentSignupPresenter;
import interface_adapter.signup.student_signup.StudentSignupViewModel;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.login.club_login.ClubLoginInputBoundary;
import use_case.login.club_login.ClubLoginInteractor;
import use_case.login.club_login.ClubLoginOutputBoundary;
import use_case.login.student_login.StudentLoginInputBoundary;
import use_case.login.student_login.StudentLoginInteractor;
import use_case.login.student_login.StudentLoginOutputBoundary;
import use_case.signup.club_signup.ClubSignupInputBoundary;
import use_case.signup.club_signup.ClubSignupInteractor;
import use_case.signup.club_signup.ClubSignupOutputBoundary;
import use_case.signup.student_signup.StudentSignupInputBoundary;
import use_case.signup.student_signup.StudentSignupInteractor;
import use_case.signup.student_signup.StudentSignupOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final StudentUserFactory studentFactory = new StudentUserFactory();
    private final ClubUserFactory clubFactory = new ClubUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

    private ClubSignupViewModel clubSignupViewModel;
    private ClubSignupView clubSignupView;

    private StudentSignupViewModel studentSignupViewModel;
    private StudentSignupView studentSignupView;

    private LoginViewModel loginViewModel;
    private LoginView loginView;

    private StudentHomeViewModel studentHomeViewModel;
    private StudentHomeView studentHomeView;

    private ClubHomeViewModel clubHomeViewModel;
    private ClubHomeView clubHomeView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Club Signup View to the application.
     * @return this builder
     */
    public AppBuilder addClubSignupView() {
        clubSignupViewModel = new ClubSignupViewModel();
        clubSignupView = new ClubSignupView(clubSignupViewModel);
        cardPanel.add(clubSignupView, clubSignupView.getViewName());
        return this;
    }

    /**
     * Adds the Student Signup View to the application.
     * @return this builder
     */
    public AppBuilder addStudentSignupView() {
        studentSignupViewModel = new StudentSignupViewModel();
        studentSignupView = new StudentSignupView(studentSignupViewModel);
        cardPanel.add(studentSignupView, studentSignupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new interface_adapter.login.LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Club Home View to the application.
     * @return this builder
     */
    public AppBuilder addClubHomeView() {
        clubHomeViewModel = new ClubHomeViewModel();
        clubHomeView = new ClubHomeView(clubHomeViewModel);
        cardPanel.add(clubHomeView, clubHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Student Home View to the application.
     * @return this builder
     */
    public AppBuilder addStudentHomeView() {
        studentHomeViewModel = new StudentHomeViewModel();
        studentHomeView = new StudentHomeView(studentHomeViewModel);
        cardPanel.add(studentHomeView, studentHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Club Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addClubSignupUseCase() {
        final ClubSignupOutputBoundary signupOutputBoundary = new ClubSignupPresenter(viewManagerModel,
                clubSignupViewModel, loginViewModel);

        final ClubSignupInputBoundary userSignupInteractor = new ClubSignupInteractor(userDataAccessObject,
                signupOutputBoundary, clubFactory);

        final ClubSignupController controller = new ClubSignupController(userSignupInteractor);
        clubSignupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Student Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addStudentSignupUseCase() {
        final StudentSignupOutputBoundary signupOutputBoundary = new StudentSignupPresenter(viewManagerModel,
                studentSignupViewModel, loginViewModel);

        final StudentSignupInputBoundary userSignupInteractor = new StudentSignupInteractor(userDataAccessObject,
                signupOutputBoundary, studentFactory);

        final StudentSignupController controller = new StudentSignupController(userSignupInteractor);
        studentSignupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Club Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addClubLoginUseCase() {
        final ClubLoginOutputBoundary loginOutputBoundary = new ClubLoginPresenter(viewManagerModel,
                clubHomeViewModel, clubSignupViewModel, loginViewModel);
        final ClubLoginInputBoundary loginInteractor = new ClubLoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final ClubLoginController loginController = new ClubLoginController(loginInteractor);
        loginView.setClubLoginController(loginController);
        return this;
    }

    /**
     * Adds the Student Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addStudentLoginUseCase() {
        final StudentLoginOutputBoundary loginOutputBoundary = new StudentLoginPresenter(viewManagerModel,
                studentHomeViewModel, studentSignupViewModel, loginViewModel);
        final StudentLoginInputBoundary loginInteractor = new StudentLoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final StudentLoginController loginController = new StudentLoginController(loginInteractor);
        loginView.setStudentLoginController(loginController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
