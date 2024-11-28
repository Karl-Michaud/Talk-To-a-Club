package use_case.logout;

import org.junit.jupiter.api.Test;

public class LogoutInteractorTest {

    @Test
    void successTest() {
        // This creates a successPresenter that tests whether the test case is as we expect.
        // Note: This use case is to just switch to the login view, so it's a bit empty
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void switchToLoginView() {
                // expected to succeed
            }
        };
        LogoutInputBoundary interactor = new LogoutInteractor (successPresenter);
        interactor.execute();
    }
}
