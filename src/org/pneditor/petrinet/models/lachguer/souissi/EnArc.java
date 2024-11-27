package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * EnArc (Entering Arc) class extending Arc.
 * Represents an arc that adds tokens to a place during a transition.
 */
public class EnArc extends Arc {

    /**
     * Constructor for creating an EnArc with a given weight, associated place, and transition.
     * 
     * @param weight The weight of the arc (must be positive).
     * @param associatedPlace The place connected to this arc.
     * @param associatedTransition The transition connected to this arc.
     */
    public EnArc(int weight, Place associatedPlace, Transition associatedTransition) {
        super(weight, associatedPlace, associatedTransition);
    }

    /**
     * Executes the move by adding tokens to the associated place.
     */
    public void executeMove() {
        Place p = this.getPlace();
        int weight = this.getWeight();
        p.addToken(weight);
    }
}
