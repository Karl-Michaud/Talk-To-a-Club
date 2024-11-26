package interface_adapter.student_home.show_posts;

import use_case.student_homepage.show_posts.ShowPostsOutputBoundary;
import use_case.student_homepage.show_posts.ShowPostsOutputData;

/**
 * The presenter that passes the posts on to the ViewModel for the StudentHomeView.
 */
public class StudentShowPostsPresenter implements ShowPostsOutputBoundary {

    private final ShowPostsViewModel viewModel;

    public StudentShowPostsPresenter(ShowPostsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void preparePostContent(ShowPostsOutputData showPostsOutputData) {
        final ShowPostsState state = viewModel.getState();
        state.setPosts(showPostsOutputData.getPosts());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ShowPostsState state = viewModel.getState();
        state.setError(errorMessage);
        viewModel.firePropertyChanged();
    }
}
