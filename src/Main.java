//Imports

import dataStructures.Array;
import dataStructures.Iterator;
import gossip.Gossip;
import landmark.Landmark;
import people.Group;
import people.Person;
import surveillancesystem.SurveillanceSystem;
import surveillancesystem.SurveillanceSystemClass;

import java.util.Scanner;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 * It enables the relation between the user and the program, through the inputs received and the outputs which will
 * be displayed. The goal of this project is to develop an application that tracks the spreeading of gossips within a
 * community, which has people and landmarks. Landmarks are places where people meet. Inside each landmark, will exist
 * groups, and it is within these groups, with more than one person, that gossip spreads.
 * The commands, the description and the messages which will be displayed as output were created as constants.
 * This work was developed in the context of the subject "Object-Oriented Programming" as part of first project, during
 * the lective year of 2022/2023, from the University of Nova Lisboa, FCT.
 */

public class Main {

    /**
     * Commands and the explanation for each one.
     */

    private static final String LANDMARK = "landmark";
    private static final String LANDMARKS = "landmarks";
    private static final String FORGETFUL = "forgetful";
    private static final String GOSSIPER = "gossiper";
    private static final String SEALED = "sealed";
    private static final String PEOPLE = "people";
    private static final String GO = "go";
    private static final String JOIN = "join";
    private static final String GROUPS = "groups";
    private static final String ISOLATE = "isolate";
    private static final String START = "start";
    private static final String GOSSIP = "gossip";
    private static final String SECRETS = "secrets";
    private static final String INFOTAINMENT = "infotainment";
    private static final String HOTTEST = "hottest";
    private static final String HELP = "help";
    private static final String EXIT = "exit";
    private static final String HOME = "home";


    /**
     * Messages which will be printed as result of command that was inserted.
     */

