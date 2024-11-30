package use_case.student_homepage.show_clubs;

import data_access.InMemoryUserDataStudentAccessObject;

import entity.user.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class StudentShowClubsInteractorTest {

    @Test
    void successTest() {
        // Uses an in memory database to test the use case with a club
        InMemoryUserDataStudentAccessObject dao = new InMemoryUserDataStudentAccessObject();
        // Create 3 example clubs and a student.
        ClubFactory clubFactory = new ClubUserFactory();
        Club climbingClub = clubFactory.create("Climbing club", "utcc@utoronto.ca", "12345678");
        Club outdoorsclub = clubFactory.create("Outdoors club", "utoc@utoronto.ca", "secure");
        Club rlClub = clubFactory.create("Rocket League club", "rlatuoft@utoronto.ca", "more_secure");

        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Fred", "frederik.brecht@mail.utoronto.ca", "password");

        // set up a sample List of Maps to compare to the output test data
        ArrayList<HashMap<String, String>> sampleList  = new ArrayList<>();
        // HashMap for climbing club.
        HashMap<String, String> climbingClubHash = new HashMap<>();
        climbingClubHash.put("username", climbingClub.getUsername());
        climbingClubHash.put("email", climbingClub.getEmail());
        climbingClubHash.put("description", climbingClub.getClubDescription());
        // HashMap for outdoors club.
        HashMap<String, String> outdoorsclubHash = new HashMap<>();
        outdoorsclubHash.put("username", outdoorsclub.getUsername());
        outdoorsclubHash.put("email", outdoorsclub.getEmail());
        outdoorsclubHash.put("description", outdoorsclub.getClubDescription());
        // HashMap for rocket league club.
        HashMap<String, String> rlClubHash = new HashMap<>();
        rlClubHash.put("username", rlClub.getUsername());
        rlClubHash.put("email", rlClub.getEmail());
        rlClubHash.put("description", rlClub.getClubDescription());

        sampleList.add(outdoorsclubHash);
        sampleList.add(climbingClubHash);
        sampleList.add(rlClubHash);

        // Add the sample student as a member to the clubs.
        climbingClub.addClubMember(student);
        outdoorsclub.addClubMember(student);
        rlClub.addClubMember(student);
        student.joinClub(outdoorsclub);
        student.joinClub(climbingClub);
        student.joinClub(rlClub);

        // Save the sample student and clubs to the in memory database.
        dao.saveStudent(student);
        dao.saveClub(outdoorsclub);
        dao.saveClub(climbingClub);
        dao.saveClub(rlClub);

        // prepare the inputData.
        StudentShowClubsInputData inputData = new StudentShowClubsInputData(student.getEmail());

        // Create a successPresenter that tests whether the test case is as we expect.
        StudentShowClubsOutputBoundary successPresenter = new StudentShowClubsOutputBoundary() {
            @Override
            public void prepareClubsContent(StudentShowClubsOutputData studentShowClubsOutputData) {
                assertEquals("frederik.brecht@mail.utoronto.ca", studentShowClubsOutputData.getCurrStudentEmail());
                assertTrue(sampleList.equals(studentShowClubsOutputData.getClubs()));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected fail call.");
            }
        };
        StudentShowClubsInputBoundary interactor = new StudentShowClubsInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        // Uses an in memory database to test the use case with a club.
        StudentShowClubsAccessInterface dao = new InMemoryUserDataStudentAccessObject();

        StudentShowClubsInputData inputData = new StudentShowClubsInputData("frederik.brecht@mail.utoronto.ca");

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentShowClubsOutputBoundary successPresenter = new StudentShowClubsOutputBoundary() {
            @Override
            public void prepareClubsContent(StudentShowClubsOutputData studentShowClubsOutputData) {
                assertEquals("frederik.brecht@mail.utoronto.ca", studentShowClubsOutputData.getCurrStudentEmail());
                assertNull(studentShowClubsOutputData.getClubs());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The account does not exist.", errorMessage);
            }
        };

        StudentShowClubsInputBoundary interactor = new StudentShowClubsInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }
}
