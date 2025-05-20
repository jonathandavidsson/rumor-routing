import java.util.ArrayList;
import java.util.Stack;

public class Request {

    private Node originNode;
    private Event event;
    private int lifeTime = 15;
    private Stack<Node> path;
    private boolean goBack;

    public Request (Node node, Event event){
        this.event = event;
        originNode = node;
        path = new Stack<>();
        path.push(originNode);
        goBack = false;

    }

    /**
     * first look for event in node,
     * else look if the node knows a event to the node
     * else takes a random path.
     */
    public void traverse(){

        if (lifeTime <= 0){
            throw new IsDeadException();
        }

        if (goBack){
            if (hasReachedOriginNode()){
                traverseBackToNode();
            }
            lifeTime--;
            return;
        }

        if (hasReachedEvent()){
            getEventInNode();
            traverseBackToNode();
            goBack = true;
            lifeTime--;
            return;
        }

        if(!followAPathToEvent()){
            moveToRandomNode();
            lifeTime--;
        }
    }

    private boolean hasReachedOriginNode(){
        return path.peek().equals(originNode);
    }

    private boolean followAPathToEvent() {
        for (Event event: getCurrentNode().getKnownEvents()) {
            //if the node request is on knows a path to a event then this function follows it.
            if (event.equals(this.event)){
                path.push(event.getNodeToEvent());
                return true;
            }
        }
        return false;
    }

    private void traverseBackToNode(){
        if (!path.empty()) {
            path.pop();
        }
        else throw new RuntimeException();
    }
    private void getEventInNode(){
        //TODO nothing needs to be done, because the request already knows the event.
    }

    private void moveToRandomNode(){
        ArrayList<Node> movableNeighbours = getMovableNeighbours(getCurrentNode().getNeighbours());

        int newNode = (int)(movableNeighbours.size() * Math.random());
        path.push(movableNeighbours.get(newNode));

    }

    private Node getCurrentNode() {
        return path.peek();
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
    public Node getNode(){
        return originNode;
    }
    public boolean isDead(){
        return lifeTime == 0;
    }

}
