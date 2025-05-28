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
    void traverse() {

        Node n1 = sim.getRequests().get(0).getCurrentNode();
        sim.getRequests().get(0).traverse();
        Node n2 = sim.getRequests().get(0).getCurrentNode();

        assertNotEquals(n1, n2);
    }

    @Test
    void getEvent() {

    }

    @Test
    void getNode() {

        assertEquals(sim.getMap().getNodes().get(0), sim.getRequests().get(0).getCurrentNode());
    }

    @Test
    void isDead() {

        assertFalse(sim.getRequests().get(0).isDead());
    }

    @Test
    void HasReachedOriginNode() {

        Node originNode = sim.getRequests().get(0).getCurrentNode();
        Request request = sim.getRequests().get(0);
        for (int i = 0; !request.isDead(); i++) {
            request.traverse();
        }

        assertEquals(originNode, request.getCurrentNode());
    }
}