package use_case.student_get_clubs;

/**
 * Input data for the get clubs use case.
 */
public class StudentGetClubsInputData {
    private final String email;

    public StudentGetClubsInputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
