package data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;


import entity.user.*;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Persisting memory implementation of the DAO for storing user data
 * This implementation uses Firebase
 */
public class FirestoreUserDataAccessObject implements ClubLoginDataAccessInterface, ClubSignupUserDataAccessInterface,
    StudentSignupUserDataAccessInterface, StudentLoginDataAccessInterface {
    private final Firestore db;
    private final String students = "students";
    private final String clubs = "clubs";
    private final String emails = "email";

    public FirestoreUserDataAccessObject() throws IOException {
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
        final DocumentReference docRef = db.collection(students).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        Student returnValue = null;
        try {
            final DocumentSnapshot document = future.get();
            if (document.exists()) {
                returnValue = document.toObject(Student.class);
            }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return returnValue;
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
        return null;
    }

    @Override
    public boolean existsByNameStudent(String username) {
        final DocumentReference docRef = db.collection(students).document(username);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        boolean returnValue;
        try {
            final DocumentSnapshot document = future.get();
            returnValue = document.exists();
        }
        catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean existsByNameClub(String username) {
        final DocumentReference docRef = db.collection(clubs).document(username);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        boolean returnValue;
        try {
            final DocumentSnapshot document = future.get();
            returnValue = document.exists();
        }
        catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean existsByEmailStudent(String email) {
        final ApiFuture<QuerySnapshot> future = db.collection(students).whereEqualTo(emails, email).get();
        boolean returnValue;
        try {
            returnValue = !future.get().isEmpty();
        }
        catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean existsByEmailClub(String email) {
        final ApiFuture<QuerySnapshot> future = db.collection(clubs).whereEqualTo(emails, email).get();
        boolean returnValue;
        try {
            returnValue = !future.get().isEmpty();
        }
        catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public void saveStudent(Student user) {
        DocumentReference docRef = db.collection(students).document(user.getEmail());
        ApiFuture<WriteResult> writeResult = docRef.set(user);
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    @Override
    public void saveClub(Club user) {
        DocumentReference docRef = db.collection("users").document(user.getUsername());
        ApiFuture<WriteResult> writeResult = docRef.set(user);
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

}
