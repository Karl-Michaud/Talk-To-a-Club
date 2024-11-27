package use_case.student_homepage.show_posts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for show posts use case.
 */
public class ShowPostsInteractor implements ShowPostsInputBoundary {
    private final ShowPostsOutputBoundary showPostsPresenter;
    private final ShowPostsAccessInterface showPostsAccessInterface;

    public ShowPostsInteractor(ShowPostsAccessInterface showPostsAccessInterface,
                               ShowPostsOutputBoundary showPostsPresenter) {
        this.showPostsPresenter = showPostsPresenter;
        this.showPostsAccessInterface = showPostsAccessInterface;
    }

    @Override
    public void execute(ShowPostsInputData inputData) {
        final String currUser = inputData.getUser();
        final Student currStudent = showPostsAccessInterface.getStudent(currUser);
        final DataStoreArrays<Club> joinedClubs = (DataStoreArrays<Club>) currStudent.getJoinedClubs();
        final Map<String, List<Map<String, Object>>> posts = new HashMap<>();
        for (Club club : joinedClubs) {
            final List<Map<String, Object>> postsList = new ArrayList<>();
            for (Post post: (DataStoreArrays<Post>) club.getClubPosts()) {
                final Map<String, Object> postAttributes = new HashMap<>();
                postAttributes.put("Title", post.getTitle());
                postAttributes.put("Content", post.getContent());
                postAttributes.put("Likes", post.numberOfLikes());
                postAttributes.put("Dislikes", post.numberOfDislikes());
                postAttributes.put("TimeOfPosting", post.timeOfPosting());
                postAttributes.put("ClubEmail", club.getEmail());
                postAttributes.put("Liked", post.getLikes().contains(currStudent));
                postsList.add(postAttributes);
            }
            posts.put(club.getUsername(), postsList);
        }
        final ShowPostsOutputData showPostsOutputData = new ShowPostsOutputData(posts, currStudent.getEmail());
        showPostsPresenter.preparePostContent(showPostsOutputData);
    }
}
