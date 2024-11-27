package org.pneditor.petrinet.models.lachguer.souissi;

import java.util.ArrayList;

/**
 * Transition class representing a transition in the Petri net.
 * Manages the exiting (ExArc) and entering (EnArc) arcs and determines if the transition can fire.
 */
public class Transition {
    private ArrayList<ExArc> exArcs; // List of exiting arcs
    private ArrayList<EnArc> enArcs; // List of entering arcs

    /**
     * Constructor for creating a Transition with empty lists of arcs.
     */
    public Transition() {
        this.exArcs = new ArrayList<ExArc>();
        this.enArcs = new ArrayList<EnArc>();
    }

    /**
     * Checks if the transition is feasible, i.e., if all output arcs are active.
     * 
     * @return True if the transition can fire, otherwise false.
     */
    public boolean isFeasible() {
        for (ExArc arc : exArcs) {
            Place placeAssociee = arc.getPlace();
            int token = placeAssociee.getToken();
            if (!arc.isActive(token)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Executes the transition by firing all exiting and entering arcs, if feasible.
     */
    public void execute() {
        if (isFeasible()) {
            for (ExArc arc : exArcs) {
                arc.executeMove();
            }
            for (EnArc arc : enArcs) {
                arc.executeMove();
            }
        }
    }

    // Getters for the lists of exiting and entering arcs
    public ArrayList<ExArc> getExArcs() {
        return exArcs;
    }

    public ArrayList<EnArc> getEnArcs() {
        return enArcs;
    }

    // Methods to add arcs to the transition
    public void addExArc(ExArc arc) {
        this.exArcs.add(arc);
    }

    public void addEnArc(EnArc arc) {
        this.enArcs.add(arc);
    }

    /**
     * Empties the lists of arcs, removing all associations with places.
     */
    public void emptyArcsList() {
        this.exArcs = new ArrayList<ExArc>();
        this.enArcs = new ArrayList<EnArc>();
    }
}
