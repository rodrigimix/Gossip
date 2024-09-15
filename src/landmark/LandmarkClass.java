package landmark;

//Imports
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import people.Group;
import people.GroupClass;
import people.Person;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * It's in the landmark that people can join any group that exist to share gossips.
 */
public class LandmarkClass implements Landmark {
    //Constant
    private static final String HOME = "home";


    //Instance Variables
    private final String name;
    private final Array<Group> groupArray;
    private  int occupation;
    private final int maxSizeArray;

    //Constructor

    /**
     * LandmarkClass is defined by a name and the maximum number of persons the landmark can receive (sizeLimit).
     * It's also has an array of groups (groupArray) and a number of persons that are occupying the
     * landmark (occupation).
     *
     * @param name the landmark's name
     * @param sizeLimit the maximum number of persons the landmark can receive
     */
    public LandmarkClass(String name, int sizeLimit){
        this.name= name;
        this.groupArray = new ArrayClass<>(sizeLimit);
        this.occupation = 0;
        this.maxSizeArray = sizeLimit;
    }

    //Public Methods
    @Override
    public String getName(){return name;}

    @Override
    public int getOccupation(){ return occupation;}

    @Override
    public int getFullCapacity(){ return maxSizeArray;}

    @Override
    public void addPerson(Person person, boolean sameLandmark) {
        Group groupParticular = new GroupClass();
        groupParticular.addPerson(person);
        groupArray.insertLast(groupParticular);
        if (!sameLandmark){
            this.occupation++;
        }
    }

    @Override
    public void removePerson(Person person, boolean sameLandmark) {
        if (!person.getLandmark().getName().equals(HOME)){
            int index = searchIndexGroup(person.getName());
            Group group = this.groupArray.get(index);
            group.removePerson(person);
            if(group.numberPersonsInGroup() <= 0){
                groupArray.removeAt(index);
            }
        }
        if (!sameLandmark){
            this.occupation--;
        }
    }

    @Override
    public void addGroupPerson(String person1, String person2) {
        Person personEntry = this.groupArray.get(searchIndexGroup(person1)).getPerson(person1);
        removePerson(personEntry,true);
        this.groupArray.get(searchIndexGroup(person2)).addPerson(personEntry);
    }

    @Override
    public Iterator<Group> groupIterator() {
        return this.groupArray.iterator();
    }

    @Override
    public Group getGroup(String personName){
        return this.groupArray.get(searchIndexGroup(personName));
    }

    @Override
    public int numberOfGroups() {
        return this.groupArray.size();
    }

    @Override
    public boolean isASameGroup(String person1, String person2) {
        int groupPerson1Index = searchIndexGroup(person1);
        int groupPerson2Index = searchIndexGroup(person2);
        return groupPerson1Index == groupPerson2Index;
    }

    public String toString(){
        Iterator<Group> groupIt = groupArray.iterator();
        String info = "";
        int counter = 0;
        while (groupIt.hasNext()){
            if(counter != 0){
                info += "\n";
            }
            Group groupTemp = groupIt.next();
            info += groupTemp.toString();
            counter++;
        }
        info += "\n";
        return info;
    }


    //Auxiliary Method

    /**
     * Get the index of the group where the person is, in array of groups.
     * @param people the person's name
     * @return the index of the group in the array of groups
     */
    private int searchIndexGroup(String people){
        int counter = 0;
        Iterator<Group> groupIt = this.groupArray.iterator();
        while(groupIt.hasNext()){
            Group group = groupIt.next();
            Iterator<Person> personIterator = group.personIterator();
            while (personIterator.hasNext()){
                Person person = personIterator.next();
                if(person.getName().equals(people))
                    return counter;
            }
            counter++;
        }
        return -1;
    }
}
