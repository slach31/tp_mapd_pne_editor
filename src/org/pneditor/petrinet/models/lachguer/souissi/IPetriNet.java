package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * Interface IPetriNet defines the methods that must be implemented by any Petri net class.
 * Provides methods for adding and removing places, transitions, and arcs, as well as running the Petri net.
 */
public interface IPetriNet {
    void addPlace(Place place);
    void addTransition(Transition transition);
    void addArc(Arc arc);
    void removePlace(Place place);
    void removeTransition(Transition transition);
    void removeArc(Arc arc);
    void run();
}
