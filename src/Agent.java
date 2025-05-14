import java.util.ArrayList;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

    Node currentNode;
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
            /* Avsluta robot. */
        }
        neighbours = new ArrayList<>();
        movable = new ArrayList<>();
        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);

        if(!movable.isEmpty()){
            currentNode = movable.get((int) (random() * movable.size()));
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
}
