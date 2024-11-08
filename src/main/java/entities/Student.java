package entities;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Student class implements User. A Student is a type of user.
 */
public class Student implements User {
    // Student's personal information
    private String username;
    private String email;
    private String password;
    private int studentID;

    // Student club information
    private Map<Integer, User> joinedClubs = new HashMap<>();

    public Student(String username, String email, String password, Map<Integer, User> joinedClubs) {
        // Initialise student's personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Temporarily sets the student's ID to -1. This means that the user is not in the database yet.
        this.studentID = -1;

        // Initialise student's club information
        this.joinedClubs = joinedClubs;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return studentID;
    }

    public void setUserID(int userID) {
        this.studentID = userID;
    }

    public Map<Integer, User> getJoinedClubs() {
        return joinedClubs;
    }

    /**
     * Student joins given club. Do not forget to update the particular club's members!
     * @param club particular club to be joined.
     */
    public void joinClub(Club club) {
        joinedClubs.put(club.getUserID(), club);
    }

    /**
     * Student leaves given club. Do not forget to update the particular club's members!
     * @param club particular club to be left.
     */
    public void leaveClub(Club club) {
        joinedClubs.remove(club.getUserID());
    }

}
