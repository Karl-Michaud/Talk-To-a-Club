package interface_adapter.club_create_post;

import interface_adapter.ViewManagerModel;
import use_case.club_create_post.ClubCreatePostOutputBoundary;
import use_case.club_create_post.ClubCreatePostOutputData;

/**
 * Create post use case presenter.
 */
public class ClubCreatePostPresenter implements ClubCreatePostOutputBoundary {
    private final ClubCreatePostViewModel clubCreatePostViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubCreatePostPresenter(ClubCreatePostViewModel clubCreatePostViewModel, ViewManagerModel viewManagerModel) {
        this.clubCreatePostViewModel = clubCreatePostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Login Use Case for students.
     * @param data the output data
     */
    public void prepareSuccessView(ClubCreatePostOutputData data) {
        final CreatePostState state = clubCreatePostViewModel.getState();
        state.setTimeOfPosting(data.getTimeOfPosting());
        state.setDateOfPosting(data.getDateOfPosting());
        state.setContent(data.getContents());
        state.setTitle(data.getTitle());
        clubCreatePostViewModel.setState(state);
        clubCreatePostViewModel.firePropertyChanged("create post");

        viewManagerModel.setState(clubCreatePostViewModel.getViewName());
        viewManagerModel.firePropertyChanged("create post");
    }

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage) {
        final CreatePostState state = clubCreatePostViewModel.getState();
        state.setCreatePostError(errorMessage);
        clubCreatePostViewModel.setState(state);
        clubCreatePostViewModel.firePropertyChanged();
    }
}
