package use_case.student_homepage.like;

import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import interface_adapter.student_logged_in.student_home.like.StudentLikePresenter;

/**
 * Interactor for the like usecase.
 */
public class StudentLikeInteractor implements StudentLikeInputBoundary {
    private final StudentLikeStudentDataAccessInterface studentDataAccess;
    private final StudentLikeClubDataAccessInterface clubDataAccess;
    private final StudentLikeOutputBoundary studentLikePresenter;

    public StudentLikeInteractor(StudentLikeStudentDataAccessInterface studentDataAccess,
                                 StudentLikeClubDataAccessInterface clubDataAccess,
                                 StudentLikeOutputBoundary studentLikePresenter) {
        this.studentDataAccess = studentDataAccess;
        this.clubDataAccess = clubDataAccess;
        this.studentLikePresenter = studentLikePresenter;
    }

    @Override
    public void execute(StudentLikeInputData studentLikeInputData) {
        final String studentEmail = studentLikeInputData.getStudentEmail();
        final String clubEmail = studentLikeInputData.getClubEmail();
        final Student currStudent = studentDataAccess.getStudent(studentEmail);
        final Club currClub = clubDataAccess.getClub(clubEmail);
        final Map<String, Object> postData = studentLikeInputData.getPost();
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

        final StudentLikeOutputData studentLikeOutputData = new StudentLikeOutputData(postData, currClub.getUsername());
        studentLikePresenter.prepareSuccessView(studentLikeOutputData);
    }
}
