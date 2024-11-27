package org.pneditor.petrinet.models.lachguer.souissi;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * PetriNet class implementing the IPetriNet interface.
 * Represents the structure and operations of a PetriNet, which consists of places, transitions, and arcs.
 */




public class PetriNet implements IPetriNet {
	private ArrayList <Place>  places;          // List of all places in the PetriNet
	private ArrayList <Arc> arcs;               // List of all arcs in the PetriNet
	private ArrayList <Transition> transitions; // List of all transitions in the PetriNet
	
	
	
	
	/**
     * Constructor for initializing the PetriNet with lists of arcs, places, and transitions.
     * 
     * @param arcs List of arcs in the PetriNet.
     * @param places List of places in the PetriNet.
     * @param transitions List of transitions in the PetriNet.
     */
		


    public  PetriNet(ArrayList <Arc> arcs, ArrayList <Place> places, ArrayList <Transition> transitions) {
    	this.places = places;
    	this.arcs = arcs;
    	this.transitions = transitions;
    }

    @Override
    public void addPlace(Place place) {
    	places.add(place);
    }

    @Override
    public void addArc(Arc arc) {
    	arcs.add(arc);
    }
    
    @Override 
    public void addTransition(Transition transition) {
    	transitions.add(transition);
    }
    
    @Override
    public void removePlace(Place place) {
    	Iterator<Arc> iterator = this.arcs.iterator();
        while (iterator.hasNext()) {
            Arc arc = iterator.next();
            Place placeCompare = arc.getPlace();
            if (placeCompare == place) {
                iterator.remove();  
            }
        }
    	place.emptyArcsList();
        places.remove(place);
        
    }
    
    @Override
    public void removeTransition(Transition transition) {
    	Iterator<Arc> iterator = this.arcs.iterator();
        while (iterator.hasNext()) {
            Arc arc = iterator.next();
            Transition transitionCompare = arc.getTransition();
            if (transitionCompare == transition) {
                iterator.remove();  
            }
        }
    	transition.emptyArcsList();
        transitions.remove(transition);
        
    }
    
    
    // Getters for the lists of places, transitions, and arcs
    public ArrayList<Transition> getTransitionsList(){
    	return this.transitions;
    }
    
    public ArrayList<Place> getPlacesList(){
    	return this.places;
    }
    
    public ArrayList<Arc> getArcsList(){
    	return this.arcs;
    }

    @Override
    public void removeArc(Arc arc) {
    	Transition transition = arc.getTransition();
    	Place place = arc.getPlace();
    	if (transition.getEnArcs().contains(arc)) {
    		transition.getEnArcs().remove(arc);
    	}
    	else if (transition.getExArcs().contains(arc)) {
    		transition.getExArcs().remove(arc);
    	}
    	if (place.getEnArcs().contains(arc)) {
    		place.getEnArcs().remove(arc);
    	}
    	else if (place.getExArcs().contains(arc)) {
    		place.getExArcs().remove(arc);
    	}
        arcs.remove(arc);
        arc = null; 
    }
    
    @Override
    public void run() {
    	// Execute all transitions in the PetriNet
        for (Transition transition : transitions) {
            transition.execute();
        }
    }
    
    /**
     * Checks for duplicate arcs between the same place and transition and prints an error message if any are found.
     */
    
    public void checkForDuplicateArcs() {
        for (int i = 0; i < arcs.size(); i++) {
            for (int j = i + 1; j < arcs.size(); j++) {
                Arc arc1 = arcs.get(i);
                Arc arc2 = arcs.get(j);
                if (arc1.getPlace() == arc2.getPlace() && arc1.getTransition() == arc2.getTransition()) {
                    System.err.println("Erreur : Arcs doublés détectés entre la même place et transition.");
                }
            }
        }
    }
    
    
    
    /**
     * Displays details of the PetriNet, including the number of places, transitions, and arcs.
     */

    public void displayPetriNetDetails() {
        System.out.println("Réseau de Petri");	
        System.out.println(places.size() + " places");
        System.out.println(transitions.size() + " transition" + (transitions.size() > 1 ? "s" : ""));
        System.out.println(arcs.size() + " arcs");

        System.out.println("Liste des places :");
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            System.out.println((i + 1) + " : place avec " + place.getToken() + " jetons, " 
                + place.getExArcs().size() + " arcs simples sortants, " 
                + place.getEnArcs().size() + " arcs entrants");
        }

        System.out.println("Liste des transitions :");
        for (int i = 0; i < transitions.size(); i++) {
            Transition transition = transitions.get(i);
            System.out.println((i + 1) + " : transition, " 
                + transition.getExArcs().size() + " arcs entrants, " 
                + transition.getEnArcs().size() + " arcs sortants");
        }

        System.out.println("Liste des arcs :");
        for (int i = 0; i < arcs.size(); i++) {
            Arc arc = arcs.get(i);
            String arcType = arc instanceof ExArc ? "arc simple" : "arc ";
            System.out.println((i + 1) + " : " + arcType + " poids " + arc.getWeight() 
                + " (" + (arc instanceof ExArc ? "place avec " + arc.getPlace().getToken() + " jetons vers transition" 
                : "transition vers place avec " + arc.getPlace().getToken() + " jetons") + ")");
        }
    }


}
