package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.lachguer.souissi.*;

//Test class for the PetriNet class, validating the addition, removal, and retrieval of components.

class PetriNetTest {
	
	private PetriNet petri;
	ArrayList<Place>  places;
	ArrayList<Arc> arcs;
	ArrayList<Transition> transitions;
	
	
    // Executed once before all test methods in this class.
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
    // Executed once after all test methods in this class.
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
    // Executed before each test method, initializing a new PetriNet instance and its components.
	@BeforeEach
	void setUp() throws Exception {
		places = new ArrayList<Place>();
		arcs = new ArrayList<Arc>();
		transitions = new ArrayList<Transition>();
		petri = new PetriNet(arcs, places, transitions);
		
	}
	
    // Executed after each test method.
	@AfterEach
	void tearDown() throws Exception {
	}
	
    // Test to check if a new PetriNet object is created successfully.
	@Test
	void testCreateEmptyPetriNet() {
		
		assertNotNull(petri, "petri shouldn't be null");
	}
	
    // Test to verify adding a transition to the PetriNet.	
	@Test
	void testAddTransitionToPetri() {
		
		Transition transition = new Transition();
		petri.addTransition(transition);
		boolean verify = transitions.contains(transition);
		assertEquals(verify, true, "transitions list should have transition");
		
	}
	
    // Test to verify adding a place to the PetriNet.	
	@Test
	void testAddPlaceToPetri() {
		
		Place place = new Place(0);
		petri.addPlace(place);
		boolean verify = places.contains(place);
		assertEquals(verify, true, "places list should have place");
		
	}
	
	
    // Test to verify adding an EnArc to the PetriNet and the proper connections.
	@Test
	void testAddEnArcToPetri() {
		Place place = new Place(0);
		petri.addPlace(place); 
		Transition transition = new Transition();
		petri.addTransition(transition);
		EnArc arc = new EnArc(1, place, transition);
		place.addEnArc(arc);
		transition.addEnArc(arc);
		petri.addArc(arc);
		boolean verify = arcs.contains(arc);
		assertEquals(verify, true, "an EnArc should be added" );
		assertEquals(place.getEnArcs().contains(arc), true, "an EnArc should be added");
		assertEquals(transition.getEnArcs().contains(arc), true, "an EnArc should be added");
		
	}
	
    // Test to verify adding an ExArc to the PetriNet and the proper connections.	
	@Test
	void testAddExArcToPetri() {
		Place place = new Place(0);
		petri.addPlace(place); 
		Transition transition = new Transition();
		petri.addTransition(transition);
		ExArc arc = new ExArc(1, place, transition);
		place.addExArc(arc);
		transition.addExArc(arc);
		petri.addArc(arc);
		boolean verify = arcs.contains(arc);
		assertEquals(verify, true, "an EnArc should be added");
		assertEquals(place.getExArcs().contains(arc), true, "an EnArc should be added");
		assertEquals(transition.getExArcs().contains(arc), true, "an EnArc should be added");
		
	}
	
	
    // Test to verify removing a place from the PetriNet.
	@Test
	void testRemovePlaceAlone() {
		Place place = new Place(0);
		petri.addPlace(place);
		petri.removePlace(place);
		assertFalse(places.contains(place), "place shouldn't be in places list anymore" );
	}
	
	
    // Test to verify removing a transition from the PetriNet.
	@Test
	void testRemoveTransitionAlone() {
		Transition transition = new Transition();
		petri.addTransition(transition);
		petri.removeTransition(transition);
		assertFalse(transitions.contains(transition), "transition shouldn't be in transitions list anymore" );
	}
	
	
    // Test to verify removing an arc from the PetriNet.	
	@Test
	void testRemoveArc() {
		Transition transition = new Transition();
		Place place = new Place(0);
		EnArc arc = new EnArc(1, place, transition);
		petri.addTransition(transition);
		petri.addPlace(place);
		petri.addArc(arc);
		place.addEnArc(arc);
		transition.addEnArc(arc);
		petri.removeArc(arc);
		assertFalse(arcs.contains(arc), "arc shouldn't be in arcs list anymore" );
	}
	
    // Test to verify removing a place that has an ExArc.	
	@Test
	void testRemovePlaceWithExArc() {
		Transition transition = new Transition();
		Place place = new Place(0);
		ExArc arc = new ExArc(1, place, transition);
		petri.addTransition(transition);
		petri.addPlace(place);
		place.addExArc(arc);
		transition.addExArc(arc);
		petri.addArc(arc);
		petri.removePlace(place);
		assertFalse(places.contains(place), "place shouldn't be in places list anymore" );
		assertFalse(arcs.contains(arc), "arc shouldn't be in arcs list anymore" );
	}
	
	
    // Test to verify removing a place that has an EnArc.
	@Test
	void testRemovePlaceWithEnArc() {
		Transition transition = new Transition();
		Place place = new Place(0);
		EnArc arc = new EnArc(1, place, transition);
		petri.addTransition(transition);
		petri.addPlace(place);
		place.addEnArc(arc);
		transition.addEnArc(arc);
		petri.addArc(arc);
		petri.removePlace(place);
		assertFalse(places.contains(place), "place shouldn't be in places list anymore" );
		assertFalse(arcs.contains(arc), "arc shouldn't be in arcs list anymore" );
	}
	
    // Test to verify removing a transition that has an EnArc.	
	@Test
	void testRemoveTransitionWithEnArc() {
		Transition transition = new Transition();
		Place place = new Place(0);
		EnArc arc = new EnArc(1, place, transition);
		petri.addTransition(transition);
		petri.addPlace(place);
		petri.addArc(arc);
		place.addEnArc(arc);
		transition.addEnArc(arc);
		petri.addArc(arc);
		petri.removeTransition(transition);
		assertFalse(transitions.contains(transition), "transition shouldn't be in transitions list anymore" );
		assertFalse(arcs.contains(arc), "arc shouldn't be in arcs list anymore" );
	}
	
    // Test to verify removing a transition that has an ExArc.	
	@Test
	void testRemoveTransitionWithExArc() {
		Transition transition = new Transition();
		Place place = new Place(0);
		ExArc arc = new ExArc(1, place, transition);
		petri.addTransition(transition);
		petri.addPlace(place);
		petri.addArc(arc);
		place.addExArc(arc);
		transition.addExArc(arc);
		petri.addArc(arc);
		petri.removeTransition(transition);
		assertFalse(transitions.contains(transition), "transition shouldn't be in transitions list anymore" );
		assertFalse(arcs.contains(arc), "arc shouldn't be in arcs list anymore" );
	}
	
	
    // Test to verify the getters for places, transitions, and arcs in the PetriNet.
	@Test
	void testGetters() {
		petri = new PetriNet(arcs, places, transitions);
		assertEquals(petri.getPlacesList(), places, "these 2 lists should be equal");
		assertEquals(petri.getTransitionsList(), transitions, "these 2 lists should be equal");
		assertEquals(petri.getArcsList(), arcs, "these 2 lists should be equal");
	}
	
}
