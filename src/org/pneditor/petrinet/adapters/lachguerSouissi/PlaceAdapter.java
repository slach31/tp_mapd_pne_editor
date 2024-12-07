package org.pneditor.petrinet.adapters.lachguerSouissi;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.lachguer.souissi.Place;


/**
 * Adapter class for integrating the `Place` class from the custom Petri Net
 * implementation with the PNEditor framework's `AbstractPlace`.
 */
public class PlaceAdapter extends AbstractPlace {
	
	private Place place; // The underlying Place object
	
	
	/**
     * Constructs a PlaceAdapter with a specified name.
     * 
     * @param name The name of the place.
     */
	public PlaceAdapter(String label) {
		super(label);
		this.place = new Place(0);
	}

	@Override
	public void addToken() {
		this.place.addToken(1);
	}

	@Override
	public void removeToken() {
		this.place.removeToken(1);
		
	}

	/**
     * Retrieves the number of tokens in the place.
     * 
     * @return The token count of the place.
     */
	@Override
	public int getTokens() {
		// TODO Auto-generated method stub
		return this.place.getToken();
	}
	
	

	/**
     * Sets the number of tokens in the place.
     * 
     * @param tokens The new token count for the place.
     */
	@Override
	public void setTokens(int tokens) {
		this.place.removeToken(this.place.getToken());
		this.place.addToken(tokens);
		
	}
	
	/**
     * Retrieves the underlying Place object from the custom Petri Net implementation.
     * 
     * @return The actual `Place` object.
     */
	public Place getActualPlace() {
		return this.place;
		
	}
}
