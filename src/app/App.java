package app;

import java.util.*;
import datastructure.*;

public class App {
    public static void main(String args[]) {
        Map<String, Integer> mapCode = DataUtilities.createMapCode();
        HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
        int numOfStations = adjMap.size();

        System.out.println("== MRT Train Optimizer ==");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the starting MRT code: ");
        String start = sc.nextLine();
        System.out.print("Enter the destination MRT code:");
        String end = sc.nextLine();

        //String start = "NE15";
        //String end = "DT11";

        Graph network = new Graph(numOfStations);
        network.solve(adjMap, start);
        printPath(network, start, end);
        //printTimeToAllStations(network, start);
    }

    public static void printPath(Graph network, String start, String end) {
        if (network.getParentMap().get(end) == null)
            return;

        printPath(network, start, network.getParentMap().get(end));

        System.out.println(network.getParentMap().get(end));
    }

    public static void printTimeToAllStations(Graph network, String start) {
        System.out.println("The shortest path from start to other nodes:");
        System.out.println("Start\t\t" + "Station\t\t" + "Time");
        for (Map.Entry<String, Integer> station : network.getDistMap().entrySet()) {
            System.out.println(start + " \t\t " + station.getKey() + "\t\t " + station.getValue());
        }
    }
}