package use_case.student_homepage;

/**
 * The implemented InputData class for the StudentHome usecase.
 */
public class StudentHomeInputData {
    private final String query;
    private final String email;

    public StudentHomeInputData(String query, String email) {
        this.query = query;
        this.email = email;

    }

    String getQuery() {
        return query;
    }

    String getEmail() {
        return email;
    }
}
