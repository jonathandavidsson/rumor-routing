import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    private Map map;
    private int timestep = 0;
    private ArrayList<Agent> agents;
    private ArrayList<Request> requests;
    private double percentChanceOfEvents = 0.002;

    public Simulation(Scanner s){
        map = new Map(s);
        agents = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void updateTime(){
        timestep++;

        if (Math.random() <= percentChanceOfEvents){
            addEventToNode(map.getRandomNode());
        }
        if (!agents.isEmpty()) {
            for (Agent agent : agents) {
                agent.traverse();
            }
        }
        if (!requests.isEmpty()) {
            for (Request request: requests) {
                request.traverse();
            }
        }
    }

    private void addEventToNode(Node node){
        Event event = new Event(0, timestep, node, 0);
        int coinflip = (int) (Math.random() * 2);
        if (coinflip == 1){
            Agent agent = new Agent(node, event);
            agents.add(agent);
        }
        map.addEvent(event);
    }
    //public void createRequest(Event event){
    //    requests.add(new Request());
    //}

    public void setPercentChanceOfEvents(double percentChanceOfEvents) {
        this.percentChanceOfEvents = percentChanceOfEvents;
    }
}
