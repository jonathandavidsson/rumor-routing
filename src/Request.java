import java.util.ArrayList;
import java.util.Stack;

/**
 * Request is a class that navigates in the class map. The request is created at a specified node in
 * the map. The purpose of the request is to find the origin of an event and carry the info from that event back to
 * the origin of the Request.
 *
 * Date: 28/05/25
 * @author: Jonathan Davidsson, Joel Lindgren - dv24jon, Liam ..., Lukasz ...
 */
public class Request {

    private final Node originNode;
    private final Event event;
    private int lifeTime;
    private Stack<Node> path;
    private Node currentNode;
    private boolean goBack;

    public Request (Node node, Event event){
        this.event = event;
        originNode = node;
        path = new Stack<>();
        path.push(originNode);
        currentNode = originNode;
        lifeTime = 45;
        goBack = false;

    }

    /**
     * This function tries to traverse in the Map by, looking if it stands on the eventNode,
     * otherwise it looks if the node it stands on knows a path to the event, otherwise it takes a random path.
     * If the request is dead it throws and exception, and if it has found the originNode it goes back.
     * If the function hasnt found the node the event Originated from in 45 steps, it dies.
     * @throws IsDeadException If the request is dead and traverse is called on it.
     * @implNote the request doesn't lose life when traversing back to the originNode
     * @return true if the request is back at its OriginNode
     */
    public boolean traverse(){

        if (lifeTime <= 0){
            throw new IsDeadException();
        }

        if (goBack){
            if (!hasReachedOriginNode()){
                traverseBackToNode();
            } else {
                lifeTime = 0;
                return true;
            }
            return false;
        }

        if (hasReachedEvent()){
            getEventInNode();
            traverseBackToNode();
            goBack = true;
            lifeTime--;
            return false;
        }

        if(!followAPathToEvent()){
            moveToRandomNode();
            lifeTime--;
            return false;
        }

        return false;
    }


    /*
     * Checks if the request are on the same position as the originNode.
     * @return true if the requests position are the same as where the request spawned.
     */
    private boolean hasReachedOriginNode(){
        return currentNode.equals(originNode);
    }

    /*
     * Traverses back, takes one step back to where it came, following its own path.
     */
    private void traverseBackToNode(){
        if (!path.empty()) {
            path.pop();
            if (!path.isEmpty()) {
                currentNode = path.peek();
            }
        }
    }

    /*
     * Try to follow nodes that knows where the event, the request is trying to find, is.
     * @return if the request takes a step the function returns true, otherwise it returns false.
     */
    private boolean followAPathToEvent() {
        ArrayList<Event> knownEvents = currentNode.getKnownEvents();
        if (!knownEvents.isEmpty())
        {
            Event matchingEvent = null;
            for (Event knownEvent: knownEvents) {
                //if the node request is on a path to an event then this function follows it.
                if (knownEvent.equals(this.event)){
                    matchingEvent = knownEvent;
                    break;
                }
            }

            if(matchingEvent != null && matchingEvent.getNodeToEvent() != null){
                path.push(matchingEvent.getNodeToEvent());
                currentNode = matchingEvent.getNodeToEvent();
                lifeTime--;
                return true;
            }
        }
        return false;
    }



    /*
     * This function is spiritual, but simulates the request taking info from the origin of an event,
     * but because the Request already knows the whole event, nothing happens.
     */
    private void getEventInNode(){
        //Does nothing
    }

    /*
     * trys to move to a random neighbour to the current node. Tries to move to a node the request hasn't been on yet,
     * but can move to such a node otherwise.
     * If it cant, the request dies.
     */
    private void moveToRandomNode(){
        ArrayList<Node> movableNeighbours = getMovableNeighbours(getCurrentNode().getNeighbours());

        if (!movableNeighbours.isEmpty()){
            int newNode = (int)(movableNeighbours.size() * Math.random());
            path.push(movableNeighbours.get(newNode));
            currentNode = movableNeighbours.get(newNode);
        }
        else {
            ArrayList<Node> neighbours = getCurrentNode().getNeighbours();

            if (!neighbours.isEmpty()){
                int newNode = (int)(neighbours.size() * Math.random());
                path.push(neighbours.get(newNode));
                currentNode = neighbours.get(newNode);
            }
            else {
                lifeTime = 0; //The request dies if it gets stuck
            }
        }

    }


    /*
     * This function returns the movable neighbours from a set of neighbours from a node.
     * @implNote Agent has almost the same private function.
     * @param neighbours of a node
     * @return Movable Neighbours
     */
    private ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();

        for (Node node: neighbours) {
            if (!path.contains(node)){
                movableNodes.add(node);
            }
        }
        return movableNodes;
    }

    /*
     * returns true if the request are on the same node as the orgin of the event the request
     * searches for.
     * @return true if request are on the same node as the orgin of a event.
     */
    private boolean hasReachedEvent(){
        if (currentNode.equals(event.getEventNode())) {
            return true;
        }

        for (Event event: getCurrentNode().getKnownEvents()) {
            if(event.equals(this.event) && event.getShortestWayToEvent() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * @return the event the request searches for.
     */
    public Event getEvent(){
        return event;
    }

    /**
     * @return the node the request was created on.
     */
    public Node getOrginNode(){
        return originNode;
    }

    /**
     * @return The node the request is on.
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * @return true if the request is dead, otherwise it returns false.
     */
    public boolean isDead(){
        return lifeTime <= 0;
    }


    /**
     * Prints the eventInfo of a request and the requests originNode.
     */
    public void printRequestEvent(){
        System.out.println("RequestMessage from Node: " + getOrginNode().getPosition().toString() +
                " found:" +
                "\n" + getEvent().toString());
    }

}
