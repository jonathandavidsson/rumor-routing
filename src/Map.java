import java.util.*;

public class Map {
    private ArrayList<Node> theInfo;
    private ArrayList<Event> Events;
    private ArrayList<Node> requestNodes;
    private int nodeReach = 15;

    public Map(Scanner s) {
        ArrayList<Position> positions = readMazeDataToPositions(s);
        addNeighbours(positions);
        addRequestNodes();
    }

    /*
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

    /*
     * Calculates which positions are in range for a node
     * and creates nodes in all positions.
     * @param positions A list with every position.
     */
    private void addNeighbours(ArrayList<Position> positions) {
        theInfo=new ArrayList<>();

        for (Position pos1: positions) {
            ArrayList<Position> pos1Neighbours = new ArrayList<>();

            for (Position pos2: positions) {

                if (!pos1.equals(pos2) && pos1.distance(pos2) <= nodeReach) {
                    pos1Neighbours.add(pos2);
                }
                theInfo.add(new Node(pos1, pos1Neighbours));
            }
            
        }
    }

    /*
     *chooses random nodes that will send out a request.
     */
    private void addRequestNodes(){
        requestNodes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            requestNodes.add(theInfo.get( (int) (Math.random() * theInfo.size())));
        }
    }

    public ArrayList<Node> getNodes() {
        return theInfo;
    }

    public void updateEvents() {

    }

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public ArrayList<Node> getRequestNodes() {
        return requestNodes;
    }

    public void setNodeReach(int reach){
        nodeReach = reach;
    }
}

