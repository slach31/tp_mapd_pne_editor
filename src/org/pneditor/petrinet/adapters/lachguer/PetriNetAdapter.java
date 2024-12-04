package org.pneditor.petrinet.adapters.lachguer;

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

import org.pneditor.petrinet.adapters.lachguer.*;

import org.pneditor.petrinet.models.lachguer.souissi.Arc;
import org.pneditor.petrinet.models.lachguer.souissi.PetriNet;
import org.pneditor.petrinet.models.lachguer.souissi.Place;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	
	private PetriNet petri;
	private List<ArcAdapter> arcAdaptersList; 
	private List<PlaceAdapter> placeAdaptersList; 
	private List<TransitionAdapter> transitionAdaptersList; 
	
	public PetriNetAdapter() {
		this.petri = new PetriNet(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		this.arcAdaptersList = new ArrayList<>();
		this.placeAdaptersList = new ArrayList<>();
		this.transitionAdaptersList = new ArrayList<>();
	}
	
	@Override
	public AbstractPlace addPlace() {
		PlaceAdapter adapter = new PlaceAdapter("place");
		Place place = adapter.getActualPlace();
		this.petri.addPlace(place);
		this.placeAdaptersList.add(adapter);
		return adapter;
	}

	@Override
	public AbstractTransition addTransition() {
		TransitionAdapter adapter = new TransitionAdapter("transition");
		Transition transition = adapter.getActualTransition();
		this.petri.addTransition(transition);
		this.transitionAdaptersList.add(adapter);
		return adapter;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		ArcAdapter adapter = new ArcAdapter(source, destination);
		Arc arc = adapter.getActualArc();
		this.petri.addArc(arc);
		this.arcAdaptersList.add(adapter);
		return adapter;
	}

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
	
	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter placeAdapter = (PlaceAdapter) place;
        this.petri.removePlace(placeAdapter.getActualPlace());
        this.placeAdaptersList.remove(placeAdapter);
        
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        this.petri.removeTransition(transitionAdapter.getActualTransition());
        this.transitionAdaptersList.remove(transitionAdapter);
	}

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
