package use_case.student_homepage.show_posts;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Interactor for show posts use case.
 */
public class ShowPostsInteractor implements ShowPostsInputBoundary {
    private final ShowPostsOutputBoundary showClubsPresenter;
    private final ShowPostsAccessInterface showPostsAccessInterface;

    public ShowPostsInteractor(ShowPostsAccessInterface showPostsAccessInterface,
                               ShowPostsOutputBoundary showClubsPresenter) {
        this.showClubsPresenter = showClubsPresenter;
        this.showPostsAccessInterface = showPostsAccessInterface;
    }

    @Override
    public void execute(ShowPostsInputData inputData) {
        final String currUser = inputData.getUser();
        final Student currStudent = showPostsAccessInterface.getStudent(currUser);
        final DataStoreArrays<Club> joinedClubs = (DataStoreArrays<Club>) currStudent.getJoinedClubs();
        final Map<String, DataStoreArrays<Post>> posts = new HashMap<String, DataStoreArrays<Post>>();
        for (Club club : joinedClubs) {
            posts.put(club.getUsername(), (DataStoreArrays<Post>) club.getClubPosts());
        }
        final ShowPostsOutputData showPostsOutputData = new ShowPostsOutputData(posts);
        showClubsPresenter.preparePostContent(showPostsOutputData);
    }
}
