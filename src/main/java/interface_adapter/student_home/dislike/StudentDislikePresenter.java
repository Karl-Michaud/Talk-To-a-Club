package interface_adapter.student_home.dislike;

import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import interface_adapter.student_home.StudentHomeState;
import interface_adapter.student_home.StudentHomeViewModel;
import use_case.student_dislike.StudentDislikeOutputBoundary;
import use_case.student_dislike.StudentDislikeOutputData;

/**
 * The presenter for the dislike usecase.
 */
public class StudentDislikePresenter implements StudentDislikeOutputBoundary {
    private final StudentHomeViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public StudentDislikePresenter(StudentHomeViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(StudentDislikeOutputData data) {
        final Map<String, Object> postData = data.getPostData();

        final StudentHomeState currentState = viewModel.getState();

        // the old post data, which as to be updated to reflect the new dislike.
        final Map<String, List<Map<String, Object>>> postsByClub = currentState.getPostData();
        final int indexToUpdate;
        for (Map<String, Object> post : postsByClub.get(data.getClubName())) {
            if (post.get("time") == postData.get("time") && post.get("state") == postData.get("state")) {
                indexToUpdate = postsByClub.get(data.getClubName()).indexOf(post);
                postsByClub.get(data.getClubName()).set(indexToUpdate, postData);
                break;
            }
        }
        currentState.setPostData(postsByClub);
        viewModel.setState(currentState);
        viewModel.firePropertyChanged("disliked");

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged("disliked");
    }

    @Override
    public void prepareErrorView(String errorMessage) {
        final StudentHomeState currentState = viewModel.getState();
        currentState.setStudentHomeError(errorMessage);
        viewModel.setState(currentState);
        viewModel.firePropertyChanged();
    }
}
