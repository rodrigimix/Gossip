package people;

/**
 * @author Rodrigo Marques n_64411 && David Pinto n_64609
 *
 * The interface of SealedLipsClass class.
 * It contains method that is unique to this type of person.
 */
public interface SealedLips {

    /**
     * Checks if the person has gossips about himself.
     * @return return true if person has gossips about himself, otherwise returns false
     */
    boolean hasOwnTargets();
}
