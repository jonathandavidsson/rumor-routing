import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void traverse() throws FileNotFoundException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);
        Event event = new Event(1, 0, new Position(0,0));

        map.addEvent(event, map.getRandomNode());

        for (Node node: map.getNodes()) {
            if (node.getKnownEvents().keys().equals(new Event(1, 0, new Position(0, 0)))){
                System.out.println("ha");
            }
        }
    }

    @Test
    void getEvent() {
    }

    @Test
    void getNode() {
    }
}