package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * ExArc (Exiting Arc) class extending Arc.
 * Represents an arc that removes tokens from a place during a transition.
 */
public class ExArc extends Arc {

    /**
     * Constructor for creating an ExArc with a given weight, associated place, and transition.
     * 
     * @param weight The weight of the arc (must be positive).
     * @param associatedPlace The place connected to this arc.
     * @param associatedTransition The transition connected to this arc.
     */
    public ExArc(int weight, Place associatedPlace, Transition associatedTransition) {
        super(weight, associatedPlace, associatedTransition);
    }

    /**
     * Checks if the arc is active, i.e., if the number of tokens in the place is
     * greater than or equal to the weight of the arc.
     * 
     * @param token The number of tokens in the place.
     * @return True if the place has enough tokens, otherwise false.
     */
    public boolean isActive(int token) {
        return token >= this.getWeight();
    }

    /**
     * Executes the move by removing tokens from the associated place.
     */
    public void executeMove() {
        Place p = this.getPlace();
        int weight = this.getWeight();
        p.removeToken(weight);
    }
}
