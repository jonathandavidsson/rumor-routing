import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    @Test
    void updateTime() {
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
                        "9,9");
        Simulation sim = new Simulation(scanner, 2);

       while(sim.getAgents().isEmpty()) {
           sim.updateTime();
       }

       Position p1 = sim.getAgents().getFirst().getNode().getPosition();

       sim.updateTime();

       Position p2 = sim.getAgents().getFirst().getNode().getPosition();

       assertNotEquals(p1, p2);






    }

    @Test
    void setPercentChanceOfEvents() {
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
                        "9,9");
        Simulation sim = new Simulation(scanner, 2);
        int preSize = sim.getMap().getEvents().size();
        System.out.println(preSize);
        sim.setPercentChanceOfEvents(1.1);

        sim.updateTime();

        int postSize = sim.getMap().getEvents().size();
        System.out.println(postSize);

        assertNotEquals(preSize, postSize);
    }


    @Test
    void createRequest(){
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
                        "9,9");
        Simulation sim = new Simulation(scanner, 2);

        int preSize = sim.getRequests().size();

        while(sim.getMap().getEvents().isEmpty()){
            sim.updateTime();
        }

       sim.createRequest(sim.getMap().getNodes().getFirst(), sim.getMap().getEvents().getFirst());

        int postSize = sim.getRequests().size();

        assertNotEquals(preSize, postSize);
    }
}