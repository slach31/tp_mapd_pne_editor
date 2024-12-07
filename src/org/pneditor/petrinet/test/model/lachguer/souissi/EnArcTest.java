package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;
/**
 * Tests the functionality of the EnArc class.
 */
class EnArcTest {

    private EnArc en_arc;
    private Transition transition;
    private Place place;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Called once before any tests run
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // Called once after all tests are done
    }

    @BeforeEach
    void setUp() throws Exception {
        // Set up a new instance of EnArc before each test
        place = new Place(0);
        transition = new Transition();
        en_arc = new EnArc(1, place, transition);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up resources after each test
    }

    @Test
    void testConstructorNegativeWeight() {
        // Test the constructor with negative weight to ensure it throws an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            en_arc = new EnArc(-11, place, transition);
        });
    }

    @Test
    void testConstructorNullPlace() {
        // Test the constructor with a null place to ensure it throws an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            en_arc = new EnArc(1, null, transition);
        });
    }

    @Test
    void testConstructorNullTransition() {
        // Test the constructor with a null transition to ensure it throws an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            en_arc = new EnArc(1, place, null);
        });
    }

    @Test
    void testConstructorNormalCase() {
        // Test the constructor with valid inputs to ensure the object is created
        en_arc = new EnArc(1, place, transition);
        assertNotNull(en_arc);
    }
}
