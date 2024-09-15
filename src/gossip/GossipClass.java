package gossip;
//Imports
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import people.Person;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * All information about the gossip.
 */
public class GossipClass implements Gossip {
    //Instance Variables
    private final Person author;
    private final Array<Person> targetArray;
    private int knowGossip;
    private int sharedGossip;
    private final String description;

    //Constructor

    /**
     * Constructor of GossipClass that is defined by an author, the size (number of targets)
     * and the description of the gossip. It also has an array of persons which contains
     * all the targets (targetArray) and a knownGossip which allows to know how many persons know about the gossip.
     * @param author the person object
     * @param size the number of targets
     * @param description the gossip's description
     */
    public GossipClass(Person author, int size, String description){
        this.author = author;
        this.targetArray = new ArrayClass<>(size);
        this.knowGossip = 0;
        this.description = description;
        this.sharedGossip = 0;
    }

    //Public Methods
    @Override
    public void addTarget(Person target) {
        this.targetArray.insertLast(target);
    }

    @Override
    public Iterator<Person> targetIterator() {
        return this.targetArray.iterator();
    }

    @Override
    public Person getAuthor() {
        return this.author;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getNumberWhoKnowsGossip() {
        return this.knowGossip;
    }

    @Override
    public int numberOfTargets(){
        return this.targetArray.size();
    }
    @Override
    public boolean hasTarget(Person person){
        return this.targetArray.searchForward(person);
    }

    @Override
    public void addAcknowledgment() {
        this.knowGossip++;
    }

    @Override
    public void removeAcknowledgment(){
        this.knowGossip--;
    }

    @Override
    public void addSharedNumber(String name){
        if(!this.author.getName().equals(name)){
            this.sharedGossip++;
        }
    }
    @Override
    public int getShareNumber(){
        return this.sharedGossip;
    }

    @Override
    public boolean gossipIsKnowledgeBySomeone(){
        return this.knowGossip > 0;
    }
}
