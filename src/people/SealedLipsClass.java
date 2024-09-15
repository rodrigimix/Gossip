package people;

//Imports
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import gossip.Gossip;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *  Handles the information about a sealed lips person, which is a specific type of person
 *  of the abstract class PersonAbstract.
 *  It contains methods that are only used by this type of person.
 *  Sealed lips person is characterized by hear the gossips but never spread it,
 *  unless the gossip is about themselves.
 */
public class SealedLipsClass extends PersonAbstract implements SealedLips{
    //Constant
    private static final int SIZE = 50;
    //Instance Variables
    Array<Gossip> personTarget;
    private boolean hasOwnTarget;
    Iterator<Gossip> gossipTargetIterator;
    private int currentTarget;


    //Constructor

    /**
     *  Constructor of SealedLipsClass which is a SubClass from PersonAbstract
     *  with the same parameters as the Superclass.
     *  A sealed lips person is defined by a name. Unlike the forgetful person, sealed lips
     *  person doesn't have a maximum limit for the gossips he knows because he never forgets,
     *  therefore it have as size 50 because is the same default value used in package dataStructures
     *  for the creation of ArrayClass. This value will be irrelevant because each time the number of
     *  gossips overcome the limit, ArrayClass will make a resize.
     *  It has also an array where store the gossips about himself (personTarget), a boolean value which says if
     *  the person have any gossip about himself (hasOwnTarget), and currentTarget which is an index used to move
     *  through the array of gossips.
     *
     * @param name the person's name
     *
     * @pre name != null
     */

    public SealedLipsClass(String name) {
        super(name, SIZE);
        this.personTarget = new ArrayClass<>();
        hasOwnTarget = false;
        this.currentTarget = 0;
    }

    //Public Methods

    @Override
    public Gossip shareGossip() {
        Iterator<Gossip> gossipIterator = this.gossipTargetIterator;
        if (currentTarget == personTarget.size()) {
            currentTarget = 0;
        }
        if(!gossipIterator.hasNext()){
            gossipIterator.rewind();
        }
        Gossip shared = gossipIterator.next();
        updateCurrent(shared);
        super.updateIterator();
        return shared;
    }

    @Override
    public void addGossip(Gossip gossip) {
        super.gossipArray.insertLast(gossip);
        gossip.addAcknowledgment();
        gossip.addSharedNumber(super.getName());
        if(isTarget(gossip)){
            personTarget.insertLast(gossip);
            this.hasOwnTarget = true;
        }
        super.updateIterator();
        updateTargetGossipIterator();
    }

    @Override
    public boolean hasOwnTargets() {
        return this.hasOwnTarget;
    }

    //Auxiliary Methods

    /**
     * Checks if the gossip has as target himself.
     *
     * @param gossip the gossip object
     * @return return true if gossip has the sealed lips person as target,
     * otherwise return false
     */
    private boolean isTarget(Gossip gossip){
        Iterator<Person> it = gossip.targetIterator();
        while (it.hasNext()){
            Person person = it.next();
            if(person.getName().equals(super.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Update the correct next gossip depending on currentTarget.
     */
    private void updateTargetGossipIterator(){
        this.gossipTargetIterator = this.personTarget.iterator();
        for(int i = 0; i < currentTarget; i++){
            gossipTargetIterator.next();
        }
    }

    /**
     * Update the position of the specific gossip
     * @param gossip the gossip object
     */
    private void updateCurrent(Gossip gossip){
        int index = this.gossipArray.searchIndexOf(gossip);
        this.current = index + 1;
    }

}
