package use_case.student_homepage;

/**
 * The implemented InputData class for the StudentHome usecase.
 */
public class StudentHomeInputData {
    private final String email;

    public StudentHomeInputData(String email) {
        this.email = email;

    }

    String getEmail() {
        return email;
    }
}
