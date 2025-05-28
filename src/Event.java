public class Event {
    private final int eventId;
    private int timeStep;
    private int shortestWayToEvent;
    private final Node eventNode;
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

    @Override
    public String toString() {
        return "Event ID: " + eventId + "\ntimeStep: " + timeStep +
                "\nEventNode position: " + eventNode.getPosition().toString();
    }

    public void setNodeToEvent(Node node){
        this.nodeToEvent = node;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Event)) {
            return false;
        }
        Event event=(Event)obj;
        return event.eventId == this.eventId;
    }

    public Event cloneEvent(){
        Event clone = new Event(
                  this.eventId,
                  this.timeStep,
                  this.eventNode,
                  this.shortestWayToEvent);
        clone.setNodeToEvent(this.nodeToEvent);
        return clone;
    }

    @Override
    public int hashCode(){
        return eventId;
    }


}
