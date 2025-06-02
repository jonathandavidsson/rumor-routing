import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static java.lang.Math.random;

/**
 * Class: Agent
 * Description: This class is an agent that traverses the map. An agent will spawn at an event node.
 * The agent will gather the info from the event and later start traversing the map and delivering the info
 * to every node that the agent walks on. If the agent walks on a node that contains info about an event that the
 * agent does not know about the agent will pick up that information and continue spreading it to other nodes.
 * The agent lives for a total of 50 timesteps.
 *
 * Date: 28/05/25
 * Authors: Jonathan Davidsson,Joel Lindgren - dv24jon, Liam Danielski c24ldi, Lukasz Polok c24lpk
 */
public class Agent implements Messenger{

    private Node currentNode;
    private Node prevNode;
    private ArrayList<Event> events;
    private Set<Node> visitedNodes = new HashSet<>();
    private int lifetime;
    private ArrayList<Node> neighbours;
    private ArrayList<Node> movable;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new ArrayList<>();
        ArrayList<Object> theInfo = new ArrayList<>();
        events.add(event.cloneEvent());
        this.lifetime = 50;
        currentNode.enqueue(this);
    }

    /**
     * Description: Checks if the agent is dead/can't move any longer.
     * @return returns a boolean, true if the agent is dead and false if it's not.
     */
    public boolean isDead(){
        return this.lifetime == 0;
    }

    /**
     * Description: Moves the agent to a movable position, if the agent cannot move anymore the
     * agent will die.
     */
    public boolean traverse(){
        if(lifetime == 0){
            return false;
        }
        if(!currentNode.dequeue(this)){
            return false;
        }
        checkEventsInNode();
        putEventsInNode();

        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);


        if(!movable.isEmpty()){ //Flyttar på agenten så länge det finns en nod som den kan gå till (kan ha fastnat i ett hörn t.ex).
            prevNode = currentNode;
            Node nextNode = movable.get((int) (random() * movable.size()));
            nextNode.enqueue(this);
            currentNode = nextNode;
            lifetime = lifetime - 1;
            updateDistance();
            return true;
        }else{
            lifetime = 0; //Om roboten inte kan röra sig sätter vi lifetime till 0 (agenten dör)
            return false;
        }
    }

    /**
     * Description: Returns the arraylist containing every event the agent holds.
     * @return Returns the arraylist of events.
     */
    public ArrayList<Event> getEvents(){
        return events;
    }

    /**
     * Description: Updates the distance and nodeToEvent with each step the agent takes.
     *
     */
    private void updateDistance() {
        for(int i = 0; i < events.size(); i++){
            Event e = events.get(i);
            if(currentNode.equals(e.getEventNode())){
                e.setShortestWayToEvent(0);
                e.setNodeToEvent(null);
            } else if (prevNode != null) {
                int currentDistance = e.getShortestWayToEvent();

                if (prevNode.equals(e.getEventNode())) {
                    e.setShortestWayToEvent(1);
                    e.setNodeToEvent(prevNode);
                } else if (currentDistance >= 0) {
                    e.setShortestWayToEvent(currentDistance + 1);
                    e.setNodeToEvent(prevNode);
                }
            }
        }
    }

    /**
     * Description: Checks what events are in the current node, if the node contains events that the
     * agent does not have, the agent will pick up the event info for that event from the node.
     */
    private void checkEventsInNode() {
        ArrayList<Event> eventsInNode = currentNode.getKnownEvents();
        for(Event nodeEvent: eventsInNode) {
            boolean hasEvent = false;
            for (Event agentEvent : events) {
                if (agentEvent.equals(nodeEvent)) {
                    if (nodeEvent.getShortestWayToEvent() < agentEvent.getShortestWayToEvent()
                            || agentEvent.getShortestWayToEvent() == -1) {
                        agentEvent.setShortestWayToEvent(nodeEvent.getShortestWayToEvent());
                        agentEvent.setNodeToEvent(nodeEvent.getNodeToEvent());
                    }
                    hasEvent = true;
                    break;
                }
            }
            if(!hasEvent){
                events.add(nodeEvent.cloneEvent());
            }
        }
    }

    /**
     * Description: The agent delivers all event information to the node that the agent possesses. If the node
     * already have that information it will skip that particular event.
     */
    private void putEventsInNode(){
        ArrayList<Event> eventsInNode = currentNode.getKnownEvents();
        for(Event agentEvent : events){
            boolean alreadyInNode = false;
            Event bestEventInNode = null;
            for(Event nodeEvent : eventsInNode){
                if(nodeEvent.equals(agentEvent)){
                    bestEventInNode = nodeEvent;
                    alreadyInNode = true;
                    break;
                }
            }
            if(alreadyInNode){
                if(agentEvent.getShortestWayToEvent() < bestEventInNode.getShortestWayToEvent()
                || bestEventInNode.getShortestWayToEvent() == -1){
                    bestEventInNode.setShortestWayToEvent(agentEvent.getShortestWayToEvent());
                    bestEventInNode.setNodeToEvent(agentEvent.getNodeToEvent());
                }
            }else{
                currentNode.setNewEvent(agentEvent.cloneEvent());
            }
        }
    }

    /**
     * Description: Returns the current node that the agent is on.
     * @return Returns the current node.
     */
    public Node getNode(){
        return currentNode;
    }

    /**
     * Description: Returns a set containing the nodes that the agent has already been on.
     * @return Returns the nodes the agent has visited.
     */
    public Set<Node> getVisitedNodes(){
        return visitedNodes;
    }

    /**
     * Description: Returns all movable positions that the agent can walk to.
     * @param neighbours all the current nodes neighbours.
     * @return Returns an arraylist with all movable nodes.
     */
    public ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(!visitedNodes.contains(neighbours.get(i))){
                movableNodes.add(neighbours.get(i));
            }
        }
        return movableNodes;
    }


}
