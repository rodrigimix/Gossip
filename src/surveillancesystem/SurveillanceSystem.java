package surveillancesystem;

// Imports
import dataStructures.Array;
import dataStructures.Iterator;
import gossip.Gossip;
import people.Group;
import landmark.Landmark;
import people.Person;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of the SurveillanceSystemClass.
 */

public interface SurveillanceSystem {

    /**
     * Adds a new Landmark in array of Landmark with the given parameters.
     *
     * @param size the maximum number of people the landmark is capable of receiving
     * @param name The name of the landmark
     */
    void addLandmark(int size, String name);

    /**
     * Allows to know if the sealed lips person have any gossip that have as target himself.
     *
     * @param name The person's name
     * @return Returns true in case the sealed lips person has any gossip that only have as target himself,
     *         otherwise returns false
     */
    boolean sealedLipsHasOwnTarget(String name);

    /**
     * Allows to know if person is a sealed lips, or it's of another type.
     * @param name  The person's name
     * @return Returns true if it's sealed lips person, otherwise returns false
     */
    boolean isAnSealedLip(String name);

    /**
     * Each person have an unique index which is used to get the values from an array of gossips.
     * This index is always being updated, so this method allows to return the current index that is being use.
     *
     * @param name The person's name
     * @return Return the current index
     */
    int getCurrent(String name);

    /**
     * @return an Iterator with all gossips.
     */
    Iterator<Gossip> GossipIterator();

    /**
     * Overrides the method toString in the class Object.
     * Enables to put a string in a specific format.
     * @return A string in a specific format.
     */
    String toString();
    /**
     * @return an Iterator with all landmarks.
     */
    Iterator<Landmark> landmarkIterator();

    /**
     * Add a forgetful person to the array of persons.
     *
     * @param size the maximum number of gossips that this person can remember
     * @param name the person's name
     */
    void addForgetful(int size, String name);

    /**
     * Add a gossiper person to the array of persons.
     * @param name the person's name
     */
    void addGossiper(String name);

    /**
     * Add a sealed lips person to the array of persons.
     * @param name the person's name
     */
    void addSealed(String name);

    /**
     * @return an Iterator with all persons.
     */
    Iterator<Person> allPeopleIt();

    /**
     * It checks if the person name exists in the community.
     *
     * @param name the person's name
     * @return Return true if person exists, otherwise returns false
     */
    boolean personExists(String name);

    /**
     * It checks if the landmark exists in the community.
     *
     * @param name the landmark's name
     * @return Return true if landmark exists, otherwise returns false
     */
    boolean landmarkExists(String name);

    /**
     * Move the person from the landmark that he were, to the landmark's name that was given.
     *
     * @param name the person's name
     * @param landmark the landmark's name
     */
    void goToLandmark(String name, String landmark);

    /**
     * Move person to home.
     *
     * @param name the person's name
     */
    void goToHome(String name);

    /**
     * It makes the first person to leave its currently group, and to join the group of the second person.
     *
     * @param name1 the person's name which is going to move in to the group of the second person
     * @param name2 the person's name of the second person
     */
    void joinGroup(String name1, String name2);

    /**
     * It isolates the person, removing from the group he was and is moved to a new group
     * where he stays alone.
     *
     * @param name The person's name
     */
    void isolate(String name);

    /**
     * Creates a new gossip using the information given.
     *
     * @param name The person's name
     * @param size The number of targets
     * @param targets The names of the persons which will be the targets
     * @param description The description of the gossip
     */
    void startGossip(String name, int size, String[] targets, String description);

    /**
     * The person's name that was given, will share gossips with the group where he's inserted.
     * In case it's gossiper person, will share 3 gossips, but if it's a forgetful person or a sealed lips person
     * will share only 1 gossip.
     *
     * @param name The person's name
     * @return An array of gossips with all the gossips shared
     */
    Array<Gossip> shareGossip(String name);

    /**
     * Checks if the person is already in the landmark or not.
     *
     * @param personName The person's name
     * @param landmarkName The landmark's name
     * @return Return true if the person is in the landmark, otherwise return false
     */
    boolean alreadyInLandmark(String personName, String landmarkName);

    /**
     * Checks if the person is already in the landmark or not.
     *
     * @param landmarkName The landmark's name
     * @return Return true if the person is in the landmark, otherwise return false
     */
    boolean landmarkCrowded(String landmarkName);

    /**
     * Checks if the person is at home.
     *
     * @param name The person's name
     * @return Return true if the person is at home, otherwise return false
     */
    boolean personAtHome(String name);

    /**
     * Checks if the first and second person are in the same landmark.
     *
     * @param firstPerson The person's name of the first person
     * @param secondPerson The person's name of the second person
     * @return Return true if both are in the same landmark, otherwise return false
     */
    boolean bothInSameLandmark(String firstPerson, String secondPerson);

    /**
     * Get the landmark's name where the person is.
     *
     * @param personName The person's name
     * @return String with the landmark's name
     */
    String getLandmarkName(String personName);

    /**
     * Checks if the first and second person are in the same group.
     *
     * @param firstPersonName The person's name of the first person
     * @param secondPersonName The person's name of the second person
     * @return Return true if both are in the same group, otherwise return false
     */
    boolean alreadySameGroup(String firstPersonName, String secondPersonName);

    /**
     * Get the group where person is.
     *
     * @param PersonName The person's name
     * @param landmark The landmark's name
     * @return The group where person is.
     */
    Group getGroup(String PersonName, String landmark);

    /**
     * Checks if the person is alone in the group.
     *
     * @param name The person's name
     * @return Return true if person is alone, otherwise return false
     */
    boolean isAlone(String name);

    /**
     * Checks if the gossip already exist, checking if has the same author, the same targets,
     * number of targets and same gossip description.
     *
     * @param author The author's name
     * @param targets The name of the person's targets
     * @param numberTargets  The number of targets
     * @param gossipDescription The description of the gossip
     * @return Return true if the gossip already exist, otherwise return false
     */
    boolean sameGossip(String author, String[] targets, int numberTargets, String gossipDescription);

    /**
     * @param name The person's name
     * @return An Iterator with all the gossips about this person
     */
    Iterator<Gossip> secrets(String name);

    /**
     * Checks if there exist any gossip about the person's name given
     *
     * @param name The person's name
     * @return Return true if there exist any gossip about that person, otherwise return false
     */
    boolean hasSecrets(String name);

    /**
     * @return An Iterator with the most shared gossips by the Community
     */
    Iterator<Gossip> hottest();

    /**
     * @param name The person's name
     * @return An Iterator with all gossips the person knows
     */
    Iterator<Gossip> knownGossips(String name);

    /**
     * Checks if there's anyone in the landmark
     *
     * @param name The landmark's name
     * @return Return true if there is anyone in the landmark, otherwise return false
     */
    boolean isAnyOneInLandMark(String name);

    /**
     * Checks if the person knows any gossip.
     *
     * @param name The person's name
     * @return Return true if he knows any gossip, otherwise return false
     */
    boolean hasGossipPerson(String name);

    /**
     * @param name The landmark's name
     * @return An Iterator with all the groups which exist in the landmark
     */
    Iterator<Group> groupIterator(String name);
}
