package use_case.login.student_login;

/**
 * The input data for the login use case for students.
 */
public class StudentLoginInputData {
    private final String studentEmail;
    private final String password;

    public StudentLoginInputData(String studentEmail, String password) {
        this.studentEmail = studentEmail;
        this.password = password;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getPassword() {
        return password;
    }

}
