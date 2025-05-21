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

        Simulation sim = new Simulation(scanner);
       // Map map = new Map(scanner);
        sim.setPercentChanceOfEvents(1);
        sim.updateTime();
        //System.out.println(sim.getMap().getEvents().get(0).getEventNode().getPosition().toString());
        //System.out.println(sim.getMap().getNodes().get(0).getPosition().toString());
        sim.createRequest(sim.getMap().getNodes().get(0), sim.getMap().getEvents().get(0));
      //  System.out.println(sim);
       // while (!sim.getRequests().get(0).isDead()){
        for (int i = 0; i < 20; i++) {
            sim.updateTime();
        }
      //      System.out.println( sim.getRequests().get(0).getNode().getPosition().toString());
       // }
        System.out.println(sim);

    }

}
