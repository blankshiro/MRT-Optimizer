package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import datastructure.*;

/**
 * Main application class.
 */
public class App {
    public static void main(String args[]) {
        HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
        int numOfStations = adjMap.size();

        // String start = "NE16";
        // String end = "EW12";

        System.out.println("== MRT Train Optimizer ==");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the starting MRT code: ");
        String start = sc.nextLine();
        System.out.print("Enter the destination MRT code:");
        String end = sc.nextLine();

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