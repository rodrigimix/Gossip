package gossip;
//Imports
import dataStructures.Iterator;
import people.Person;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of the GossipClass.
 */
public interface Gossip {

    /**
     * Add person as a target in the gossip.
     * @param target the person object
     */
    void addTarget(Person target);

    /**
     * @return an iterator of all targets of the gossip
     */
    Iterator<Person> targetIterator();

    /**
     * Get the author of the gossip.
     * @return the person object which is the author
     */
    Person getAuthor();

    /**
     * Get the description of the gossip.
     * @return the description of the gossip
     */
    String getDescription();

    /**
     * Get the number of persons who knows about the gossip.
     * @return the number of persons who knows about the gossip
     */
    int getNumberWhoKnowsGossip();

    /**
     * Get the number of the targets for the gossip.
     * @return the number of the targets for the gossip
     */
    int numberOfTargets();

    /**
     * Check if the gossip has as target a specific person.
     * @param person the person object
     * @return true if it has that person as target, otherwise return false
     */
    boolean hasTarget(Person person);

    /**
     * Each time a different person knows about the gossip, increments the value knowGossip
     */
    void addAcknowledgment();

    /**
     * Each time a different person forgets about the gossip, decrements the value knowGossip
     */
    void removeAcknowledgment();

    /**
     * Each time a person shares a gossip to another person, increments the value shareGossip
     * @param name name of the citizen
     */
    void addSharedNumber(String name);

    /**
     * Gives the number of sharing of this gossip
     * @return int of shareGossip Array
     */
    int getShareNumber();

    /**
     * Verify if this gossip is known
     * @return true - if this gossip is known | false - the opposite
     */
    boolean gossipIsKnowledgeBySomeone();
}
