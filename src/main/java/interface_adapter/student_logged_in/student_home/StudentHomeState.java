package interface_adapter.student_logged_in.student_home;

import java.util.List;
import java.util.Map;

/**
 * The state for the Student Home View Model.
 */
public class StudentHomeState {
    private Map<String, List<Map<String, Object>>> postData;
    private String currentUserEmail;
    private String username = "";
    private String query = "";
    private String studentHomeError;
    private List<Map<String, String>> clubsFollowed;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "StudentHomeState{"
                + "username='" + username + '\''
                + '}';
    }

    public void setCurrentUser(String currUserEmail) {
        this.currentUserEmail = currUserEmail;
    }

    public String getCurrentUser() {
        return this.currentUserEmail;
    }

    public void setClubs(List<Map<String, String>> clubs) {
        this.clubsFollowed = clubs;
    }

    public List<Map<String, String>> getClubs() {
        return this.clubsFollowed;
    }

    public Map<String, List<Map<String, Object>>> getPostData() {
        return this.postData;
    }
    public void setPostData(Map<String, List<Map<String, Object>>> postData) {
        this.postData = postData;
    }
}
