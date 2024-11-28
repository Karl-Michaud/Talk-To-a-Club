package interface_adapter.student_profile;

import interface_adapter.ViewModel;

public class StudentProfileViewModel extends ViewModel<StudentProfileState> {
    public StudentProfileViewModel() {
        super("student profile");
        setState(new StudentProfileState());
    }
}
