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
        final String studentEmail = studentLoginInputData.getStudentEmail();
        final String password = studentLoginInputData.getPassword();
        if (!studentDataAccessObject.existsByEmailStudent(studentEmail)) {
            studentLoginPresenter.prepareFailView(studentEmail + ": Account does not exist.");
        }
        else {
            final String pwd = studentDataAccessObject.getStudent(studentEmail).getPassword();
            if (!pwd.equals(password)) {
                studentLoginPresenter.prepareFailView("Incorrect password for \"" + studentEmail + "\".");
            }
            else {
                final Student student = studentDataAccessObject.getStudent(studentEmail);
                final StudentLoginOutputData loginOutputData = new StudentLoginOutputData(student.getUsername(),
                        student.getEmail(), false);
                studentLoginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    /**
     * Switches to the Student Signup View.
     */
    @Override
    public void switchToStudentSignupView() {
        studentLoginPresenter.switchToStudentSignupView();
    }
}
