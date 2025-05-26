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
        events.add(event);
        this.lifetime = 50;
    }

    public void traverse(){
        if(lifetime == 0){ //Om lifetime är noll, sluta gå
            return;
        }
        checkEventsInNode();

        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);


        if(!movable.isEmpty()){//Flyttar på agenten så länge det finns en nod som den kan gå till (kan ha fastnat i ett hörn t.ex).
            prevNode = currentNode;
            currentNode = movable.get((int) (random() * movable.size()));
            lifetime = lifetime - 1;
        }else{
            lifetime = 0; //Om roboten inte kan röra sig sätter vi lifetime till 0 (agenten dör)
        }
        putEventsInNode();
        updateDistance();
    }
    public ArrayList<Event> getEvents(){
        return events;
    }

    private void updateDistance() {
        for(int i = 0; i < events.size(); i++){
            Event e = events.get(i);
            e.setShortestWayToEvent(e.getShortestWayToEvent() + 1);
            e.setNodeToEvent(prevNode);
        }
    }

    private void checkEventsInNode() {
        ArrayList<Event> eventsInNode = currentNode.getKnownEvents();
        for(int i = 0; i < eventsInNode.size(); i++){
            Event e = eventsInNode.get(i);
            if(!events.contains(e)){
               events.add(e);
            }
        }
    }
    private void putEventsInNode(){
        ArrayList<Event> eventsInNode = currentNode.getKnownEvents();
        for(int i = 0; i < events.size(); i++){
            Event agentEvent = events.get(i);
            boolean alreadyInNode = false;
            for(int j = 0; j < eventsInNode.size(); j++){
                Event nodeEvent = eventsInNode.get(j);
                if(agentEvent.equals(nodeEvent)){
                    alreadyInNode = true;
                    break;
                }
            }
            if(!alreadyInNode){
                eventsInNode.add(agentEvent.cloneEvent());
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
