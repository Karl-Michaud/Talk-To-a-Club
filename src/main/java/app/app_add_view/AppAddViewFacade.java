package app.app_add_view;

/**
 * View Facade that delegates the work of creating different views to other classes.
 */
public class AppAddViewFacade {
    private final ClubCreatePostViewMaker clubCreatePostViewMaker;
    private final ClubHomeViewMaker clubHomeViewMaker;
    private final ClubSignupViewMaker clubSignupViewMaker;
    private final LoginViewMaker loginViewMaker;
    private final StudentHomeViewMaker studentHomeViewMaker;
    private final StudentProfileViewMaker studentProfileViewMaker;
    private final StudentSignupViewMaker studentSignupViewMaker;

    public AppAddViewFacade() {
        clubCreatePostViewMaker = new ClubCreatePostViewMaker();
        clubHomeViewMaker = new ClubHomeViewMaker();
        clubSignupViewMaker = new ClubSignupViewMaker();
        loginViewMaker = new LoginViewMaker();
        studentHomeViewMaker = new StudentHomeViewMaker();
        studentProfileViewMaker = new StudentProfileViewMaker();
        studentSignupViewMaker = new StudentSignupViewMaker();
    }

    public ClubCreatePostViewMaker getClubCreatePostViewMaker() {
        return clubCreatePostViewMaker;
    }

    public ClubHomeViewMaker getClubHomeViewMaker() {
        return clubHomeViewMaker;
    }

    public ClubSignupViewMaker getClubSignupViewMaker() {
        return clubSignupViewMaker;
    }

    public LoginViewMaker getLoginViewMaker() {
        return loginViewMaker;
    }

    public StudentHomeViewMaker getStudentHomeViewMaker() {
        return studentHomeViewMaker;
    }

    public StudentProfileViewMaker getStudentProfileViewMaker() {
        return studentProfileViewMaker;
    }

    public StudentSignupViewMaker getStudentSignupViewMaker() {
        return studentSignupViewMaker;
    }

}
