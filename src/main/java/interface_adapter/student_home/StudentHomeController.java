package interface_adapter.student_home;

import use_case.student_homepage.StudentHomeInputBoundary;
import use_case.student_homepage.StudentHomeInputData;

public class StudentHomeController {

    private final StudentHomeInputBoundary studentHomeUseCaseInteractor;

    public StudentHomeController(StudentHomeInputBoundary studentHomeUseCaseInteractor) {
        this.studentHomeUseCaseInteractor = studentHomeUseCaseInteractor;
    }

    public void execute(String query, String email) {

        final StudentHomeInputData studentHomeInputData = new StudentHomeInputData(query, email);

        studentHomeUseCaseInteractor.execute(studentHomeInputData);
    }

    public void switchToLoginView() {
        studentHomeUseCaseInteractor.switchToLoginView();
    }

    public void switchToProfileView() {
        studentHomeUseCaseInteractor.switchToProfileView();
    }
}
