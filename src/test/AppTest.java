package test;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import java.util.*;

// public class AppTest {

//     @Test
//     public void Maurice() {              
//         HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
//         int numOfStations = adjMap.size();

//         String start = "NE16";
//         String end = "DT11";

//         Graph network = new Graph(numOfStations);
//         network.solve(adjMap, start);

//         ArrayList<String> firstPath = new ArrayList<>();
//         DataUtilities.getPath(network, start, end, end, firstPath);

//         ArrayList<String> expectedPath = {NE16, NE15, NE14, NE13, NE12, NE11, NE10, NE9, NE8, NE7, DT12, DT11};
//         assertEquals(expectedPath, firstPath);
//     }
// }
