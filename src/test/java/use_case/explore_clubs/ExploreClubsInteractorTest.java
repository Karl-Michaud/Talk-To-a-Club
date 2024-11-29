package use_case.explore_clubs;

import data_access.InMemoryUserDataAccessObject;
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExploreClubsInteractorTest {
    @Test
    void successTest() {
        // In-memory data setup
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        // Create clubs
        DataStore<Student> students1 = new DataStoreArrays<Student>();
        DataStore<Student> students2 = new DataStoreArrays<Student>();
        Club club1 = new Club("Club1", "club1@example.com", "password1", students1, null);
        Club club2 = new Club("Club2", "club2@example.com", "password2", students2, null);
        club1.setClubDescription("Description1");
        club2.setClubDescription("Description2");

        // Create a student and associate with one club
        DataStore<Club> joinedClubs = new DataStoreArrays<>();
        joinedClubs.add(club1);
        Student student = new Student("Student1", "student1@example.com", "password", joinedClubs);
        club1.addClubMember(student);

        userRepository.saveStudent(student);
        userRepository.saveClub(club1);
        userRepository.saveClub(club2);

        // Input data
        ExploreClubsInputData inputData = new ExploreClubsInputData("student1@example.com");

        // Output boundary to verify success
        ExploreClubsOutputBoundary successPresenter = new ExploreClubsOutputBoundary() {
            @Override
            public void prepareSuccessView(ExploreClubsOutputData data) {
                assertEquals("student1@example.com", data.getStudentEmail());
                assertFalse(data.isUseCaseFailed());
                assertEquals(1, data.getNotJoinedClubs().size());
                ArrayList<String> joinTest = new ArrayList<>();
                joinTest.add("club1@example.com");
                assertEquals(joinTest, data.getJoinedClubsEmails());

                Map<String, String> club = data.getNotJoinedClubs().get(0);
                assertEquals("Club2", club.get("username"));
                assertEquals("club2@example.com", club.get("email"));
                assertEquals("Description2", club.get("description"));
                assertEquals("0", club.get("numMembers"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected fail call");
            }

            @Override
            public void switchToClubView(Map<String, String> club) {
                // Not used in this test
            }

            @Override
            public void switchToHomeView() {
                // Not used in this test
            }
        };

        // Interactor execution
        ExploreClubsInteractor interactor = new ExploreClubsInteractor(userRepository,
                successPresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        // In-memory data setup
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Input data for a non-existent student
        ExploreClubsInputData inputData = new ExploreClubsInputData("nonexistent@example.com");

        // Output boundary to verify failure
        ExploreClubsOutputBoundary failPresenter = new ExploreClubsOutputBoundary() {
            @Override
            public void prepareSuccessView(ExploreClubsOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("nonexistent@example.com: Account does not exist.", errorMessage);
            }

            @Override
            public void switchToClubView(Map<String, String> club) {
                // Not used in this test
            }

            @Override
            public void switchToHomeView() {
                // Not used in this test
            }
        };

        // Interactor execution
        ExploreClubsInteractor interactor = new ExploreClubsInteractor(userRepository,
                failPresenter, userRepository);
        interactor.execute(inputData);
    }
}