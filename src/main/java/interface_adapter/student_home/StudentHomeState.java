package interface_adapter.student_home;

/**
 * The state for the Student Home View Model.
 */
public class StudentHomeState {
    private String username = "";

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentHomeState{"
                + "username='" + username + '\''
                + '}';
    }
}
