import org.junit.jupiter.api.Test;
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