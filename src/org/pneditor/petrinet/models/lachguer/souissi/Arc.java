package org.pneditor.petrinet.models.lachguer.souissi;

import java.util.ArrayList;

/**
 * Abstract class representing an Arc in a Petri net, connecting a Place to a Transition.
 */
public abstract class Arc {
    // Weight of the arc, which determines the number of tokens needed or transferred
    private int weight;
    
    // The Place associated with this arc
    private Place associatedPlace;
    
    // The Transition associated with this arc
    private Transition associatedTransition;

    /**
     * Constructor for creating an Arc with a specified weight, place, and transition.
     * 
     * @param weight The weight of the arc (must be strictly positive).
     * @param associatedPlace The place associated with this arc (cannot be null).
     * @param associatedTransition The transition associated with this arc (cannot be null).
     * @throws IllegalArgumentException If the weight is not positive or if either place or transition is null.
     */
    public Arc(int weight, Place associatedPlace, Transition associatedTransition) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight needs to be strictly positive");
        } else if ((associatedPlace == null) || (associatedTransition == null)) {
            throw new IllegalArgumentException("Transition and place cannot be null");
        } else {
            this.weight = weight;
            this.associatedPlace = associatedPlace;
            this.associatedTransition = associatedTransition;
        }
    }

    /**
     * Returns the weight of the arc.
     * 
     * @return The weight as an integer.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Changes the weight of the arc.
     * 
     */
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
    /**
     * Returns the associated Place of the arc.
     * 
     * @return The Place object linked to this arc.
     */
    public Place getPlace() {
        return this.associatedPlace;
    }

    /**
     * Returns the associated Transition of the arc.
     * 
     * @return The Transition object linked to this arc.
     */
    public Transition getTransition() {
        return this.associatedTransition;
    }

    /**
     * Abstract method to execute the move of tokens.
     * Subclasses must implement this to define specific behavior.
     */
    abstract void executeMove();
}
