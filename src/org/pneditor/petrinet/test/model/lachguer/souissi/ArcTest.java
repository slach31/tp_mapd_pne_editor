package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;

/**
 * Unit test class for testing the Arc class in a Petri net implementation.
 */
class ArcTest {
    
    private Arc arc;
    private Place place;
    private Transition transition;

    /**
     * Setup method executed once before all tests.
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * Teardown method executed once after all tests.
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * Setup method executed before each test. Initializes Place and Transition objects.
     */
    @BeforeEach
    void setUp() throws Exception {
        place = new Place(0);
        transition = new Transition();
    }

    /**
     * Teardown method executed after each test.
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test to verify that the Arc constructor throws an exception if the weight is negative.
     */
    @Test
    void testConstructorNegativeWeight() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arc = new ExArc(-11, place, transition);
        });
    }

    /**
     * Test to verify that the Arc constructor throws an exception if the Place is null.
     */
    @Test
    void testConstructorNullPlace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arc = new EnArc(1, null, transition);
        });
    }

    /**
     * Test to verify that the Arc constructor throws an exception if the Transition is null.
     */
    @Test
    void testConstructorNullTransition() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arc = new ExArc(1, place, null);
        });
    }

    /**
     * Test to verify that the Arc constructor creates a valid Arc object with correct inputs.
     */
    @Test
    void testConstructorNormalCase() {
        Arc arc = new EnArc(1, place, transition);
        assertNotNull(arc, "arc shouldn't be null");
    }

    /**
     * Test to verify that getters for Arc return the correct values.
     */
    @Test 
    void testGetters() {
        Arc arc = new EnArc(1, place, transition);
        assertEquals(arc.getPlace(), place, "places should be equal");
        assertEquals(arc.getTransition(), transition, "transitions should be equal");
        assertEquals(arc.getWeight(), 1, "the weights should be equal");
    }
}
