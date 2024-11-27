package org.pneditor.petrinet.adapters.lachguer;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.lachguer.souissi.Place;

public class PlaceAdapter extends AbstractPlace {
	
	private Place place;

	public PlaceAdapter(String label) {
		super(label);
		this.place = new Place(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addToken() {
		this.place.addToken(1);
	}

	@Override
	public void removeToken() {
		this.place.removeToken(1);
		
	}

	@Override
	public int getTokens() {
		// TODO Auto-generated method stub
		return this.place.getToken();
	}

	@Override
	public void setTokens(int tokens) {
		this.place.removeToken(this.place.getToken());
		this.place.addToken(tokens);
		
	}
	
	
	public Place getActualPlace() {
		return this.place;
		
	}
}
