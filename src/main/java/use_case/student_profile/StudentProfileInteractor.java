package use_case.student_profile;

/**
 * Interactor for the student profile use case.
 */
public class StudentProfileInteractor implements StudentProfileInputBoundary {
    private final StudentProfileOutputBoundary studentProfileOutputBoundary;

    public StudentProfileInteractor(StudentProfileOutputBoundary studentProfileOutputBoundary) {
        this.studentProfileOutputBoundary = studentProfileOutputBoundary;
    }

    @Override
    public void switchToHomeScreen() {
        studentProfileOutputBoundary.switchToHomeScreen();
    }
}
