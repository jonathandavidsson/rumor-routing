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
        System.out.println("Event Position;" + sim.getMap().getEvents().get(0).getEventNode().getPosition().toString());
        System.out.println("********************");
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));

        do{
            sim.updateTime();
            System.out.println("**************");
            System.out.println("Request position:" + sim.getRequests().get(0).getCurrentNode().getPosition().toString());
            System.out.println("        Agent position:" + sim.getAgents().get(0).getNode().getPosition().toString());
            System.out.println("        Agent event"    + sim.getAgents().get(0).getEvents().get(0).getNodeToEvent().getPosition().toString());
            for(Event event : sim.getRequests().get(0).getCurrentNode().getKnownEvents()) {
                if (event.getNodeToEvent() != null && event.equals(sim.getRequests().get(0).getEvent())){
                    System.out.println("EventID" + event.getEventId() +" - pathToEvent: " +  event.getNodeToEvent().getPosition().toString());
                }
            }
            System.out.println("*************\n");
        }while (!sim.getRequests().get(0).isDead());
    }
}
