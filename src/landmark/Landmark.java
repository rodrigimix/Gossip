package landmark;
//Imports
import dataStructures.Iterator;
import people.Group;
import people.Person;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of the LandmarkClass.
 */
public interface Landmark {

    /**
     * Get the name of the landmark.
     * @return the name of the landmark
     */
    String getName();

    /**
     * Get how many persons are in the landmark.
     *
     * @return how many persons are in the landmark
     */
    int getOccupation();

    /**
     * Get the maximum capacity of the landmark to receive persons.
     *
     * @return the maximum capacity of the landmark
     */
    int getFullCapacity();

    /**
     * Depending on the value of the sameLandmark, adds a person to the landmark and to a new group
     * and the occupation increase, but if the person is already in the landmark, will only move in to a new
     * group and the occupation keeps the same.
     *
     * @param person the person object
     * @param sameLandmark if true occupation of the landmark keeps the same, otherwise it changes.
     */
    void addPerson(Person person, boolean sameLandmark);

    /**
     * Depending on the value of the sameLandmark, remove a person from the group and so from landmark and the
     * occupation decrease, but if the person remains in the landmark, will only remove the person from the group and
     * the occupation keeps the same.
     * @param person person object
     * @param sameLandmark if true occupation of the landmark keeps the same, otherwise it changes.
     */
    void removePerson(Person person, boolean sameLandmark);

    /**
     * Add person1 to the group of the person2.
     * @param person1 the name of the person1
     * @param person2 the name of the person2
     */
    void addGroupPerson(String person1, String person2);

    /**
     * @return an Iterator with all groups which exist in the landmark
     */
    Iterator<Group> groupIterator();

    /**
     * Get group where a specific person is.
     *
     * @param personName the person's name
     * @return the group where the person is
     */
    Group getGroup(String personName);

    /**
     * Give the number of groups that exist in the landmark.
     *
     * @return the number of groups in the landmark
     */
    int numberOfGroups();

    /**
     * Checks if the person1 and person2 are in the same group.
     * @param person1 the name of the person1
     * @param person2 the name of the person2
     * @return return true if both are in the same group, otherwise return false
     */
    boolean isASameGroup(String person1, String person2);

}
