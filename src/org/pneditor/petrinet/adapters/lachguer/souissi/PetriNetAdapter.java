package org.pneditor.petrinet.adapters.lachguer.souissi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.adapters.lachguer.souissi.*;
import org.pneditor.petrinet.models.lachguer.souissi.Arc;
import org.pneditor.petrinet.models.lachguer.souissi.PetriNet;
import org.pneditor.petrinet.models.lachguer.souissi.Place;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;


/**
 * Adapter class to link the `PetriNetInterface` from PNEditor
 * with the `PetriNet` class from the custom PetriNet implementation.
 */
public class PetriNetAdapter extends PetriNetInterface {
	
	private PetriNet petri;
	private List<ArcAdapter> arcAdaptersList; // List of arc adapters
	private List<PlaceAdapter> placeAdaptersList; // List of place adapters
	private List<TransitionAdapter> transitionAdaptersList; // List of transition adapters
	
	
	/**
     * Constructor for `PetriNetAdapter`.
     * Initializes an empty PetriNet and associated adapter lists.
     */
	public PetriNetAdapter() {
		this.petri = new PetriNet(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		this.arcAdaptersList = new ArrayList<>();
		this.placeAdaptersList = new ArrayList<>();
		this.transitionAdaptersList = new ArrayList<>();
	}
	
	
	/**
     * Adds a new place to the PetriNet.
     * 
     * @return The abstract representation of the newly created place.
     */
	@Override
	public AbstractPlace addPlace() {
		// Create a new PlaceAdapter
		PlaceAdapter adapter = new PlaceAdapter("place");
		Place place = adapter.getActualPlace();
		
		// Add the place to the underlying PetriNet and the adapters list
		this.petri.addPlace(place);
		this.placeAdaptersList.add(adapter);
		return adapter;
	}

	
	/**
     * Adds a new transition to the PetriNet.
     * 
     * @return The abstract representation of the newly created transition.
     */
	@Override
	public AbstractTransition addTransition() {
		// Create a new TransitionAdapter
		TransitionAdapter adapter = new TransitionAdapter("transition");
		Transition transition = adapter.getActualTransition();
		// Add the transition to the underlying PetriNet and the adapters list
		this.petri.addTransition(transition);
		this.transitionAdaptersList.add(adapter);
		return adapter;
	}
	
	

	/**
     * Adds a new regular arc  to the PetriNet.
     * 
     * @return The abstract representation of the newly created Regular Arc.
     */
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		ArcAdapter adapter = new ArcAdapter(source, destination);
		Arc arc = adapter.getActualArc();
		this.petri.addArc(arc);
		this.arcAdaptersList.add(adapter);
		return adapter;
	}
	
	/**
     * Adds a new inhibitory arc  to the PetriNet.
     * 
     * @return The abstract representation of the newly created Inhibitory Arc.
     */
	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
	        throws UnimplementedCaseException {
	    ArcAdapter matchingAdapter = null;
	    for (ArcAdapter adapter : this.arcAdaptersList) {
	        AbstractNode source = adapter.getSource();
	        AbstractNode destination = adapter.getDestination();
	        if ((source instanceof PlaceAdapter) && (destination instanceof TransitionAdapter)) {
	            if (source.equals(place) && destination.equals(transition)) {
	                matchingAdapter = adapter;
	                break;
	            }
	        }
	    }
	    if (matchingAdapter != null) {
	        // Convert existing arc to inhibitory
	        Arc oldArc = matchingAdapter.getActualArc();
	        Arc newArc = matchingAdapter.makeIntoInhibitoryArc();
	        // Remove old arc from Petri net
	        petri.removeArc(oldArc);
	        // Add new inhibitory arc
	        petri.addArc(newArc);
	        return matchingAdapter;
	    } else {
	        // Create a new inhibitory arc
	        ArcAdapter newAdapter = new ArcAdapter(place, transition);
	        newAdapter.makeIntoInhibitoryArc();
	        petri.addArc(newAdapter.getActualArc());
	        arcAdaptersList.add(newAdapter);
	        return newAdapter;
	    }
	}
	
	/**
     * Adds a new reset arc  to the PetriNet.
     * 
     * @return The abstract representation of the newly created Reset Arc.
     */
	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		ArcAdapter matchingAdapter = null;
	    for (ArcAdapter adapter : this.arcAdaptersList) {
	        AbstractNode source = adapter.getSource();
	        AbstractNode destination = adapter.getDestination();
	        if ((source instanceof PlaceAdapter) && (destination instanceof TransitionAdapter)) {
	            if (source.equals(place) && destination.equals(transition)) {
	                matchingAdapter = adapter;
	                break;
	            }
	        }
	    }
	    if (matchingAdapter != null) {
	        // Convert existing arc to reset
	        Arc oldArc = matchingAdapter.getActualArc();
	        Arc newArc = matchingAdapter.makeIntoResetArc();
	        // Remove old arc from Petri net
	        petri.removeArc(oldArc);
	        // Add new reset arc
	        petri.addArc(newArc);
	        return matchingAdapter;
	    } else {
	        // Create a new reset arc
	        ArcAdapter newAdapter = new ArcAdapter(place, transition);
	        newAdapter.makeIntoResetArc();
	        petri.addArc(newAdapter.getActualArc());
	        arcAdaptersList.add(newAdapter);
	        return newAdapter;
	    }
	}
	
	
	/**
     * Removes a place from the PetriNet.
     * 
     * @param place The place to remove.
     */
	@Override
	public void removePlace(AbstractPlace place) {
		// Cast the AbstractPlace to a PlaceAdapter
		PlaceAdapter placeAdapter = (PlaceAdapter) place;
        this.petri.removePlace(placeAdapter.getActualPlace());
        this.placeAdaptersList.remove(placeAdapter);
        
	}
	

	/**
     * Removes a transition from the PetriNet.
     * 
     * @param transition The transition to remove.
     */
	@Override
	public void removeTransition(AbstractTransition transition) {
		// Cast the AbstractTransition to a TransitionAdapter
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
		// Remove the transition from the underlying PetriNet and the adapters list
        this.petri.removeTransition(transitionAdapter.getActualTransition());
        this.transitionAdaptersList.remove(transitionAdapter);
	}

	/**
     * Removes an arc from the PetriNet.
     * 
     * @param arc The arc to remove.
     */
	@Override
	public void removeArc(AbstractArc arc) {
		ArcAdapter arcAdapter = (ArcAdapter) arc;
        this.petri.removeArc(arcAdapter.getActualArc());
        this.arcAdaptersList.remove(arcAdapter);
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
	     if (!(transition instanceof TransitionAdapter)) {
	        throw new IllegalArgumentException("Invalid transition type.");
	    }
	    TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
	    for (TransitionAdapter registeredTransition : this.transitionAdaptersList) {
	        if (registeredTransition.equals(transitionAdapter)) {
	            Transition t = registeredTransition.getActualTransition();
	            return t.isFeasible();
	        }
	    }
	    return false; 
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
	    if (!(transition instanceof TransitionAdapter)) {
	        throw new IllegalArgumentException("Invalid transition type.");
	    }
	    TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
	    for (TransitionAdapter registeredTransition : this.transitionAdaptersList) {
	        if (registeredTransition.equals(transitionAdapter)) {
	            Transition t = registeredTransition.getActualTransition();
	            t.execute();
	            return;
	        }
	    }
	}

}
