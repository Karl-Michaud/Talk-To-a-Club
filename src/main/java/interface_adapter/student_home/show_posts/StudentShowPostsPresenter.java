package interface_adapter.student_home.show_posts;

import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.student_homepage.show_posts.StudentShowPostsOutputBoundary;
import use_case.student_homepage.show_posts.StudentShowPostsOutputData;

/**
 * The presenter that passes the posts on to the ViewModel for the StudentHomeView.
 */
public class StudentShowPostsPresenter implements StudentShowPostsOutputBoundary {
    private final StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentShowPostsPresenter(StudentHomeViewModel studentHomeViewModel, ViewManagerModel viewManagerModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePostContent(StudentShowPostsOutputData studentShowPostsOutputData) {
        final StudentHomeState state = studentHomeViewModel.getState();
        final Map<String, List<Map<String, Object>>> postData = studentShowPostsOutputData.getPostData();

        state.setPostData(postData);
        state.setCurrentUser(studentShowPostsOutputData.getCurrStudent());

        studentHomeViewModel.setState(state);
        studentHomeViewModel.firePropertyChanged("show posts");

        viewManagerModel.firePropertyChanged(studentHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged("show posts");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final StudentHomeState state = studentHomeViewModel.getState();
        state.setStudentHomeError(errorMessage);
    }
}
