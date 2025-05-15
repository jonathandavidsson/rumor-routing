import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import static java.lang.Math.random;

public class Agent {

    Node currentNode;
    Node prevNode;
    Hashtable<Event, ArrayList<Object>> events;
    Set<Node> visitedNodes;
    int lifetime;
    ArrayList<Node> neighbours;
    ArrayList<Node> movable;

    public Agent(Node currentNode, Event event){
        this.currentNode = currentNode;
        events = new Hashtable<>();
        ArrayList<Object> theInfo = new ArrayList<>();
        theInfo.add(currentNode);
        theInfo.add(0);
        events.put(event, theInfo);
    }

    public void traverse(){
        if(lifetime == 0){
            return;
        }
        deliverInformationToNode();
        if(currentNode.getEvent() != null && events.containsKey(currentNode.getEvent())){
            ArrayList<Object> info = new ArrayList<>();
            info.add(currentNode);
            info.add(0);
            events.put(currentNode.getEvent(),info);
        }
        neighbours = new ArrayList<>();
        movable = new ArrayList<>();
        neighbours = currentNode.getNeighbours();
        movable = getMovableNeighbours(neighbours);
        visitedNodes.add(currentNode);

        if(!movable.isEmpty()){
            prevNode = currentNode;
            currentNode = movable.get((int) (random() * movable.size()));
            lifetime = lifetime - 1;
        }else{
            lifetime = 0;
        }
        ArrayList<Event> keys = new ArrayList<>(events.keySet());
        for(int i = 0; i < keys.size(); i++){
            Event e = keys.get(i);
            ArrayList<Object> eventInfo = events.get(e);
            eventInfo.set(0, prevNode);
            eventInfo.set(1, (int) eventInfo.get(1) + 1);
        }

    }

    public Node getNode(){
        return currentNode;
    }

    public Set<Node> getVisitedNodes(){
        return visitedNodes;
    }

//    public ArrayList<Event> getEvent(){
//        return events;
//    }

    private ArrayList<Node> getMovableNeighbours(ArrayList<Node> neighbours){
        ArrayList<Node> movableNodes = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(!visitedNodes.contains(neighbours.get(i))){
                movableNodes.add(neighbours.get(i));
            }
        }
        return movableNodes;
    }

    private void deliverInformationToNode(){
        /*Lämnar över information om event och vars agenten kom ifrån till nod.*/
        currentNode.takeAgentInfo(events);
    }

}
