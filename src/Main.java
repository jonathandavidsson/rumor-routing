import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

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
            System.out.println("Request position:" + request.getCurrentNode().getPosition().toString());
            System.out.println("        Agent position:" + firstAgent.getNode().getPosition().toString());
            System.out.println("        Agent event"    + firstAgent.getEvents().get(0).getNodeToEvent().getPosition().toString());
            for(Event event : request.getCurrentNode().getKnownEvents()) {
                if (event.getNodeToEvent() != null && event.equals(request.getEvent())){
                    System.out.println("found in" + request.getCurrentNode().getPosition().toString() + "EventID" + event.getEventId() +" - pathToEvent: " +  event.getNodeToEvent().getPosition().toString());
                }
            }
            System.out.println("*************\n");
        }while (!sim.getRequests().get(0).isDead());
        System.out.println("Amount of events during simulation: " + sim.getEvents().size());
    }
}
