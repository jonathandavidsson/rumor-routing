import java.util.Scanner;

public class Simulation {
    private Map map;
    private int timestep = 0;

    public Simulation(Scanner s){
        map = new Map(s);
    }
    public void updateTime(){
        timestep++;
    }

    private void addEventToNode(Node node){
        Event event = new Event(0, timestep, node.getPosition());
        int coinflip = (int) (Math.random() * 2);
        if (coinflip == 1){
            Agent agent = new Agent(node, event);
        }


        map.updateEvents();
    }
    public void createRequest(Event event){

    }
}
