package people;
//Imports
import dataStructures.Iterator;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of the GroupClass.
 */
public interface Group {
    /**
     * Add a person to the group.
     * @param person the person object
     */
    void addPerson(Person person);

    /**
     * Get the number of persons that exist in the group.
     * @return the number of persons in the group
     */
    int numberPersonsInGroup();

    /**
     * @return an Iterator with all persons within the group
     */
    Iterator<Person> personIterator();

    /**
     * Remove a person from the group.
     * @param person the person object
     */
    void removePerson(Person person);

    /**
     * Get a specific person from the group
     * @param name the person's name
     * @return Persons object from the group
     */
    Person getPerson(String name);

    /**
     * It checks if a specific person object exist in the group
     * @param person the person object
     * @return  return true if person exist in the group, otherwise return false
     */
    Boolean personExistsInGroup(Person person);
}
