package app;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * The Main class of our application.
 */
public class App {
    /**
     * Builds and runs the CA architecture of the application.
     * @param filePath file path to the Service Account Key file on the user's computer.
     * @throws IOException and IOException when the credentials aren't fetched properly.
     */
    public void run(String filePath) throws IOException {
        final FileInputStream serviceAccount =
                new FileInputStream(filePath);

        final FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        final FirebaseAppBuilder appBuilder = new FirebaseAppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addClubSignupView()
                .addStudentSignupView()
                .addClubHomeView()
                .addStudentHomeView()
                .addStudentProfileView()
                .addCreatePostView()
                .addExploreClubsView()
                .addClubPageView()
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
                .addClubRemoveMemberUseCase()
                .addExploreClubsUseCase()
                .addJoinUseCase()
                .addLeaveUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
