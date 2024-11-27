package org.pneditor.petrinet.adapters.lachguer;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.lachguer.souissi.Arc;
import org.pneditor.petrinet.models.lachguer.souissi.EnArc;
import org.pneditor.petrinet.models.lachguer.souissi.ExArc;
import org.pneditor.petrinet.models.lachguer.souissi.ZArc;
import org.pneditor.petrinet.models.lachguer.souissi.EmArc;
import org.pneditor.petrinet.models.lachguer.souissi.Place;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;

public class ArcAdapter extends AbstractArc {
	
	private Arc arc;
	private EnArc enarc;
	private ExArc exarc;
	private ZArc zarc;
	private EmArc emarc;
	
	public ArcAdapter(Place associatedPlace, Transition associatedTransition) {
		
	}
	
	public ExArc createExArc(int weight, Place associatedPlace, Transition associatedTransition) {
		return new ExArc(weight,  associatedPlace, associatedTransition);
	}
	
	public EnArc createEnArc(int weight, Place associatedPlace, Transition associatedTransition) {
		return new EnArc(weight,  associatedPlace, associatedTransition);
	}
	
	public EmArc createResetArc(int weight, Place associatedPlace, Transition associatedTransition) {
		return new EmArc(weight,  associatedPlace, associatedTransition);
	}
	
	public ZArc createInhibitoryArc(Place associatedPlace, Transition associatedTransition) {
		return new ZArc(associatedPlace, associatedTransition);
	}

	@Override
	public AbstractNode getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractNode getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegular() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}

}
