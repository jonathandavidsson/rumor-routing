import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void TestgetPosition() {
        Position pos = new Position(1,1);
        Node node = new Node(pos);

        assertEquals(pos, node.getPosition());
    }

    @Test
    void TestgetNeighbours() {
        Position centerPos = new Position(0, 0);
        Position neighbor1Pos = new Position(1, 0);
        Position neighbor2Pos = new Position(0, 1);

        Node center = new Node(centerPos);
        Node neighbor1 = new Node(neighbor1Pos);
        Node neighbor2 = new Node(neighbor2Pos);

        center.addNeighbour(neighbor1);
        center.addNeighbour(neighbor2);

        ArrayList<Node> neighbours = center.getNeighbours();

        assertTrue(neighbours.contains(neighbor1));
        assertTrue(neighbours.contains(neighbor2));
    }


    @Test
    void TestgetKnownEvents() {
        Position pos = new Position(0, 0);
        Node node = new Node(pos);

        Event event1 = new Event(1, 10, node, 5);
        Event event2 = new Event(2, 10, node, 8);

        node.setNewEvent(event1);
        node.setNewEvent(event2);

        ArrayList<Event> events = node.getKnownEvents();
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
    }

    @Test
    void TestsetNewEvent() {
        Position pos = new Position(0, 0);
        Node node = new Node(pos);

        Event event1 = new Event(1, 10, node, 5);
        Event event2 = new Event(2, 10, node, 8);

        node.setNewEvent(event1);
        node.setNewEvent(event2);

        ArrayList<Event> events = node.getKnownEvents();
        assertEquals(2, events.size());
    }


    @Test
    void testEquals() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 1);
        Node node1 = new Node(pos1);
        Node node2 = new Node(pos2);

        assertEquals(node1, node2);

        Position pos3 = new Position(1, 1);
        Position pos4 = new Position(1, 2);
        Node node3 = new Node(pos3);
        Node node4 = new Node(pos4);

        assertNotEquals(node3,node4);
    }

    @Test
    void TestaddNeighbour() {
        Node mainNode = new Node(new Position(0, 0));
        Node neighbour = new Node(new Position(1, 0));

        mainNode.addNeighbour(neighbour);

        assertTrue(mainNode.getNeighbours().contains(neighbour));
    }

    @Test
    void TestEnqueue(){
        Node node = new Node(new Position(1,0));
        Event event = new Event(1, 10, node, 5);
        Agent agent = new Agent(node,event);

        int sizeBefore = node.getMessengersInQueue().size();
        node.enqueue(agent);
        int sizeAfter = node.getMessengersInQueue().size();

        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    void Testdequeue(){
        Node node = new Node(new Position(1,0));
        Event event = new Event(1, 10, node, 5);
        Agent agent = new Agent(node,event);

        node.enqueue(agent);
        boolean EmptyQueue = node.dequeue(agent);
        assertTrue(EmptyQueue);
    }

}