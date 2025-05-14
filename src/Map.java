import java.util.ArrayList;

public class Map {
    ArrayList<Node> theInfo;
    ArrayList<Event> Events;
    ArrayList<Node> requestNodes;

    public Map() {

    }

    public ArrayList<Node> getMap() {
        return theInfo;
    }

    public void updateEvents() {

    }

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public ArrayList<Node> getRequestNodes() {
        return requestNodes;
    }
}
