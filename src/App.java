import java.util.*;

public class App {
    public static void main(String args[]) {
        HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
        int numOfStations = adjMap.size();

        String start = "NE16";
        String end = "EW12";

        Graph network = new Graph(numOfStations);
        network.solve(adjMap, start);

        ArrayList<String> firstPath = new ArrayList<>();
        DataUtilities.getPath(network.getParentMap(), start, end, end, firstPath);
        System.out.println(firstPath);

        // this will give an identical path as first path if no second path available.
        ArrayList<String> secondPath = new ArrayList<>();
        DataUtilities.getPath(network.getParentMap2(), start, end, end, secondPath);
        System.out.println(secondPath);

        // DataUtilities.printTimeToAllStations(network.getDistMap(), start);
        // DataUtilities.printTimeToAllStations(network.getDistMap2(), start);

        // DataUtilities.printParentMap(network.getParentMap());
        // DataUtilities.printParentMap(network.getParentMap2());
    }
}