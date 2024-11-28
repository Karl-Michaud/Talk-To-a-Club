package use_case.student_homepage.show_posts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.User;

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
        final String currUserEmail = inputData.getUserEmail();
        if (!showPostsAccessInterface.existsByEmailStudent(currUserEmail)) {
            showPostsPresenter.prepareFailView("The accounts does not exist.");
        }
        else {
            final DataStoreArrays<Club> clubs = (DataStoreArrays<Club>) showPostsAccessInterface.getStudent(
                    currUserEmail).getJoinedClubs();
            final User currentUser = showPostsAccessInterface.getStudent(currUserEmail);
            final Map<String, List<Map<String, Object>>> postData = new HashMap<>();
            for (final Club club : clubs) {
                final ArrayList<Map<String, Object>> clubPostData = new ArrayList<>();
                for (final Post post: (DataStoreArrays<Post>) club.getClubPosts()) {
                    final Map<String, Object> singlePostData = new HashMap<>();
                    singlePostData.put("title", post.getTitle());
                    singlePostData.put("content", post.getContent());
                    singlePostData.put("likes", post.numberOfLikes());
                    singlePostData.put("dislikes", post.numberOfDislikes());
                    singlePostData.put("liked", post.getLikes().contains(currentUser));
                    singlePostData.put("disliked", post.getDislikes().contains(currentUser));
                    singlePostData.put("club-email", club.getEmail());
                    singlePostData.put("time", post.dateOfPosting());
                    singlePostData.put("date", post.dateOfPosting());
                    clubPostData.add(singlePostData);
                }
                postData.put(club.getUsername(), clubPostData);
            }
            final ShowPostsOutputData outputData = new ShowPostsOutputData(postData, currUserEmail);
            showPostsPresenter.preparePostContent(outputData);
        }
    }
}
