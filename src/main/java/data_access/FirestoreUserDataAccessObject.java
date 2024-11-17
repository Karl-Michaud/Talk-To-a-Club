package data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;


import entity.user.*;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Persisting memory implementation of the DAO for storing user data
 * This implementation uses Firebase
 */
public class FirestoreUserDataAccessObject implements ClubLoginDataAccessInterface, ClubSignupUserDataAccessInterface,
StudentSignupUserDataAccessInterface, StudentLoginDataAccessInterface {
    private final Firestore db;
    private final ClubUserFactory clubUserFactory;
    private final StudentUserFactory studentUserFactory;

    public FirestoreUserDataAccessObject(ClubUserFactory clubUserFactory) throws IOException {
        this.clubUserFactory = clubUserFactory;
        this.studentUserFactory = new StudentUserFactory();

        // TODO fix this to be environment variable
        FileInputStream serviceAccount =
                new FileInputStream("/ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }

    @Override
    public boolean existsByName(String username) {
        DocumentReference docRef = db.collection("users").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student getStudent(String username) {
        DocumentReference docRef = db.collection("users").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Student.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null; // Return null if no student is found
    }

    @Override
    public boolean existsByEmail(String email) {
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("email", email).get();
        try {
            return !future.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void saveStudent(User user) {
        DocumentReference docRef = db.collection("users").document(user.getUsername());
        ApiFuture<WriteResult> writeResult = docRef.set(user);
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    @Override
    public void saveClub(User user) {
        DocumentReference docRef = db.collection("users").document(user.getUsername());
        ApiFuture<WriteResult> writeResult = docRef.set(user);
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    @Override
    public Club getClub(String email) {
        ApiFuture<QuerySnapshot> future = db.collection("clubs").whereEqualTo("email", email).get();
        try {
            QuerySnapshot documents = future.get();
            if (!documents.isEmpty()) {
                DocumentSnapshot document = documents.getDocuments().get(0);
                return document.toObject(Club.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null; // Return null if no club is found
    }
}
