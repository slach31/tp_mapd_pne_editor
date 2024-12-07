package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.*;

import org.pneditor.petrinet.models.lachguer.souissi.*;

/**
 * MainTest class performs unit tests on the PetriNet implementation.
 * It sets up a PetriNet with places, transitions, and arcs,
 * and tests various aspects of its behavior.
 */
class MainTest {
    private PetriNet petri; // The PetriNet instance to be tested
    private Place place1, place2, place3; // Places in the PetriNet
    private Transition transition1, transition2; // Transitions in the PetriNet

    /**
     * Initializes the PetriNet and its components before each test.
     */
    @BeforeEach
    void setUp() {
        // Creating places with initial token counts
        place1 = new Place(5);
        place2 = new Place(2);
        place3 = new Place(11);

        // Creating transitions
        transition1 = new Transition();
        transition2 = new Transition();

        // Creating arcs connecting places and transitions
        ExArc exArc1 = new ExArc(1, place1, transition1);
        EnArc enArc1 = new EnArc(1, place2, transition1);
        ExArc exArc2 = new ExArc(1, place2, transition2);
        EnArc enArc2 = new EnArc(3, place3, transition2);

        // Setting up arcs for the places
        place1.getExArcs().add(exArc1);
        place2.getEnArcs().add(enArc1);
        place2.getExArcs().add(exArc2);
        place3.getEnArcs().add(enArc2);

        // Setting up arcs for the transitions
        transition1.getExArcs().add(exArc1);
        transition1.getEnArcs().add(enArc1);
        transition2.getExArcs().add(exArc2);
        transition2.getEnArcs().add(enArc2);

        // Adding places, arcs, and transitions to the Petri net
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

        petri = new PetriNet(arcs, places, transitions);
    }

    /**
     * Tests if the initial token counts of the places are set correctly.
     */
    @Test
    void testInitialTokenCounts() {
        assertEquals(5, place1.getToken(), "Place 1 should have 5 tokens initially");
        assertEquals(2, place2.getToken(), "Place 2 should have 2 tokens initially");
        assertEquals(11, place3.getToken(), "Place 3 should have 11 tokens initially");
    }

    /**
     * Tests if transitions run correctly and update token counts in places as expected.
     */
    @Test
    void testRunTransitions() {
        petri.run();
        assertEquals(4, place1.getToken(), "Place 1 should have 4 tokens after transition 1");
        assertEquals(2, place2.getToken(), "Place 2 should have 2 tokens after transition 1");
        assertEquals(14, place3.getToken(), "Place 3 should have 14 tokens after transition 2");
    }

    /**
     * Tests if the method for checking duplicate arcs is working.
     */
    @Test
    void testDuplicateArcCheck() {
        petri.checkForDuplicateArcs();
    }

    /**
     * Cleans up resources or resets states after each test.
     */
    @AfterEach
    void tearDown() {
    }
}
