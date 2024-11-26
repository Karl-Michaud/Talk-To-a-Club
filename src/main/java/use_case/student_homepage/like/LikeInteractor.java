package use_case.student_homepage.like;

/**
 * Interactor for the like usecase.
 */
public class LikeInteractor implements LikeInputBoundary {
    private final LikeAccessInterface likeAccessInterface;

    public LikeInteractor(LikeAccessInterface likeAccessInterface) {
        this.likeAccessInterface = likeAccessInterface;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        final String studentEmail = likeInputData.getStudentEmail();
        final String clubName = likeInputData.getClubName();
    }
}
