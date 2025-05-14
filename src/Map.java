import java.util.*;

public class Map {
    private ArrayList<Node> theInfo;
    private ArrayList<Event> Events;
    private ArrayList<Node> requestNodes;
    private final int nodeReach = 15;

    public Map(Scanner s) {
        ArrayList<Position> positions = readMazeDataToPositions(s);
        addNeighbours(positions);
    }

    private ArrayList<Position> readMazeDataToPositions(Scanner s){
        String str;
        theInfo=new ArrayList<>();
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
    private void addNeighbours(ArrayList<Position> positions) {
        for (Position pos1: positions) {
            ArrayList<Position> pos1Neigbours = new ArrayList<>();
            for (Position pos2: positions) {
                
                if (!pos1.equals(pos2) && pos1.distance(pos2) <= nodeReach) {
                    pos1Neigbours.add(pos2);
                }
                theInfo.add(new Node(pos1, pos1Neigbours));
            }
            
        }
    }

    public ArrayList<Node> getMap() {
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
}
