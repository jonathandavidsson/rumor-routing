import java.util.*;

/**
 * Class: Map
 * Description: This class is the base of the project. It is the map where everything transpires.
 * It is the map of all the nodes in the project. The map gets createad and after that it will only
 * add the info of events, request and such that are created within the map.
 *
 * Date: 28/05/25
 * Authors: Jonathan Davidsson,Joel Lindgren - dv24jon, Liam Danielski c24ldi, Lukasz Polok c24lpk
 */
public class Map {
    private ArrayList<Node> theInfo;
    private ArrayList<Event> events;
    private ArrayList<Node> requestNodes;
    private int nodeReach;

    public Map(Scanner s, int nodeReach) {
        this.nodeReach = nodeReach;
        ArrayList<Position> positions = readMazeDataToPositions(s);
        addNeighbours(positions);
        add4RequestNodes();
        events = new ArrayList<>();
    }

    /**
     * reads the scanner and converts the file to positions
     * throws a RuntimeException
     * @param s A scanner
     * @return An ArrayList with the positions of the file
     */
    private ArrayList<Position> readMazeDataToPositions(Scanner s){
        String str;
        ArrayList<Position> positions = new ArrayList<>();

        try {
            int numberOfLines = Integer.parseInt(s.nextLine());
            for (int i = numberOfLines; i > 0; i--){
                str=s.nextLine();
                String[] numbers = str.split(",");
                int x = Integer.parseInt(numbers[0]);
                int y = Integer.parseInt(numbers[1]);

                positions.add(new Position(x, y));
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return positions;
    }

    /**
     * Calculates which positions are in range for a node
     * and creates nodes in all positions.
     * @param positions A list with every position.
     */
    private void addNeighbours(ArrayList<Position> positions) {
        theInfo=new ArrayList<>();

        for (Position pos: positions) {
            theInfo.add(new Node(pos));
        }

        for (int i = 0; i < theInfo.size(); i++) {
            Node node = theInfo.get(0);
        }

        for (Node node1: theInfo) {
            for (Node node2: theInfo) {
                if (!node1.equals(node2) && node1.getPosition().distance(node2.getPosition()) <= nodeReach) {
                    node1.addNeighbour(node2);
                }
            }
        }
    }

    /**
     *chooses random nodes that will send out a request.
     */
    private void add4RequestNodes(){
        requestNodes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            requestNodes.add(theInfo.get( (int) (Math.random() * theInfo.size())));
        }
    }

    /**
     * Description: Returns a random node from the map.
     * @return Returns a random node.
     */
    public Node getRandomNode() {
        return theInfo.get((int) (Math.random() * theInfo.size()));
    }

    /**
     * Desription Returns every node in the map.
     * @return Returns an arraylist containing every node in the map.
     */
    public ArrayList<Node> getNodes() {
        return theInfo;
    }

    /**
     * Description: Adds an event to the correct node when created.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
        for (Node node: theInfo) {
            if (node.getPosition().equals(event.getEventNode().getPosition())){
                node.setNewEvent(event);
                return;
            }
        }
    }

    /**
     * Description: Returns all the events in the map.
     * @return Returns an arraylist containing every event that is currently in the map.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Description: Returns the nodes that has created requests.
     * @return Returns an arraylist of all nodes that has created a request.
     */
    public ArrayList<Node> getRequestNodes() {
        return requestNodes;
    }

    /**
     * Description: Sets the node reach
     * @param reach The value of reach to be set.
     */
    public void setNodeReach(int reach){
        nodeReach = reach;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(new String());
        int y =  theInfo.get(0).getPosition().getY();
        for (Node node : theInfo){
            if (y != node.getPosition().getY()){
                stringBuilder.append("\n").append(node.getPosition().toString()).append(" ");
            } else {
                stringBuilder.append(node.getPosition().toString()).append(" ");
            }
            y = node.getPosition().getY();
        }

        return stringBuilder.toString();
    }

}

