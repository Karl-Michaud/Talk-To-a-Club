package use_case.club_create_post;

import entity.post.Announcement;
import entity.user.Club;

/**
 * Club post creation use case interactor.
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
        final String title = clubCreatePostInputData.getTitle();
        final String content = clubCreatePostInputData.getContent();

        if (title.isEmpty()) {
            createPostPresenter.prepareFailView("Tittle field is empty.");
        }
        else if (content.isEmpty()) {
            createPostPresenter.prepareFailView("Content field is empty.");
        }
        else {
            final Announcement post = new Announcement(title, content);
            final ClubCreatePostOutputData outputData = new ClubCreatePostOutputData(
                    post.getTitle(), post.getContent(), post.timeOfPosting(), post.dateOfPosting(), false);
            // Get club for save
            final Club club = createPostDataAccessObject.getClub(clubCreatePostInputData.getEmail());

            // Save post to database
            createPostDataAccessObject.savePost(post, club);

            // Prepare the success view after saving the post to database
            createPostPresenter.prepareSuccessView(outputData);
        }
    }

}
