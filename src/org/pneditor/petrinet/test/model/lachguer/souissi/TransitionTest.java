package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;

// Class for testing transitions in the Petri net
class TransitionTest {

    private Place place;           // Variable to hold a Place object
    private Transition transition; // Variable to hold a Transition object
    private EnArc en_arc;         // Variable to hold an EnArc object
    private ExArc ex_arc;         // Variable to hold an ExArc object

    // Executed once before any of the test methods in this class
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    // Executed once after all the test methods in this class
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    // Executed before each test method
    @BeforeEach
    void setUp() throws Exception {
        transition = new Transition(); // Initialize a Transition
        place = new Place(0); // Initialize a Place with 0 tokens
        en_arc = new EnArc(1, place, transition); // Create a new EnArc with weight 1
        ex_arc = new ExArc(1, place, transition); // Create a new ExArc with weight 1
    }

    // Executed after each test method
    @AfterEach
    void tearDown() throws Exception {
    }

    // Test for the constructor of Transition
    @Test
    void testConstructor() {
        transition = new Transition(); // Create a new Transition
        assertNotNull(transition, "transition shouldn't be null"); // Check if transition is created
    }

    // Test for adding an EnArc to the transition
    @Test
    void testAddEnArc() {
        transition.addEnArc(en_arc); // Add en_arc to the transition
        ArrayList<EnArc> en_arcs = transition.getEnArcs(); // Retrieve the list of EnArcs
        boolean verify = en_arcs.contains(en_arc); // Check if en_arc is in the list
        assertEquals(verify, true, "en_arcs should have en_arc"); // Verify that en_arc was added
    }

    // Test for adding an ExArc to the transition
    @Test
    void testAddExArc() {
        transition.addExArc(ex_arc); // Add ex_arc to the transition
        ArrayList<ExArc> ex_arcs = transition.getExArcs(); // Retrieve the list of ExArcs
        boolean verify = ex_arcs.contains(ex_arc); // Check if ex_arc is in the list
        assertEquals(verify, true, "ex_arcs should have ex_arc"); // Verify that ex_arc was added
    }
}

