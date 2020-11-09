package test;

import java.util.*;
import datastructure.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AlgorithmTest class. Please note before testing: Go to DataUtilities.java and
 * change the path of the traveltime.txt file in the createAdjMapTest to your
 * actual file path.
 */
public class AlgorithmTest {

    HashMap<String, List<Station>> adjMapTest = DataUtilities.createAdjMapTest();
    int numOfStationsTest = adjMapTest.size();

    Graph networkTest = new Graph(numOfStationsTest);

    String start = "DT1";
    String end = "DT3";

    @Test
    public void assertNumofStations() {
        assertEquals(adjMapTest.size(), networkTest.getNumOfStations());
    }

    @Test
    public void assertEndOfParentMap() {
        networkTest.solve(adjMapTest, start);
        /** End of parent map should be DT2 because DT2 is the parent of DT3. */
        assertEquals(networkTest.getParentMap().get(end), "DT2");
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

    String start2 = "NS1";
    String end2 = "DT5";

    @Test
    public void assertExpectedPath2() {
        networkTest.solve(adjMapTest, start2);
        ArrayList<String> firstPath2 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end2, end2, firstPath2);

        ArrayList<String> expectedPath = new ArrayList<>();
        expectedPath.add("NS1");
        expectedPath.add("NS2");
        expectedPath.add("NS3");
        expectedPath.add("NS4");
        expectedPath.add("DT1");
        expectedPath.add("DT2");
        expectedPath.add("DT3");
        // should be missing DT4 because there is no data on DT4
        expectedPath.add("DT5");
        assertEquals(expectedPath, firstPath2);
    }

    String start3 = "EW15";
    String end3 = "DT12";

    @Test
    public void assertExpectedPath3() {
        networkTest.solve(adjMapTest, start3);
        ArrayList<String> firstPath3 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end3, end3, firstPath3);

        ArrayList<String> expectedPath = new ArrayList<>();
        expectedPath.add("EW15");
        expectedPath.add("EW14");
        expectedPath.add("EW13");
        expectedPath.add("EW12");
        expectedPath.add("DT14");
        expectedPath.add("DT13");
        expectedPath.add("DT12");
        assertEquals(expectedPath, firstPath3);
    }

    @Test
    public void assertAlternatePath() {
        networkTest.solve(adjMapTest, start3);
        ArrayList<String> alternatePath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap2(), start3, end3, end3, alternatePath);

        ArrayList<String> expectedPath = new ArrayList<>();
        expectedPath.add("EW15");
        expectedPath.add("EW16");
        expectedPath.add("NE3");
        expectedPath.add("NE4");
        expectedPath.add("NE5");
        expectedPath.add("NE6");
        expectedPath.add("NE7");
        expectedPath.add("DT12");
        assertEquals(expectedPath, alternatePath);
    }
}
