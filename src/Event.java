public class Event {
    int eventId;
    int timeStep;
    int shortestWayToEvent;
    Position eventPosition;
    public Event() {

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
