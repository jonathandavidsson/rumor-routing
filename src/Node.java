import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Class: Node
 * Description: Node i a very important class to this program. Every node has a specific position in the map. Agents, events and requests
 * can be created at a node, and agent and request uses nodes to traverse through the map.
 *
 * Date: 28/05/25
 * Authors: Jonathan Davidsson, Joel ..., Liam ..., Lukasz ...
 */
public class Node {

    private ArrayList<Node> neighbours;
    private Position pos;
    private ArrayList<Event> knownEvents;



    public Node(Position pos){
        this.pos = pos;
        neighbours = new ArrayList<>();
        knownEvents = new ArrayList<>();
    }

    /**
     * Description: Returns the postion of the node.
     * @return Returns the position.
     */
    public Position getPosition(){
        return pos;
    }

    /**
     * Returns the neighbours of the node.
     * @return Return an arraylist containing every neighbouring node of this node.
     */
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    /**
     * Description: Returns the known events in the node.
     * @return Returns an arraylist containing every event known to the specific node.
     */
    public ArrayList<Event> getKnownEvents() {
        return knownEvents;
    }

    /**
     * Description: Adds events to the nodes arraylist of known events.
     * @param event The event to be added.
     */
    public void setNewEvent(Event event) {
        knownEvents.add(event);
    }

    public void setNearestEventDirection(Event event, Position pos){

    }

    //public void takeRequest(Request req){

    //}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node)) {
            return false;
        }
        Node node=(Node)obj;
        return node.getPosition().equals(getPosition());
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

//    public void takeAgentInfo(Hashtable<Event, ArrayList<Object>> info){
//        ArrayList<Event> keys = new ArrayList<>(info.keySet());
//        for(int i = 0; i < info.size(); i++){
//            Event e = keys.get(i);
//            ArrayList<Object> agentInfo = info.get(e);
//            knownEvents.put(e, agentInfo);
//        }
//    }

    /**
     * Description: Adds a neighbour to the nodes arraylist of neighbours.
     * @param node the node to be added to the neighbours arraylist.
     */
    public void addNeighbour(Node node){
        neighbours.add(node);
    }
}
