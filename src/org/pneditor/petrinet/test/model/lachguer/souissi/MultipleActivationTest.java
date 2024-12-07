package org.pneditor.petrinet.test.model.lachguer.souissi;

import org.pneditor.petrinet.models.lachguer.souissi.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MultipleActivationTest class performs tests on the PetriNet implementation
 * with multiple transitions and places to ensure correct behavior.
 */
class MultipleActivationTest {
    private PetriNet petri; // The Petri net instance to be tested

    /**
     * Sets up resources before all tests are run.
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * Cleans up resources after all tests are run.
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * Initializes the PetriNet before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        ArrayList<Place> places = new ArrayList<>();
        ArrayList<Arc> arcs = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        petri = new PetriNet(arcs, places, transitions);
    }

    /**
     * Cleans up resources or resets states after each test.
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Tests the activation of multiple transitions and verifies
     * the token counts in all places before and after running transitions.
     */
    @Test
    void testMultipleTransitionActivation() {
        // Creating places with initial token counts
        Place place1 = new Place(5);
        Place place2 = new Place(2);
        Place place3 = new Place(11);
        Place place4 = new Place(6);
        Place place5 = new Place(7);
        Place place6 = new Place(0);
        Place place7 = new Place(0);

        // Adding places to the Petri net
        petri.addPlace(place1);
        petri.addPlace(place2);
        petri.addPlace(place3);
        petri.addPlace(place4);
        petri.addPlace(place5);
        petri.addPlace(place6);
        petri.addPlace(place7);

        // Creating transitions
        Transition transition1 = new Transition();
        Transition transition2 = new Transition();
        Transition transition3 = new Transition();

        // Creating arcs to connect places and transitions
        ExArc exArc1 = new ExArc(1, place1, transition1);
        ExArc exArc2 = new ExArc(1, place3, transition2);
        EnArc enArc1 = new EnArc(1, place2, transition1);
        EnArc enArc2 = new EnArc(3, place2, transition2);
        ExArc exArc3 = new ExArc(5, place4, transition3);
        ExArc exArc4 = new ExArc(4, place5, transition3);
        EnArc enArc3 = new EnArc(3, place6, transition3);
        EnArc enArc4 = new EnArc(2, place7, transition3);

        // Setting up arcs for places
        place1.getExArcs().add(exArc1);
        place2.getEnArcs().add(enArc1);
        place3.getExArcs().add(exArc2);
        place2.getEnArcs().add(enArc2);
        place4.getExArcs().add(exArc3);
        place6.getEnArcs().add(enArc3);
        place5.getExArcs().add(exArc4);
        place7.getEnArcs().add(enArc4);

        // Setting up arcs for transitions
        transition1.getExArcs().add(exArc1);
        transition1.getEnArcs().add(enArc1);
        transition2.getExArcs().add(exArc2);
        transition2.getEnArcs().add(enArc2);
        transition3.getExArcs().add(exArc3);
        transition3.getExArcs().add(exArc4);
        transition3.getEnArcs().add(enArc3);
        transition3.getEnArcs().add(enArc4);

        // Adding transitions and arcs to the Petri net
        petri.addTransition(transition1);
        petri.addTransition(transition2);
        petri.addTransition(transition3);
        petri.addArc(exArc1);
        petri.addArc(exArc2);
        petri.addArc(exArc3);
        petri.addArc(exArc4);
        petri.addArc(enArc1);
        petri.addArc(enArc2);
        petri.addArc(enArc3);
        petri.addArc(enArc4);

        // Asserting initial token counts
        assertEquals(5, place1.getToken(), "Place 1 should have 5 token");
        assertEquals(2, place2.getToken(), "Place 2 should have 2 token");
        assertEquals(11, place3.getToken(), "Place 3 should have 11 tokens");
        assertEquals(6, place4.getToken(), "Place 4 should have 6 token");
        assertEquals(7, place5.getToken(), "Place 5 should have 7 token");
        assertEquals(0, place6.getToken(), "Place 6 should have 0 tokens");
        assertEquals(0, place7.getToken(), "Place 7 should have 0 tokens");

        // Running transitions
        petri.run();

        // Asserting updated token counts
        assertEquals(4, place1.getToken(), "Place 1 should have 4 token after run");
        assertEquals(6, place2.getToken(), "Place 2 should have 6 token after run");
        assertEquals(10, place3.getToken(), "Place 3 should have 10 tokens after run");
        assertEquals(1, place4.getToken(), "Place 4 should have 1 token after run");
        assertEquals(3, place5.getToken(), "Place 5 should have 3 token after run");
        assertEquals(3, place6.getToken(), "Place 6 should have 3 tokens after run");
        assertEquals(2, place7.getToken(), "Place 7 should have 2 tokens after run");
    }
}
