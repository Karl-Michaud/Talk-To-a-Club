package app;

import data_access.UserDataAccessObject;
import entity.user.ClubUserFactory;
import entity.user.StudentUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.ClubSignupView;
import view.StudentHomeView;
import view.LoginView;
import view.StudentSignupView;
import view.ViewManager;
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

    private ClubSignupView clubSignupView;
    private StudentSignupView studentSignupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private StudentHomeViewModel homeViewModel;
    private StudentHomeView homeView;
    private LoginView loginView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */

    public AppBuilder addClubSignupView() {
        signupViewModel = new SignupViewModel();
        clubSignupView = new ClubSignupView(signupViewModel);
        cardPanel.add(clubSignupView, clubSignupView.getViewName());
        return this;
    }

    public AppBuilder addStudentSignupView() {
        signupViewModel = new SignupViewModel();
        studentSignupView = new StudentSignupView(signupViewModel);
        cardPanel.add(studentSignupView, studentSignupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        homeViewModel = new StudentHomeViewModel();
        homeView = new StudentHomeView(homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary,
                clubFactory, studentFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        clubSignupView.setSignupController(controller);
        studentSignupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                homeViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
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
