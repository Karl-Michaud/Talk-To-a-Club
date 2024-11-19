package use_case.student_homepage;

public interface StudentHomeInputBoundary {
    void execute(StudentHomeInputData student);

    void switchToLoginView();

    void switchToProfileView();
}
