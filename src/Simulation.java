import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The Simulation is the class responsible for simulating a rumor-routing algorithm.
 * It contains a map where events may spawn.
 * From the event agents may spawn and go around spreading the word of the shortest way to
 * find the event. Requests can be spawned in this simulation, request searches for a event.
 * Date: 28/05/25
 *
 * @author: Jonathan Davidsson, Joel Lindgren - dv24jon, Liam ..., Lukasz ...
 */
public class Simulation {
    private final Map map;
    private int timestep;
    private ArrayList<Agent> agents;
    private ArrayList<Request> requests;
    private double percentChanceOfEvents;
    private ArrayList<Event> events;

    public Simulation(Scanner s, int nodeReach){
        map = new Map(s, nodeReach);
        agents = new ArrayList<>();
        requests = new ArrayList<>();
        percentChanceOfEvents = 0.0001;
        timestep = 0;
    }

    /**
     * This is the function that updates the simulation. It will make all alive Requests and Agents move,
     * if they can. It will also spawn new events by chance. Default for the chance of an event spawning is 0,01%.
     * An agent will then have a 50% chance to spawn from the event.
     */
    public void updateTime(){
        timestep++;

        for (Node node: map.getNodes()) {
            if (Math.random() <= percentChanceOfEvents) {
                addEventToNode(node);
            }
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

    /**
     * Adds a event at a specified node, with a 50% chance for an agent to spawn.
     * @param node the node the event will spawn on.
     */
    public void addEventToNode(Node node){
        Event event = new Event(timestep, timestep, node, 0);
        int coinflip = (int) (Math.random() * 2);
        if (coinflip == 1) {
            Agent agent = new Agent(node, event);
            agents.add(agent);
        }
        map.addEvent(event);
    }

    /**
     * Sets the chance for a event to incur in the sim.
     * @param percentChanceOfEvents the chance for an event in the sim to incur.
     *                              1 being 100%, 0,1 being 10% and so on.
     */
    public void setPercentChanceOfEvents(double percentChanceOfEvents) {
        this.percentChanceOfEvents = percentChanceOfEvents;
    }

    /**
     * creates the class Request in the simulation at a specified node,
     * searching for a specified event-
     * @param node The OriginNode for the request.
     * @param event The Event request will search for.
     */
    public void createRequest(Node node, Event event){
        requests.add(new Request(node, event));
    }

    /**
     * @return the classMap
     */
    public Map getMap(){
        return map;
    }

    /**
     * @return an arrayList of all the requests.
     */
    public ArrayList<Request> getRequests(){
        return requests;
    }

    /**
     * @return an arrayList of all the agents.
     */
    public ArrayList<Agent> getAgents(){
        return agents;
    }

    /**
     * A string with the map, agents positions and request positions.
     * @return a string with info of the simulation.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(map.toString());
        builder.append("\n\nAgents Positions:");
        int i = 0;
        if (agents.isEmpty()){
            builder.append("No agents\n");
        } else {
            for (Agent agent : agents) {
                builder.append("Agent:").append(i).append(" position:").
                        append(agent.getNode().getPosition().toString()).append("   ");
            }
        }
        builder.append("\n");

        if (requests.isEmpty()){
            builder.append("No Requests\n");
        } else {
            for (Request request : requests){
                builder.append("Request:").append(i).append(" position:").
                        append(request.getCurrentNode().getPosition().toString()).append("   ");
            }

        }
        return builder.toString();
    }

    /**
     * @return an arraylist of all the events in the simulation.
     */
    public ArrayList<Event> getEvents(){
        this.events = map.getEvents();
        return events;
    }
}
