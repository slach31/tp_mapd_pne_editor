package org.pneditor.petrinet.test.model.lachguer.souissi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.pneditor.petrinet.models.lachguer.souissi.*;

/**
 * Unit test class for testing console output and duplicate arc detection in a Petri net.
 */
public class ConsoleTest {

    private PetriNet petri;
    private ArrayList<Place> places;
    private ArrayList<Arc> arcs;
    private ArrayList<Transition> transitions;

    /**
     * Setup method executed before each test. Initializes the Petri net and its components.
     */
    @BeforeEach
    public void setUp() {
        arcs = new ArrayList<Arc>();
        places = new ArrayList<Place>();
        transitions = new ArrayList<Transition>();
        petri = new PetriNet(arcs, places, transitions);
    }

    /**
     * Test to check for duplicate arcs and ensure no duplicates exist.
     */
    @Test
    public void testCheckForDuplicateArcs_noDuplicates() {
        // Set up unique arcs
        Place place1 = new Place(0);
        Transition transition1 = new Transition();
        EnArc arc1 = new EnArc(1, place1, transition1);
        
        Place place2 = new Place(2);
        Transition transition2 = new Transition();
        ExArc arc2 = new ExArc(3, place2, transition2);
        
        arcs.add(arc1);
        arcs.add(arc2);
        
        // Capture System.err output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Test for no duplicate error message
        petri.checkForDuplicateArcs();
        
        // Verify that no error message was printed
        assertTrue(errContent.toString().isEmpty());

        // Reset System.err
        System.setErr(System.err);
    }

    /**
     * Test to check for duplicate arcs and ensure the correct error message is output.
     */
    @Test
    public void testCheckForDuplicateArcs_withDuplicates() {
        // Set up duplicate arcs between the same place and transition
        Place place = new Place(3);
        Transition transition = new Transition();
        EnArc arc1 = new EnArc(1, place, transition);
        ExArc arc2 = new ExArc(3, place, transition);

        arcs.add(arc1);
        arcs.add(arc2);

        // Capture System.err output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Test for duplicate error message
        petri.checkForDuplicateArcs();

        // Verify that an error message was printed
        assertTrue(errContent.toString().contains("Erreur : Arcs doublés détectés entre la même place et transition."));

        // Reset System.err
        System.setErr(System.err);
    }

    /**
     * Test to verify that the details of the Petri net are correctly displayed.
     */
    @Test
    public void testDisplayPetriNetDetails() {
        // Set up places, transitions, and arcs
        Place place = new Place(3);
        Transition transition = new Transition();
        ExArc arc1 = new ExArc(5, place, transition);
        places.add(place);
        transitions.add(transition);
        arcs.add(arc1);

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the displayPetriNetDetails method
        petri.displayPetriNetDetails();

        // Verify output
        String output = outContent.toString();
        assertTrue(output.contains("Réseau de Petri"));
        assertTrue(output.contains("1 places"));
        assertTrue(output.contains("1 transition"));
        assertTrue(output.contains("1 arcs"));
        assertTrue(output.contains("Liste des places :"));
        assertTrue(output.contains("Liste des transitions :"));
        assertTrue(output.contains("Liste des arcs :"));

        // Reset System.out
        System.setOut(System.out);
    }
}
