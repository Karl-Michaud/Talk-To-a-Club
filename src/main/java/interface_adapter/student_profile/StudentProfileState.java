package interface_adapter.student_profile;

/**
 * The state for the ViewModel of the student profile view.
 */
public class StudentProfileState {
    private String studentProfileError;

    public Object getStudentProfileError() {
        return this.studentProfileError;
    }

    public void setStudentProfileError(String studentProfileError) {
        this.studentProfileError = studentProfileError;
    }
}
