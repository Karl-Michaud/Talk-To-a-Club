package use_case.login.student_login;

import entity.user.Student;

/**
 * The interactor for the student log in use case.
 */
public class StudentLoginInteractor implements StudentLoginInputBoundary {
    private final StudentLoginDataAccessInterface studentDataAccessObject;
    private final StudentLoginOutputBoundary studentLoginPresenter;

    public StudentLoginInteractor(StudentLoginDataAccessInterface studentDataAccessInterface,
                                  StudentLoginOutputBoundary studentLoginPresenter) {
        this.studentDataAccessObject = studentDataAccessInterface;
        this.studentLoginPresenter = studentLoginPresenter;
    }

    /**
     * Executes the login use case for the student user.
     * @param studentLoginInputData the input data
     */
    public void execute(StudentLoginInputData studentLoginInputData) {
        final String username = studentLoginInputData.getUsername();
        final String password = studentLoginInputData.getPassword();
        if (!studentDataAccessObject.existsByName(username)) {
            studentLoginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = studentDataAccessObject.getStudent(username).getPassword();
            if (!pwd.equals(password)) {
                studentLoginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
                final Student student = studentDataAccessObject.getStudent(username);
                final StudentLoginOutputData loginOutputData = new StudentLoginOutputData(student.getUsername(),
                        student.getJoinedClubs(), false);
                studentLoginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToStudentHomeView() {
        studentLoginPresenter.switchToStudentHomeView();
    }
}
