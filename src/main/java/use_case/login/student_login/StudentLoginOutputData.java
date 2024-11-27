package use_case.login.student_login;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * The output data for the login use case for students.
 */
public class StudentLoginOutputData {
    private final String username;
    private final String email;
    private final boolean useCaseFailed;

    public StudentLoginOutputData(String username, String email, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Returns a value that tells us if the use case failed or not.
     * @return a boolean value true if the use case failed, else false
     */
    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
