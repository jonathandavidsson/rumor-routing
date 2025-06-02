/**
 * Class: Event
 * Description: This class is used for the events in the map. Events are created at random nodes with a chance of 0.001%
 * for every node. Event collaborates a lot with Agent and Request.
 *
 * Date: 28/05/25
 * Authors: Jonathan Davidsson, Joel Lindgren - dv24jon, Liam Danielski c24ldi, Lukasz Polok c24lpk
 */

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

    /**
     * Description: Returns the number of steps for the shortest way to the event
     * @return Returns the shortestWayToEvent integer.
     */
    public int getShortestWayToEvent() {
        return shortestWayToEvent;
    }

    /**
     * Description: Sets the shortestWayToEvent to a new given value.
     * @param shortestWayToEvent The new value of shortestWayToEvent.
     */
    public void setShortestWayToEvent(int shortestWayToEvent) {
        this.shortestWayToEvent = shortestWayToEvent;
    }

    /**
     * Description: Returns the timeStep that the event was created at.
     * @return Returns the event's timeStep.
     */
    public int getTimeStep() {
        return timeStep;
    }

    /**
     * Description: Returns the direction towards the event (for event clones).
     * @return Returns the nodeToEvent.
     */
    public Node getNodeToEvent() {
        return nodeToEvent;
    }

    /**
     * Description: Returns the id of the event.
     * @return Returns the events eventID.
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Description: Returns the node the event was created at.
     * @return Returns the event node.
     */
    public Node getEventNode() {
        return eventNode;
    }

    /**
     * @return a string with data about the event.
     */
    @Override
    public String toString() {
        return "Event ID: " + eventId + "\ntimeStep: " + timeStep +
                "\nEventNode position: " + eventNode.getPosition().toString();
    }

    /**
     * Sets a new node for the event to be in.
     * @param node the node the event will be in.
     */
    public void setNodeToEvent(Node node){
        this.nodeToEvent = node;
    }

    /**
     * Implements an equals function
     * @param obj a object
     * @return true if the events are the same, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Event)) {
            return false;
        }
        Event event=(Event)obj;
        return event.eventId == this.eventId;
    }

    /**
     * Description: Creates a clone of the event.
     * @return Returns the created clone of the event.
     */
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
