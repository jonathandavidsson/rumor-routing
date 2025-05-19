import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @org.junit.jupiter.api.Test
    void getShortestWayToEvent() {
        Event event1 = new Event(0, 2, new Node(new Position(3, 5)));
        Event event2 = new Event(0, 2, new Node(new Position(3, 5)));

        ArrayList<Event> arrayList = new ArrayList<>();
        arrayList.add(event2);
        System.out.println(arrayList.contains(event1));
    }

    @org.junit.jupiter.api.Test
    void setShortestWayToEvent() {
    }

    @org.junit.jupiter.api.Test
    void getTimeStep() {
    }

    @org.junit.jupiter.api.Test
    void getEventId() {
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