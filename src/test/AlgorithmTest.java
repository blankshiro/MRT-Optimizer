package test;

import java.util.*;
import datastructure.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    Map<String, Integer> mapCodeTest = DataUtilities.createMapCode();
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
}
