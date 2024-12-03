package org.pneditor.petrinet.adapters.lachguer;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;

import org.pneditor.petrinet.adapters.lachguer.*;

import org.pneditor.petrinet.models.lachguer.souissi.Arc;
import org.pneditor.petrinet.models.lachguer.souissi.EnArc;
import org.pneditor.petrinet.models.lachguer.souissi.ExArc;
import org.pneditor.petrinet.models.lachguer.souissi.PetriNet;
import org.pneditor.petrinet.models.lachguer.souissi.Place;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	
	private PetriNet petri;
	
	public PetriNetAdapter() {
		this.petri = new PetriNet(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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
		// TODO Auto-generated method stub
		ArcAdapter adapter = new ArcAdapter(source, destination);
		Arc arc = adapter.getActualArc();
		this.petri.addArc(arc);
		return adapter;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void removePlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		PlaceAdapter placeAdapter = (PlaceAdapter) place;
        petri.removePlace(placeAdapter.getActualPlace());
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        petri.removeTransition(transitionAdapter.getActualTransition());
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		ArcAdapter arcAdapter = (ArcAdapter) arc;
        petri.removeArc(arcAdapter.getActualArc());
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition t = transitionAdapter.getActualTransition();
        return t.isFeasible();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition t = transitionAdapter.getActualTransition();
        t.execute();
	}

}
