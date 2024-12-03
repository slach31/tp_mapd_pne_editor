package org.pneditor.petrinet.adapters.lachguer;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;


public class TransitionAdapter extends AbstractTransition {
	
	private Transition transition;

	public TransitionAdapter(String label) {
		super(label);
		this.transition = new Transition();
		 
	}

	public Transition getActualTransition() {
		return this.transition;
		
	}
}
