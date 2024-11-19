package use_case.student_homepage;

public interface StudentHomeOutputBoundary {

    void prepareSuccessView(StudentHomeOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToLoginView();

    void switchToProfileView();
}
