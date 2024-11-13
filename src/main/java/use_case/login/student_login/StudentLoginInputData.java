package use_case.login.student_login;

/**
 * The input data for the login use case for students.
 */
public class StudentLoginInputData {
    private final String username;
    private final String password;

    public StudentLoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
