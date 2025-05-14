public class Event {
    int eventId;
    int timeStep;
    int shortestWayToEvent;
    Position eventPosition;
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
}
