package surveillancesystem;
 //Imports
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import gossip.Gossip;
import gossip.GossipClass;
import landmark.Landmark;
import landmark.LandmarkClass;
import people.*;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * Here the relathionship between main and classes from the other packages is created.
 */
public class SurveillanceSystemClass implements SurveillanceSystem {
    //Instance Variables
    private static final String HOME = "home";
    private static final int GOSSIPER_SHARING = 3;
    private static final int FORGETFUL_AND_SEALED_LIPS_SHARING = 1;
    private final Array<Person> personArray;
    private final Array<Gossip> gossipArray;
    private final Array<Landmark> landmarkArray;
    private Array<Gossip> sharedGossips;

    //Constructor

    /**
     * A SurveillanceSystemClass is defined by an array of persons (personArray), an array
     * of gossips (gossipArray), and an array of landmarks (landmarkArray).
     */
    public SurveillanceSystemClass() {
        this.personArray = new ArrayClass<>();
        this.gossipArray = new ArrayClass<>();
        this.landmarkArray = new ArrayClass<>();
        this.sharedGossips = new ArrayClass<>();
    }

    //Public Methods
    @Override
    public void addLandmark(int size, String name) {
        Landmark landmark = new LandmarkClass(name, size);
        this.landmarkArray.insertLast(landmark);
    }

    @Override
    public Iterator<Landmark> landmarkIterator() {
        return this.landmarkArray.iterator();
    }

    @Override
    public void addForgetful(int size, String name) {
        Person person = new ForgetfulPersonClass(name, size);
        this.personArray.insertLast(person);
    }

    @Override
    public void addGossiper(String name) {
        Person person = new GossipersClass(name);
        this.personArray.insertLast(person);
    }

    @Override
    public void addSealed(String name) {
        Person person = new SealedLipsClass(name);
        this.personArray.insertLast(person);
    }

    @Override
    public Iterator<Person> allPeopleIt() {
        return this.personArray.iterator();
    }


    @Override
    public boolean personExists(String name) {
        return searchIndexPerson(name) > -1;
    }

    @Override
    public boolean landmarkExists(String name) {
        return searchIndexLandmark(name) > -1;
    }

    @Override
    public void goToLandmark(String name, String landmark) {
        Person citizen = this.personArray.get(searchIndexPerson(name));
        Landmark landmarkObj = this.landmarkArray.get(searchIndexLandmark(landmark));
        if (!citizen.getLandmark().getName().equalsIgnoreCase((HOME))) {
            landmarkArray.get(searchIndexLandmark(citizen.getLandmark().getName())).removePerson(citizen, false);
        }
        citizen.addLandmark(landmarkObj);
        landmarkObj.addPerson(citizen, false);
    }

    @Override
    public void goToHome(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        Landmark home = new LandmarkClass(HOME, 1);
        Landmark landmark = person.getLandmark();
        landmark.removePerson(person, false);
        person.addLandmark(home);
    }

    @Override
    public void joinGroup(String name1, String name2) {
        Person person = this.personArray.get(searchIndexPerson(name1));
        int index = this.landmarkArray.searchIndexOf(person.getLandmark());
        landmarkArray.get(index).addGroupPerson(name1, name2);
        if (person instanceof ForgetfulPerson person1) {
            person1.defaultCurrent();
        }
    }

    @Override
    public void isolate(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        Landmark landmark = person.getLandmark();
        landmark.removePerson(person, true);
        landmark.addPerson(person, true);
        person.isolate();
    }

    @Override
    public void startGossip(String name, int size, String[] targets, String description) {
        Person principal = this.personArray.get(searchIndexPerson(name));
        Gossip gossip = new GossipClass(principal, size, description);
        for (int i = 0; i < size; i++) {
            Person person = this.personArray.get(searchIndexPerson(targets[i]));
            gossip.addTarget(person);
        }
        this.gossipArray.insertLast(gossip);
        principal.addGossip(gossip);
    }

