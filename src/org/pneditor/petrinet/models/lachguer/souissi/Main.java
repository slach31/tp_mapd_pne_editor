package org.pneditor.petrinet.models.lachguer.souissi;

import java.util.ArrayList;


/**
 * Main class to demonstrate the functionality of the Petri net.
 */

public class Main {
    public static void main(String[] args) {
    	// Create places with initial token counts
        Place place1 = new Place(5);
        Place place2 = new Place(2);
        Place place3 = new Place(11);
        
        
        // Create transitions
        Transition transition1 = new Transition();
        Transition transition2 = new Transition();
        
        // Create arcs linking places to transitions
        ExArc exArc1 = new ExArc(1, place1, transition1);
        EnArc enArc1 = new EnArc(1, place2, transition1);
        ExArc exArc2 = new ExArc(1, place2, transition2);
        EnArc enArc2 = new EnArc(3, place3, transition2);
        
        
        // Associate arcs with the corresponding places
        place1.getExArcs().add(exArc1);
        place2.getEnArcs().add(enArc1);
        place2.getExArcs().add(exArc2);
        place3.getEnArcs().add(enArc2);
        
        
        // Associate arcs with the corresponding transitions
        transition1.getExArcs().add(exArc1);
        transition1.getEnArcs().add(enArc1);
        transition2.getExArcs().add(exArc2);
        transition2.getEnArcs().add(enArc2);
        
        
        // Create lists to hold places, arcs, and transitions
        ArrayList<Place> places = new ArrayList<>();
        places.add(place1);
        places.add(place2);
        places.add(place3);

        ArrayList<Arc> arcs = new ArrayList<>();
        arcs.add(exArc1);
        arcs.add(enArc1);
        arcs.add(exArc2);
        arcs.add(enArc2);

        ArrayList<Transition> transitions = new ArrayList<>();
        transitions.add(transition1);
        transitions.add(transition2);
        
        
        // Create the PetriNet with the lists of arcs, places, and transitions
        PetriNet petriNet = new PetriNet(arcs, places, transitions);
        
        // Check for duplicate arcs and display an error if found
        petriNet.checkForDuplicateArcs();

        // Display the details of the PetriNet
        petriNet.displayPetriNetDetails();
        
        
        // Display the token count in each place before running the PetriNet
        System.out.println("Jetons avant l'exécution:");
        System.out.println("Place 1: " + place1.getToken());
        System.out.println("Place 2: " + place2.getToken());
        System.out.println("Place 3: " + place3.getToken());
        
        
        // Execute the PetriNet (fire all transitions)
        petriNet.run();
        
        
        // Display the token count in each place after running the Petri net
        System.out.println("Jetons après l'exécution:");
        System.out.println("Place 1: " + place1.getToken());
        System.out.println("Place 2: " + place2.getToken());
        System.out.println("Place 3: " + place3.getToken());
    }
}