    private static final String LANDMARKH = "landmark - adds a new landmark to the community\n";
    private static final String LANDMARKSH = "landmarks - displays the list of landmarks in the community\n";
    private static final String FORGETFULH = "forgetful - adds a forgetful person to the community\n";
    private static final String GOSSIPERH = "gossiper - adds a gossiper person to the community\n";
    private static final String SEALEDH = "sealed - adds a sealed lips person to the community\n";
    private static final String PEOPLEH = "people - lists all the persons in the community\n";
    private static final String GOH = "go - moves a person to a landmark, or home\n";
    private static final String JOINH = "join - joins a person to a group\n";
    private static final String GROUPSH = "groups - lists the groups composition in a landmark\n";
    private static final String ISOLATEH = "isolate - makes a person leave the current group, " +
            "but not the landmark the person is currently on\n";
    private static final String STARTH = "start - starts a new gossip\n";
    private static final String GOSSIPH = "gossip - share a gossip within the current group in the current landmark\n";
    private static final String SECRETSH = "secrets - lists the gossip about a particular person\n";
    private static final String INFOTAINMENTH = "infotainment - lists the gossips a particular person is aware of\n";
    private static final String HOTTESTH = "hottest - lists the most shared gossip\n";
    private static final String HELPH = "help - shows the available commands\n";
    private static final String EXITH = "exit - terminates the execution of the program\n";
    private static final String EXITING = "Bye!";
    private static final String ERROR = "Unknown command. Type help to see available commands.\n";
    private static final String LANDMARK_SUCCESS = "%s added.\n";
    private static final String LANDMARK_INVALID = "Invalid landmark capacity %d!\n";
    private static final String LANDMARK_HOME = "Cannot create a landmark called home. " +
            "You know, there is no place like home!\n";
    private static final String LANDMARK_ALREADY_EXISTS = "Landmark %s already exists!\n";
    private static final String ALREADY_EXISTS = "%s already exists!\n";
    private static final String THIS_DOESNT_EXIST = "This community does not have any %s yet!\n";
    private static final String FORGETFUL_SUCCESS = "%s can only remember up to %d gossips.\n";
    private static final String FORGETFUL_INVALID = "Invalid gossips capacity %d!\n";
    private static final String GOSSIPER_SUCCESS = "%s is a gossiper.\n";
    private static final String SEALED_SUCCESS = "%s lips are sealed.\n";
    private static final String GO_SUCCESS = "%s is now at %s.\n";
    private static final String DOESNT_EXIST = "%s does not exist!\n";
    private static final String GO_UNKNOWN_PLACE = "Unknown place %s\n";
    private static final String GO_ALREADY_EXIST = "What do you mean go to %s? %s is already there!\n";
    private static final String GO_FULL = "%s is too crowded! %s went home.\n";
    private static final String JOIN_SUCCESS = "%s joined %s, at the %s.\n";
    private static final String JOIN_SAME_NAME = "%s needs company from someone else!\n";
    private static final String HOME_MESSAGE = "%s is at home!\n";
    private static final String JOIN_SAME_GROUP = "%s and %s are already in the same group!\n";
    private static final String JOIN_NOT_SAME_LANDMARK = "%s is not in %s!\n";
    private static final String GROUPS_SUCCESS = "%d groups at %s:\n";
    private static final String GROUPS_HOME = "You must understand we have no surveillance tech at home! " +
            "Privacy is our top concern!\n";
    private static final String GROUPS_NOBODY = "Nobody is at %s!\n";
    private static final String ISOLATE_SUCCESS = "%s is now alone at %s.\n";
    private static final String ISOLATE_ALREADY_ALONE = "%s is already alone!\n";
    private static final String START_INVALID_NUMBER = "Invalid number %d of gossip targets!\n";
    private static final String START_GOSSIP_DUPLICATED = "Duplicate gossip!\n";
    private static final String START_SUCCESS = "Have you heard about %s? %s\n";
    private static final String GOSSIP_ALONE = "%s has nobody to gossip with right now!\n";
    private static final String GOSSIP_KNOWS_NOTHING = "%s knows nothing!\n";
    private static final String GOSSIP_WITHOUT_WILL = "%s does not wish to gossip right now!\n";
    private static final String GOSSIP_SUCCESS = "%s shared with %ssome hot news!\n";
    private static final String SECRETS_SUCCESS = "Here is what we know about %s:\n";
    private static final String SECRETS_NO_SECRETS = "%s lives a very boring life!\n";
    private static final String INFOTAINMENT_SUCCESS = "%s knows things:\n";
    private static final String INFOTAINMENT_NOTHING = "%s knows nothing!\n";
    private static final String HOTTEST_SUCCESS = "The hottest gossips were shared %d times!\n";
    private static final String HOTTEST_NOTHING = "No gossips we are aware of!\n";
    private static final String HOTTEST_UNSHARED = "No gossips were shared, yet!\n";


    /**
     * Main method which calls the commandScanner method, and through this
     * receives the commands and execute the messages
     * @param args arguments of main
     */
    public static void main(String[] args) {
        Main.commandScanner();
    }

