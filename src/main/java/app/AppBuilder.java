package app;

import data_access.UserDataAccessObject;
import entity.user.ClubUserFactory;
import entity.user.StudentUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_home.ClubHomeViewModel;
import interface_adapter.club_logged_in.create_post.CreatePostViewModel;
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
import interface_adapter.student_home.StudentHomeController;
import interface_adapter.student_home.StudentHomePresenter;
import interface_adapter.student_home.StudentHomeViewModel;
import interface_adapter.student_home.dislike.StudentDislikeController;
import interface_adapter.student_home.like.StudentLikeController;
import interface_adapter.student_home.show_clubs.ShowClubsController;
import interface_adapter.student_home.show_clubs.ShowClubsViewModel;
import interface_adapter.student_home.show_clubs.StudentShowClubsPresenter;
import interface_adapter.student_home.show_posts.ShowPostsController;
import interface_adapter.student_home.show_posts.ShowPostsViewModel;
import interface_adapter.student_home.show_posts.StudentShowPostsPresenter;
import interface_adapter.student_profile.StudentProfileController;
import interface_adapter.student_profile.StudentProfilePresenter;
import interface_adapter.student_profile.StudentProfileViewModel;
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
import use_case.student_homepage.StudentHomeInputBoundary;
import use_case.student_homepage.StudentHomeInteractor;
import use_case.student_homepage.StudentHomeOutputBoundary;
import use_case.student_homepage.dislike.DislikeInputBoundary;
import use_case.student_homepage.dislike.DislikeInteractor;
import use_case.student_homepage.like.LikeInputBoundary;
import use_case.student_homepage.like.LikeInteractor;
import use_case.student_homepage.show_clubs.ShowClubsInputBoundary;
import use_case.student_homepage.show_clubs.ShowClubsInteractor;
import use_case.student_homepage.show_clubs.ShowClubsOutputBoundary;
import use_case.student_homepage.show_posts.ShowPostsInputBoundary;
import use_case.student_homepage.show_posts.ShowPostsInteractor;
import use_case.student_homepage.show_posts.ShowPostsOutputBoundary;
import use_case.student_profile.StudentProfileInputBoundary;
import use_case.student_profile.StudentProfileInteractor;
import use_case.student_profile.StudentProfileOutputBoundary;
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
    private ShowPostsViewModel showPostsViewModel;
    private ShowClubsViewModel showClubsViewModel;

    private ClubHomeViewModel clubHomeViewModel;
    private ClubHomeView clubHomeView;

    private CreatePostViewModel createPostViewModel;
    private CreatePostView createPostView;

    private StudentProfileViewModel studentProfileViewModel;
    private StudentProfileView studentProfileView;

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
        showPostsViewModel = new ShowPostsViewModel();
        showClubsViewModel = new ShowClubsViewModel();
        studentHomeView = new StudentHomeView(studentHomeViewModel, showPostsViewModel, showClubsViewModel);
        cardPanel.add(studentHomeView, studentHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Student Profile View to the application.
     * @return this builder
     */
    public AppBuilder addStudentProfileView() {
        studentProfileViewModel = new StudentProfileViewModel();
        studentProfileView = new StudentProfileView(studentProfileViewModel);
        cardPanel.add(studentProfileView, studentProfileView.getViewName());
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
                studentHomeViewModel, studentSignupViewModel, loginViewModel, showPostsViewModel);
        final StudentLoginInputBoundary loginInteractor = new StudentLoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final StudentLoginController loginController = new StudentLoginController(loginInteractor);
        loginView.setStudentLoginController(loginController);
        return this;
    }

    public AppBuilder addStudentHomeUseCase() {
        final StudentHomeOutputBoundary studentHomeOutputBoundary = new StudentHomePresenter(studentHomeViewModel,
                viewManagerModel, loginViewModel, studentProfileViewModel);
        final StudentHomeInputBoundary studentHomeInteractor = new StudentHomeInteractor(studentHomeOutputBoundary);

        final StudentHomeController studentHomeController = new StudentHomeController(studentHomeInteractor);
        studentHomeView.setStudentHomeController(studentHomeController);
        return this;
    }

    public AppBuilder addShowPostsUseCase() {
        final ShowPostsOutputBoundary showPostsOutputBoundary = new StudentShowPostsPresenter(showPostsViewModel);
        final ShowPostsInputBoundary showPostsInteractor = new ShowPostsInteractor(userDataAccessObject,
                showPostsOutputBoundary);

        final ShowPostsController showPostsController = new ShowPostsController(showPostsInteractor);
        studentHomeView.setShowPostsController(showPostsController);
        return this;
    }

    public AppBuilder addShowClubsUseCase() {
        final ShowClubsOutputBoundary showClubsOutputBoundary = new StudentShowClubsPresenter(showClubsViewModel);
        final ShowClubsInputBoundary showClubsInteractor = new ShowClubsInteractor(showClubsOutputBoundary,
                userDataAccessObject);
        final ShowClubsController showClubsController = new ShowClubsController(showClubsInteractor);
        studentHomeView.setShowClubsController(showClubsController);
    }

    public AppBuilder addLikeUseCase() {
        final LikeInputBoundary likeInteractor = new LikeInteractor(userDataAccessObject);
        final StudentLikeController likeController = new StudentLikeController(likeInteractor);
        studentHomeView.setLikeController(likeController);
        return this;
    }

    public AppBuilder addDislikeUseCase() {
        final DislikeInputBoundary dislikeInteractor = new DislikeInteractor(userDataAccessObject);
        final StudentDislikeController dislikeController = new StudentDislikeController(dislikeInteractor);
        studentHomeView.setDislikeController(dislikeController);
        return this;
    }

    public AppBuilder addStudentProfileUseCase() {
        final StudentProfileOutputBoundary studentProfileOutputBoundary = new StudentProfilePresenter(viewManagerModel,
                studentProfileViewModel, studentHomeViewModel);
        final StudentProfileInputBoundary studentProfileInteractor = new StudentProfileInteractor(
                studentProfileOutputBoundary
        );
        final StudentProfileController studentProfileController = new StudentProfileController(
                studentProfileInteractor
        );
        studentProfileView.setStudentProfileController(studentProfileController);
        return this;
    }

//    /**
//     * Adds the Club Create Post use case to the application.
//     * @return this builder
//     */
//    public AppBuilder addClubCreatePostUseCase() {
//        final ClubCreatePostOutputBoundary clubCreatePostOutputBoundary = new CreatePostPresenter(createPostViewModel,
//                viewManagerModel);
//        final ClubCreatePostInputBoundary clubCreatePostInteractor = new ClubCreatePostInteractor(userDataAccessObject,
//                clubCreatePostOutputBoundary);
//        final CreatePostController createPostController = new CreatePostController(clubCreatePostInteractor);
//        createPostView.setCreatePostController(createPostController);
//        return this;
//    }

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
