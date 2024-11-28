package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.user.Student;
import use_case.club_remove_member.ClubRemoveMemberStudentDataAccessInterface;
import use_case.explore_clubs.StudentExploreClubsDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.student_homepage.StudentHomeAccessInterface;
import use_case.student_homepage.dislike.StudentDislikeStudentDataAccessInterface;
import use_case.student_homepage.like.StudentLikeStudentDataAccessInterface;
import use_case.student_homepage.show_clubs.StudentShowClubsAccessInterface;
import use_case.student_homepage.show_posts.StudentShowPostsAccessInterface;
import use_case.student_join_club.StudentJoinClubDataAccessInterface;
import use_case.student_leave_club.StudentLeaveClubDataAccessInterface;
import use_case.student_search_club.StudentSearchClubAccessInterface;

/**
 * Persisting memory implementation of the DAO for storing user data.
 * This implementation uses Firebase and only persists data regarding the
 * Student entity
 */
public class StudentFirestoreUserDataAccessObject implements StudentLoginDataAccessInterface,
        StudentSignupUserDataAccessInterface, StudentJoinClubDataAccessInterface,
        StudentLeaveClubDataAccessInterface, StudentSearchClubAccessInterface,
        ClubRemoveMemberStudentDataAccessInterface, StudentExploreClubsDataAccessInterface,
        StudentDislikeStudentDataAccessInterface, StudentLikeStudentDataAccessInterface,
        StudentShowClubsAccessInterface, StudentShowPostsAccessInterface, StudentHomeAccessInterface {
    private final Firestore db;
    private final String students = "students";
    private final String usernames = "username";

    public StudentFirestoreUserDataAccessObject() throws IOException {
        // TODO fix this to be environment variable
        final FileInputStream serviceAccount =
                new FileInputStream("/ServiceAccountKey.json");

        final FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }

    @Override
    public Student getStudent(String email) {
        // get student through email
        final DocumentReference docRef = db.collection(students).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        Student returnValue = null;
        try {
            final DocumentSnapshot document = future.get();
            if (document.exists()) {
                returnValue = document.toObject(Student.class);
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public boolean existsByNameStudent(String username) {
        final ApiFuture<QuerySnapshot> future = db.collection(students).whereEqualTo(usernames, username).get();
        boolean returnValue;
        try {
            returnValue = !future.get().isEmpty();
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean existsByEmailStudent(String email) {
        final DocumentReference docRef = db.collection(students).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        boolean returnValue;
        try {
            final DocumentSnapshot document = future.get();
            returnValue = document.exists();
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public void saveStudent(Student user) {
        // save students based off their usernames for ID's
        final DocumentReference docRef = db.collection(students).document(user.getEmail());
        final ApiFuture<WriteResult> writeResult = docRef.set(user);
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateStudentClubsJoined(Student student) {
        // updates the student clubs joined
        // very similar to saveStudent
        final String email = student.getEmail();
        final DocumentReference docRef = db.collection(students).document(email);
        final ApiFuture<WriteResult> writeResult = docRef.set(student);

        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }
}
