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
    public void takeAgentInfo(Hashtable<Event, ArrayList<Object> info){
        ArrayList<Event> keys = new ArrayList<>(info.keySet());
        for(int i = 0; i < info.size(); i++){
            Event e = keys.get(i);
            ArrayList<Object> agentInfo = info.get(e);
            eventInfo.put(e, agentInfo);
        }
    }
    public void addNeighbour(Node node){
        neighbours.add(node);
    }
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
}
