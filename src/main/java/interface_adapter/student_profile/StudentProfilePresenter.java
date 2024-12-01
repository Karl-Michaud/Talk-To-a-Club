package interface_adapter.student_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_logged_in.student_home.StudentHomeViewModel;
import use_case.student_profile.StudentProfileOutputBoundary;

/**
 * The presenter for the student profile page view.
 */
public class StudentProfilePresenter implements StudentProfileOutputBoundary {
    private final StudentProfileViewModel studentProfileViewModel;
    private final ViewManagerModel viewManagerModel;
    private final StudentHomeViewModel studentHomeViewModel;

    public StudentProfilePresenter(ViewManagerModel viewManagerModel, StudentProfileViewModel studentProfileViewModel,
                                   StudentHomeViewModel studentHomeViewModel) {
        this.studentProfileViewModel = studentProfileViewModel;
        this.studentHomeViewModel = studentHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToHomeScreen() {
        viewManagerModel.setState(studentHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
