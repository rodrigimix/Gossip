package people;
//Imports
import dataStructures.Iterator;
import gossip.Gossip;
import landmark.Landmark;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of the PersonAbstract
 */
public interface Person {
    /**
     * Function that allows you to give the name of the citizen
     * @return name of the citizen
     */
    String getName();

    /**
     * Iterator that allows parsing the citizen's array of Gossip Objects
     * @return Iterator of Gossip Objects
     */
    Iterator<Gossip> gossipIterator();

    /**
     * Function that allows sharing the citizen's gossip to other people
     * @return current gossip
     */
    Gossip shareGossip();

    /**
     * Function that allows saving the citizen's gossip
     * @param gossip the citizen's gossip
     */
    void addGossip(Gossip gossip);

    /**
     * This function aims to redefine the limit of gossips shared by the citizen after leaving a group
     */
    void isolate();

    /**
     * Checks if the person is alone inside the group
     * @return return true if is alone, otherwise return false
     */
    boolean isAlone();


    /**
     * Method which allows you to get the landmark
     * @return the landmark object
     */
    Landmark getLandmark();

    /**
     * Define as landmark the landmark given
     * @param landmark the landmark object
     */
    void addLandmark(Landmark landmark);
    /**
     * Method which allows you to get the current index
     * @return the current index
     */
    boolean hasGossip();

    /**
     * Method which allows you to get the current index
     * @return the current index
     */
    int getCurrent();

    /**
     * Checks if a specific gossip already exist
     * @param gossip the gossip object
     * @return return true if the gossip already exist, otherwise return false
     */
    boolean gossipExists(Gossip gossip);

    /**
     * Method which allows you to get the gossip's array size
     * @return gossip's array size
     */
    int getSize();
}
