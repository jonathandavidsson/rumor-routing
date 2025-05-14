import java.util.ArrayList;
import java.util.Set;

public class Agent {

    Node currentNode;
    ArrayList<Event> events;
    Set<Node> visitedNodes;
    int lifetime;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new ArrayList<>();
        events.add(event);
    }

    public void traverse(){

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
}
