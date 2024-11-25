package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutDataAccessInterface logoutDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutDataAccessInterface logoutDataAccessObject,
                                   LogoutOutputBoundary logoutPresenter) {
        this.logoutDataAccessObject = logoutDataAccessObject;
        this.logoutPresenter = logoutPresenter;
    }

    @Override
    public void execute() {

    }
}