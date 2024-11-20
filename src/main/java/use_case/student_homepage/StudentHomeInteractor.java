package use_case.student_homepage;

import entity.user.Student;

/**
 * The Student Home Interactor.
 */
public class StudentHomeInteractor implements StudentHomeInputBoundary {
    private final StudentHomeAccessInterface studentHomeAccessObject;
    private final StudentHomeOutputBoundary studentHomePresenter;

    public StudentHomeInteractor(StudentHomeAccessInterface studentHomeAccessInterface,
                                 StudentHomeOutputBoundary studentHomeOutputBoundary) {
        this.studentHomePresenter = studentHomeOutputBoundary;
        this.studentHomeAccessObject = studentHomeAccessInterface;

    }

    @Override
    public void execute(StudentHomeInputData studentHomeInputData) {
        // We do not have to check whether the email exists here because the student is already signed in,
        // so it must necessarily exist.
        final String email = studentHomeInputData.getEmail();
        final Student student = studentHomeAccessObject.getStudent(email);
        final StudentHomeOutputData studentHomeOutputData = new StudentHomeOutputData(student.getJoinedClubs(),
                studentHomeAccessObject.getStudent(email));
        studentHomePresenter.prepareClubPostContent(studentHomeOutputData);
    }

    @Override
    public void switchToLoginView() {
        studentHomePresenter.switchToLoginView();
    }

    @Override
    public void switchToProfileView() {
        studentHomePresenter.switchToProfileView();
    }
}
