package org.pneditor.petrinet.models.lachguer.souissi;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interactive main class to demonstrate the functionality of the Petri Net with user input.
 */
public class InteractiveMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the initial number of tokens for two places
        System.out.print("Entrez le nombre de jetons à la place 1: ");
        int tokensPlace1 = scanner.nextInt();

        System.out.print("Entrez le nombre de jetons à la place 2: ");
        int tokensPlace2 = scanner.nextInt();

        // Prompt user to enter the weights for the exiting and entering arcs
        System.out.print("Entrez le poids de l'arc sortant de la place 1: ");
        int weightExArc = scanner.nextInt();

        System.out.print("Entrez le poids de l'arc entrant à la place 2: ");
        int weightEnArc = scanner.nextInt();

        // Create places with user-defined token counts
        Place place1 = new Place(tokensPlace1);
        Place place2 = new Place(tokensPlace2);

        // Create a transition
        Transition transition = new Transition();

        // Create arcs with user-defined weights and link them to the transition and places
        ExArc exArc = new ExArc(weightExArc, place1, transition);
        EnArc enArc = new EnArc(weightEnArc, place2, transition);

        // Link arcs to places
        place1.getExArcs().add(exArc);
        place2.getEnArcs().add(enArc);

        // Link arcs to transition
        transition.getExArcs().add(exArc);
        transition.getEnArcs().add(enArc);

        // Create lists to hold places, arcs, and transitions
        ArrayList<Place> places = new ArrayList<>();
        places.add(place1);
        places.add(place2);

        ArrayList<Arc> arcs = new ArrayList<>();
        arcs.add(exArc);
        arcs.add(enArc);

        ArrayList<Transition> transitions = new ArrayList<>();
        transitions.add(transition);

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

        // Execute the PetriNet (fire all transitions)
        petriNet.run();

        // Display the token count in each place after running the Petri net
        System.out.println("Jetons après l'exécution:");
        System.out.println("Place 1: " + place1.getToken());
        System.out.println("Place 2: " + place2.getToken());

        // Close the scanner
        scanner.close();
    }
}
