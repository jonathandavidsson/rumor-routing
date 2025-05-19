public class Event {
    private int eventId;
    private int timeStep;
    private int shortestWayToEvent;
    private Node eventNode;

    public Event(int eventId, int timeStep, Node eventNode) {
        this.eventId = eventId;
        this.timeStep = timeStep;
        this.eventNode = eventNode;
        shortestWayToEvent = 0;
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

    public int getEventId() {
        return eventId;
    }

    public Node getEventPosition() {
        return eventNode;
    }
    public void printEvent() {

    }

    public boolean equals(Event event) {
        return eventId == event.eventId;
    }

    @Override
    public int hashCode(){
        return eventId;
    }
}
