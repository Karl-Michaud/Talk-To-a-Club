package use_case.student_homepage;

/**
 * Interface for the StudentHome output boundary.
 */
public interface StudentHomeOutputBoundary {

    /**
     * Prepares a failed view incase something goes wrong.
     * @param errorMessage the error message relating to the issue.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();
}
