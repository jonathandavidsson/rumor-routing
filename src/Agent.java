import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

    Node currentNode;
    Node prevNode;
    ArrayList<Event> events;
    Set<Node> visitedNodes;
    int lifetime;
    ArrayList<Node> neighbours;
    ArrayList<Node> movable;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new ArrayList<>();
        events.add(event);
    }

    public void traverse(){
        if(lifetime == 0){
            return;
        }
        deliverInformationToNode();
        neighbours = new ArrayList<>();
        movable = new ArrayList<>();
        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);

        if(!movable.isEmpty()){
            prevNode = currentNode;
            currentNode = movable.get((int) (random() * movable.size()));
            lifetime = lifetime - 1;
        }else{
            lifetime = 0;
        }

    }

    public Node getNode(){
        return null;
    }

    public Set<Node> getVisitedNodes(){
        return visitedNodes;
    }

    public ArrayList<Event> getEvent(){
        return events;
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
        /*Lämnar över information om event och vars agenten kom ifrån till nod.*/
        int distance = visitedNodes.size();
        currentNode.takeAgentInfo(events, prevNode, distance);
    }

}
