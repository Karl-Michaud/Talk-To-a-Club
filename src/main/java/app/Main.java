package app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.swing.JFrame;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) throws IOException {
        final FileInputStream serviceAccount =
                new FileInputStream("/Users/kabirkumar/Desktop/ServiceAccountKey.json");

        final FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

//        final AppBuilder appBuilder = new AppBuilder();
//        // TODO: add the Logout Use Case to the app using the appBuilder
//        final JFrame application = appBuilder
//                .addLoginView()
//                .addClubSignupView()
//                .addStudentSignupView()
//                .addClubHomeView()
//                .addStudentHomeView()
//                .addStudentProfileView()
//                .addCreatePostView()
//                .addClubSignupUseCase()
//                .addStudentSignupUseCase()
//                .addClubLoginUseCase()
//                .addStudentLoginUseCase()
//                .addStudentHomeUseCase()
//                .addStudentProfileUseCase()
//                .addShowPostsUseCase()
//                .addLikeUseCase()
//                .addDislikeUseCase()
//                .addClubUpdateDescUseCase()
//                .addClubCreatePostUseCase()
//                .addClubGetPostsUseCase()
//                .addClubGetMembersUseCase()
//                .addLogoutUseCase()
//                .addShowClubsUseCase()
//                .addClubRemoveMemberUseCase()
//                .build();

        final FirebaseAppBuilder appBuilder = new FirebaseAppBuilder();
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                .addLoginView()
                .addClubSignupView()
                .addStudentSignupView()
                .addClubHomeView()
                .addStudentHomeView()
                .addStudentProfileView()
                .addCreatePostView()
                .addExploreClubsView()
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
                .build();

        application.pack();
        application.setVisible(true);
    }
}
