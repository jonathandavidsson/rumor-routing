import java.util.ArrayList;
import java.util.Stack;

public class Request implements Messenger{

    private Node originNode;
    private Event event;
    private int lifeTime = 15;
    private Stack<Node> path;

    public Request (Node node, Event event){
        this.event = event;
        originNode = node;
        path = new Stack<>();
        path.add(originNode);

    }

    /**
     * first look for event in node, else take random
     */
    public void traverse(){

        if (path.peek().getKnownEvents().containsKey(event)) {
            path.add((Node) path.peek().getKnownEvents().get(event).get(0));
        }else {

        }

        ArrayList<Node> movableNeighbours = getMovableNeighbours(path.peek().getNeighbours());

        int newNode = (int)(path.peek().getNeighbours().size() * Math.random());
        if (newNode == 0) {

        }

        originNode = path.peek().getNeighbours().get(newNode);


    }

    private ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(!path.contains(neighbours.get(i))){
                movableNodes.add(neighbours.get(i));
            }
        }
        return movableNodes;
    }

    public Event getEvent(){
        return event;
    }
    public Node getNode(){
        return originNode;
    }

    private boolean hasReachedEvent(){
        ArrayList<Event> nodeEvents = new ArrayList<>(path.peek().getEventKeys());
        for(int i = 0; i < nodeEvents.size(); i++){
            Event e = nodeEvents.get(i);
            if(e == event){
                return true;
            }
        }
        return false;
    }
}
