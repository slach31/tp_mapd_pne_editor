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
	
	public PetriNetAdapter() {
		this.petri = new PetriNet(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		this.arcAdaptersList = new LinkedList<>();
	}
	
	@Override
	public AbstractPlace addPlace() {
		PlaceAdapter adapter = new PlaceAdapter("place");
		Place place = adapter.getActualPlace();
		this.petri.addPlace(place);
		return adapter;
	}

	@Override
	public AbstractTransition addTransition() {
		TransitionAdapter adapter = new TransitionAdapter("transition");
		Transition transition = adapter.getActualTransition();
		this.petri.addTransition(transition);
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
		for (ArcAdapter adapter : this.arcAdaptersList) {
			AbstractNode source = adapter.getSource();
			AbstractNode destination = adapter.getDestination();
			if ((source instanceof TransitionAdapter) && (destination instanceof PlaceAdapter)) {
				continue;
			}
			else if ((source instanceof PlaceAdapter) && (destination instanceof TransitionAdapter)) {
				if ((source.equals(place)) && (destination.equals(transition))){
					Arc arc = adapter.getActualArc();
					Arc newArc = adapter.makeIntoInhibitoryArc();
					this.petri.addArc(newArc);
					return adapter;
				}
			}
		}
		return null;
		
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
				for (ArcAdapter adapter : this.arcAdaptersList) {
					AbstractNode source = adapter.getSource();
					AbstractNode destination = adapter.getDestination();
					if ((source instanceof TransitionAdapter) && (destination instanceof PlaceAdapter)) {
						continue;
					}
					else if ((source instanceof PlaceAdapter) && (destination instanceof TransitionAdapter)) {
						if ((source.equals(place)) && (destination.equals(transition))){
							Arc arc = adapter.getActualArc();
							Arc newArc = adapter.makeIntoResetArc();
							this.petri.addArc(newArc);
							return adapter;
						}
					}
				}
				return null;
	}
	
	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter placeAdapter = (PlaceAdapter) place;
        petri.removePlace(placeAdapter.getActualPlace());
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        petri.removeTransition(transitionAdapter.getActualTransition());
	}

	@Override
	public void removeArc(AbstractArc arc) {
		ArcAdapter arcAdapter = (ArcAdapter) arc;
        petri.removeArc(arcAdapter.getActualArc());
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition t = transitionAdapter.getActualTransition();
        return t.isFeasible();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition t = transitionAdapter.getActualTransition();
        t.execute();
	}

}
