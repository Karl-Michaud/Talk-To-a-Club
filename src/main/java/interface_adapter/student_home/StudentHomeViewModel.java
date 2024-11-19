package interface_adapter.student_home;


import interface_adapter.ViewModel;

/**
 * The ViewModel for the Club Home View.
 */
public class StudentHomeViewModel extends ViewModel<StudentHomeState> {

    public StudentHomeViewModel() {
        super("student home");
        setState(new StudentHomeState());
    }
}