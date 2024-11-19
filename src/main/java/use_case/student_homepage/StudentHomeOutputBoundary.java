package use_case.student_homepage;

public interface StudentHomeOutputBoundary {

    void prepareClubPostContent(StudentHomeOutputData studentHomeOutputData);

    void prepareFailView(String errorMessage);

    void switchToLoginView();

    void switchToProfileView();
}
