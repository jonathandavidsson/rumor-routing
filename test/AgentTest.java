import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

class AgentTest {

    Agent agent;
    @BeforeEach
    void setup()throws IOException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);
        ArrayList<Node> nodes = new ArrayList<>(map.getNodes());
        Node node = nodes.get((int) (random() * nodes.size()));
        Event event = new Event(1, 1, node);
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
    void traverseUpdateEvents(){
        Hashtable<Event, ArrayList<Object>> info = agent.getEvents();
        Event e = info.keys().nextElement();
        int eventDistance = (int) info.get(e).get(1);
        agent.traverse();
        Hashtable<Event, ArrayList<Object>> infoAfter = agent.getEvents();
        int eventDistance2 = (int) infoAfter.get(e).get(1);
        assertNotEquals(eventDistance, eventDistance2, "The distance should have been updated!");

    }


    @org.junit.jupiter.api.Test
    void getNode() {

    }

    @org.junit.jupiter.api.Test
    void getVisitedNodes() {

    }
}