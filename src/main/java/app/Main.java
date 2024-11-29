package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addClubSignupView()
                                            .addStudentSignupView()
                                            .addClubHomeView()
                                            .addStudentHomeView()
                                            .addStudentProfileView()
                                            .addCreatePostView()
                                            .addClubSignupUseCase()
                                            .addStudentSignupUseCase()
                                            .addClubLoginUseCase()
                                            .addStudentLoginUseCase()
                                            .addStudentHomeUseCase()
                                            .addStudentProfileUseCase()
                                            .addShowPostsUseCase()
                                            .addLikeUseCase()
                                            .addDislikeUseCase()
                                            .addClubUpdateDescUseCase()
                                            .addClubCreatePostUseCase()
                                            .addClubGetPostsUseCase()
                                            .addClubGetMembersUseCase()
                                            .addLogoutUseCase()
                                            .addShowClubsUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
