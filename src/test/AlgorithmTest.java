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
    /** Construct the adjacency map for testing. */
    HashMap<String, List<Station>> adjMapTest = DataUtilities.createAdjMapTest();
    /** Construct the last timing map for testing */
    HashMap<String, ArrayList<HashMap<String, LocalTime>>> timeMapTest = TimeCheck.createTimeMapTest();
    int numOfStationsTest = adjMapTest.size();

    /** Construct the MRT map for testing. */
    Graph networkTest = new Graph(numOfStationsTest);
    HashMap<String, Integer> distMapTest;

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
    public void assertBinaryPath() {
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
    public void assertBinaryRouteCheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);
        Boolean check = false;
        // last train timing of DT1 is 23:35
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath.get(0), firstPath.get(1), timeMapTest, LocalTime.of(23, 50)));
    }

    @Test
    public void assertBinaryRouteCheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start);
        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start, end, end, firstPath);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath, timeMapTest, distMapTest, LocalTime.of(23, 50));
        assertEquals(0, failedInterchange.size());
    }

    String start2 = "NS1";
    String end2 = "DT5";

    @Test
    public void assertNonBinaryRoute() {
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

    @Test
    public void assertNonBinaryRouteCheckFirstStationValid() {
        networkTest.solve(adjMapTest, start2);
        ArrayList<String> firstPath2 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start2, end2, end2, firstPath2);
        Boolean check = true;
        // last train timing of NS1 is 22:46
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath2.get(0), firstPath2.get(1), timeMapTest, LocalTime.of(22, 30)));
    }

    @Test
    public void assertNonBinaryRouteCheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start2);
        ArrayList<String> firstPath2 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start2, end2, end2, firstPath2);
        Boolean check = false;
        // last train timing of NS1 is 22:46
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath2.get(0), firstPath2.get(1), timeMapTest, LocalTime.of(22, 50)));
    }

    @Test
    public void assertNonBinaryRouteCheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start2);
        ArrayList<String> firstPath2 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start2, end2, end2, firstPath2);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath2, timeMapTest, distMapTest, LocalTime.of(22, 30));
        assertEquals(0, failedInterchange.size());
    }

    String start3 = "EW15";
    String end3 = "DT12";

    @Test
    public void assertNonBinaryRoute2() {
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
    public void assertNonBinaryRoute2CheckFirstStationValid() {
        networkTest.solve(adjMapTest, start3);
        ArrayList<String> firstPath3 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start3, end3, end3, firstPath3);
        Boolean check = true;
        // last train timing of EW15 is 00:05
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath3.get(0), firstPath3.get(1), timeMapTest, LocalTime.of(0, 0)));
    }

    @Test
    public void assertNonBinaryRoute2CheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start3);
        ArrayList<String> firstPath3 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start3, end3, end3, firstPath3);
        Boolean check = false;
        // last train timing of EW15 is 00:05
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath3.get(0), firstPath3.get(1), timeMapTest, LocalTime.of(0, 10)));
    }

    @Test
    public void assertNonBinaryRoute2CheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start3);
        ArrayList<String> firstPath3 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start3, end3, end3, firstPath3);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath3, timeMapTest, distMapTest, LocalTime.of(0, 0));
        assertEquals(0, failedInterchange.size());
    }

    @Test
    public void assertAlternateRouteForNonBinaryRoute2() {
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
    public void assertNonBinaryRoute3() {
        networkTest.solve(adjMapTest, start4);
        ArrayList<String> firstPath4 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start4, end4, end4, firstPath4);

        String[] expectedPath = {"NE1", "NE3", "NE4", "NE5", "NE6", "NE7", "NE8", "NE9", "NE10", "NE11", "NE12", "CC13", "CC14", "CC15"};
        assertArrayEquals(expectedPath, firstPath4.toArray());
        }

    @Test
    public void assertNonBinaryRoute3CheckFirstStationValid() {
        networkTest.solve(adjMapTest, start4);
        ArrayList<String> firstPath4 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start4, end4, end4, firstPath4);
        Boolean check = true;
        // last train timing of NE1 is 23:55
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath4.get(0), firstPath4.get(1), timeMapTest, LocalTime.of(23, 50)));
    }

    @Test
    public void assertNonBinaryRoute3CheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start4);
        ArrayList<String> firstPath4 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start4, end4, end4, firstPath4);
        Boolean check = false;
        // last train timing of NE1 is 23:55
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath4.get(0), firstPath4.get(1), timeMapTest, LocalTime.of(0, 0)));
    }

    @Test
    public void assertNonBinaryRoute3CheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start4);
        ArrayList<String> firstPath4 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start4, end4, end4, firstPath4);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath4, timeMapTest, distMapTest, LocalTime.of(23, 50));
        assertEquals(0, failedInterchange.size());
    }

    String start5 = "EW1";
    String end5 = "DT27";

    @Test
    public void assertNonBinaryRoute4() {
        networkTest.solve(adjMapTest, start5);
        ArrayList<String> firstPath5 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start5, end5, end5, firstPath5);

        String[] expectedPath = {"EW1", "EW2", "DT32", "DT31", "DT30", "DT29", "DT28", "DT27"};
        assertArrayEquals(expectedPath, firstPath5.toArray());
    }

    @Test
    public void assertNonBinaryRoute4CheckFirstStationValid() {
        networkTest.solve(adjMapTest, start5);
        ArrayList<String> firstPath5 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start5, end5, end5, firstPath5);
        Boolean check = true;
        // last train timing of EW1 is 23:23
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath5.get(0), firstPath5.get(1), timeMapTest, LocalTime.of(23, 20)));
    }

    @Test
    public void assertNonBinaryRoute4CheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start5);
        ArrayList<String> firstPath5= new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start5, end5, end5, firstPath5);
        Boolean check = false;
        // last train timing of EW1 is 23:23
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath5.get(0), firstPath5.get(1), timeMapTest, LocalTime.of(23, 30)));
    }

    @Test
    public void assertNonBinaryRoute4CheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start5);
        ArrayList<String> firstPath5 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start5, end5, end5, firstPath5);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath5, timeMapTest, distMapTest, LocalTime.of(23, 20));
        assertEquals(0, failedInterchange.size());
    }

    String start6 = "DT6";
    String end6 = "CC13";

    @Test
    public void assertNonBinaryRoute5() {
        networkTest.solve(adjMapTest, start6);
        ArrayList<String> firstPath6 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start6, end6, end6, firstPath6);

        String[] expectedPath = { "DT6", "DT7", "DT8", "DT9", "CC19", "CC17", "CC16", "CC15", "CC14", "CC13" };
        assertArrayEquals(expectedPath, firstPath6.toArray());
    }

    @Test
    public void assertNonBinaryRoute5CheckFirstStationValid() {
        networkTest.solve(adjMapTest, start6);
        ArrayList<String> firstPath6 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start6, end6, end6, firstPath6);
        Boolean check = true;
        // last train timing of DT6 is 23:44
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath6.get(0), firstPath6.get(1), timeMapTest, LocalTime.of(23, 40)));
    }

    @Test
    public void assertNonBinaryRoute5CheckFirstStationInvalid() {
        networkTest.solve(adjMapTest, start6);
        ArrayList<String> firstPath6 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start6, end6, end6, firstPath6);
        Boolean check = false;
        // last train timing of DT6 is 23:44
        assertEquals(check,
                TimeCheck.checkFirstStation(firstPath6.get(0), firstPath6.get(1), timeMapTest, LocalTime.of(23, 50)));
    }

    @Test
    public void assertNonBinaryRoute5CheckNoFailedInterchange() {
        networkTest.solve(adjMapTest, start6);
        ArrayList<String> firstPath6 = new ArrayList<>();
        DataUtilities.getPath(networkTest.getParentMap(), start6, end6, end6, firstPath6);
        distMapTest = networkTest.getDistMap();
        ArrayList<String> failedInterchange = TimeCheck.checkInterchangeTime(firstPath6, timeMapTest, distMapTest, LocalTime.of(23, 40));
        // this should fail because the user cannot make it in time for CC13 and the failed interchange is [DT9, CC12]
        assertEquals(2, failedInterchange.size());
    }

}
