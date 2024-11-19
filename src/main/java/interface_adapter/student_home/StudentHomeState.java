package interface_adapter.student_home;

/**
 * The state for the Student Home View Model.
 */
public class StudentHomeState {
    private String username = "";
    private String email = "";
    private String query = "";
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

    @Override
    public String toString() {
        return "StudentHomeState{"
                + "username='" + username + '\''
                + '}';
    }
}
