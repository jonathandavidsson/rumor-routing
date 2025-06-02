import java.util.*;

/**
 * Class: Node
 * Description: Node i a very important class to this program. Every node has a specific position in the map. Agents, events and requests
 * can be created at a node, and agent and request uses nodes to traverse through the map.
 *
 * Date: 28/05/25
 * Authors: Jonathan Davidsson, Joel Lindgren - dv24jon, Liam Danielski c24ldi, Lukasz Polok c24lpk
 */
public class Node {
    private Queue<Messenger> messengers;
    private ArrayList<Node> neighbours;
    private Position pos;
    private ArrayList<Event> knownEvents;



    public Node(Position pos){
        this.pos = pos;
        neighbours = new ArrayList<>();
        knownEvents = new ArrayList<>();
        messengers = new LinkedList<>();
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


    /**
     * Description: Adds a neighbour to the nodes arraylist of neighbours.
     * @param node the node to be added to the neighbours arraylist.
     */
    public void addNeighbour(Node node){
        neighbours.add(node);
    }

    public void enqueue(Messenger messenger){
        messengers.offer(messenger);
    }

    public Queue<Messenger> getMessengersInQueue(){
        return messengers;
    }

    public boolean dequeue(Messenger messenger){
        if(!messengers.isEmpty() && messengers.peek().equals(messenger)){
            messengers.poll();
            return true;
        }
        return false;
    }

}
