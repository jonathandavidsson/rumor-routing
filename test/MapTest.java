import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @org.junit.jupiter.api.Test
    void getMap() throws FileNotFoundException {
        Scanner s = new Scanner(new File("layout.txt"));
        Map map = new Map(s);
        System.out.println(map.getMap().toString());

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