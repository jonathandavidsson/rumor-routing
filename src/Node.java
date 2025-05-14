import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Node {

    private ArrayList<Position> neighbours;
    private Position pos;
    private Event event;
    //Hashtable<Event, ArrayList<Object>> eventInfo;
    /*- events: Table<event: Event, direction: Position, shortest way: int*/


    public Node(Position pos, ArrayList<Position> neighbours){


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
