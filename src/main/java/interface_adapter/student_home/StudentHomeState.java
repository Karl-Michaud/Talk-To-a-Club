package interface_adapter.student_home;

import entity.data_structure.DataStore;
import entity.user.Club;
import entity.user.User;

/**
 * The state for the Student Home View Model.
 */
public class StudentHomeState {
    private User currentUser;
    private String username = "";
    private String email = "";
    private String query = "";
    private DataStore<Club> followedClubs;
    private String studentHomeError;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getStudentHomeError() {
        return this.studentHomeError;
    }

    public void setStudentHomeError(String studentHomeError) {
        this.studentHomeError = studentHomeError;
    }

    public void setFollowedClubs(DataStore<Club> followedClubs) {
        this.followedClubs = followedClubs;
    }

    public DataStore<Club> getFollowedClubs() {
        return this.followedClubs;
    }

    @Override
    public String toString() {
        return "StudentHomeState{"
                + "username='" + username + '\''
                + '}';
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
