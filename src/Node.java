import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Node {

    private ArrayList<Node> neighbours;
    private Position pos;
    private Hashtable<Event, ArrayList<Object>> knownEvents;



    public Node(Position pos){
        this.pos = pos;
        neighbours = new ArrayList<>();
        knownEvents = new Hashtable<>();
    }

    public Position getPosition(){
        return pos;
    }
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
    public Hashtable<Event, ArrayList<Object>> getKnownEvents() {
        return knownEvents;
    }

    public Event getEvent() {
        return knownEvents.keys().nextElement();
    }

    public Set<Event> getEventKeys(){
        return knownEvents.keySet();
    }

    public void setNearestEventDirection(Event event, Position pos){

    }

    public void takeRequest(Request req){

    }


    public boolean equals(Node node) {
        return node.getPosition().equals(pos);
    }


    public void takeAgentInfo(Hashtable<Event, ArrayList<Object>> info){
        ArrayList<Event> keys = new ArrayList<>(info.keySet());
        for(int i = 0; i < info.size(); i++){
            Event e = keys.get(i);
            ArrayList<Object> agentInfo = info.get(e);
            knownEvents.put(e, agentInfo);
        }
    }
    public void addNeighbour(Node node){
        neighbours.add(node);
    }
}
