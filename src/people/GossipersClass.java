package people;

//Imports
import dataStructures.Iterator;
import gossip.Gossip;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * Handles the information about a gossiper person, which is a specific type of person
 * of the abstract class PersonAbstract.
 * It contains methods that are only used by this type of person.
 * Gossiper person is characterized by never forget a gossip, and by sharing up to three
 * gossips whenever they gossip. Also, they go around all their gossips before they repeat themselves.
 *
 */

public class GossipersClass extends PersonAbstract {
    //Instance variable
    private static final int SIZE = 50;
    //Constructor

    /**
     * Constructor of GossipersClass which is a SubClass from PersonAbstract
     * with the same parameters as the Superclass.
     * A Gossiper is defined by a name and a size. Unlike the forgetful person, gossiper
     * person doesn't have a maximum limit for the gossips he knows because he never forgets,
     * therefore it has as size 50 because is the same default value used in package dataStructures
     * for the creation of ArrayClass. This value will be irrelevant because each time the number of
     * gossips overcome the limit, ArrayClass will make a resize.
     *
     * @param name The person's name
     * @pre name != null
     */
    public GossipersClass(String name) {
        super(name, SIZE);
    }

    //Public methods

    @Override
    public Gossip shareGossip() {
        Iterator<Gossip> gossipIterator1 = super.gossipIterator();
        if (super.current == super.gossipArray.size()){
            super.current = 0;
        }
        if (!gossipIterator1.hasNext()){
            gossipIterator1.rewind();
        }
        Gossip shared = gossipIterator1.next();
        super.current++;
        return shared;
    }

    @Override
    public void addGossip(Gossip gossip) {
        super.gossipArray.insertLast(gossip);
        gossip.addAcknowledgment();
        gossip.addSharedNumber(super.getName());
        super.updateIterator();
    }

}
