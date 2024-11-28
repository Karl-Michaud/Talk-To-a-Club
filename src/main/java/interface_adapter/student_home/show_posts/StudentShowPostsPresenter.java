package interface_adapter.student_home.show_posts;

import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.student_homepage.show_posts.ShowPostsOutputBoundary;
import use_case.student_homepage.show_posts.ShowPostsOutputData;

/**
 * The presenter that passes the posts on to the ViewModel for the StudentHomeView.
 */
public class StudentShowPostsPresenter implements ShowPostsOutputBoundary {
    private final StudentHomeViewModel studentHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentShowPostsPresenter(StudentHomeViewModel studentHomeViewModel, ViewManagerModel viewManagerModel) {
        this.studentHomeViewModel = studentHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePostContent(ShowPostsOutputData showPostsOutputData) {
        final StudentHomeState state = studentHomeViewModel.getState();
        final Map<String, List<Map<String, Object>>> postData = showPostsOutputData.getPostData();

        state.setPostData(postData);
        state.setCurrentUser(showPostsOutputData.getCurrStudent());

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
