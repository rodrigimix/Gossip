package people;

//Imports
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import gossip.Gossip;
import landmark.Landmark;
import landmark.LandmarkClass;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * Abstract class that contains all the methods in common to all the type of people
 */
public abstract class PersonAbstract implements Person {

    //Constant
    private static final String HOME = "home";

    //Instance Variables
    private final String name;
    private Landmark landmark;

    //Protected Variables
    protected Array<Gossip> gossipArray;
    protected int current;
    protected boolean hasPassedLimitShare;
    protected Iterator<Gossip> gossipIterator;

    //Constructor

    /**
     * An abstract constructor that represents the Person.
     * A person is defined by a name and a size.
     * It also has an array of gossips (gossipArray), a current which is the index used by each person
     * to move through the positions of the array, a landmark (place where person will stay), and
     * a boolean that says if the person has passed the limit of sharing (hasPassedLimitShare) by
     * default is false.
     *
     * @param name the person's name
     * @param size the maximum limit of gossips person can remember
     */
    PersonAbstract(String name, int size){
        this.name = name;
        this.gossipArray = new ArrayClass<>(size);
        this.current = 0;
        this.landmark = new LandmarkClass(HOME,1);
        this.hasPassedLimitShare = false;
    }

    //Public Methods
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Landmark getLandmark(){
        return this.landmark;
    }

    @Override
    public Iterator<Gossip> gossipIterator() {
        return this.gossipIterator;
    }

    @Override
    public void addLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    @Override
    public boolean isAlone() {
        Group group = landmark.getGroup(this.name);
        return group.numberPersonsInGroup() == 1;
    }

    @Override
    public void isolate(){
        this.hasPassedLimitShare = false;
    }

    @Override
    public boolean hasGossip(){
        return this.gossipArray.size() > 0;
    }

    @Override
    public int getCurrent(){return this.current;}

    @Override
    public boolean gossipExists(Gossip gossip){
        return this.gossipArray.searchForward(gossip);
    }
    @Override
    public int getSize(){
        return this.gossipArray.size();
    }

    public String toString() {
        return getName() + " at " + getLandmark().getName() + " knows " + gossipArray.size() + " gossips.\n";
    }

    /**
     * Update the iterator each time the person share a gossip, this way the right
     * position to get access the next gossip will be updated.
     */
    protected void updateIterator(){
        this.gossipIterator = this.gossipArray.iterator();
        for(int i = 0; i < current; i++){
            gossipIterator.next();
        }
    }

}
