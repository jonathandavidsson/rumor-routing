import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void getPosition() {
    Position pos = new Position(1,1);
    Node node = new Node(pos);

    assertEquals(pos, node.getPosition());
    }

    @Test
    void getNeighbours() {
        Position centerPos = new Position(0, 0);
        Position neighbor1Pos = new Position(1, 0);
        Position neighbor2Pos = new Position(0, 1);

        Node center = new Node(centerPos);
        Node neighbor1 = new Node(neighbor1Pos);
        Node neighbor2 = new Node(neighbor2Pos);

        center.addNeighbour(neighbor1);
        center.addNeighbour(neighbor2);

        ArrayList<Node> neighbours = center.getNeighbours();

        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains(neighbor1));
        assertTrue(neighbours.contains(neighbor2));
    }


    @Test
    void getKnownEvents() {

    }

    @Test
    void setNewEvent() {
    }

    @Test
    void setNearestEventDirection() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void addNeighbour() {
    }
}