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
    private AbstractNode source;
    private AbstractNode destination;
	
	public ArcAdapter(AbstractNode source, AbstractNode destination) {
		this.source = source;
        this.destination = destination;

        if (source instanceof PlaceAdapter && destination instanceof TransitionAdapter) {
            Place place = ((PlaceAdapter) source).getActualPlace();
            Transition transition = ((TransitionAdapter) destination).getActualTransition();
            this.arc = new ExArc(1, place, transition);
            
    }
        else if (source instanceof TransitionAdapter && destination instanceof PlaceAdapter) {
            Place place = ((PlaceAdapter) destination).getActualPlace();
            Transition transition = ((TransitionAdapter) source).getActualTransition();
            this.arc = new EnArc(1, place, transition);
      
    }
        else {
        	throw new IllegalArgumentException("Invalid inputs");
        }
	}
	
	public Arc makeIntoInhibitoryArc() {
		Place place = ((PlaceAdapter) this.source).getActualPlace();
        Transition transition = ((TransitionAdapter) this.destination).getActualTransition();
		this.arc = new ZArc(place, transition);
		return this.arc;
	}
	
	public Arc makeIntoResetArc() {
		Place place = ((PlaceAdapter) this.source).getActualPlace();
        Transition transition = ((TransitionAdapter) this.destination).getActualTransition();
		this.arc = new EmArc(1, place, transition);
		return this.arc;
	}

	@Override
	public AbstractNode getSource() {
		return this.source;
	}

	@Override
	public AbstractNode getDestination() {
		return this.destination;
	}

	@Override
	public boolean isReset() {
		return (this.arc instanceof EmArc);
	}

	@Override
	public boolean isRegular() {
		return ((!(this.arc instanceof EmArc)) && (!(this.arc instanceof ZArc)));
	}

	@Override
	public boolean isInhibitory() {
		return (this.arc instanceof EmArc);
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return this.arc.getWeight();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		this.arc.setWeight(multiplicity);
		
	}
	
	public Arc getActualArc() {
		return this.arc;
	}

}
