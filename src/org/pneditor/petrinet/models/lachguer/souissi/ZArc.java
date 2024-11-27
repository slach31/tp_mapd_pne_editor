package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * ZArc class extending ExArc.
 * Represents a special output arc that is only active when the associated place has zero tokens.
 */
public class ZArc extends ExArc {

    /**
     * Constructor for creating a ZArc with a given weight, associated place, and transition.
     * 
     * @param weight The weight of the arc (is infinite).
     * @param associatedPlace The place connected to this arc.
     * @param associatedTransition The transition connected to this arc.
     */
	
	private static final int weight = Integer.MAX_VALUE;
    public ZArc(Place associatedPlace, Transition associatedTransition) {
        super(weight, associatedPlace, associatedTransition);
    }

    @Override
    public boolean isActive(int token) {
        return token == 0;
    }
}
