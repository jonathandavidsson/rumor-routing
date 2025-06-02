import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest  {

    Simulation sim;
    @BeforeEach
    void setup()throws IOException {
        Scanner scanner = new Scanner(new File("layout.txt"));
        sim = new Simulation(scanner, 2);
        sim.addEventToNode(sim.getMap().getNodes().get(8));
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));
    }


    @Test
    void TestTraverse() {

        Node n1 = sim.getRequests().get(0).getNode();
        sim.getRequests().get(0).traverse();
        Node n2 = sim.getRequests().get(0).getNode();

        assertNotEquals(n1, n2);
    }

    @Test
    void TestGetEvent() {
        assertEquals(sim.getMap().getEvents().get(0), sim.getRequests().get(0).getEvent());
    }

    @Test
    void TestgetNode() {
        assertEquals(sim.getMap().getNodes().get(0), sim.getRequests().get(0).getNode());
    }

    @Test
    void TestIsDead() {
        assertFalse(sim.getRequests().get(0).isDead());
    }

    @Test
    void TestRequestLifetime() {
        Request request = sim.getRequests().get(0);
        int traverseCount = 0;

        while (!request.isDead() && traverseCount < 50){
        request.traverse();
        traverseCount++;
        }

        assertTrue(traverseCount >= 45);
    }

}