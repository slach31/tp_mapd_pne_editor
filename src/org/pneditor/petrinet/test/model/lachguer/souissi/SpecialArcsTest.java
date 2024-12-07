package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;


// Class for testing special arcs in the Petri net
class SpecialArcsTest {

    private Place place;           // Variable to hold a Place object
    private Transition transition; // Variable to hold a Transition object
    private ZArc z_arc;           // Variable to hold a ZArc object
    private EmArc em_arc;         // Variable to hold an EmArc object

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
        place = new Place(0); // Initialize a Place with 0 tokens
        transition = new Transition(); // Initialize a Transition
    }

    // Executed after each test method
    @AfterEach
    void tearDown() throws Exception {
    }

    // Test for the constructor of ZArc
    @Test
    void testZArcConstructor() {
        z_arc = new ZArc(place, transition); // Create a new ZArc with place and transition
        assertNotNull(z_arc, "z_arc shouldn't be null"); // Check if z_arc is created
        assertEquals(z_arc.getWeight(), Integer.MAX_VALUE, "the weight of a z_arc should be infinite"); // Verify the weight
    }

    // Test for the constructor of EmArc
    @Test
    void testEmArcConstructor() {
        em_arc = new EmArc(1, place, transition); // Create a new EmArc with weight, place, and transition
        assertNotNull(em_arc, "em_arc shouldn't be null"); // Check if em_arc is created
    }

    // Test for ZArc activation cases
    @Test
    void testZArcActivationCases() {
        place = new Place(5); // Initialize a Place with 5 tokens
        z_arc = new ZArc(place, transition); // Create a new ZArc
        int token1 = place.getToken(); // Get the number of tokens
        assertFalse(z_arc.isActive(token1), "z_arc shouldn't be active"); // Verify z_arc is not active
        place = new Place(0); // Initialize a Place with 0 tokens
        z_arc = new ZArc(place, transition); // Create a new ZArc
        int token2 = place.getToken(); // Get the number of tokens
        assertTrue(z_arc.isActive(token2), "z_arc should be active"); // Verify z_arc is active
    }

    // Test for EmArc activation cases
    @Test
    void testEmArcActivationCases() {
        place = new Place(5); // Initialize a Place with 5 tokens
        em_arc = new EmArc(3, place, transition); // Create a new EmArc with weight 3
        int token1 = place.getToken(); // Get the number of tokens
        assertTrue(em_arc.isActive(token1), "em_arc shouldn't be active"); // Verify em_arc is active
        place = new Place(2); // Initialize a Place with 2 tokens
        em_arc = new EmArc(3, place, transition); // Create a new EmArc with weight 3
        int token2 = place.getToken(); // Get the number of tokens
        assertFalse(em_arc.isActive(token2), "em_arc should be active"); // Verify em_arc is not active
    }
}

