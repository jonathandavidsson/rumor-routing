import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void traverse() {
        Scanner scanner = new Scanner(
                "10\n" +
                        "0,0\n" +
                        "1,1\n" +
                        "2,2\n" +
                        "3,3\n" +
                        "4,4\n" +
                        "5,5\n" +
                        "6,6\n" +
                        "7,7\n" +
                        "8,8\n" +
                        "9,9\n" +
                        "12,12");
        Simulation sim = new Simulation(scanner, 2);

        sim.addEventToNode(sim.getMap().getNodes().get(9));
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));

        Node n1 = sim.getRequests().get(0).getCurrentNode();
        sim.getRequests().getFirst().traverse();
        Node n2 = sim.getRequests().get(0).getCurrentNode();

        assertNotEquals(n1, n2);

        assertFalse(sim.getRequests().get(0).isDead());


    }

    @Test
    void getEvent() {
    }

    @Test
    void getNode() {
        Scanner scanner = new Scanner(
                "10\n" +
                        "0,0\n" +
                        "1,1\n" +
                        "2,2\n" +
                        "3,3\n" +
                        "4,4\n" +
                        "5,5\n" +
                        "6,6\n" +
                        "7,7\n" +
                        "8,8\n" +
                        "9,9\n" +
                        "12,12");
        Simulation sim = new Simulation(scanner, 2);

        sim.addEventToNode(sim.getMap().getNodes().get(9));
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));

        assertEquals(sim.getMap().getNodes().get(0), sim.getRequests().get(0).getCurrentNode());
    }

    @Test
    void isDead() {
    }
}