package people;
//Imports
import gossip.Gossip;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * Handles the information about a forgetful person, which is a specific type of person
 * of the abstract class PersonAbstract.
 * It contains methods that are only used by this type of person.
 * Forgetful person is characterized by having poor memory, so he con only remember the last
 * specific number of gossips they heard, and this limit number is defined when forgetful person
 * is created.
 *
 */
public class ForgetfulPersonClass extends PersonAbstract implements ForgetfulPerson {
    //Instance variable
    private final int sizeLimit;

    //Constructor

    /**
     * Constructor of ForgetfulPersonClass, which is a SubClass from PersonAbstract.
     * This constructor has the same parameters as the Superclass and a size,
     * which is maximum limit of gossips that this type of person can remember.
     * Each person have a different name and a size greater than 0.
     *
     * @param name The person's name
     * @param size The maximum limit of gossips that this type of person can remember
     *
     * @pre name != null && size > 0
     */

    public ForgetfulPersonClass(String name, int size) {
        super(name, size);
        this.sizeLimit = size;
    }

    //Public Methods
    @Override
    public Gossip shareGossip() {
        if(!super.gossipIterator.hasNext()){
            super.gossipIterator.rewind();
        }
        if(super.current+1 == this.sizeLimit || !(super.gossipArray.size() > 1)){
            super.current = 0;
        }
        else {
            super.current++;
        }
        return super.gossipIterator.next();
    }

    @Override
    public void addGossip(Gossip gossip) {
        super.gossipArray.insertLast(gossip);
        gossip.addAcknowledgment();
        gossip.addSharedNumber(super.getName());
        if(super.gossipArray.size() > this.sizeLimit){
            super.gossipArray.get(0).removeAcknowledgment();
            super.gossipArray.removeAt(0);
            if(current > 0){
                current--;
            }
        }
        super.updateIterator();
    }

    @Override
    public void defaultCurrent(){

        super.current = 0;
        super.updateIterator();
    }

}
