package use_case.student_homepage;

public class StudentHomeInputData {
    private final String query;

    public StudentHomeInputData(String query) {
        this.query = query;
    }

    String getQuery() {
        return query;
    }


}
