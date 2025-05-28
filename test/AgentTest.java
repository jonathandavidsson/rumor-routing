import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

class AgentTest {

    Agent agent;
    @BeforeEach
    void setup()throws IOException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s, 2);
        ArrayList<Node> nodes = new ArrayList<>(map.getNodes());
        Node node = nodes.get((int) (random() * nodes.size()));
        Event event = new Event(0, 0, node, 0);
        agent = new Agent(node, event);
    }

    @org.junit.jupiter.api.Test
    void traverse() {
        Node node = agent.getNode();
        agent.traverse();
        Node node2 = agent.getNode();
        assertNotEquals(node, node2, "The agent should have moved!");
    }
    @org.junit.jupiter.api.Test
    void traverseUpdateEventDistance(){
        ArrayList<Event> events = agent.getEvents();
        int eventDistance = events.getFirst().getShortestWayToEvent();
        agent.traverse();
        int eventDistance2 = events.getFirst().getShortestWayToEvent();
        assertNotEquals(eventDistance, eventDistance2, "The distance should have been updated!");

    }
    @org.junit.jupiter.api.Test
    void traverseAddEventsInNode(){
        ArrayList<Node> movable = agent.getMovableNeighbours(agent.getNode().getNeighbours());
        Node node = movable.get(0);
        int eventsBefore = node.getKnownEvents().size();
        agent.traverse();
        int eventsAfter = agent.getNode().getKnownEvents().size();
        assertTrue(eventsAfter > eventsBefore, "Events after should be bigger!");

    }

    @org.junit.jupiter.api.Test
    void getNode() {
        Node node = agent.getNode();
        assertNotNull(node, "Node should have been returned!");
    }

    @org.junit.jupiter.api.Test
    void getVisitedNodes() {
        Set<Node> visitedNodes = agent.getVisitedNodes();
        assertNotNull(visitedNodes, "visitedNodes set should have been returned!");
    }

    @org.junit.jupiter.api.Test
    void getMovableNeighbours(){
        ArrayList<Node> neighbours = agent.getMovableNeighbours(agent.getNode().getNeighbours());
        assertNotNull(neighbours, "movableNeighbours arraylist should have been returned.");
    }
}