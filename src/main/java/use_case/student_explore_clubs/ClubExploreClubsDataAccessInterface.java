package use_case.student_explore_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * Data access interface for the explore clubs use case.
 * Handles only the Club entity data.
 */
public interface ClubExploreClubsDataAccessInterface {

    /**
     * Returns all the clubs in the database.
     * @return all the clubs in the database in DataStore type.
     */
    DataStore<Club> getAllClubs();
}
