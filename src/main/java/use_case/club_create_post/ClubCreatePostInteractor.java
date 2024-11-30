package use_case.club_create_post;

import entity.post.Announcement;
import entity.post.AnnouncementFactory;
import entity.user.Club;

/**
 * Club post creation use case interactor. Contains all use cases to do with creating a club post.
 */
public class ClubCreatePostInteractor implements ClubCreatePostInputBoundary {
    private final ClubCreatePostUserDataAccessInterface createPostDataAccessObject;
    private final ClubCreatePostOutputBoundary createPostPresenter;

    public ClubCreatePostInteractor(ClubCreatePostUserDataAccessInterface createPostDataAccessObject,
                                    ClubCreatePostOutputBoundary createPostPresenter) {
        this.createPostDataAccessObject = createPostDataAccessObject;
        this.createPostPresenter = createPostPresenter;
    }

    @Override
    public void execute(ClubCreatePostInputData clubCreatePostInputData) {
        final int maxDescriptionLength = 380;
        final int maxTitleLength = 130;
        final String title = clubCreatePostInputData.getTitle();
        final String content = clubCreatePostInputData.getContent();
        if (title.isEmpty() && content.isEmpty()) {
            createPostPresenter.prepareFailView("Title field and Content field are empty.");
        }
        else if (title.isEmpty()) {
            createPostPresenter.prepareFailView("Title field is empty.");
        }
        else if (content.isEmpty()) {
            createPostPresenter.prepareFailView("Content field is empty.");
        }
        else if (content.length() > maxDescriptionLength) {
            createPostPresenter.prepareFailView("Content field is longer than "
                    + maxDescriptionLength + " characters.");
        }
        else if (title.length() > maxTitleLength) {
            createPostPresenter.prepareFailView("Title field is longer than "
                    + maxTitleLength + " characters.");
        }
        else {
            final AnnouncementFactory announcementFactory = new AnnouncementFactory();
            final Announcement post = announcementFactory.create(title, content);
            final ClubCreatePostOutputData outputData = new ClubCreatePostOutputData(
                    post.getTitle(), post.getContent(), post.timeOfPosting(), post.dateOfPosting(), false);
            // Get club by email, since the user exists and logged in, we know the email exists for save
            final Club club = createPostDataAccessObject.getClub(clubCreatePostInputData.getEmail());

            // Save post to club entity
            club.addClubPost(post);

            // Save post to database
            createPostDataAccessObject.savePost(post, club);

            // Prepare the success view after saving the post to database
            createPostPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToCreatePostView() {
        createPostPresenter.switchToCreatePostView();
    }

    @Override
    public void switchToClubLoggedInView() {
        createPostPresenter.switchToClubLoggedInView();
    }

}
