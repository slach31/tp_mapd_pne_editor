package org.pneditor.petrinet.test.model.lachguer.souissi;

//Importing necessary classes for the PetriNet and unit testing

import org.pneditor.petrinet.models.lachguer.souissi.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


//A test class for the PetriNet to verify the activation of simple transitions and the flow of tokens

class SimpleActivationTest {
    private PetriNet petri; // Instance of PetriNet to be used in tests
    
    
    // Executed once before any test methods run in this class
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        
    }
    
    // Executed once after all the test methods in this class have run
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        
    }
    
    // Executed before each test to initialize the PetriNet with empty lists of places, arcs, and transitions

    @BeforeEach
    void setUp() throws Exception {
        ArrayList<Place> places = new ArrayList<>();
        ArrayList<Arc> arcs = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        petri = new PetriNet(arcs, places, transitions);
    }
     
     // Executed after each test method to clean up or reset if needed
    @AfterEach
    void tearDown() throws Exception {
        
    }
    
    
 // A test to verify the activation of simple transitions and the token flow in the PetriNet
    @Test
    void testSimpleTransitionActivation() {
    	
    	// Create places with initial tokens
       
        Place place1 = new Place(1);
        Place place2 = new Place(0);
        
        Place place3 = new Place(1);
        Place place4 = new Place(2);
        
        // Add places to the PetriNet
        
        petri.addPlace(place1);
        petri.addPlace(place2);
        
        petri.addPlace(place3);
        petri.addPlace(place4);
        
        // Create transitions for the PetriNet
        Transition transition1 = new Transition();
        Transition transition2 = new Transition();
        
        
        // Create arcs that connect places and transitions
        ExArc exArc1 = new ExArc(1, place1, transition1);
        EnArc enArc1 = new EnArc(1, place2, transition1);
        ExArc exArc2 = new ExArc(3, place3, transition2);
        EnArc enArc2 = new EnArc(2, place4, transition2);
        
        // Link arcs to the respective places
        
        place1.getExArcs().add(exArc1);
        place2.getEnArcs().add(enArc1);
        place3.getExArcs().add(exArc2);
        place4.getEnArcs().add(enArc2);
        
        // Link arcs to the respective transitions

        transition1.getExArcs().add(exArc1);
        transition1.getEnArcs().add(enArc1);
        transition2.getExArcs().add(exArc2);
        transition2.getEnArcs().add(enArc2);
        
        // Add transitions and arcs to the PetriNet
        
        petri.addTransition(transition1);
        petri.addTransition(transition2);
        petri.addArc(exArc1);
        petri.addArc(enArc1);
        petri.addArc(exArc2);
        petri.addArc(enArc2);
        
        // Verify the initial token counts of the places
        assertEquals(1, place1.getToken(), "Place 1 should have 1 token");
        assertEquals(0, place2.getToken(), "Place 2 should have 0 tokens");
        assertEquals(1, place3.getToken(), "Place 3 should have 1 tokens");
        assertEquals(2, place4.getToken(), "Place 4 should have 2 tokens");

        // Run the PetriNet to activate transitions and flow tokens
        petri.run();
        
        // Verify the token counts of the places after running the PetriNet
        
        assertEquals(0, place1.getToken(), "Place 1 should be empty after transition");
        assertEquals(1, place2.getToken(), "Place 2 should have 1 token after transition");
        assertEquals(1, place3.getToken(), "Place 3 should have 1 tokens");
        assertEquals(2, place4.getToken(), "Place 4 should have 2 tokens");
    }
}

