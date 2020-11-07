// package datastructure;

import java.util.*;
import java.io.*;
import datastructure.*;

public class DataUtilities {
    public static HashMap<String, List<Station>> createAdjMap()  {
        HashMap<String, List<Station>> adjMap = new HashMap<String, List<Station>>();
        try (Scanner sc = new Scanner(new File("traveltime.txt"));) {
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" ");
                // System.out.println(Arrays.toString(arr));
                if (arr.length == 3 && !(arr[0].contains("//"))) {
                    Station nextStation = new Station(arr[1], Integer.parseInt(arr[2]));
                    List<Station> adjacentStations = adjMap.getOrDefault(arr[0], new ArrayList<>());
                    adjacentStations.add(nextStation);
                    adjMap.put(arr[0], adjacentStations);

                    // add the opposite because undirected
                    nextStation = new Station(arr[0], Integer.parseInt(arr[2]));
                    adjacentStations = adjMap.getOrDefault(arr[1], new ArrayList<>());
                    adjacentStations.add(nextStation);
                    adjMap.put(arr[1], adjacentStations);
                }
            }
        } catch (FileNotFoundException e) {
            // swallow the exception
            e.printStackTrace();
        }
        // System.out.println(adjMap.size()); // should print out 137
        return adjMap;
    }

    public static void printAdjacencyMap(HashMap<String, List<Station>> adjMap) {
        System.out.println("Adjacency Map:");
        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void printParentMap(HashMap<String, String> parentMap) {
        System.out.println("Parent Map:");
        for (Map.Entry<String, String> entry : parentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void getPath(HashMap<String, String> parentMap, String start, String end, String current, ArrayList<String> path) {
        if (parentMap.get(current) == null) return;
        
        getPath(parentMap, start, end, parentMap.get(current), path);
        path.add(parentMap.get(current));
        if (current == end) path.add(end);
    }

    public static void printTimeToAllStations(HashMap<String, Integer> distMap, String start) {
        System.out.println("The shortest path from start to other nodes:");
        System.out.println("Start\t\t" + "Station\t\t" + "Time");
        for (Map.Entry<String, Integer> station : distMap.entrySet()) {
            System.out.println(start + " \t\t " + station.getKey() + "\t\t " + station.getValue());
        }
    }
}
