package test;

import java.util.*;
import datastructure.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    HashMap<String, List<Station>> adjMapTest = DataUtilities.createAdjMap();
    int numOfStationsTest = adjMapTest.size();

    Graph networkTest = new Graph(numOfStationsTest);

    String start = "DT1";
    String end = "DT3";
    
    @Test
    public void checkNumofStations() {
        assertEquals(adjMapTest.size(), networkTest.getNumOfStations());
    }

    @Test
    public void assertEndStation() {
        networkTest.solve(adjMapTest, start);
        assertEquals(networkTest.getParentMap().get(end), "DT3");
    }

    @Test
    public void assertExpectedPath() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);

        ArrayList<String> expectedPath = new ArrayList<>();
        expectedPath.add("DT1");
        expectedPath.add("DT2");
        expectedPath.add("DT3");
        assertEquals(expectedPath, firstPath);
    }
}
