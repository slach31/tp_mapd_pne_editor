package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;


//A test class for the Place class to ensure correct behavior of its methods and token handling.


class PlaceTest {

	private Place place;
	private Transition transition;
	private EnArc en_arc;
	private ExArc ex_arc;
	
    // Executed once before any of the test methods in this class run.
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
    // Executed once after all the test methods in this class have run.
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
    // Executed before each test to initialize a Place and related arcs.
	@BeforeEach
	void setUp() throws Exception {
		transition = new Transition();
		place = new Place(0);
		en_arc = new EnArc(1, place, transition);
		ex_arc = new ExArc(1, place, transition);
	}
	
    // Executed after each test method to clean up or reset if needed.
	@AfterEach
	void tearDown() throws Exception {
	}
	
    // Test to verify that a Place object is created with a positive number of tokens.
	@Test
	void testConstructorNormalCase() {
		// here we test if a place is created in a normal scenario (tokens >= 0)
		place = new Place(5);
		assertNotNull(place, "place shouldn't be null");
	}
	
	
	
	@Test
	void testConstructorErrorCase() {
		// here we test if an exception will be thrown if inputs are incorrect (tokens < 0)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Place(-3);
        });
	}
	
	@Test 
	void testAddTokensNegativeCase() {
		// here we test if an exception will be thrown if inputs are incorrect (tokens < 0)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            place.addToken(-5);
        });
	}
	
	@Test 
	void testgetToken() {
		// here we test if we get tokens
		place = new Place(5);
		int nb_tokens = place.getToken();
		assertEquals(nb_tokens, 5);
	}
	
	@Test 
	void testAddTokensNormalCase() {
		// here we test if tokens are added in a normal scenario (tokens >= 0)
		place.addToken(5);
		int nb_tokens = place.getToken();
		assertEquals(nb_tokens, 5);
	}
	
	@Test 
	void testRemoveTokensNegativeCase() {
		place.addToken(5);
		// here we test if an exception will be thrown if inputs are incorrect (tokens < 0)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            place.removeToken(-5);
        });
	}
	
	@Test
	void testRemoveTokensFirstCase() {
		place.addToken(5);
		// here we test what would happen if entry is bigger than existing tokens 
		place.removeToken(7);
		int nb_tokens = place.getToken();
		System.out.println(nb_tokens);
		assertEquals(nb_tokens, 0);
	}
	
	@Test
	void testRemoveTokensSecondCase() {
		place.addToken(5);
		// here we test what would happen if entry is smaller than or equal to existing tokens 
		place.removeToken(3);
		int nb_tokens = place.getToken();
		System.out.println(nb_tokens);
		assertEquals(nb_tokens, 2);
	}
	
	@Test
	void testAddEnArc() {
		place.addEnArc(en_arc);
		//here we test if we can add an entering arc.
		ArrayList<EnArc> en_arcs = place.getEnArcs();
		boolean verify = en_arcs.contains(en_arc);
		assertEquals(verify, true, "en_arcs should have en_arc");
	}
	
	@Test
	void testAddExArc() {
		place.addExArc(ex_arc);
		// here we test if we can add an exiting arc.
		ArrayList<ExArc> ex_arcs = place.getExArcs();
		boolean verify = ex_arcs.contains(ex_arc);
		assertEquals(verify, true, "ex_arcs should have ex_arc");
	}
	
}
