package org.pneditor.petrinet.models.lachguer.souissi;

import java.util.ArrayList;

/**
 * Represents a place in a PetriNet, which holds a certain number of tokens and manages
 * incoming and outgoing arcs.
 */
public class Place {
    // The number of tokens currently in this place
    private int token;
    
    // List of exiting arcs from this place
    private ArrayList<ExArc> exArcs;
    
    // List of entering arcs to this place
    private ArrayList<EnArc> enArcs;

    /**
     * Constructor to initialize a Place with a given number of tokens.
     * Throws an exception if the initial token count is negative.
     *
     * @param token Initial number of tokens in the place
     */
    public Place(int token) {
        if (token < 0) {
            throw new IllegalArgumentException("Token cannot be negative");
        }
        this.token = token;
        this.exArcs = new ArrayList<ExArc>(); // Initialize the list of outgoing arcs
        this.enArcs = new ArrayList<EnArc>(); // Initialize the list of incoming arcs
    }

    /**
     * Gets the current number of tokens in this place.
     *
     * @return The number of tokens
     */
    public int getToken() {
        return token;
    }

    /**
     * Adds a specified number of tokens to this place.
     * Throws an exception if the number is negative.
     *
     * @param n Number of tokens to add
     */
    public void addToken(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Token cannot be negative");
        }
        this.token += n;
    }

    /**
     * Removes a specified number of tokens from this place.
     * Throws an exception if the number is negative.
     * If the current number of tokens is less than the number to remove,
     * sets the token count to zero.
     *
     * @param n Number of tokens to remove
     */
    public void removeToken(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Token cannot be negative");
        } else if (this.token < n) {
            this.token = 0; // Set to zero if not enough tokens
        } else {
            this.token -= n;
        }
    }

    /**
     * Empties this place by setting the token count to zero.
     */
    public void emptyPlace() {
        this.token = 0;
    }

    /**
     * Gets the list of outgoing arcs from this place.
     *
     * @return List of outgoing arcs (ExArc)
     */
    public ArrayList<ExArc> getExArcs() {
        return exArcs;
    }

    /**
     * Gets the list of incoming arcs to this place.
     *
     * @return List of incoming arcs (EnArc)
     */
    public ArrayList<EnArc> getEnArcs() {
        return enArcs;
    }

    /**
     * Adds an outgoing arc to this place.
     *
     * @param arc The outgoing arc to add
     */
    public void addExArc(ExArc arc) {
        this.exArcs.add(arc);
    }

    /**
     * Adds an incoming arc to this place.
     *
     * @param arc The incoming arc to add
     */
    public void addEnArc(EnArc arc) {
        this.enArcs.add(arc);
    }

    /**
     * Empties the lists of both incoming and outgoing arcs.
     */
    public void emptyArcsList() {
        this.exArcs = new ArrayList<ExArc>(); // Clear exiting arcs
        this.enArcs = new ArrayList<EnArc>(); // Clear entering arcs
    }
}
