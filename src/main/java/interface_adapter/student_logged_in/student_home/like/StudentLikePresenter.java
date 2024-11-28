package interface_adapter.student_logged_in.student_home.like;

import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_logged_in.student_home.StudentHomeState;
import interface_adapter.student_logged_in.student_home.StudentHomeViewModel;
import use_case.student_homepage.like.StudentLikeOutputBoundary;
import use_case.student_homepage.like.StudentLikeOutputData;

/**
 * The presenter for the like usecase.
 */
public class StudentLikePresenter implements StudentLikeOutputBoundary {
    private final StudentHomeViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentLikePresenter(StudentHomeViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(StudentLikeOutputData data) {
        final Map<String, Object> postData = data.getPostData();

        final StudentHomeState currentState = viewModel.getState();

        // the old post data, which as to be updated to reflect the new like.
        final Map<String, List<Map<String, Object>>> postsByClub = currentState.getPostData();
        final int indexToUpdate;
        for (Map<String, Object> post : postsByClub.get(data.getClubName())) {
            if (post.get("time") == postData.get("time") && post.get("date") == postData.get("date")) {
                indexToUpdate = postsByClub.get(data.getClubName()).indexOf(post);
                postsByClub.get(data.getClubName()).set(indexToUpdate, postData);
                break;
            }
        }
        currentState.setPostData(postsByClub);
        viewModel.setState(currentState);
        viewModel.firePropertyChanged("liked");

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged("liked");
    }

    @Override
    public void prepareErrorView(String errorMessage) {
        final StudentHomeState currentState = viewModel.getState();
        currentState.setStudentHomeError(errorMessage);
        viewModel.setState(currentState);
        viewModel.firePropertyChanged();
    }
}
