import java.util.ArrayList;
import java.util.Stack;

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
     * first look for event in node,
     * else look if the node knows a event to the node
     * else takes a random path.
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

    /**
     * Returns true
     * @return
     */
    private boolean hasReachedOriginNode(){
        return currentNode.equals(originNode);
    }

    private boolean followAPathToEvent() {
        ArrayList<Event> knownEvents = currentNode.getKnownEvents();
        if (!knownEvents.isEmpty())
        {
            Event matchingEvent = null;
            for (Event knownEvent: knownEvents) {
                //if the node request is on knows a path to a event then this function follows it.
                if (knownEvent.equals(this.event)){
                    matchingEvent = knownEvent;
                    break;
                }
            }

            if(matchingEvent != null && matchingEvent.getNodeToEvent() != null){
                path.push(matchingEvent.getNodeToEvent());
                currentNode = matchingEvent.getNodeToEvent();
//                System.out.println("Follow path to event");
//                System.out.println("found in(" + getCurrentNode().getPosition().toString() +
//                        ")EventID" + matchingEvent.getEventId() + " - pathToEvent: " +
//                        matchingEvent.getNodeToEvent().getPosition().toString());
                lifeTime--;
                return true;
            }
        }
        return false;
    }

    private void traverseBackToNode(){
        if (!path.empty()) {
            path.pop();
            if (!path.isEmpty()) {
                currentNode = path.peek();
            }
        }
    }
    private void getEventInNode(){
        //TODO nothing needs to be done, because the request already knows the event.
    }

    private void moveToRandomNode(){
        ArrayList<Node> movableNeighbours = getMovableNeighbours(getCurrentNode().getNeighbours());

        if (!movableNeighbours.isEmpty()){
            int newNode = (int)(movableNeighbours.size() * Math.random());
            path.push(movableNeighbours.get(newNode));
            currentNode = movableNeighbours.get(newNode);
        }
        else {
            lifeTime = 0; //The request dies if it gets stuck
        }

    }

    public Node getCurrentNode() {
        return currentNode;
    }

    private ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();

        for (Node node: neighbours) {
            if (!path.contains(node)){
                movableNodes.add(node);
            }
        }
        return movableNodes;
    }

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

    public Event getEvent(){
        return event;
    }
    public Node getOrginNode(){
        return originNode;
    }
    public boolean isDead(){
        return lifeTime <= 0;
    }

    public void printRequestEvent(){
        System.out.println("RequestMessage from Node: " + getOrginNode().getPosition().toString() +
                " found:" +
                "\n" + getEvent().toString());
    }

}