    @Override
    public Array<Gossip> shareGossip(String name) {
        int size;
        Array<Gossip> gossipsShared = new ArrayClass<>();
        Person person = this.personArray.get(searchIndexPerson(name));
        Landmark landmark = person.getLandmark();
        Group group = getGroup(person.getName(), landmark.getName());
        Iterator<Person> groupPersonIt = group.personIterator();

        if (person instanceof GossipersClass) {
            if (person.getSize() >= GOSSIPER_SHARING)
                size = GOSSIPER_SHARING;
            else {
                size = person.getSize();
            }
        } else {
            size = FORGETFUL_AND_SEALED_LIPS_SHARING;
        }

        for (int counter = 0; counter < size; counter++) {
            groupPersonIt.rewind();
            Gossip gossip = person.shareGossip();
            gossipsShared.insertLast(gossip);
            while (groupPersonIt.hasNext()) {
                Person friend = groupPersonIt.next();
                if (!friend.equals(person)) {
                    if (!gossipExists(gossip, friend)) {
                        friend.addGossip(gossip);
                    }
                }
            }
            updateSharedGossips(gossip);
        }
        return gossipsShared;
    }

    @Override
    public boolean alreadyInLandmark(String personName, String landmarkName) {
        Person person = this.personArray.get(searchIndexPerson(personName));
        Landmark landmark = this.landmarkArray.get(searchIndexLandmark(landmarkName));
        return person.getLandmark().equals(landmark);
    }

    @Override
    public boolean landmarkCrowded(String landmarkName) {
        Landmark landmark = this.landmarkArray.get(searchIndexLandmark(landmarkName));
        return landmark.getFullCapacity() <= landmark.getOccupation();
    }

    @Override
    public boolean personAtHome(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        return person.getLandmark().getName().equals(HOME);
    }

    @Override
    public boolean bothInSameLandmark(String firstPerson, String secondPerson) {
        Person person1 = this.personArray.get(searchIndexPerson(firstPerson));
        Person person2 = this.personArray.get(searchIndexPerson(secondPerson));
        return person1.getLandmark().equals(person2.getLandmark());
    }

    @Override
    public String getLandmarkName(String personName) {
        Person person = this.personArray.get(searchIndexPerson(personName));
        return person.getLandmark().getName();
    }

    @Override
    public boolean alreadySameGroup(String firstPersonName, String secondPersonName) {
        Landmark landmark = this.personArray.get(searchIndexPerson(firstPersonName)).getLandmark();
        return landmark.isASameGroup(firstPersonName, secondPersonName);
    }

    @Override
    public Group getGroup(String PersonName, String landmark) {
        Iterator<Group> groupIterator = groupIterator(landmark);
        Group groupTemp = null;
        while (groupIterator.hasNext()) {
            groupTemp = groupIterator.next();
            Person person = this.personArray.get(searchIndexPerson(PersonName));
            if (groupTemp.personExistsInGroup(person)) {
                break;
            }
        }
        return groupTemp;
    }

    @Override
    public boolean isAlone(String name) {

        Person person = this.personArray.get(searchIndexPerson(name));
        return person.isAlone();
    }

