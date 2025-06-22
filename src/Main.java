import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File(args[0]));
        Simulation sim = new Simulation(scanner, 15);
        System.out.println("chance of events: 0,02%");
        System.out.println("Nodes where requests will be created:");
        for (Node requestNodes : sim.getMap().getRequestNodes()) {
            System.out.println(requestNodes.getPosition());
        }
        System.out.println("*******************");

        int requestCounter = 0;
        for (int i = 1; i <= 10000; i++) {
            sim.updateTime();
            if (i%400 == 0 && !sim.getEvents().isEmpty()){
                requestCounter += 4;
                for (int j = 0; j < 4; j++) {
                    sim.createRequest(sim.getMap().getRequestNodes().get(j),
                            sim.getEvents().get((int) (Math.random() * sim.getEvents().size())));
                }

            }
        }
        System.out.println("****************");
        System.out.println("Amount of events created while running: " + sim.getEvents().size());
        System.out.println("Amount of requests created while running: " + requestCounter);
        System.out.println("number of successful requests: " + sim.getNumbSuccessfulRequests());
    }





    public void testForAgentAndRequestTogether(){

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
        // Map map = new Map(scanner);
        sim.setPercentChanceOfEvents(1);
        sim.addEventToNode(sim.getMap().getNodes().get(9));
        System.out.println("Agent position:" + sim.getAgents().get(0).getNode().getPosition().toString());
        System.out.print("\nNode0,0 neigbours:");
        for(Node node : sim.getMap().getNodes().get(0).getNeighbours() ) {
            System.out.println(node.getPosition().toString() + " ");
        }
        Agent firstAgent = sim.getAgents().get(0);
        System.out.println("Event Position;" + sim.getMap().getEvents().get(0).getEventNode().getPosition().toString());
        System.out.println("********************");
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));
        Request request = sim.getRequests().get(0);

        do{
            sim.updateTime();
            System.out.println("**************");
            System.out.println("Request position:" + request.getNode().getPosition().toString());
            System.out.println("        Agent position:" + firstAgent.getNode().getPosition().toString());
            System.out.println("        Agent event"    + firstAgent.getEvents().get(0).getNodeToEvent().getPosition().toString());
            for(Event event : request.getNode().getKnownEvents()) {
                if (event.getNodeToEvent() != null && event.equals(request.getEvent())){
                    System.out.println("found in" + request.getNode().getPosition().toString() + "EventID" + event.getEventId() +" - pathToEvent: " +  event.getNodeToEvent().getPosition().toString());
                }
            }
            System.out.println("*************\n");
        }while (!sim.getRequests().get(0).isDead());
        System.out.println("Amount of events during simulation: " + sim.getEvents().size());
    }
}

