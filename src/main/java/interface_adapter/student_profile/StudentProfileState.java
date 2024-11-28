package interface_adapter.student_profile;

public class StudentProfileState {
    private String studentProfileError;

    public Object getStudentProfileError() {
        return this.studentProfileError;
    }

    public void setStudentProfileError(String studentProfileError) {
        this.studentProfileError = studentProfileError;
    }
}
