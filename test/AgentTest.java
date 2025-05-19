import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        assertEquals(node, node2, "The agent should have moved!");
    }
    @org.junit.jupiter.api.Test
    void traverseUpdateEvents(){

    }


    @org.junit.jupiter.api.Test
    void getNode() {

    }

    @org.junit.jupiter.api.Test
    void getVisitedNodes() {

    }
}