package use_case.club_update_desc;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class ClubUpdateDescInteractorTest {

    @Test
    void successTest() {
        // Uses an in memory database to test the use case with a club
        ClubUpdateDescDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.saveClub(new Club("Roy", "ok@k.com", "password", null, null));

        ClubUpdateDescInputData inputData = new ClubUpdateDescInputData("ok@k.com", "test");

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubUpdateDescOutputBoundary successPresenter = new ClubUpdateDescOutputBoundary() {
            @Override
            public void prepareSuccessMessage(ClubUpdateDescOutputData outputData) {
                assertEquals("test", outputData.getNewDesc());
                assertEquals("Success in changing description.", outputData.getMessage());
                assertEquals("test", userRepository.getClub("ok@k.com").getClubDescription());
            }

            @Override
            public void prepareFailMessage(ClubUpdateDescOutputData outputData) {
                fail("Unexpected fail call");
            }
        };

        ClubUpdateDescInputBoundary interactor = new ClubUpdateDescInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        // Uses an in memory database to test the use case
        ClubUpdateDescDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        ClubUpdateDescInputData inputData = new ClubUpdateDescInputData("ok@k.com", "test");

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubUpdateDescOutputBoundary successPresenter = new ClubUpdateDescOutputBoundary() {
            @Override
            public void prepareSuccessMessage(ClubUpdateDescOutputData outputData) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailMessage(ClubUpdateDescOutputData outputData) {
                assertNull(outputData.getNewDesc());
                assertEquals("Failure in changing description: Club not Found", outputData.getMessage());
            }
        };

        ClubUpdateDescInputBoundary interactor = new ClubUpdateDescInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
