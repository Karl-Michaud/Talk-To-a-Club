package interface_adapter.student_home.show_clubs;

import java.util.ArrayList;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.student_homepage.show_clubs.StudentShowClubsOutputBoundary;
import use_case.student_homepage.show_clubs.StudentShowClubsOutputData;

/**
 * The presenter that passes the clubs on to the ViewModel for the StudentHomeView.
 */
public class StudentShowClubsPresenter implements StudentShowClubsOutputBoundary {
    private StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentShowClubsPresenter(StudentHomeViewModel studentHomeViewModel, ViewManagerModel viewManagerModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePostContent(StudentShowClubsOutputData studentShowClubsOutputData) {
        final StudentHomeState state = studentHomeViewModel.getState();
        final ArrayList<String> clubNames = (ArrayList<String>) studentShowClubsOutputData.getClubs();

        state.setClubs(clubNames);

        studentHomeViewModel.setState(state);
        studentHomeViewModel.firePropertyChanged("show clubs");

        viewManagerModel.setState(studentHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged("show clubs");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final StudentHomeState state = studentHomeViewModel.getState();
        state.setStudentHomeError(errorMessage);
    }
}
