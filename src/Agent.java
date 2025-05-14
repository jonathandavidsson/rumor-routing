import java.util.ArrayList;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

    Node currentNode;
    ArrayList<Event> events;
    Set<Node> visitedNodes;
    int lifetime;
    ArrayList<Node> neighbours;
    ArrayList<Position> movable;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new ArrayList<>();
        events.add(event);
    }

    public void traverse(){
        if(lifetime == 50){
            /* Avsluta robot. */
        }
        neighbours = new ArrayList<>();
        movable = new ArrayList<>();
        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);

        if(!movable.isEmpty()){
            Node tempNode = new Node(movable.get((int) (random() * movable.size())), neighbours);
            currentNode = tempNode;
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

    private ArrayList<Position> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Position> movableNodes = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(!visitedNodes.contains(neighbours.get(i))){
                movable.add(neighbours.get(i).getPosition());
            }
        }
        return movableNodes;
    }
}
