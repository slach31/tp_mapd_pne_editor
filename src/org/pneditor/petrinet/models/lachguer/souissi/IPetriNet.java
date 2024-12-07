package org.pneditor.petrinet.models.lachguer.souissi;

/**
 * Interface IPetriNet defines the core methods for managing and executing a PetriNet model.
 * Provides methods for adding and removing places, transitions, and arcs, as well as running the PetriNet.
 */
public interface IPetriNet {
	
	
	
	/**
     * Adds a place to the PetriNet.
     *
     * @param place The place to be added.
     */
    void addPlace(Place place);
    
    
    /**
     * Adds a transition to the PetriNet.
     *
     * @param transition The transition to be added.
     */
    void addTransition(Transition transition);
    
    
    /**
     * Adds an arc to the PetriNet.
     *
     * @param arc The arc to be added, connecting a place and a transition.
     */
    void addArc(Arc arc);
    
    
    /**
     * Removes a place from the PetriNet.
     *
     * @param place The place to be removed.
     */
    void removePlace(Place place);
    
    
    /**
     * Removes a transition from the PetriNet.
     *
     * @param transition The transition to be removed.
     */
    void removeTransition(Transition transition);
    
    
    /**
     * Removes an arc from the PetriNet.
     *
     * @param arc The arc to be removed.
     */
    void removeArc(Arc arc);
    
    
    /**
     * Executes the PetriNet by triggering all the transitions and updating the state of places and arcs.
     */
    void run();
}
