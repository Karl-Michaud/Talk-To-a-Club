package interface_adapter.club_logged_in.club_get_posts;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import use_case.club_get_posts.ClubGetPostsOutputBoundary;
import use_case.club_get_posts.ClubGetPostsOutputData;

/**
 * The presenter for the Club Get Posts Use Case.
 */
public class ClubGetPostsPresenter implements ClubGetPostsOutputBoundary {

    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubGetPostsPresenter(ClubLoggedInViewModel clubLoggedInViewModel, ViewManagerModel viewManagerModel) {
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareDisplayPosts(ClubGetPostsOutputData outputData) {
        // Get the state of the current ClubLoggedInViewModel and set the message and club post info to the new ones
        final ClubLoggedInState clubLoggedInState = clubLoggedInViewModel.getState();
        clubLoggedInState.setMessage(outputData.getMessage());
        clubLoggedInState.setPostTitles(outputData.getPostTitles());
        clubLoggedInState.setPostBodies(outputData.getPostBodies());

        // Saves the state and tells the view to reload the club's displayed posts
        clubLoggedInViewModel.setState(clubLoggedInState);
        clubLoggedInViewModel.firePropertyChanged("reload posts");
    }

    @Override
    public void prepareFailMessage(ClubGetPostsOutputData outputData) {
        // Get the state of the current ClubLoggedInViewModel and set the message to the new one
        final ClubLoggedInState clubLoggedInState = clubLoggedInViewModel.getState();
        clubLoggedInState.setMessage(outputData.getMessage());

        // Saves the state and tells the view to show the message
        clubLoggedInViewModel.setState(clubLoggedInState);
        clubLoggedInViewModel.firePropertyChanged("show message");
    }
}
