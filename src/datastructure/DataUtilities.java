package datastructure;

import java.util.*;
import java.io.*;

/**
 * DataUtilities class.
 */
public class DataUtilities {
    public static HashMap<String, List<Station>> createAdjMap() {
        /**
         * create a hashmap that stores the value of the current station and maps it to
         * its adjacent stations (can be more than 1)
         */
        HashMap<String, List<Station>> adjMap = new HashMap<String, List<Station>>();
        try (Scanner sc = new Scanner(new File("C:/Users/User/Desktop/CS201G2T5/src/data/traveltime.txt"));) {
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" ");
                // System.out.println(Arrays.toString(arr));

                // if the array is length of 3 and does not contain "//"
                if (arr.length == 3 && !(arr[0].contains("//"))) {
                    // create the next train station from arr[1] with the travel time at arr[2]
                    Station nextStation = new Station(arr[1], Integer.parseInt(arr[2]));
                    /**
                     * checks for the adjacent stations that is mapped from the current station (if
                     * any)
                     */
                    List<Station> adjacentStations = adjMap.getOrDefault(arr[0], new ArrayList<>());
                    // add the next station to the list of adjacent stations
                    adjacentStations.add(nextStation);
                    // put it into the hashmap
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

    /**
     * Prints the adjacency map of train stations.
     * 
     * @param adjMap The adjacency map to be printed.
     */
    public static void printAdjacencyMap(HashMap<String, List<Station>> adjMap) {
        System.out.println("Adjacency Map:");
        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Prints the parent map of the train station.
     * 
     * @param parentMap The parent map to be printed.
     */
    public static void printParentMap(HashMap<String, String> parentMap) {
        System.out.println("Parent Map:");
        for (Map.Entry<String, String> entry : parentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * 
     * 
     * @param parentMap
     * @param start
     * @param end
     * @param current
     * @param path
     */
    public static void getPath(HashMap<String, String> parentMap, String start, String end, String current,
            ArrayList<String> path) {
        if (parentMap.get(current) == null)
            return;

        getPath(parentMap, start, end, parentMap.get(current), path);
        path.add(parentMap.get(current));
        if (current == end)
            path.add(end);
    }

    /**
     * 
     * 
     * @param distMap
     * @param start
     */
    public static void printTimeToAllStations(HashMap<String, Integer> distMap, String start) {
        System.out.println("The shortest path from start to other nodes:");
        System.out.println("Start\t\t" + "Station\t\t" + "Time");
        for (Map.Entry<String, Integer> station : distMap.entrySet()) {
            System.out.println(start + " \t\t " + station.getKey() + "\t\t " + station.getValue());
        }
    }
}