    @Override
    public boolean sameGossip(String author, String[] targets, int numberTargets, String gossipDescription) {
        boolean flag = false;
        Iterator<Gossip> gossipIterator = this.gossipArray.iterator();
        while (gossipIterator.hasNext()) {
            Gossip gossip = gossipIterator.next();
            if (gossip.getAuthor().getName().equals(author) && gossip.numberOfTargets() == numberTargets
                    && gossip.getDescription().equals(gossipDescription)) {
                for (int count = 0; count < numberTargets; count++) {
                    Person target = this.personArray.get(searchIndexPerson(targets[count]));
                    if (gossip.hasTarget(target)) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Iterator<Gossip> secrets(String name) {
        Iterator<Gossip> gossipIterator = this.gossipArray.iterator();
        Array<Gossip> targetGossip = new ArrayClass<>();
        while (gossipIterator.hasNext()) {
            Gossip gossip = gossipIterator.next();
            Person person = this.personArray.get(searchIndexPerson(name));
            if (gossip.hasTarget(person)) {
                targetGossip.insertLast(gossip);
            }
        }
        return targetGossip.iterator();
    }

    @Override
    public boolean hasSecrets(String name) {
        Iterator<Gossip> gossipIterator = secrets(name);
        return gossipIterator.hasNext();
    }

    @Override
    public Iterator<Gossip> hottest() {
        int sharedSize = 0;
        boolean flag = false;
        Array<Gossip> hottestArray = new ArrayClass<>();
        Iterator<Gossip> gossipIterator = this.sharedGossips.iterator();
        while (gossipIterator.hasNext()) {
            Gossip gossip1 = gossipIterator.next();
            if (gossip1.gossipIsKnowledgeBySomeone()) {
                sharedSize = gossip1.getShareNumber();
                flag = true;
                break;
            }
        }

        if(flag){
            gossipIterator.rewind();
            while (gossipIterator.hasNext()){
                Gossip gossip = gossipIterator.next();
                if(gossip.getShareNumber() == sharedSize){
                    hottestArray.insertLast(gossip);
                }
            }
        }
        return hottestArray.iterator();
    }

    @Override
    public Iterator<Gossip> knownGossips(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        return person.gossipIterator();
    }

    @Override
    public boolean isAnyOneInLandMark(String name) {
        Landmark landmark = this.landmarkArray.get(searchIndexLandmark(name));
        return landmark.getOccupation() > 0;
    }

    @Override
    public boolean hasGossipPerson(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        return !person.hasGossip();
    }

    @Override
    public Iterator<Group> groupIterator(String name) {
        Landmark landmark = this.landmarkArray.get(searchIndexLandmark(name));
        return landmark.groupIterator();
    }

    @Override
    public boolean sealedLipsHasOwnTarget(String name) {
        SealedLips person = (SealedLips) this.personArray.get(searchIndexPerson(name));
        return person.hasOwnTargets();
    }

    @Override
    public boolean isAnSealedLip(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        return person instanceof SealedLips;
    }

    @Override
    public int getCurrent(String name) {
        Person person = this.personArray.get(searchIndexPerson(name));
        return person.getCurrent();
    }

    @Override
    public Iterator<Gossip> GossipIterator(){
        return this.gossipArray.iterator();
    }

    public String toString() {
        String listLandmarks = "";
        Iterator<Landmark> itLand = landmarkIterator();
        Landmark land;
        while (itLand.hasNext()) {
            land = itLand.next();
            listLandmarks += land.getName() + ": " + land.getFullCapacity() + " " + land.getOccupation() + ".\n";
        }
        return listLandmarks;
    }

    //Auxiliary Methods

    /**
     * Gets the index of a specific landmark in array of landmarks.
     * @param name the landmark's name
     * @return the index of the landmark in array of landmarks
     */
    private int searchIndexLandmark(String name) {
        int counter = 0;
        Iterator<Landmark> landmarkIterator = this.landmarkArray.iterator();
        while (landmarkIterator.hasNext()) {
            Landmark landmark = landmarkIterator.next();
            if (landmark.getName().equals(name)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    /**
     * Gets the index of a specific persons in array of persons.
     * @param name the person's name
     * @return the index of the person in array of persons
     */
    private int searchIndexPerson(String name) {
        int counter = 0;
        Iterator<Person> personIterator = this.personArray.iterator();
        while (personIterator.hasNext()) {
            Person person = personIterator.next();
            if (person.getName().equals(name)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    /**
     * Check if the specific gossip is known by the person
     * @param gossip a gossip object
     * @param person a person object
     * @return return true if the person knows this gossip, otherwise return false
     */
    private boolean gossipExists(Gossip gossip, Person person) {
        return person.gossipExists(gossip);
    }

    /**
     * Update the array "sharedGossips" putting in sort most shared gossips to less shared gossips
     * @param gossip Inserting gossip to sharedGossips
     */
    private void updateSharedGossips(Gossip gossip) {
        int count = 0;
        Array<Gossip> tmp = new ArrayClass<>();
        this.sharedGossips.insertLast(gossip);
        Iterator<Gossip> gossipIterator1 = this.sharedGossips.iterator();
        Iterator<Gossip> gossipIterator2 = this.sharedGossips.iterator();
        while (gossipIterator1.hasNext()) {
            Gossip gossip1 = gossipIterator1.next();
            while (gossipIterator2.hasNext()) {
                Gossip gossip2 = gossipIterator2.next();
                if (gossip1.getShareNumber() > gossip2.getShareNumber()) {
                    tmp.insertAt(gossip1, count);
                    break;
                } else if (gossip1.getShareNumber() == 0) {
                    break;
                } else if ((gossip1.getShareNumber() == gossip2.getShareNumber() && !gossip1.equals(gossip2))
                        || (gossip1.equals(gossip2) && !gossipIterator2.hasNext())) {
                    tmp.insertLast(gossip1);
                }
                count++;
            }
        }

        this.sharedGossips = tmp;
    }

}
