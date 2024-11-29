package entity.user;

import entity.data_structure.DataStore;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.post.PostFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Club entity.
 */
public class ClubTests {
    static String clubName = "Test Club Name";
    static String clubEmail = "club@email.com";
    static String clubPassword = "TestClubPassword123";

    @Test
    public void testClubCreation() {
        // Initialize the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Create a Club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Verify that the instance variables are correct
        assertEquals(clubName, testClub.getUsername());
        assertEquals(clubEmail, testClub.getEmail());
        assertEquals(clubPassword, testClub.getPassword());
        assertEquals("", testClub.getClubDescription());

        // Verify that the setter method work
        testClub.setClubDescription("Test Club Description");
        assertEquals("Test Club Description", testClub.getClubDescription());
    }

    @Test
    public void testClubMemberManipulation() {
        // Initialize the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Create a Club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Test the members
        StudentFactory studentFactory = new StudentUserFactory();
        Student studentToRemove = null;
        for (int i = 0; i < 10; i++) {
            Student student = studentFactory.create("member" + i, "member" + i +"@gmail.com",
                    "password" + i);
            testClub.addClubMember(student);
            studentToRemove = student;
        }
        // Check that there are 10 members
        int sizeMembers = testClub.getClubMembers().size();
        assertEquals(10, sizeMembers);

        // Remove Student
        testClub.removeClubMember(studentToRemove);

        // Check new size
        int sizeMembersAfterRemove = testClub.getClubMembers().size();
        assertEquals(9, sizeMembersAfterRemove);

        // Check if member is out
        DataStore<Student> members = testClub.getClubMembers();
        int index = 0;
        while (index < members.size()) {
            Student studentAtIndex = members.getByIndex(index);
            // Check for name and password since they are in theory unique.
            assertNotEquals(studentToRemove.getEmail(), studentAtIndex.getEmail());
            assertNotEquals(studentToRemove.getUsername(), studentAtIndex.getUsername());
            index++;
        }
    }

    @Test
    public void testClubPostManipulation() {
        // Initialize the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Create a Club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Create 10 club posts
        PostFactory postFactory = new AnnouncementFactory();
        Post postToRemove = null;
        for (int i = 0; i < 10; i++) {
            Post post = postFactory.create("post" + i, "description" + i);
            testClub.addClubPost(post);
            postToRemove = post;
        }

        // Check that there are 10 posts
        int sizePosts = testClub.getClubPosts().size();
        assertEquals(10, sizePosts);

        // Remove post
        testClub.removeClubPost(postToRemove);

        // Check new size
        int sizePostsAfterRemove = testClub.getClubPosts().size();
        assertEquals(9, sizePostsAfterRemove);

        // Check if post is out
        DataStore<Post> posts = testClub.getClubPosts();
        int index = 0;
        while (index < posts.size()) {
            Post postAtIndex = posts.getByIndex(index);

            // Verify that no posts has the same exact title and description
            assertTrue(!postToRemove.getTitle().equals(postAtIndex.getTitle()) &&
                    !postToRemove.getContent().equals(postAtIndex.getContent()));
            index++;
        }
    }
}
