package test;

import java.util.*;
import java.time.*;
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
    HashMap<String, ArrayList<HashMap<String, LocalTime>>> timeMap = TimeCheck.createTimeMapTest();
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

    @Test
    public void assertBinaryRouteCheckFirstStationValid() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);
        Boolean check = true;
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath.get(0), firstPath.get(1), timeMap, LocalTime.of(23, 30)));
    }

    @Test
    public void assertBinaryRouteCheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath, timeMap, LocalTime.of(10,0));
        assertEquals(0, failedInterchange.size());
    }

    @Test
    public void assertBinaryRouteCheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);
        Boolean check = false;
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath.get(0), firstPath.get(1), timeMap, LocalTime.of(23, 50)));
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
        DataUtilities.getPath(networkTest.getParentMap(), start3, end3, end3, firstPath3);

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

    String start4 = "NE1";
    String end4 = "CC15";

    @Test
    public void assertExpectedPath4() {
        networkTest.solve(adjMapTest, start4);
        ArrayList<String> firstPath4 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start4, end4, end4, firstPath4);

        String[] expectedPath = { "NE1", "NE3", "NE4", "NE5", "NE6", "NE7", "NE8", "NE9", "NE10", "NE11", "NE12",
                "CC13", "CC14", "CC15" };
        assertArrayEquals(expectedPath, firstPath4.toArray());
    }

    String start5 = "EW1";
    String end5 = "DT27";

    @Test
    public void assertExpectedPath5() {
        networkTest.solve(adjMapTest, start5);
        ArrayList<String> firstPath5 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start5, end5, end5, firstPath5);

        String[] expectedPath = { "EW1", "EW2", "DT32", "DT31", "DT30", "DT29", "DT28", "DT27" };
        assertArrayEquals(expectedPath, firstPath5.toArray());
    }

    String start6 = "DT6";
    String end6 = "CC13";

    @Test
    public void assertExpectedPath6() {
        networkTest.solve(adjMapTest, start6);
        ArrayList<String> firstPath6 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start6, end6, end6, firstPath6);

        String[] expectedPath = { "DT6", "DT7", "DT8", "DT9", "CC19", "CC17", "CC16", "CC15", "CC14", "CC13" };
        assertArrayEquals(expectedPath, firstPath6.toArray());
    }



}
