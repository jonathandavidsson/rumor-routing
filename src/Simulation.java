import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Simulation {
    private final Map map;
    private int timestep;
    private ArrayList<Agent> agents;
    private ArrayList<Request> requests;
    private double percentChanceOfEvents;
    private ArrayList<Event> events;

    public Simulation(Scanner s, int nodeReach){
        map = new Map(s, nodeReach); //TODO
        agents = new ArrayList<>();
        requests = new ArrayList<>();
        percentChanceOfEvents = 0.0001;
        timestep = 0;
    }

    public void updateTime(){
        timestep++;

        if (Math.random() <= percentChanceOfEvents){
            addEventToNode(map.getRandomNode());
        }
        if (!agents.isEmpty()) {
            Iterator<Agent> agentIterator = agents.iterator();
            while (agentIterator.hasNext()){
                Agent agent = agentIterator.next();
                agent.traverse();
                if (agent.isDead()){
                    agentIterator.remove();
                }
            }
        }
        if (!requests.isEmpty()) {
            Iterator<Request> iterator = requests.iterator();
            while (iterator.hasNext()) {
                Request request = iterator.next();
                if (request.traverse()) {
                    request.printRequestEvent();
                }
                if (request.isDead()) {
                    iterator.remove();
                }
            }
        }
    }

    public void addEventToNode(Node node){
        Event event = new Event(timestep, timestep, node, 0);
        int coinflip = (int) (Math.random() * 2);
        if (coinflip == 1) {
            Agent agent = new Agent(node, event);
            agents.add(agent);
        }
        map.addEvent(event);
    }


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
    public ArrayList<Event> getEvents(){
        this.events = map.getEvents();
        return events;
    }
}
