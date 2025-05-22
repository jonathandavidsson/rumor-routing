import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PositionTest {

    @Test
    void testEquals() {
        Position pos1 = new Position(1,2);
        Position pos2 = new Position(2, 1);
        Position pos3 = new Position(1, 2);

        assertFalse(pos1.equals(pos2));
        assertTrue(pos1.equals(pos3));

    }

    @Test
    void getX() {
        Position pos = new Position(1,2);
        assertEquals(1, pos.getX());
    }

    @Test
    void getY() {
        Position pos = new Position(1,2);
        assertEquals(2, pos.getY());
    }

    @Test
    void getPosToNorth() {
        Position pos = new Position(1,1);
        assertEquals(new Position(1,0), pos.getPosToNorth());
    }

    @Test
    void getPosToSouth() {
        Position pos = new Position(1,1);
        assertEquals(new Position(1,2), pos.getPosToSouth());
    }

    @Test
    void getPosToWest() {
        Position pos = new Position(1,1);
        assertEquals(new Position(0,1), pos.getPosToWest());
    }

    @Test
    void getPosToEast() {
        Position pos = new Position(1,1);
        assertEquals(new Position(2,1), pos.getPosToEast());
    }

    @Test
    void distance() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(4,3);
        assertEquals(2, pos1.distance(pos2));
    }
}