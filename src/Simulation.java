import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    private Map map;
    private int timestep = 0;
    private ArrayList<Agent> agents;
    private ArrayList<Request> requests;
    private double percentChanceOfEvents;

    public Simulation(Scanner s){
        map = new Map(s);
        agents = new ArrayList<>();
        requests = new ArrayList<>();
        percentChanceOfEvents = 0.002;
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

    public void createRequest(Node node, Event event){
        requests.add(new Request(node, event));
    }
    public Map getMap(){
        return map;
    }
    public ArrayList<Request> getRequests(){
        return requests;
    }
    public ArrayList<Agent> getAgents(){
        return agents;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(map.toString());
        builder.append("\n\nAgents Positions:");
        int i = 0;
        if (agents.isEmpty()){
            builder.append("No agents\n");
        } else {
            for (Agent agent : agents) {
                builder.append("Agent:").append(i).append(" position:").append(agent.getNode().getPosition().toString()).append("   ");
            }
        }
        builder.append("\n");

        if (requests.isEmpty()){
            builder.append("No Requests\n");
        } else {
            for (Request request : requests){
                builder.append("Request:"+ i + " position:" + request.getCurrentNode().getPosition().toString() + "   ");
            }

        }
        return builder.toString();
    }
}
