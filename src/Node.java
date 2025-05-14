import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private ArrayList<Position> neighbours;
    private Position pos;
    /*- events: Table<event: Event, direction: Position, shortest way: int*/

    public Node(Position pos){

    }

    public Position getPosition(){
        return pos;
    }

    public Event getEvent(){
    }

    public void setNearestEventDirection(Event event, Position pos){

    }

    public void takeRequest(Request req){

    }
}
