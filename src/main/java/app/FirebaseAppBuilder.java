package app;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.ClubFirestoreUserDataAccessObject;
import data_access.StudentFirestoreUserDataAccessObject;
import entity.user.ClubUserFactory;
import entity.user.StudentUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_create_post.ClubCreatePostController;
import interface_adapter.club_create_post.ClubCreatePostPresenter;
import interface_adapter.club_create_post.ClubCreatePostViewModel;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import interface_adapter.club_logged_in.club_get_members.ClubGetMembersController;
import interface_adapter.club_logged_in.club_get_members.ClubGetMembersPresenter;
import interface_adapter.club_logged_in.club_get_posts.ClubGetPostsController;
import interface_adapter.club_logged_in.club_get_posts.ClubGetPostsPresenter;
import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberController;
import interface_adapter.club_logged_in.club_remove_member.ClubRemoveMemberPresenter;
import interface_adapter.club_logged_in.club_update_desc.ClubUpdateDescController;
import interface_adapter.club_logged_in.club_update_desc.ClubUpdateDescPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.club_login.ClubLoginController;
import interface_adapter.login.club_login.ClubLoginPresenter;
import interface_adapter.login.student_login.StudentLoginController;
import interface_adapter.login.student_login.StudentLoginPresenter;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.club_signup.ClubSignupController;
import interface_adapter.signup.club_signup.ClubSignupPresenter;
import interface_adapter.signup.club_signup.ClubSignupViewModel;
import interface_adapter.signup.student_signup.StudentSignupController;
import interface_adapter.signup.student_signup.StudentSignupPresenter;
import interface_adapter.signup.student_signup.StudentSignupViewModel;
import interface_adapter.student_explore_clubs.ExploreClubsController;
import interface_adapter.student_explore_clubs.ExploreClubsPresenter;
import interface_adapter.student_explore_clubs.ExploreClubsViewModel;
import interface_adapter.student_home.StudentHomeController;
import interface_adapter.student_home.StudentHomePresenter;
import interface_adapter.student_home.StudentHomeViewModel;
import interface_adapter.student_home.student_dislike.StudentDislikeController;
import interface_adapter.student_home.student_dislike.StudentDislikePresenter;
import interface_adapter.student_home.student_join_club.JoinClubController;
import interface_adapter.student_home.student_join_club.JoinClubPresenter;
import interface_adapter.student_home.student_leave_club.LeaveClubController;
import interface_adapter.student_home.student_leave_club.LeaveClubPresenter;
import interface_adapter.student_home.student_like.StudentLikeController;
import interface_adapter.student_home.student_like.StudentLikePresenter;
import interface_adapter.student_home.student_show_clubs.StudentShowClubsController;
import interface_adapter.student_home.student_show_clubs.StudentShowClubsPresenter;
import interface_adapter.student_home.student_show_posts.StudentShowPostsController;
import interface_adapter.student_home.student_show_posts.StudentShowPostsPresenter;
import interface_adapter.student_profile.StudentProfileController;
import interface_adapter.student_profile.StudentProfilePresenter;
import interface_adapter.student_profile.StudentProfileViewModel;
import use_case.club_create_post.ClubCreatePostInputBoundary;
import use_case.club_create_post.ClubCreatePostInteractor;
import use_case.club_create_post.ClubCreatePostOutputBoundary;
import use_case.club_get_members.ClubGetMembersInputBoundary;
import use_case.club_get_members.ClubGetMembersInteractor;
import use_case.club_get_members.ClubGetMembersOutputBoundary;
import use_case.club_get_posts.ClubGetPostsInputBoundary;
import use_case.club_get_posts.ClubGetPostsInteractor;
import use_case.club_get_posts.ClubGetPostsOutputBoundary;
import use_case.club_remove_member.ClubRemoveMemberInputBoundary;
import use_case.club_remove_member.ClubRemoveMemberInteractor;
import use_case.club_remove_member.ClubRemoveMemberOutputBoundary;
import use_case.club_update_desc.ClubUpdateDescInputBoundary;
import use_case.club_update_desc.ClubUpdateDescInteractor;
import use_case.club_update_desc.ClubUpdateDescOutputBoundary;
import use_case.login.club_login.ClubLoginInputBoundary;
import use_case.login.club_login.ClubLoginInteractor;
import use_case.login.club_login.ClubLoginOutputBoundary;
import use_case.login.student_login.StudentLoginInputBoundary;
import use_case.login.student_login.StudentLoginInteractor;
import use_case.login.student_login.StudentLoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.club_signup.ClubSignupInputBoundary;
import use_case.signup.club_signup.ClubSignupInteractor;
import use_case.signup.club_signup.ClubSignupOutputBoundary;
import use_case.signup.student_signup.StudentSignupInputBoundary;
import use_case.signup.student_signup.StudentSignupInteractor;
import use_case.signup.student_signup.StudentSignupOutputBoundary;
import use_case.student_dislike.StudentDislikeInputBoundary;
import use_case.student_dislike.StudentDislikeInteractor;
import use_case.student_dislike.StudentDislikeOutputBoundary;
import use_case.student_explore_clubs.ExploreClubsInputBoundary;
import use_case.student_explore_clubs.ExploreClubsInteractor;
import use_case.student_explore_clubs.ExploreClubsOutputBoundary;
import use_case.student_homepage.StudentHomeInputBoundary;
import use_case.student_homepage.StudentHomeInteractor;
import use_case.student_homepage.StudentHomeOutputBoundary;
import use_case.student_join_club.StudentJoinClubInputBoundary;
import use_case.student_join_club.StudentJoinClubInteractor;
import use_case.student_join_club.StudentJoinClubOutputBoundary;
import use_case.student_leave_club.StudentLeaveClubInputBoundary;
import use_case.student_leave_club.StudentLeaveClubInteractor;
import use_case.student_leave_club.StudentLeaveClubOutputBoundary;
import use_case.student_like.StudentLikeInputBoundary;
import use_case.student_like.StudentLikeInteractor;
import use_case.student_like.StudentLikeOutputBoundary;
import use_case.student_profile.StudentProfileInputBoundary;
import use_case.student_profile.StudentProfileInteractor;
import use_case.student_profile.StudentProfileOutputBoundary;
import use_case.student_show_clubs.StudentShowClubsInputBoundary;
import use_case.student_show_clubs.StudentShowClubsInteractor;
import use_case.student_show_clubs.StudentShowClubsOutputBoundary;
import use_case.student_show_posts.StudentShowPostsInputBoundary;
import use_case.student_show_posts.StudentShowPostsInteractor;
import use_case.student_show_posts.StudentShowPostsOutputBoundary;
import view.ClubCreatePostView;
import view.ClubLoggedInView;
import view.ClubPageView;
import view.ClubSignupView;
import view.ExploreClubsView;
import view.LoginView;
import view.StudentHomeView;
import view.StudentProfileView;
import view.StudentSignupView;
import view.ViewManager;

// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class FirebaseAppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final StudentUserFactory studentFactory = new StudentUserFactory();
    private final ClubUserFactory clubFactory = new ClubUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel, viewManagerModel);

    private final ClubFirestoreUserDataAccessObject clubFirestoreUserDataAccessObject =
            new ClubFirestoreUserDataAccessObject();
    private final StudentFirestoreUserDataAccessObject studentFirestoreUserDataAccessObject =
            new StudentFirestoreUserDataAccessObject();

    private ClubSignupViewModel clubSignupViewModel;
    private ClubSignupView clubSignupView;

    private StudentSignupViewModel studentSignupViewModel;
    private StudentSignupView studentSignupView;

    private LoginViewModel loginViewModel;
    private LoginView loginView;

    private StudentHomeViewModel studentHomeViewModel;
    private StudentHomeView studentHomeView;

    private ClubLoggedInViewModel clubLoggedInViewModel;
    private ClubLoggedInView clubLoggedInView;

    private ClubCreatePostViewModel clubCreatePostViewModel;
    private ClubCreatePostView clubCreatePostView;

    private StudentProfileViewModel studentProfileViewModel;
    private StudentProfileView studentProfileView;

    private ExploreClubsViewModel exploreClubsViewModel;
    private ExploreClubsView exploreClubsView;

    private ClubPageView clubPageView;

    public FirebaseAppBuilder() throws IOException {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Club Signup View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubSignupView() {
        clubSignupViewModel = new ClubSignupViewModel();
        clubSignupView = new ClubSignupView(clubSignupViewModel);
        cardPanel.add(clubSignupView, clubSignupView.getViewName());
        return this;
    }

    /**
     * Adds the Student Signup View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addStudentSignupView() {
        studentSignupViewModel = new StudentSignupViewModel();
        studentSignupView = new StudentSignupView(studentSignupViewModel);
        cardPanel.add(studentSignupView, studentSignupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Club Home View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubHomeView() {
        clubLoggedInViewModel = new ClubLoggedInViewModel();
        clubLoggedInView = new ClubLoggedInView(clubLoggedInViewModel);
        cardPanel.add(clubLoggedInView, clubLoggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Student Home View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addStudentHomeView() {
        studentHomeViewModel = new StudentHomeViewModel();
        studentHomeView = new StudentHomeView(studentHomeViewModel);
        cardPanel.add(studentHomeView, studentHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Student Profile View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addStudentProfileView() {
        studentProfileViewModel = new StudentProfileViewModel();
        studentProfileView = new StudentProfileView(studentProfileViewModel);
        cardPanel.add(studentProfileView, studentProfileView.getViewName());
        return this;
    }

    /**
     * Adds the Club Create Post View to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addCreatePostView() {
        clubCreatePostViewModel = new ClubCreatePostViewModel();
        clubCreatePostView = new ClubCreatePostView(clubCreatePostViewModel);
        cardPanel.add(clubCreatePostView, clubCreatePostView.getViewName());
        return this;
    }

    /**
     * Adds the explore clubs view to the application.
     * @return this.
     */
    public FirebaseAppBuilder addExploreClubsView() {
        exploreClubsViewModel = new ExploreClubsViewModel();
        exploreClubsView = new ExploreClubsView(exploreClubsViewModel);
        cardPanel.add(exploreClubsView, exploreClubsView.getViewName());
        return this;
    }

    /**
     * Adds the club page view to the application.
     * @return this.
     */
    public FirebaseAppBuilder addClubPageView() {
        clubPageView = new ClubPageView(exploreClubsViewModel);
        cardPanel.add(clubPageView, clubPageView.getViewName());
        return this;
    }

    /**
     * Adds the Club Signup Use Case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubSignupUseCase() {
        final ClubSignupOutputBoundary signupOutputBoundary = new ClubSignupPresenter(viewManagerModel,
                clubSignupViewModel, loginViewModel);

        final ClubSignupInputBoundary userSignupInteractor = new ClubSignupInteractor(clubFirestoreUserDataAccessObject,
                signupOutputBoundary, clubFactory);

        final ClubSignupController controller = new ClubSignupController(userSignupInteractor);
        clubSignupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Student Signup Use Case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addStudentSignupUseCase() {
        final StudentSignupOutputBoundary signupOutputBoundary = new StudentSignupPresenter(viewManagerModel,
                studentSignupViewModel, loginViewModel);

        final StudentSignupInputBoundary userSignupInteractor = new StudentSignupInteractor(
                studentFirestoreUserDataAccessObject,
                signupOutputBoundary, studentFactory);

        final StudentSignupController controller = new StudentSignupController(userSignupInteractor);
        studentSignupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Club Login Use Case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubLoginUseCase() {
        final ClubLoginOutputBoundary loginOutputBoundary = new ClubLoginPresenter(viewManagerModel,
                clubLoggedInViewModel, clubSignupViewModel, loginViewModel);
        final ClubLoginInputBoundary loginInteractor = new ClubLoginInteractor(
                clubFirestoreUserDataAccessObject, loginOutputBoundary);

        final ClubLoginController loginController = new ClubLoginController(loginInteractor);
        loginView.setClubLoginController(loginController);
        return this;
    }

    /**
     * Adds the Student Login Use Case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addStudentLoginUseCase() {
        final StudentLoginOutputBoundary loginOutputBoundary = new StudentLoginPresenter(viewManagerModel,
                studentHomeViewModel, studentSignupViewModel, loginViewModel);
        final StudentLoginInputBoundary loginInteractor = new StudentLoginInteractor(
                studentFirestoreUserDataAccessObject, loginOutputBoundary);

        final StudentLoginController loginController = new StudentLoginController(loginInteractor);
        loginView.setStudentLoginController(loginController);
        return this;
    }

    /**
     * Adds the student home use case to the application.
     * @return this builder.
     */
    public FirebaseAppBuilder addStudentHomeUseCase() {
        final StudentHomeOutputBoundary studentHomeOutputBoundary = new StudentHomePresenter(studentHomeViewModel,
                viewManagerModel, loginViewModel, studentProfileViewModel);
        final StudentHomeInputBoundary studentHomeInteractor = new StudentHomeInteractor(studentHomeOutputBoundary);

        final StudentHomeController studentHomeController = new StudentHomeController(studentHomeInteractor);
        studentHomeView.setStudentHomeController(studentHomeController);
        return this;
    }

    /**
     * Adds the student show posts use case to this application.
     * @return this builder.
     */
    public FirebaseAppBuilder addShowPostsUseCase() {
        final StudentShowPostsOutputBoundary studentShowPostsOutputBoundary =
                new StudentShowPostsPresenter(studentHomeViewModel, viewManagerModel);

        final StudentShowPostsInputBoundary showPostsInteractor = new StudentShowPostsInteractor(
                studentFirestoreUserDataAccessObject, clubFirestoreUserDataAccessObject,
                studentShowPostsOutputBoundary);

        final StudentShowPostsController studentShowPostsController =
                new StudentShowPostsController(showPostsInteractor);
        studentHomeView.setShowPostsController(studentShowPostsController);
        clubPageView.setStudentShowPostsController(studentShowPostsController);
        return this;
    }

    /**
     * Adds the student show clubs use case to this application.
     * @return this builder.
     */
    public FirebaseAppBuilder addShowClubsUseCase() {
        final StudentShowClubsOutputBoundary studentShowClubsOutputBoundary =
                new StudentShowClubsPresenter(studentHomeViewModel, viewManagerModel);
        final StudentShowClubsInputBoundary showClubsInteractor =
                new StudentShowClubsInteractor(studentShowClubsOutputBoundary, studentFirestoreUserDataAccessObject);
        final StudentShowClubsController studentShowClubsController =
                new StudentShowClubsController(showClubsInteractor);
        studentHomeView.setShowClubsController(studentShowClubsController);
        clubPageView.setStudentShowClubsController(studentShowClubsController);
        return this;
    }

    /**
     * Adds the student like usecase to this application.
     * @return this builder.
     */
    public FirebaseAppBuilder addLikeUseCase() {
        final StudentLikeOutputBoundary studentLikeOutputBoundary =
                new StudentLikePresenter(studentHomeViewModel, viewManagerModel);
        final StudentLikeInputBoundary likeInteractor =
                new StudentLikeInteractor(studentFirestoreUserDataAccessObject,
                clubFirestoreUserDataAccessObject, studentLikeOutputBoundary);
        final StudentLikeController likeController = new StudentLikeController(likeInteractor);
        studentHomeView.setLikeController(likeController);
        return this;
    }

    /**
     * Adds the student dislike usecase to this application.
     * @return this builder.
     */
    public FirebaseAppBuilder addDislikeUseCase() {
        final StudentDislikeOutputBoundary studentDislikeOutputBoundary =
                new StudentDislikePresenter(studentHomeViewModel, viewManagerModel);
        final StudentDislikeInputBoundary dislikeInteractor =
                new StudentDislikeInteractor(clubFirestoreUserDataAccessObject,
                studentFirestoreUserDataAccessObject, studentDislikeOutputBoundary);
        final StudentDislikeController dislikeController = new StudentDislikeController(dislikeInteractor);
        studentHomeView.setDislikeController(dislikeController);
        return this;
    }

    /**
     * Adds the student profile use case to this application.
     * @return This builder.
     */
    public FirebaseAppBuilder addStudentProfileUseCase() {
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

    /**
     * Adds the Club Create Post use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubCreatePostUseCase() {
        final ClubCreatePostOutputBoundary clubCreatePostOutputBoundary = new ClubCreatePostPresenter(
                clubCreatePostViewModel, clubLoggedInViewModel, viewManagerModel);
        final ClubCreatePostInputBoundary clubCreatePostInteractor = new ClubCreatePostInteractor(
                clubFirestoreUserDataAccessObject, clubCreatePostOutputBoundary);
        final ClubCreatePostController createPostController = new ClubCreatePostController(clubCreatePostInteractor);
        clubCreatePostView.setClubCreatePostController(createPostController);
        clubLoggedInView.setClubCreatePostController(createPostController);
        clubCreatePostView.setClubCreatePostController(createPostController);
        return this;
    }

    /**
     * Adds the Club Get Members use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubGetMembersUseCase() {
        final ClubGetMembersOutputBoundary clubGetMembersOutputBoundary = new ClubGetMembersPresenter(
                clubLoggedInViewModel, viewManagerModel);
        final ClubGetMembersInputBoundary clubGetMembersInteractor = new ClubGetMembersInteractor(
                clubFirestoreUserDataAccessObject, clubGetMembersOutputBoundary);
        final ClubGetMembersController clubGetMembersController = new ClubGetMembersController(
                clubGetMembersInteractor);
        clubLoggedInView.setClubGetMembersController(clubGetMembersController);
        return this;
    }

    /**
     * Adds the Logout use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loginViewModel, viewManagerModel);
        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        clubLoggedInView.setLogoutController(logoutController);
        studentHomeView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Club Get Posts use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubGetPostsUseCase() {
        final ClubGetPostsOutputBoundary clubGetPostsOutputBoundary =
                new ClubGetPostsPresenter(clubLoggedInViewModel, viewManagerModel);
        final ClubGetPostsInputBoundary clubGetPostsInteractor =
                new ClubGetPostsInteractor(clubFirestoreUserDataAccessObject, clubGetPostsOutputBoundary);

        final ClubGetPostsController clubGetPostsController = new ClubGetPostsController(clubGetPostsInteractor);
        clubLoggedInView.setClubGetPostsController(clubGetPostsController);
        return this;
    }

    /**
     * Adds the Club Update Description use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubUpdateDescUseCase() {
        final ClubUpdateDescOutputBoundary clubUpdateDescOutputBoundary =
                new ClubUpdateDescPresenter(clubLoggedInViewModel, viewManagerModel);
        final ClubUpdateDescInputBoundary clubUpdateDescInteractor =
                new ClubUpdateDescInteractor(clubFirestoreUserDataAccessObject,
                clubUpdateDescOutputBoundary);

        final ClubUpdateDescController clubUpdateDescController =
                new ClubUpdateDescController(clubUpdateDescInteractor);
        clubLoggedInView.setClubUpdateDescController(clubUpdateDescController);
        return this;
    }

    /**
     * Adds the CLub Remove Member use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addClubRemoveMemberUseCase() {
        final ClubRemoveMemberOutputBoundary clubRemoveMemberOutputBoundary =
                new ClubRemoveMemberPresenter(clubLoggedInViewModel, viewManagerModel);
        final ClubRemoveMemberInputBoundary clubRemoveMemberInteractor =
                new ClubRemoveMemberInteractor(studentFirestoreUserDataAccessObject,
                clubFirestoreUserDataAccessObject, clubRemoveMemberOutputBoundary);

        final ClubRemoveMemberController clubRemoveMemberController =
                new ClubRemoveMemberController(clubRemoveMemberInteractor);
        clubLoggedInView.setClubRemoveMemberController(clubRemoveMemberController);
        return this;
    }

    /**
     * Adds the explore clubs use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addExploreClubsUseCase() {
        final ExploreClubsOutputBoundary exploreClubsOutputBoundary = new ExploreClubsPresenter(viewManagerModel,
                exploreClubsViewModel, studentHomeViewModel);
        final ExploreClubsInputBoundary exploreClubsInteractor =
                new ExploreClubsInteractor(studentFirestoreUserDataAccessObject, exploreClubsOutputBoundary,
                        clubFirestoreUserDataAccessObject);

        final ExploreClubsController exploreClubsController = new ExploreClubsController(exploreClubsInteractor);
        studentHomeView.setExploreClubsController(exploreClubsController);
        exploreClubsView.setExploreClubsController(exploreClubsController);
        clubPageView.setExploreClubsController(exploreClubsController);
        return this;
    }

    /**
     * Adds the join use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addJoinUseCase() {
        final StudentJoinClubOutputBoundary joinClubOutputBoundary = new JoinClubPresenter(exploreClubsViewModel);
        final StudentJoinClubInputBoundary joinClubInteractor =
                new StudentJoinClubInteractor(studentFirestoreUserDataAccessObject,
                        clubFirestoreUserDataAccessObject, joinClubOutputBoundary);

        final JoinClubController joinClubController = new JoinClubController(joinClubInteractor);
        clubPageView.setJoinClubController(joinClubController);
        return this;
    }

    /**
     * Adds the leave use case to the application.
     * @return this builder
     */
    public FirebaseAppBuilder addLeaveUseCase() {
        final StudentLeaveClubOutputBoundary leaveClubOutputBoundary = new LeaveClubPresenter(exploreClubsViewModel);
        final StudentLeaveClubInputBoundary leaveClubInteractor =
                new StudentLeaveClubInteractor(studentFirestoreUserDataAccessObject,
                        clubFirestoreUserDataAccessObject, leaveClubOutputBoundary);

        final LeaveClubController leaveClubController = new LeaveClubController(leaveClubInteractor);
        clubPageView.setLeaveClubController(leaveClubController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Talk To a Club app");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
