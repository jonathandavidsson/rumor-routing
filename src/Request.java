import java.util.ArrayList;

public class Request implements Messenger{

    private Node originNode;
    private Event event;
    private int lifeTime;
    private ArrayList<Node> path;

    public Request (){

    }

    public void traverse(){

    }

    public Event getEvent(){
        return event;
    }
    public Node getNode(){
        return originNode;
    }

    private boolean hasReachedEvent(){
        return true;
    }
}
