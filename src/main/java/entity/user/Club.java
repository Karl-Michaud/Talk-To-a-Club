package entity.user;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Post;

/**
 * This is the Club class which implements the User interface. A Club is a typer of User.
 */
public class Club implements User {
    // Club's personal information
    private final String username;
    private final String email;
    private final String password;
    private String clubDescription = "";
    // Club's members and Posts information
    private final DataStore<String> clubMembersEmails;
    private final DataStore<String> clubMembersNames;

    private final DataStore<String> clubPostsTitle;
    private final DataStore<String> clubPostsDescription;

    public Club() {
        // Initialize club personal information
        this.username = "";
        this.email = "";
        this.password = "";

        // Initialize club members and posts information
        this.clubMembersEmails = new DataStoreArrays<>();
        this.clubMembersNames = new DataStoreArrays<>();
        this.clubPostsTitle = new DataStoreArrays<>();
        this.clubPostsDescription = new DataStoreArrays<>();
    }

    public Club(String username, String email, String password, DataStore<String> clubMembersEmails,
                DataStore<String> clubMembersNames, DataStore<String> clubPostsTitle,
                DataStore<String> clubPostsDescription) {
        // Initialize club personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Initialize club members and posts information
        this.clubMembersEmails = clubMembersEmails;
        this.clubMembersNames = clubMembersNames;
        this.clubPostsTitle = clubPostsTitle;
        this.clubPostsDescription = clubPostsDescription;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public DataStore<String> getClubMembersEmails() {
        return this.clubMembersEmails;
    }

    public DataStore<String> getClubMembersNames() {
        return this.clubMembersNames;
    }

    public DataStore<String> getClubPostsTitle() {
        return this.clubPostsTitle;
    }

    public DataStore<String> getClubPostsDescription() {
        return this.clubPostsDescription;
    }

    /**
     * Adds a club member to the Club.
     * @param user particular user joining the club.
     */
    public void addClubMember(Student user) {
        clubMembersEmails.add(user.getEmail());
        clubMembersNames.add(user.getUsername());
    }

    /**
     * Adds a club post to the history of the club's posts.
     * @param post particular post to add.
     */
    public void addClubPost(Post post) {
        clubPostsTitle.add(post.getTitle());
        clubPostsDescription.add(post.getContent());
    }

    /**
     * Removes a user from the club.
     * @param user particular user leaving the club.
     */
    public void removeClubMember(Student user) {
        clubPostsDescription.remove(user.getUsername());
        clubMembersEmails.remove(user.getEmail());
    }

    /**
     * Removes a club post from the club posts' history.
     * @param post particular post to be removed
     */
    public void removeClubPost(Post post) {
        clubPostsDescription.remove(post.getTitle());
        clubMembersNames.remove(post.getContent());
    }

    public void setClubDescription(String newDescription) {
        this.clubDescription = newDescription;
    }
}

//package entity.user;
//import com.google.cloud.firestore.annotation.PropertyName;
//import entity.data_structure.DataStore;
//import entity.data_structure.DataStoreArrays;
//import entity.post.Post;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * This is the Club class which implements the User interface. A Club is a type of User.
// */
//public class Club implements User {
//    // Club's personal information
//    private final String username;
//    private final String email;
//    private final String password;
//    private String clubDescription = "";
//
//    // Internal DataStores
//    private final transient DataStore<String> clubMembersEmails;
//    private final transient DataStore<String> clubMembersNames;
//    private final transient DataStore<String> clubPostsTitle;
//    private final transient DataStore<String> clubPostsDescription;
//
//    // Firebase-compliant lists
//    @PropertyName("clubMembersEmails")
//    private List<String> clubMembersEmailsFirestore;
//
//    @PropertyName("clubMembersNames")
//    private List<String> clubMembersNamesFirestore;
//
//    @PropertyName("clubPostsTitle")
//    private List<String> clubPostsTitleFirestore;
//
//    @PropertyName("clubPostsDescription")
//    private List<String> clubPostsDescriptionFirestore;
//
//    // No-argument constructor (required by Firestore)
//    public Club() {
//        this.username = "";
//        this.email = "";
//        this.password = "";
//
//        // Initialize DataStores and Firestore lists
//        this.clubMembersEmails = new DataStoreArrays<>();
//        this.clubMembersNames = new DataStoreArrays<>();
//        this.clubPostsTitle = new DataStoreArrays<>();
//        this.clubPostsDescription = new DataStoreArrays<>();
//
//        this.clubMembersEmailsFirestore = new ArrayList<>();
//        this.clubMembersNamesFirestore = new ArrayList<>();
//        this.clubPostsTitleFirestore = new ArrayList<>();
//        this.clubPostsDescriptionFirestore = new ArrayList<>();
//    }
//
//    public Club(String username, String email, String password, DataStore<String> clubMembersEmails,
//                DataStore<String> clubMembersNames, DataStore<String> clubPostsTitle,
//                DataStore<String> clubPostsDescription) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//
//        this.clubMembersEmails = clubMembersEmails;
//        this.clubMembersNames = clubMembersNames;
//        this.clubPostsTitle = clubPostsTitle;
//        this.clubPostsDescription = clubPostsDescription;
//
//        this.clubMembersEmailsFirestore = clubMembersEmails.toArrayList();
//        this.clubMembersNamesFirestore = clubMembersNames.toArrayList();
//        this.clubPostsTitleFirestore = clubPostsTitle.toArrayList();
//        this.clubPostsDescriptionFirestore = clubPostsDescription.toArrayList();
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getClubDescriptionInternal() {
//        return clubDescription;
//    }
//
//    public DataStore<String> getClubMembersEmailsInternal() {
//        return clubMembersEmails;
//    }
//
//    @PropertyName("clubMembersEmails")
//    public List<String> getClubMembersEmailsFirestore() {
//        return new ArrayList<>(clubMembersEmails.toArrayList());
//    }
//
//    @PropertyName("clubMembersEmails")
//    public void setClubMembersEmailsFirestore(List<String> emails) {
//        this.clubMembersEmailsFirestore = emails;
//        emails.forEach(clubMembersEmails::add);
//    }
//
//    public DataStore<String> getClubMembersNamesInternal() {
//        return clubMembersNames;
//    }
//
//    @PropertyName("clubMembersNames")
//    public List<String> getClubMembersNamesFirestore() {
//        return new ArrayList<>(clubMembersNames.toArrayList());
//    }
//
//    @PropertyName("clubMembersNames")
//    public void setClubMembersNamesFirestore(List<String> names) {
//        this.clubMembersNamesFirestore = names;
//        names.forEach(clubMembersNames::add);
//    }
//
//    public DataStore<String> getClubPostsTitleInternal() {
//        return clubPostsTitle;
//    }
//
//    @PropertyName("clubPostsTitle")
//    public List<String> getClubPostsTitleFirestore() {
//        return new ArrayList<>(clubPostsTitle.toArrayList());
//    }
//
//    @PropertyName("clubPostsTitle")
//    public void setClubPostsTitleFirestore(List<String> titles) {
//        this.clubPostsTitleFirestore = titles;
//        titles.forEach(clubPostsTitle::add);
//    }
//
//    public DataStore<String> getClubPostsDescriptionInternal() {
//        return clubPostsDescription;
//    }
//
//    @PropertyName("clubPostsDescription")
//    public List<String> getClubPostsDescriptionFirestore() {
//        return new ArrayList<>(clubPostsDescription.toArrayList());
//    }
//
//    @PropertyName("clubPostsDescription")
//    public void setClubPostsDescriptionFirestore(List<String> descriptions) {
//        this.clubPostsDescriptionFirestore = descriptions;
//        descriptions.forEach(clubPostsDescription::add);
//    }
//
//    /**
//     * Adds a club member to the Club.
//     *
//     * @param user particular user joining the club.
//     */
//    public void addClubMember(Student user) {
//        clubMembersEmails.add(user.getEmail());
//        clubMembersNames.add(user.getUsername());
//
//        // Sync with Firestore lists
//        clubMembersEmailsFirestore.add(user.getEmail());
//        clubMembersNamesFirestore.add(user.getUsername());
//    }
//
//    /**
//     * Adds a club post to the history of the club's posts.
//     *
//     * @param post particular post to add.
//     */
//    public void addClubPost(Post post) {
//        clubPostsTitle.add(post.getTitle());
//        clubPostsDescription.add(post.getContent());
//
//        // Sync with Firestore lists
//        clubPostsTitleFirestore.add(post.getTitle());
//        clubPostsDescriptionFirestore.add(post.getContent());
//    }
//
//    /**
//     * Removes a user from the club.
//     *
//     * @param user particular user leaving the club.
//     */
//    public void removeClubMember(Student user) {
//        clubMembersEmails.remove(user.getEmail());
//        clubMembersNames.remove(user.getUsername());
//
//        // Sync with Firestore lists
//        clubMembersEmailsFirestore.remove(user.getEmail());
//        clubMembersNamesFirestore.remove(user.getUsername());
//    }
//
//    /**
//     * Removes a club post from the club posts' history.
//     *
//     * @param post particular post to be removed
//     */
//    public void removeClubPost(Post post) {
//        clubPostsTitle.remove(post.getTitle());
//        clubPostsDescription.remove(post.getContent());
//
//        // Sync with Firestore lists
//        clubPostsTitleFirestore.remove(post.getTitle());
//        clubPostsDescriptionFirestore.remove(post.getContent());
//    }
//
//    public void setClubDescription(String newDescription) {
//        this.clubDescription = newDescription;
//    }
//}