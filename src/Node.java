import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Node {

    private ArrayList<Node> neighbours;
    private Position pos;
    private Event event;
    Hashtable<Event, ArrayList<Object>> eventInfo;



    public Node(Position pos){
        this.pos = pos;
        neighbours = new ArrayList<>();
    }

    public Position getPosition(){
        return pos;
    }

    public Event getEvent(){
        return event;
    }

    public void setNearestEventDirection(Event event, Position pos){

    }

    public void takeRequest(Request req){

    }
    public void takeAgentInfo(Event event, Node node, int distance){
        ArrayList<Object> info = new ArrayList<>();
        info.add(node);
        info.add(distance);
        eventInfo.put(event, info);
    }
    public void addNeighbour(Node node){
        neighbours.add(node);
    }
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
}
