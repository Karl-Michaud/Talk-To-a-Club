package entity.user;

import entity.data_structure.DataStore;

/**
 * This is the Student class implements User. A Student is a type of user.
 */
public class Student implements User {
    // Student's personal information
    private String username;
    private String email;
    private String password;

    // Student club information
    private DataStore<Club> joinedClubs;

    public Student(String username, String email, String password, DataStore<Club> joinedClubs) {
        // Initialise student's personal information
        this.username = username;
        this.email = email;
        this.password = password;

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

    public DataStore<Club> getJoinedClubs() {
        return joinedClubs;
    }

    /**
     * Student joins given club. Do not forget to update the particular club's members!
     * @param club particular club to be joined.
     */
    public void joinClub(Club club) {
        joinedClubs.add(club);
    }

    /**
     * Student leaves given club. Do not forget to update the particular club's members!
     * @param club particular club to be left.
     */
    public void leaveClub(Club club) {
        joinedClubs.remove(club);
    }

}
