package interface_adapter.student_home;

import use_case.student_homepage.StudentHomeInputBoundary;
import use_case.student_homepage.StudentHomeInputData;

public class StudentHomeController {

    private final StudentHomeInputBoundary studentHomeUseCaseInteractor;

    public StudentHomeController(StudentHomeInputBoundary studentHomeUseCaseInteractor) {
        this.studentHomeUseCaseInteractor = studentHomeUseCaseInteractor;
    }

    public void execute(String query) {
        final StudentHomeInputData studentHomeInputData = new StudentHomeInputData(query);

        studentHomeUseCaseInteractor.execute(studentHomeInputData);
    }

    public void switchToLoginView() {
        studentHomeUseCaseInteractor.switchToLoginView();
    }

    public void switchToProfileView() {
        studentHomeUseCaseInteractor.switchToProfileView();
    }
}
