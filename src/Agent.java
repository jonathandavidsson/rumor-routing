import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

    Node currentNode;
    Node prevNode;
    Hashtable<Event, ArrayList<Object>> events;
    Set<Node> visitedNodes = new HashSet<>();
    int lifetime;
    ArrayList<Node> neighbours;
    ArrayList<Node> movable;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new Hashtable<>();
        ArrayList<Object> theInfo = new ArrayList<>();
        theInfo.add(currentNode);
        theInfo.add(0);
        events.put(event, theInfo);
    }

    public void traverse(){
        if(lifetime == 0){ //Om lifetime är noll, sluta gå
            return;
        }
        deliverInformationToNode(); //Denna funktion lämnar över agentens information till noden.
        checkEventsInNode();
        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);

        if(!movable.isEmpty()){ //Flyttar på agenten så länge det finns en nod som den kan gå till (kan ha fastnat i ett hörn t.ex).
            prevNode = currentNode;
            currentNode = movable.get((int) (random() * movable.size()));
            lifetime = lifetime - 1;
        }else{
            lifetime = 0; //Om roboten inte kan röra sig sätter vi lifetime till 0 (agenten dör)
        }
        updateDistance();

    }

    private void updateDistance() {
        ArrayList<Event> keys = new ArrayList<>(events.keySet());
        for(int i = 0; i < keys.size(); i++){ //Tillsist uppdaterar agenten distansen på alla sina events som agenten håller med + 1 (pga att agenten går ett steg).
            Event e = keys.get(i);
            ArrayList<Object> eventInfo = events.get(e);
            eventInfo.set(0, prevNode);
            eventInfo.set(1, (int) eventInfo.get(1) + 1);
        }
    }

    private void checkEventsInNode() {
        ArrayList<Event> nodeKeys = new ArrayList<>(currentNode.getEventKeys());
        for(int i = 0; i < nodeKeys.size(); i++){ //Denna for-loop kollar den nya nodens events efter nya, och om det finns lägger den in den infon i
            Event e = nodeKeys.get(i);            //dess egna hashtabell "events".
            if(!events.containsKey(e)){
                ArrayList<Object> nodeInfo = currentNode.getKnownEvents().get(e);
                events.put(e, nodeInfo);
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

    private void deliverInformationToNode(){
        currentNode.takeAgentInfo(events);
    }

}
