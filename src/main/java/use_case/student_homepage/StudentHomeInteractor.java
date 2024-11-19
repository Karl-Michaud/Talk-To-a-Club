package use_case.student_homepage;

/**
 * The Student Home Interactor.
 */
public class StudentHomeInteractor implements StudentHomeInputBoundary {

    private final StudentHomeOutputBoundary studentHomeOutputBoundary;

    public StudentHomeInteractor(StudentHomeOutputBoundary studentHomeOutputBoundary) {
        this.studentHomeOutputBoundary = studentHomeOutputBoundary;
    }

    @Override
    public void execute(StudentHomeInputData student) {

    }

    @Override
    public void switchToLoginView() {
        studentHomeOutputBoundary.switchToLoginView();
    }

    @Override
    public void switchToProfileView() {
        studentHomeOutputBoundary.switchToProfileView();
    }
}
