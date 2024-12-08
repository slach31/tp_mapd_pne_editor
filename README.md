# TP_MAPD



## Petri Net 
A Petri net is a mathematical model used to represent various types of systems, such as computing and industrial systems. It enables the modeling and verification of the dynamic behavior of discrete-event systems. A Petri net consists of places, which can contain tokens, and transitions. Arcs (carrying a specific value) link places to transitions (outgoing arcs from places) and transitions to places (incoming arcs into places). If unspecified, the value of an arc defaults to 1.

A transition is enabled when the places connected to it by an incoming arc have at least as many tokens as indicated by the arc’s value. When an enabled transition fires (called a "step"), tokens are removed from the upstream places according to the values of the outgoing arcs, and tokens are added to the downstream places based on the values of the incoming arcs. Therefore, the total number of tokens can change with each step.

The goal of this project is to design and develop a tool to simulate a Petri net.



## Quick Start

Now let's simulate a Petri Net, first clone the project from GitLab using this command: 

`git clone [https://github.com/slach31/tp_mapd_pne_editor]`

Import the project on your IDE's workspace, make sure you add JUnit to your build path to avoid getting error messages, since it is used to run tests for the projects.

Now you can simulate your Petri Net, to do so open the petrinet package and run the main file, if you are more curious and want to to test our PetriNet model, you can run the class Main of the org.pneditor.petrinet.models.lachguer.souissi. If you want to test that implementation without the PNEditor,  we created an "InteractiveMain" file in that same package, it'll allow you to chose the number of tokens, the arcs weights using the console so you can get an in-depth understanding of the functioning of our model.

You can see that places, transitions, arcs and tokens are already created and their values are set by default, if you want to change their values and try a simulation with a different number of elements, you can do so by editing the class main. 

If you want to use the PNEditor with our model, make sure to run the Main class in the org.pneditor.editor package and select the lachguerSouissi model (all our adapters are in the org.pneditor.petrinet.models.lachguerSouissi package).


## Development

This project is developed by Soufiane LACHGUER and Mouad SOUISSI, for a university project related to petri nets. It uses Java 23.0.1 and JUnit 5. Files are shared via Gitlab.

If you encounter any technical problems regarding this project, you can write to us at soufiane.lachguer@imt-atlantique.net or at mouad.souissi@imt-atlantique.net

## Explanation of some difficulties and diiferences with the initial conception 

For further explanations regarding this point, make sure to see the PetriExplanations.pdf file 
