public class Event {
    private int eventId;
    private int timeStep;
    private int shortestWayToEvent;
    private Node eventNode;
    private Node nodeToEvent;

    public Event(int eventId, int timeStep, Node eventNode, int distance) {
        this.eventId = eventId;
        this.timeStep = timeStep;
        this.eventNode = eventNode;
        shortestWayToEvent = distance;
    }

    public int getShortestWayToEvent() {
        return shortestWayToEvent;
    }

    public void setShortestWayToEvent(int shortestWayToEvent) {
        this.shortestWayToEvent = shortestWayToEvent;
    }

    public int getTimeStep() {
        return timeStep;
    }

    public Node getNodeToEvent() {
        return nodeToEvent;
    }

    public int getEventId() {
        return eventId;
    }

    public Node getEventNode() {
        return eventNode;
    }
    public void printEvent() {

    }

    public void setNodeToEvent(Node node){
        this.nodeToEvent = node;
    }

    public boolean equals(Event event) {
        return eventId == event.eventId;
    }

    @Override
    public int hashCode(){
        return eventId;
    }
}
