import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MapTest {

    @org.junit.jupiter.api.Test
    void getMap() throws FileNotFoundException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);

        int i = 0;
        for (Node node: map.getNodes()) {
            assert node.getPosition().equals(new Position(i, i));
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void updateEvents() {
    }

    @org.junit.jupiter.api.Test
    void getEvents() throws FileNotFoundException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);

        System.out.println(map.getEvents().toString());
    }

    @org.junit.jupiter.api.Test
    void getRequestNodes() throws FileNotFoundException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);
        System.out.println(map.getRequestNodes().size());

    }
}