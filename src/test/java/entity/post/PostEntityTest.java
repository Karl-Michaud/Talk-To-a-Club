package entity.post;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;
import entity.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PostEntityTest {

    @Test
    void testPostCreation() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Test Title", "This is the content of the test post.");

        assertEquals("Test Title", post.getTitle(), "Post title should match the input.");
        assertEquals("This is the content of the test post.", post.getContent(), "Post content should match the input.");
        assertNotNull(post.dateOfPosting(), "Post date should not be null.");
        assertNotNull(post.timeOfPosting(), "Post time should not be null.");
    }

    @Test
    void testLikePost() {
        Post post = new Announcement("Sample Title", "Sample Content");

        DataStore<Club> joined = new DataStoreArrays<>();
        User user = new Student("John Doe", "johndoe@example.com", "12345678hello", joined);

        post.addLike(user);
        assertEquals(1, post.numberOfLikes());
        assertTrue(post.getLikes().contains(user));

        post.removeLike(user);
        assertEquals(0, post.numberOfLikes());
        assertFalse(post.getLikes().contains(user));
    }

    @Test
    void testDislikePost() {
        Post post = new Announcement("Sample Title", "Sample Content");
        DataStore<Club> joined = new DataStoreArrays<>();
        User user = new Student("John Doe", "johndoe@example.com", "12345678hello", joined);

        post.addDislike(user);
        assertEquals(1, post.numberOfDislikes());
        assertTrue(post.getDislikes().contains(user));

        post.removeDislike(user);
        assertEquals(0, post.numberOfDislikes());
        assertFalse(post.getDislikes().contains(user));
    }

    @Test
    void testMultipleLikesAndDislikes() {
        Post post = new Announcement("Title", "Content");

        DataStore<Club> joined1 = new DataStoreArrays<>();
        User user1 = new Student("Alice", "alice@example.com", "12345678hello", joined1);

        DataStore<Club> joined2 = new DataStoreArrays<>();
        User user2 = new Student("Bob", "bob@example.com", "12345678hello", joined2);

        post.addLike(user1);
        post.addLike(user2);
        assertEquals(2, post.numberOfLikes());

        post.addDislike(user1);
        assertEquals(1, post.numberOfDislikes());

        post.removeLike(user2);
        assertEquals(1, post.numberOfLikes());
    }

    @Test
    void testPostMetadata() {
        Post post = new Announcement("Metadata Title", "Metadata Content");

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        assertEquals(today, post.dateOfPosting());
        assertNotNull(post.timeOfPosting());
    }

    @Test
    void testPostFactory() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Factory Title", "Factory Content");

        assertEquals("Factory Title", post.getTitle());
        assertEquals("Factory Content", post.getContent());
        assertInstanceOf(Announcement.class, post, "Factory should create an instance of Announcement.");
    }
}