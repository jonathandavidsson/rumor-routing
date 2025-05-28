import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

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
    }

    public boolean isDead(){
        return this.lifetime == 0;
    }

    public void traverse(){
        if(lifetime == 0){ //Om lifetime är noll, sluta gå
            return;
        }
        checkEventsInNode();

        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);


        if(!movable.isEmpty()){ //Flyttar på agenten så länge det finns en nod som den kan gå till (kan ha fastnat i ett hörn t.ex).
            prevNode = currentNode;
            currentNode = movable.get((int) (random() * movable.size()));
            lifetime = lifetime - 1;
            updateDistance();
        }else{
            lifetime = 0; //Om roboten inte kan röra sig sätter vi lifetime till 0 (agenten dör)
        }
        putEventsInNode();
    }
    public ArrayList<Event> getEvents(){
        return events;
    }

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

    public Node getNode(){
        return currentNode;
    }

    public Set<Node> getVisitedNodes(){
        return visitedNodes;
    }

    private ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(!visitedNodes.contains(neighbours.get(i))){
                movableNodes.add(neighbours.get(i));
            }
        }
        return movableNodes;
    }


}
