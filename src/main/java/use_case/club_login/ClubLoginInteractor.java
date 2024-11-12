package use_case.club_login;

import entity.user.Club;

/**
 * The interactor for the login use case for clubs.
 */
public class ClubLoginInteractor implements ClubLoginInputBoundary {
    private final ClubLoginDataAccessInterface clubDataAccessObject;
    private final ClubLoginOutputBoundary clubLoginPresenter;

    public ClubLoginInteractor(ClubLoginDataAccessInterface clubDataAccessObject,
                               ClubLoginOutputBoundary clubLoginPresenter) {
        this.clubDataAccessObject = clubDataAccessObject;
        this.clubLoginPresenter = clubLoginPresenter;
    }

    /**
     * Executes the login use case for the student user.
     * @param clubLoginInputData the input data
     */
    public void execute(ClubLoginInputData clubLoginInputData) {
        final String email = clubLoginInputData.getEmail();
        final String password = clubLoginInputData.getPassword();
        if (!clubDataAccessObject.existsByEmail(email)) {
            clubLoginPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            final String pwd = clubDataAccessObject.getClub(email).getPassword();
            if (!pwd.equals(password)) {
                clubLoginPresenter.prepareFailView("Incorrect password for \"" + email + "\".");
            }
            else {
                final Club club = clubDataAccessObject.getClub(email);
                final ClubLoginOutputData loginOutputData = new ClubLoginOutputData(club.getEmail(),
                        club.getEmail(), club.getClubPosts(), false);
                clubLoginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

}
