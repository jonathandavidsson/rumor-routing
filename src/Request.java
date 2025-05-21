import java.util.ArrayList;
import java.util.Stack;

public class Request {

    private Node originNode;
    private Event event;
    private int lifeTime = 15;
    private Stack<Node> path;
    private Node currentNode;
    private boolean goBack;

    public Request (Node node, Event event){
        this.event = event;
        originNode = node;
        path = new Stack<>();
        path.push(originNode);
        currentNode = originNode;
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
            }
            System.out.println("go back");
            return false;
        }

        if (hasReachedEvent()){
            getEventInNode();
            traverseBackToNode();
            goBack = true;
            lifeTime--;
            System.out.println("has reached event");
            return false;
        }

        if(!followAPathToEvent()){
            moveToRandomNode();
            lifeTime--;
            System.out.println("follow path to event");
            return false;
        }
        if ((goBack && originNode.equals(currentNode )) || originNode.equals(event.getEventNode())){
            System.out.println("WHATTT");
            return true;
        }
        return false;
    }

    private boolean hasReachedOriginNode(){
        return currentNode.equals(originNode);
    }

    private boolean followAPathToEvent() {
        if (!getCurrentNode().getKnownEvents().isEmpty())
        {
            for (Event event: getCurrentNode().getKnownEvents()) {
                //if the node request is on knows a path to a event then this function follows it.
                if (event.equals(this.event) && event.getNodeToEvent() != null){
                    path.push(event.getNodeToEvent());
                    currentNode = event.getNodeToEvent();
                    return true;
                }
            }
        }

        return false;
    }

    private void traverseBackToNode(){
        if (!path.empty()) {
            currentNode = path.pop();
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
            lifeTime = 0;
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

}
