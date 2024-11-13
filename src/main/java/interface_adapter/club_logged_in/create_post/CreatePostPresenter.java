package interface_adapter.club_logged_in.create_post;

import interface_adapter.ViewManagerModel;
import use_case.club_create_post.ClubCreatePostOutputBoundary;
import use_case.club_create_post.ClubCreatePostOutputData;

/**
 * Create post use case presenter.
 */
public class CreatePostPresenter implements ClubCreatePostOutputBoundary {
    private final CreatePostViewModel createPostViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreatePostPresenter(CreatePostViewModel createPostViewModel, ViewManagerModel viewManagerModel) {
        this.createPostViewModel = createPostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Login Use Case for students.
     * @param data the output data
     */
    public void prepareSuccessView(ClubCreatePostOutputData data) {
        final CreatePostState state = createPostViewModel.getState();
        state.setTimeOfPosting(data.getTimeOfPosting());
        state.setDateOfPosting(data.getDateOfPosting());
        state.setContent(data.getContents());
        state.setTitle(data.getTitle());
        createPostViewModel.setState(state);
        createPostViewModel.firePropertyChanged("create post");

        viewManagerModel.setState(createPostViewModel.getViewName());
        viewManagerModel.firePropertyChanged("create post");
    }

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage) {
        final CreatePostState state = createPostViewModel.getState();
        state.setCreatePostError(errorMessage);
        createPostViewModel.setState(state);
        createPostViewModel.firePropertyChanged();
    }
}
