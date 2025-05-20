import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    Event event;
    Node node;
    @BeforeEach
    void setup(){
        node = new Node(new Position(1,1));
        event = new Event(0, 0, node, 0);
    }

    @org.junit.jupiter.api.Test
    void getShortestWayToEvent() {
        Event event1 = new Event(0, 2, new Node(new Position(3, 5)), 0);
        Event event2 = new Event(0, 2, new Node(new Position(3, 5)), 0);

        ArrayList<Event> arrayList = new ArrayList<>();
        arrayList.add(event2);
        System.out.println(arrayList.contains(event1));
    }

    @org.junit.jupiter.api.Test
    void setShortestWayToEvent() {
        Event event = new Event(0, 0, new Node(new Position(1,2)), 0 );
        event.setShortestWayToEvent(1);
        int newDistance = event.getShortestWayToEvent();
        assertNotEquals(0, newDistance, "The distance should be updated!");
    }

    @org.junit.jupiter.api.Test
    void getTimeStep() {
        int timestep = event.getTimeStep();
        assertEquals(0, timestep, "Timestep should be 0!");
    }

    @org.junit.jupiter.api.Test
    void getEventId() {
        int eventId = event.getEventId();
        assertEquals(0, eventId, "The eventId should be 0!");
    }

    @org.junit.jupiter.api.Test
    void getEventNode() {
        Node n = event.getEventNode();
        assertEquals(n, this.node, "The nodes should be the same!");
    }

    @org.junit.jupiter.api.Test
    void printEvent() {
    }

    @org.junit.jupiter.api.Test
    void cloneEvent(){
        Event e = event.cloneEvent();

        assertNotSame(e, this.event, "The events should be different objects!");

        assertEquals(e.getTimeStep(), this.event.getTimeStep(), "Timestep should be the same!");
        assertEquals(e.getEventId(), this.event.getEventId(), "EventId should be the same!");
        assertEquals(e.getEventNode(), this.event.getEventNode(), "EventNode should be the same!");
        assertEquals(e.getShortestWayToEvent(), this.event.getShortestWayToEvent(), "Distance should be the same!");
    }

    @org.junit.jupiter.api.Test
    void testEqualsTrue() {
        Event e = event.cloneEvent();
        boolean test = event.equals(e);
        assertTrue(test, "The events should be the equal!");
    }

    @org.junit.jupiter.api.Test
    void testEqualsFalse(){
        Event e = new Event(1, 1, node, 0);
        boolean test = event.equals(e);
        assertFalse(test, "The events should not be equal!");
    }


}