package interface_adapter.club_create_post;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import use_case.club_create_post.ClubCreatePostOutputBoundary;
import use_case.club_create_post.ClubCreatePostOutputData;

/**
 * Create post use case presenter.
 */
public class ClubCreatePostPresenter implements ClubCreatePostOutputBoundary {
    private final ClubCreatePostViewModel clubCreatePostViewModel;
    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubCreatePostPresenter(ClubCreatePostViewModel clubCreatePostViewModel,
                                   ClubLoggedInViewModel clubLoggedInViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.clubCreatePostViewModel = clubCreatePostViewModel;
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Login Use Case for students.
     * @param data the output data
     */
    public void prepareSuccessView(ClubCreatePostOutputData data) {
        final ClubCreatePostState state = clubCreatePostViewModel.getState();
        state.setTimeOfPosting(data.getTimeOfPosting());
        state.setDateOfPosting(data.getDateOfPosting());
        state.setContent(data.getContents());
        state.setTitle(data.getTitle());
        clubCreatePostViewModel.setState(state);
      
        clubCreatePostViewModel.firePropertyChanged("create post");
        clubLoggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage) {
        final ClubCreatePostState state = clubCreatePostViewModel.getState();
        state.setCreatePostError(errorMessage);
        clubCreatePostViewModel.firePropertyChanged("create post error");
    }

    @Override
    public void switchToCreatePostView() {
        // resets the state before switching
        final ClubCreatePostState state = clubCreatePostViewModel.getState();
        state.setEmail(clubLoggedInViewModel.getState().getEmail());
        state.setContent(null);
        state.setTitle(null);
        state.setCreatePostError(null);
        clubCreatePostViewModel.setState(state);
        clubCreatePostViewModel.firePropertyChanged();

        // fires a property change to the view manager model to switch to the create post view
        viewManagerModel.setState(clubCreatePostViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToClubLoggedInView() {
        // fires a property change to the view manager model to switch to the club logged in view
        viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
