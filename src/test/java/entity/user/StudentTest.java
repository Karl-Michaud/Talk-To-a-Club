package entity.user;

import entity.data_structure.DataStore;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Test for the student entity.
 */
public class StudentTest {
    static String studentUsername = "student";
    static String studentEmail = "student@email.com";
    static String studentPassword = "student12345";

    @Test
    public void testStudentCreation() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the student
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Verify that the instance variables hold true
        assertEquals(studentUsername, testStudent.getUsername());
        assertEquals(studentEmail, testStudent.getEmail());
        assertEquals(studentPassword, testStudent.getPassword());
    }

    @Test
    public void testStudentJoinClub() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the student
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Create 10 clubs for the student to join
        ClubFactory clubFactory = new ClubUserFactory();
        Club clubToRemove = null;
        for (int i = 0; i < 10; i++) {
            Club club = clubFactory.create("club" + i,
                    "club" + i + "@gmail.com", "password" + i);
            testStudent.joinClub(club);
            clubToRemove = club;
        }

        // Check that there are 10 clubs joined
        int sizeClubsJoined = testStudent.getJoinedClubs().size();
        assertEquals(10, sizeClubsJoined);

        // Leave club
        testStudent.leaveClub(clubToRemove);

        // Check new size
        int sizeClubsLeave = testStudent.getJoinedClubs().size();
        assertEquals(9, sizeClubsLeave);

        // Check the club is no longer in clubs joined
        DataStore<Club> joinedClubs = testStudent.getJoinedClubs();
        int index = 0;
        while (index < joinedClubs.size()) {
            // Check that the left club is no longer in the joined clubs by checking the unique username and password
            Club clubAtIndex = joinedClubs.getByIndex(index);
            assertNotEquals(clubToRemove.getEmail(), clubAtIndex.getEmail());
            assertNotEquals(clubToRemove.getUsername(), clubAtIndex.getUsername());
            index++;
        }
    }
}
