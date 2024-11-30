package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;
import entity.user.StudentFactory;
import entity.user.StudentUserFactory;
import use_case.club_remove_member.ClubRemoveMemberStudentDataAccessInterface;
import use_case.explore_clubs.StudentExploreClubsDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.student_homepage.StudentHomeAccessInterface;
import use_case.student_homepage.dislike.StudentDislikeStudentDataAccessInterface;
import use_case.student_homepage.like.StudentLikeStudentDataAccessInterface;
import use_case.student_homepage.show_clubs.StudentShowClubsAccessInterface;
import use_case.student_homepage.show_posts.StudentShowPostsStudentAccessInterface;
import use_case.student_join_club.StudentJoinClubDataAccessInterface;
import use_case.student_leave_club.StudentLeaveClubDataAccessInterface;
import use_case.student_search_club.StudentSearchClubAccessInterface;

/**
 * Persisting memory implementation of the DAO for storing user data.
 * This implementation uses Firebase and only persists data regarding the
 * Student entity
 */
public class StudentFirestoreUserDataStudentAccessObject implements StudentLoginDataAccessInterface,
        StudentSignupUserDataAccessInterface, StudentJoinClubDataAccessInterface,
        StudentLeaveClubDataAccessInterface, StudentSearchClubAccessInterface,
        ClubRemoveMemberStudentDataAccessInterface, StudentExploreClubsDataAccessInterface,
        StudentDislikeStudentDataAccessInterface, StudentLikeStudentDataAccessInterface,
        StudentShowClubsAccessInterface, StudentShowPostsStudentAccessInterface, StudentHomeAccessInterface {
    private final Firestore db;
    private final String students = "students";
    private final String usernames = "username";

    private final String password = "password";
    private final String joinedClubNames = "joinedClubNames";
    private final String joinedClubEmails = "joinedClubEmails";

    public StudentFirestoreUserDataStudentAccessObject() throws IOException {
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
                final String usernameVal = document.getString(usernames);
                final String passwordVal = document.getString(password);

                final ArrayList<String> joinedNames;
                joinedNames = (ArrayList<String>) document.get(joinedClubNames);
                final DataStore<String> joinedClubNamesVal = new DataStoreArrays().toDataStore(joinedNames);

                final ArrayList<String> joinedEmails;
                joinedEmails = (ArrayList<String>) document.get(joinedClubEmails);
                final DataStore<String> joinedClubEmailsVal = new DataStoreArrays().toDataStore(joinedEmails);

                final StudentUserFactory studentUserFactory = new StudentUserFactory();
                final Student student = studentUserFactory.create(email, usernameVal,
                        passwordVal, joinedClubEmailsVal, joinedClubNamesVal);

                returnValue = student;
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    /**
     * Gets the joined clubs for the given student.
     *
     * @param student the student
     * @return an array lists of clubs
     */
    @Override
    public ArrayList<Club> getStudentJoinedClubs(Student student) {
        // Similar to in-memory implementation
        final ClubFirestoreUserDataAccessObject clubDataAccess = new ClubFirestoreUserDataAccessObject();
        final DataStoreArrays<Club> allClubs = (DataStoreArrays<Club>) clubDataAccess.getAllClubs();
        final ArrayList<Club> returnValue = new ArrayList<>();
        for (Club club : allClubs) {
            if (club.getClubMembersEmails().contains(student.getEmail())) {
                returnValue.add(club);
            }
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
        final Map<String, Object> mapStudent = new HashMap<>();
        mapStudent.put(usernames, user.getUsername());
        mapStudent.put("email", user.getEmail());
        mapStudent.put(password, user.getPassword());
        mapStudent.put(joinedClubEmails, user.getJoinedClubsEmails().toArrayList().stream().toList());
        mapStudent.put(joinedClubNames, user.getJoinedClubsNames().toArrayList().stream().toList());

        final ApiFuture<WriteResult> writeResult = docRef.set(mapStudent);
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

        final ApiFuture<WriteResult> writeEmails = docRef.update(joinedClubEmails,
                student.getJoinedClubsEmails().toArrayList().stream().toList());

        final ApiFuture<WriteResult> writeNames = docRef.update(joinedClubNames,
                student.getJoinedClubsNames().toArrayList().stream().toList());

        try {
            System.out.println("Update time : " + writeEmails.get().getUpdateTime());
            System.out.println("Update time : " + writeNames.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }
}
