package use_case.student_homepage.show_posts;

import java.util.ArrayList;

import entity.post.Post;
import entity.user.Club;

/**
 * Data Access interface for the show clubs use case. This interface is dedicated to only club related DAO activities.
 */
public interface StudentShowPostsClubAccessInterface {
    /**
     * Gets the posts of a given club.
     * @param club the club
     * @return An arraylist of the posts of a given club.
     */
    ArrayList<Post> getPosts(Club club);

}
