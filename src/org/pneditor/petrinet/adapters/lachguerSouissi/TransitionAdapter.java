package org.pneditor.petrinet.adapters.lachguerSouissi;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.lachguer.souissi.Arc;
import org.pneditor.petrinet.models.lachguer.souissi.Transition;
import org.pneditor.petrinet.models.lachguer.souissi.ZArc;
import org.pneditor.petrinet.models.lachguer.souissi.ExArc;


/**
 * Adapter class for integrating the `Transition` class from the custom PetriNet
 * implementation with the PNEditor's `AbstractTransition`.
 */
public class TransitionAdapter extends AbstractTransition {
	
	private Transition transition;// The underlying Transition object
	
	

	/**
     * Constructs a TransitionAdapter with a specified name.
     * 
     * @param name The name of the transition.
     */
	public TransitionAdapter(String label) {
		super(label);
		this.transition = new Transition();
		 
	}
	
	/**
     * Retrieves the underlying Transition object from the custom Petri Net implementation.
     * 
     * @return The actual `Transition` object.
     */
	public Transition getActualTransition() {
		return this.transition;
		
	}
	
	
}
