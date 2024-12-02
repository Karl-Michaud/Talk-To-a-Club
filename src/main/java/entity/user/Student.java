package entity.user;

import entity.data_structure.DataStore;

/**
 * This is the Student class implements User. A Student is a type of user.
 */
public class Student implements User {
    // Student's personal information
    private final String username;
    private final String email;
    private final String password;

    // Student club information
    private final DataStore<String> joinedClubsNames;
    private final DataStore<String> joinedClubsEmails;

    public Student(String username, String email, String password, DataStore<String> joinedClubsEmails,
                   DataStore<String> joinedClubsNames) {
        // Initialise student's personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Initialise student's club information
        this.joinedClubsEmails = joinedClubsEmails;
        this.joinedClubsNames = joinedClubsNames;
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

    public DataStore<String> getJoinedClubsNames() {
        return joinedClubsNames;
    }

    public DataStore<String> getJoinedClubsEmails() {
        return joinedClubsEmails;
    }

    /**
     * Student joins given club. Do not forget to update the particular club's members!
     * @param club particular club to be joined.
     */
    public void joinClub(User club) {
        joinedClubsNames.add(club.getUsername());
        joinedClubsEmails.add(club.getEmail());
    }

    /**
     * Student leaves given club. Do not forget to update the particular club's members!
     * @param club particular club to be left.
     */
    public void leaveClub(User club) {
        joinedClubsNames.remove(club.getUsername());
        joinedClubsEmails.remove(club.getEmail());
    }

}
