package use_case.student_homepage.dislike;

import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import interface_adapter.student_home.dislike.StudentDislikePresenter;

/**
 * Interactor for the dislike usecase.
 */
public class StudentDislikeInteractor implements StudentDislikeInputBoundary {
    private final StudentDislikeClubDataAccessInterface clubDataAccess;
    private final StudentDislikeStudentDataAccessInterface studentDataAccess;
    private final StudentDislikePresenter studentDislikePresenter;

    public StudentDislikeInteractor(StudentDislikeClubDataAccessInterface clubDataAccess,
                                    StudentDislikeStudentDataAccessInterface studentDataAccess,
                                    StudentDislikePresenter presenter) {
        this.clubDataAccess = clubDataAccess;
        this.studentDataAccess = studentDataAccess;
        this.studentDislikePresenter = presenter;
    }

    @Override
    public void execute(StudentDislikeInputData studentDislikeInputData) {
        final String studentEmail = studentDislikeInputData.getStudentEmail();
        final String clubEmail = studentDislikeInputData.getClubEmail();
        final Student currStudent = studentDataAccess.getStudent(studentEmail);
        final Club currClub = clubDataAccess.getClub(clubEmail);
        final Map<String, Object> postData = studentDislikeInputData.getPost();
        final DataStoreArrays<Post> clubPosts = (DataStoreArrays<Post>) currClub.getClubPosts();
        Post postObject = null;
        // Find the corresponding Post object for the data, using the date at which it was posted.
        for (final Post post: clubPosts) {
            if (post.dateOfPosting() == postData.get("time") && post.dateOfPosting() == postData.get("date")) {
                postObject = post;
                break;
            }
        }
        if (postObject.getDislikes().contains(currStudent)) {
            postObject.removeDislike(currClub);
            clubDataAccess.savePost(postObject, currClub);
        }
        else {
            postObject.addDislike(currStudent);
            clubDataAccess.savePost(postObject, currClub);
        }
        postData.put("Disliked", !(Boolean) postData.get("Disliked"));

        final StudentDislikeOutputData studentDislikeOutputData = new StudentDislikeOutputData(postData, currClub.getUsername());
        studentDislikePresenter.prepareSuccessView(studentDislikeOutputData);
    }
}
