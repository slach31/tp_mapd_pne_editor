package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * EmArc (Empty Arc) class extending ExArc.
 * Represents an arc that empties a place during a transition.
 */
public class EmArc extends ExArc {

    /**
     * Constructor for creating an EmArc with a given weight, associated place, and transition.
     * 
     * @param weight The weight of the arc (must be positive).
     * @param associatedPlace The place connected to this arc.
     * @param associatedTransition The transition connected to this arc.
     */
    public EmArc(int weight, Place associatedPlace, Transition associatedTransition) {
        super(weight, associatedPlace, associatedTransition);
        
    }

    /**
     * Empties the associated place by removing all tokens.
     */
    @Override
    public void executeMove() {
        getPlace().emptyPlace();
    }

}