    /**
     * It creates a SurveillanceSystem object and a Scanner.Using the scanner, executes the commands given,
     * by receiving the information from the user and in commandsCenter method. In this method, it
     * will select the constant with the command. The program wil be running until receiving the EXIT command,
     * which allows the user to terminate the execution of the program.
     */
    private static void commandScanner() {
        SurveillanceSystem land = new SurveillanceSystemClass();
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.next().trim().toLowerCase();
            commandsCenter(land, scanner, input);
        } while (!input.equals(EXIT));
        scanner.close();
    }

    /**
     * This method receives the input given by the user, as well the SurveillanceSystem object and the Scanner.
     * Here will be selected command that the user wanted using a switch and constant for each case.
     * In case the input don't match with the command that program allows, it will display the error message.
     * @param system object which connects the Main with the SurveillanceSystem class
     *               from the package surveillancesystem
     * @param scanner scans the input given from the System.in
     * @param command string with the input given, which allows user to have access to one of the commands
     *               of the program.
     */
    private static void commandsCenter(SurveillanceSystem system, Scanner scanner, String command) {
        switch (command) {
            case LANDMARK -> createLandmark(scanner,system);
            case LANDMARKS -> showLandmarks(system);
            case FORGETFUL -> addForgetfulPerson(scanner,system);
            case GOSSIPER -> addGossiperPerson(scanner,system);
            case SEALED -> addSealedPerson(scanner,system);
            case PEOPLE -> showPersons(system);
            case GO -> movePerson(scanner,system);
            case JOIN -> joinGroup(scanner,system);
            case GROUPS -> listGroups(scanner,system);
            case ISOLATE -> isolatePerson(scanner,system);
            case START -> startGossip(scanner,system);
            case GOSSIP -> shareGossip(scanner,system);
            case SECRETS -> showSecrets(scanner,system);
            case INFOTAINMENT -> infotainmentList(scanner,system);
            case HOTTEST -> hottestList(system);
            case HELP -> listAllCommandsHelp();
            case EXIT -> System.out.println(EXITING);
            default -> System.out.print(ERROR);
        }
    }

    /**
     * Prints a list of all commands that exist in the program and a description of their functionalities,
     * using the constants that were created to display the messages.
     */
    private static void listAllCommandsHelp() {
        System.out.print(LANDMARKH + LANDMARKSH + FORGETFULH + GOSSIPERH + SEALEDH + PEOPLEH + GOH + JOINH + GROUPSH
                + ISOLATEH + STARTH + GOSSIPH + SECRETSH + INFOTAINMENTH + HOTTESTH + HELPH + EXITH);
    }

    /**
     * It creates a new landmark to the community using the information given by the user.
     * The input given will be the name of the landmark and the capacity (number of the people the
     * landmark is capable of receiving). In case the command execution is successful
     * it will print a success message (LANDMARK_SUCCESS).
     * This method fails if:
     * - the capacity is less or equal to 0 (LANDMARK_INVALID)
     * - if the landmark name is "home" (LANDMARK_HOME)
     * - the landmark name already exists in the community (LANDMARK_ALREADY_EXISTS).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void createLandmark(Scanner input, SurveillanceSystem system){
        int capacity = Integer.parseInt(input.next());
        String name = input.nextLine().trim();

        if(capacity <= 0){
            System.out.printf(LANDMARK_INVALID,capacity);
        } else if (name.equals(HOME)){
            System.out.print(LANDMARK_HOME);
        }  else if (system.landmarkExists(name)){
            System.out.printf(LANDMARK_ALREADY_EXISTS,name);
        } else {
            system.addLandmark(capacity,name);
            System.out.printf(LANDMARK_SUCCESS,name);
        }
    }

    /**
     * Shows a list of all landmarks which exist in the community.
     * This method never fails, but in case there's not any landmark in community it will
     * print an error message (THIS_DOESNT_EXIST).
     *
     * @param system a SurveillanceSystem object
     */
    private static void showLandmarks(SurveillanceSystem system){
        Iterator<Landmark> landmarkIterator = system.landmarkIterator();

        if (!landmarkIterator.hasNext()){
            System.out.printf(THIS_DOESNT_EXIST,LANDMARKS.toLowerCase());
        } else {
            System.out.print(system);
        }
    }

    /**
     * Creates a new forgetful person and will be added to the community. It receives as arguments the number
     * of gossips that this person can remember and the name of the person. In case the command execution is successful
     * it will print a success message (FORGETFUL_SUCCESS).
     * This method fails if:
     * - number of gossips which forgetful person can remember is less or equal to 0 (FORGETFUL_INVALID)
     * - the forgetful person's name already exists in the community (ALREADY_EXISTS).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void addForgetfulPerson (Scanner input, SurveillanceSystem system){
        int capacity = Integer.parseInt(input.next());
        String name = input.nextLine().trim();

        if (capacity<=0){
            System.out.printf(FORGETFUL_INVALID,capacity);
        } else if (system.personExists(name)){
            System.out.printf(ALREADY_EXISTS,name);
        } else {
            system.addForgetful(capacity,name);
            System.out.printf(FORGETFUL_SUCCESS,name,capacity);
        }
    }

    /**
     * Creates a new gossiper person and will be added to the community. It receives as arguments the name of the person
     * In case the command execution is successful it will print a success message (GOSSIPER_SUCCESS).
     * This method fails if:
     * - the gossiper's name already exists in the community (ALREADY_EXISTS).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void addGossiperPerson (Scanner input, SurveillanceSystem system){
        String name = input.nextLine().trim();

        if (system.personExists(name)){
            System.out.printf(ALREADY_EXISTS,name);
        } else {
            system.addGossiper(name);
            System.out.printf(GOSSIPER_SUCCESS,name);
        }
    }

    /**
     * Creates a new sealed lips person and will be added to the community. It receives as arguments the name of the
     * person. In case the command execution is successful it will print a success message (SEALED_SUCCESS).
     * This method fails if:
     * - the sealed lips person's name already exists in the community (ALREADY_EXISTS).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void addSealedPerson (Scanner input, SurveillanceSystem system){
        String name = input.nextLine().trim();

        if (system.personExists(name)){
            System.out.printf(ALREADY_EXISTS,name);
        } else {
            system.addSealed(name);
            System.out.printf(SEALED_SUCCESS,name);
        }
    }

    /**
     * Print a list of all persons in the community. This method always succeeds and will print all persons
     * with the following information with this order: person’s name, current location and count of known gossips.
     * In case there's not any person in the community will print an error message (THIS_DOESNT_EXIST).
     *
     * @param system a SurveillanceSystem object
     */
    private static void showPersons (SurveillanceSystem system){
        Iterator<Person> personIterator = system.allPeopleIt();

        if (!personIterator.hasNext()){
            System.out.printf(THIS_DOESNT_EXIST,PEOPLE.toLowerCase());
        } else {
            while (personIterator.hasNext()){
                System.out.print(personIterator.next());
            }
        }
    }

    /**
     * This method moves a person to a landmark or to home. It receives as arguments the person's name and the place
     * where the person is moving to. Each time a person is moved to another landmark, he will be moved to a new group
     * where this person will be alone. In case the command execution is successful it will print
     * a success message (GO_SUCCESS).
     * This method fails if:
     * - the person's name doesn't exist (DOESNT_EXIST)
     * - the landmark given by the user doesn't exist (GO_UNKNOWN_PLACE)
     * - the person is already in the landmark which was supposed to move in (GO_ALREADY_EXIST)
     * - the landmark has reached its maximum capacity so cannot receive another person (GO_FULL).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void movePerson (Scanner input, SurveillanceSystem system){
        String personName = input.nextLine().trim();
        String landmarkName = input.nextLine().trim();

        if (!system.personExists(personName)){
            System.out.printf(DOESNT_EXIST,personName);
        } else if (!system.landmarkExists(landmarkName)){
            System.out.printf(GO_UNKNOWN_PLACE,landmarkName);
        } else if (system.alreadyInLandmark(personName,landmarkName)){
            System.out.printf(GO_ALREADY_EXIST,landmarkName,personName);
        } else if (system.landmarkCrowded(landmarkName)){
            System.out.printf(GO_FULL,landmarkName,personName);
            system.goToHome(personName);
        } else {
            system.goToLandmark(personName,landmarkName);
            System.out.printf(GO_SUCCESS,personName,landmarkName);
        }
    }

    /**
     * Show a list of the groups that exists in specific landmark. It receives as argument the landmark.
     * In case the command execution is successful it will print a success message (GROUPS_SUCCESS) followed by
     * all the groups in that landmark in order of creation.
     * This method fails if:
     * - it's used to check the groups at home (GROUPS_HOME)
     * - the landmark does not exist (DOESNT_EXIST)
     * - nobody is in the landmark (GROUPS_NOBODY).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void listGroups(Scanner input, SurveillanceSystem system){
        String landmarkName = input.nextLine().trim();

        if (landmarkName.equals(HOME)){
            System.out.print(GROUPS_HOME);
        } else if (!system.landmarkExists(landmarkName)) {
            System.out.printf(DOESNT_EXIST,landmarkName);
        } else if (!system.isAnyOneInLandMark(landmarkName)){
            System.out.printf(GROUPS_NOBODY,landmarkName);
        } else {
            Iterator<Landmark> landmarkIterator = system.landmarkIterator();
            while(landmarkIterator.hasNext()){
                Landmark landmark = landmarkIterator.next();
                if(landmark.getName().equals(landmarkName)){
                    System.out.printf(GROUPS_SUCCESS,landmark.numberOfGroups(),landmarkName);
                    System.out.printf(String.valueOf(landmark));
                }
            }
        }
    }

    /**
     * This method allows a person to join a another group. It receives as input the person's name and the name of
     * another person but in the same landmark. Then the first person will leave the group where he was and joins
     * the group of the second person. In case the command execution is successful it will print
     * a success message (JOIN_SUCCESS).
     * This method fails if:
     * - first person's name and second person's name are the same (JOIN_SAME_NAME)
     * - the first person’s name does not exist in the community (DOESNT_EXIST)
     * - the second person’s name does not exist in the community (DOESNT_EXIST)
     * - the first person is at home rather than in a landmark (HOME_MESSAGE)
     * - the second person is not in the same landmark (JOIN_NOT_SAME_LANDMARK)
     * - the first person and the second person are already in the same group (JOIN_SAME_GROUP)
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void joinGroup (Scanner input, SurveillanceSystem system){
        String firstPersonName = input.nextLine().trim();
        String secondPersonName = input.nextLine().trim();

        if (firstPersonName.equals(secondPersonName)){
            System.out.printf(JOIN_SAME_NAME,firstPersonName);
        } else if (!system.personExists(firstPersonName)){
            System.out.printf(DOESNT_EXIST,firstPersonName);
        } else if (!system.personExists(secondPersonName)){
            System.out.printf(DOESNT_EXIST,secondPersonName);
        } else if (system.personAtHome(firstPersonName)){
            System.out.printf(HOME_MESSAGE,firstPersonName);
        } else if (!system.bothInSameLandmark(firstPersonName,secondPersonName)) {
            String landmark = system.getLandmarkName(firstPersonName);
            System.out.printf(JOIN_NOT_SAME_LANDMARK,secondPersonName,landmark);
        } else if (system.alreadySameGroup(firstPersonName,secondPersonName)){
            System.out.printf(JOIN_SAME_GROUP,firstPersonName,secondPersonName);
        } else {
            String landmark = system.getLandmarkName(firstPersonName);
            Group group = system.getGroup(secondPersonName,landmark);
            String prevGroup = group.toString();
            system.joinGroup(firstPersonName,secondPersonName);
            System.out.printf(JOIN_SUCCESS,firstPersonName,prevGroup,landmark);
        }
    }

    /**
     * This method makes the person leave the current group, but not the landmark the person is currently on. So it
     * creates a new group only for this person where he will be alone.
     * It receives as arguments the person's name. In case the command execution is successful it will print
     * a success message (ISOLATE_SUCCESS).
     * This method fails if:
     * - the person’s name does not exist in the community (DOESNT_EXIST)
     * - the person is at home (HOME_MESSAGE)
     * - he person is already alone (ISOLATE_ALREADY_ALONE).
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void isolatePerson (Scanner input, SurveillanceSystem system){
        String personName = input.nextLine().trim();

        if (!system.personExists(personName)){
            System.out.printf(DOESNT_EXIST,personName);
        } else if (system.personAtHome(personName)) {
            System.out.printf(HOME_MESSAGE,personName);
        } else if (system.isAlone(personName)) {
            System.out.printf(ISOLATE_ALREADY_ALONE,personName);
        } else {
            system.isolate(personName);
            String landmark = system.getLandmarkName(personName);
            System.out.printf(ISOLATE_SUCCESS,personName,landmark);
        }
    }

    /**
     * Starts a new gossip using the information given as input like gossip creator's name,
     * the number of people who are targets of that gossip, the names of those people, and
     * the gossip itself. In case the command execution is successful it will print
     * a success message (START_SUCCESS).
     * This method fails if:
     * - the person’s name does not exist in the community (DOESNT_EXIST)
     * - the number of gossip targets is less or equal to 0 (START_INVALID_NUMBER)
     * - any of the targets of the gossip does not exist (DOESNT_EXIST)
     * - there is already a similar gossip, with the same creator, involved people,
     *   and description (START_GOSSIP_DUPLICATED)
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void startGossip (Scanner input, SurveillanceSystem system){
        String author = input.nextLine().trim();
        int numberTargets = Integer.parseInt(input.nextLine());

        if (numberTargets < 1) {
            input.nextLine();
            System.out.printf(START_INVALID_NUMBER,numberTargets);
        } else {
            String[] targetsName = new String[numberTargets];
            String nameDoesntExist = "";
            boolean allPersonsExist = true;

            for (int i = 0; i < numberTargets ; i++) {
                    String target = input.nextLine().trim();
                    targetsName[i] = target;
            }

            String gossipDescription = input.nextLine().trim();
            int length = targetsName.length;

            for (int i = 0; i < length; i++) {
                if (!system.personExists(targetsName[i])){
                    nameDoesntExist += targetsName[i];
                    allPersonsExist = false;
                    break;
                }
            }

            if (!system.personExists(author)){
                System.out.printf(DOESNT_EXIST,author);
            } else if (!allPersonsExist) {
                System.out.printf(DOESNT_EXIST,nameDoesntExist);
            } else if (system.sameGossip(author,targetsName,numberTargets,gossipDescription)){
                System.out.print(START_GOSSIP_DUPLICATED);
            } else {
                system.startGossip(author,numberTargets,targetsName,gossipDescription);
                System.out.printf(START_SUCCESS,stringConcat(targetsName),gossipDescription);
            }
        }
    }

    /**
     * Print a list of gossips a particular person is aware of. It receives as argument
     * the person's name. In case the command execution is successful it
     * will print a success message (INFOTAINMENT_SUCCESS) followed by all gossips that person knows.
     * This method fails if:
     * - the person’s name does not exist in the community (DOESNT_EXIST)
     * - the person knows nothing (INFOTAINMENT_NOTHING)
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void infotainmentList(Scanner input, SurveillanceSystem system){
        String personName = input.nextLine().trim();
        String info = "";

        if (!system.personExists(personName)){
            System.out.printf(DOESNT_EXIST,personName);
        } else if (system.hasGossipPerson(personName)) {
            System.out.printf(INFOTAINMENT_NOTHING,personName);
        } else {
            boolean flag = true;
            Iterator<Gossip> gossipIt = system.knownGossips(personName);
            int current = system.getCurrent(personName);
            int count = current;
            if (current == 0 || !gossipIt.hasNext()){
                flag = false;
            }
            if (!gossipIt.hasNext()){
                gossipIt.rewind();
            }
            while (gossipIt.hasNext()){
                Gossip gossipTemp = gossipIt.next();
                info +=  gossipTemp.getDescription() + "\n";
                if (count+1 == current) {
                    break;
                }
                else if(!gossipIt.hasNext() && flag){
                    gossipIt.rewind();
                    flag = false;
                    count = 0;
                    continue;
                }
                count++;
            }
            System.out.printf(INFOTAINMENT_SUCCESS,personName);
            System.out.print(info);
        }
    }

    /**
     * Print a list of gossips about a particular person. It receives as argument the person's name.
     * In case the command execution is successful it will print a success message (SECRETS_SUCCESS) followed
     * by all existing gossips about that person.
     * This method fails if:
     * -the person’s name does not exist in the community (DOESNT_EXIST)
     * -the person has no secrets (SECRETS_NO_SECRETS)
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void showSecrets (Scanner input, SurveillanceSystem system){
        String personName = input.nextLine().trim();
        String info = "";

        if (!system.personExists(personName)){
            System.out.printf(DOESNT_EXIST,personName);
        } else if (!system.hasSecrets(personName)) {
            System.out.printf(SECRETS_NO_SECRETS,personName);
        } else {
            Iterator<Gossip> secretIt = system.secrets(personName);
            while (secretIt.hasNext()){
                Gossip gossipTemp = secretIt.next();
                if (gossipTemp.getNumberWhoKnowsGossip()==0){
                    continue;
                }
                info += gossipTemp.getNumberWhoKnowsGossip() + " " + gossipTemp.getDescription() + "\n";
            }
            System.out.printf(SECRETS_SUCCESS,personName);
            System.out.print(info);
        }
    }

    /**
     * It shares a gossip within the current group in the current landmark. It will receive
     * as argument the person's name. This method will also make sure that all features of each
     * kind of person to share gossips is respected. In case the command execution is successful it will print
     * a success message (GOSSIP_SUCCESS) followed by the gossip/gossips which were shared.
     * This method fails if:
     * - the person’s name does not exist in the community (DOESNT_EXIST)
     * - the person is alone, either in a landmark, or at home (GOSSIP_ALONE)
     * - the person has no gossips to share (GOSSIP_SUCCESS)
     * - some people might be aware of gossips, but not willing to share them because are sealed persons
     *   and they only share gossips about themselves (GOSSIP_WITHOUT_WILL)
     *
     * @param input the input given by the user
     * @param system a SurveillanceSystem object
     */
    private static void shareGossip (Scanner input, SurveillanceSystem system){
        String personName = input.nextLine().trim();

        if (!system.personExists(personName)){
            System.out.printf(DOESNT_EXIST,personName);
        } else if (system.personAtHome(personName) || system.isAlone(personName)){
            System.out.printf(GOSSIP_ALONE,personName);
        } else if (system.hasGossipPerson(personName)) {
            System.out.printf(GOSSIP_KNOWS_NOTHING,personName);
        } else if (system.isAnSealedLip(personName) && !system.sealedLipsHasOwnTarget(personName)){
            System.out.printf(GOSSIP_WITHOUT_WILL,personName);
        } else {
            Array<Gossip> gossipShared = system.shareGossip(personName);
            String info = "";

            for (int i = 0; i < gossipShared.size(); i++) {
                info += gossipShared.get(i).getDescription() + "\n";
            }

            String landmark = system.getLandmarkName(personName);
            String groupArray = system.getGroup(personName,landmark).toString();
            String finalGroup = modifierStringGroup(groupArray, personName);
            System.out.printf(GOSSIP_SUCCESS,personName,finalGroup);
            System.out.print(info);
        }
    }

    /**
     * Auxiliary method that enables to put a string in a specific format,
     * using as arguments the group and the name.
     *
     * @param group group of people in a specific landmark
     * @param name name of the person who is sharing the gossip
     * @return A string in a specific format
     */
    private static String modifierStringGroup(String group, String name){
        int i = 0;
        String[] splitGroup = group.split(", ");
        String[] finalGroup = new String[splitGroup.length];
        String finalString = "";
        for (int count = 0; count < splitGroup.length; count++){
            if(!name.equals(splitGroup[count])){
                finalGroup[i] = splitGroup[count];
                i++;
            }
        }

        for(int count = 0; count < splitGroup.length; count++){
            if(count+1 < splitGroup.length && splitGroup[count+1] == null){
                finalString += finalGroup[count] + ",";
                break;
            }
            else if (count+1 < splitGroup.length && splitGroup[count+1] != null){
                finalString += finalGroup[count] + ", ";
            }
        }
        return finalString;
    }

    /**
     * This function displays the most shared gossips by the community.
     * In case the command execution is successful it will print
     * a success message (HOTTEST_SUCCESS) followed by the gossip sharing number among people in the community
     * and the description of the most shared gossip(s).
     * This method fails if:
     * - there is no gossip known to the community (HOTTEST_NOTHING)
     * - there is no gossip that has been shared by the community (HOTTEST_UNSHARED)
     * @param system a SurveillanceSystem object
     */
    private static void hottestList(SurveillanceSystem system){
        Iterator<Gossip> gossipAll = system.GossipIterator();
        Iterator<Gossip> hottestGossip = system.hottest();
        String info = "";
        int numberShared = 0;

        if (!gossipAll.hasNext()){
            System.out.printf(HOTTEST_NOTHING);
        }else if (!hottestGossip.hasNext()) {
            System.out.printf(HOTTEST_UNSHARED);
        } else {
            while (hottestGossip.hasNext()){
                Gossip gossip = hottestGossip.next();
                numberShared = gossip.getShareNumber();
                info += gossip.getDescription() + "\n";
            }

            System.out.printf(HOTTEST_SUCCESS,numberShared);
            System.out.print(info);
        }
    }

    /**
     * Put the string in a specific format.
     *
     * @param names The person's names
     * @return The string in a specific format
     */
    private static String stringConcat(String[] names) {
        String info = "";
        for (int i = 0; i < names.length; i++) {

            info += names[i];

            if (i != names.length - 1) {
                info += ", ";
            }
        }

        return info;
    }
}
