import java.security.PrivateKey;

public class Event {
    private int eventId;
    private int timeStep;
    private int shortestWayToEvent;
    private Position eventPosition;
    private Node nextStepToEvent;

    public Event(int eventId, int timeStep, Position eventPosition) {
        this.eventId = eventId;
        this.timeStep = timeStep;
        this.eventPosition = eventPosition;
        shortestWayToEvent = 0;
    }

    public int getShortestWayToEvent() {
        return shortestWayToEvent;
    }

    public void setShortestWayToEvent(int shortestWayToEvent) {
        this.shortestWayToEvent = shortestWayToEvent;
    }

    public void setNextStepToEvent(Node node) {
        nextStepToEvent = node;
    }

    public Node getNextStepToEvent(){
        return nextStepToEvent;
    }

    public int getTimeStep() {
        return timeStep;
    }

    public int getEventId() {
        return eventId;
    }

    public Position getEventPosition() {
        return eventPosition;
    }
    public void printEvent() {

    }

    public boolean equals(Event event) {
        return eventId == event.eventId;
    }


}
