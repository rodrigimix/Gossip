package people;
//Imports
import dataStructures.ArrayClass;
import dataStructures.Iterator;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
  * Here the groups of persons are created. It's within each group, when there's more than 1 person, that
 * people share the gossips with each other.
 */
public class GroupClass implements Group {

    //Instance variable
    private final ArrayClass<Person> peopleArray;

    //Constructor

    /**
     * GroupClass is defined by an array of persons (peopleArray)
     */

    public GroupClass() {
        this.peopleArray = new ArrayClass<>();
    }

    //Public Methods
    @Override
    public void addPerson(Person person) {
        peopleArray.insertLast(person);
    }

    @Override
    public int numberPersonsInGroup() {
        return this.peopleArray.size();
    }

    @Override
    public Iterator<Person> personIterator() {
        return this.peopleArray.iterator();
    }

    @Override
    public void removePerson(Person person) {
        this.peopleArray.removeAt(searchIndexPerson(person.getName()));
    }


    @Override
    public Person getPerson(String name) {
        return this.peopleArray.get(searchIndexPerson(name));
    }

    @Override
    public Boolean personExistsInGroup(Person person) {
        return this.peopleArray.searchForward(person);
    }


    public String toString(){
        String allNames = "";
        for (int i = 0; i < this.peopleArray.size(); i++) {
            allNames += this.peopleArray.get(i).getName();
            if(i != this.peopleArray.size()-1){
                allNames += ", ";
            }
        }
        return allNames;
    }

    //Auxiliary Methods

    /**
     * Search the index of a specific person in array of persons.
     *
     * @param person the person's name
     * @return the index of the person in array of persons
     */
    private int searchIndexPerson(String person) {
        int counter = 0;
        Iterator<Person> personIterator = this.peopleArray.iterator();
        while (personIterator.hasNext()) {
            Person citizen = personIterator.next();
            if (citizen.getName().equals(person)) {
                return counter;
            }
            counter++;
        }
        return -1;

    }
}
