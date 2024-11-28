package use_case.student_homepage.like;

import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import interface_adapter.student_home.like.StudentLikePresenter;

/**
 * Interactor for the like usecase.
 */
public class LikeInteractor implements LikeInputBoundary {
    private final LikeStudentDataAccessInterface studentDataAccess;
    private final LikeClubDataAccessInterface clubDataAccess;
    private final StudentLikePresenter studentLikePresenter;

    public LikeInteractor(LikeStudentDataAccessInterface studentDataAccess,
                          LikeClubDataAccessInterface clubDataAccess, StudentLikePresenter studentLikePresenter) {
        this.studentDataAccess = studentDataAccess;
        this.clubDataAccess = clubDataAccess;
        this.studentLikePresenter = studentLikePresenter;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        final String studentEmail = likeInputData.getStudentEmail();
        final String clubEmail = likeInputData.getClubEmail();
        final Student currStudent = studentDataAccess.getStudent(studentEmail);
        final Club currClub = clubDataAccess.getClub(clubEmail);
        final Map<String, Object> postData = likeInputData.getPost();
        final DataStoreArrays<Post> clubPosts = (DataStoreArrays<Post>) currClub.getClubPosts();
        Post postObject = null;
        // Find the corresponding Post object for the data, using the date at which it was posted.
        for (final Post post: clubPosts) {
            if (post.dateOfPosting() == postData.get("time") && post.dateOfPosting() == postData.get("date")) {
                postObject = post;
                break;
            }
        }
        if (postObject.getLikes().contains(currStudent)) {
            postObject.removeLike(currClub);
            clubDataAccess.savePost(postObject, currClub);
        }
        else {
            postObject.addLike(currStudent);
            clubDataAccess.savePost(postObject, currClub);
        }
        postData.put("Liked", !(Boolean) postData.get("Liked"));

        final LikeOutputData likeOutputData = new LikeOutputData(postData, currClub.getUsername());
        studentLikePresenter.prepareSuccessView(likeOutputData);
    }
}
