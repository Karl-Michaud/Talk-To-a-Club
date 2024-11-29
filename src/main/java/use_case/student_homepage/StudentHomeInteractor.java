package use_case.student_homepage;

/**
 * The Student Home Interactor.
 */
public class StudentHomeInteractor implements StudentHomeInputBoundary {
    private final StudentHomeOutputBoundary studentHomePresenter;

    public StudentHomeInteractor(StudentHomeOutputBoundary studentHomeOutputBoundary) {
        this.studentHomePresenter = studentHomeOutputBoundary;
    }

    @Override
    public void switchToProfileView() {
        studentHomePresenter.switchToProfileView();
    }
}
