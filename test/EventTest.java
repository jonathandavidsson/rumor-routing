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
    void getEventPosition() {
    }

    @org.junit.jupiter.api.Test
    void printEvent() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }
}