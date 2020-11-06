package datastructure;

import java.util.*;
import java.io.*;

public class DataUtilities {
    public static HashMap<String, Integer> createMapCode() {
        HashMap<String, Integer> m = new HashMap<>();

        m.put("DT1", 0);
        m.put("DT2", 1);
        m.put("DT3", 2);
        // No DT4
        m.put("DT5", 4);
        m.put("DT6", 5);
        m.put("DT7", 6);
        m.put("DT8", 7);
        m.put("DT9", 8);
        m.put("DT10", 9);
        m.put("DT11", 10);
        m.put("DT12", 11);
        m.put("DT13", 12);
        m.put("DT14", 13);
        m.put("DT15", 14);
        m.put("DT16", 15);
        m.put("DT17", 16);
        m.put("DT18", 17);
        m.put("DT19", 18);
        m.put("DT20", 19);
        m.put("DT21", 20);
        m.put("DT22", 21);
        m.put("DT23", 22);
        m.put("DT24", 23);
        m.put("DT25", 24);
        m.put("DT26", 25);
        m.put("DT27", 26);
        m.put("DT28", 27);
        m.put("DT29", 28);
        m.put("DT30", 29);
        m.put("DT31", 30);
        m.put("DT32", 31);
        m.put("DT33", 32);
        m.put("DT34", 33);
        m.put("DT35", 34);
        // Circle line
        m.put("CC1", 35);
        m.put("CC2", 36);
        m.put("CC3", 37);
        m.put("CC4", 38);
        m.put("CC5", 39);
        m.put("CC6", 40);
        m.put("CC7", 41);
        m.put("CC8", 42);
        m.put("CC9", 43);
        m.put("CC10", 44);
        m.put("CC11", 45);
        m.put("CC12", 46);
        m.put("CC13", 47);
        m.put("CC14", 48);
        m.put("CC15", 49);
        m.put("CC16", 50);
        m.put("CC17", 51);
        // No CC18
        m.put("CC19", 52);
        m.put("CC20", 53);
        m.put("CC21", 54);
        m.put("CC22", 55);
        m.put("CC23", 56);
        m.put("CC24", 57);
        m.put("CC25", 58);
        m.put("CC26", 59);
        m.put("CC27", 60);
        m.put("CC28", 61);
        m.put("CC29", 62);
        m.put("CE1", 63);
        m.put("CE2", 64);
        // East West Line
        m.put("EW1", 65);
        m.put("EW2", 66);
        m.put("EW3", 67);
        m.put("EW4", 68);
        m.put("EW5", 69);
        m.put("EW6", 70);
        m.put("EW7", 71);
        m.put("EW8", 72);
        m.put("EW9", 73);
        m.put("EW10", 74);
        m.put("EW11", 75);
        m.put("EW12", 76);
        m.put("EW13", 77);
        m.put("EW14", 78);
        m.put("EW15", 79);
        m.put("EW16", 80);
        m.put("EW17", 81);
        m.put("EW18", 82);
        m.put("EW19", 83);
        m.put("EW20", 84);
        m.put("EW21", 85);
        m.put("EW22", 86);
        m.put("EW23", 87);
        m.put("EW24", 88);
        m.put("EW25", 89);
        m.put("EW26", 90);
        m.put("EW27", 91);
        m.put("EW28", 92);
        m.put("EW29", 93);// 94 to 97
        // North East Line
        m.put("NE1", 98);
        // no NE2
        m.put("NE3", 99);
        m.put("NE4", 100);
        m.put("NE5", 101);
        m.put("NE6", 102);
        m.put("NE7", 103);
        m.put("NE8", 104);
        m.put("NE9", 105);
        m.put("NE10", 106);
        m.put("NE11", 107);
        m.put("NE12", 108);
        m.put("NE13", 109);
        m.put("NE14", 110);
        m.put("NE15", 111);
        m.put("NE16", 112);
        m.put("NE17", 113); // 114 to 123
        // NS Line
        m.put("NS1", 3);
        m.put("NS2", 124);
        m.put("NS3", 125);
        m.put("NS4", 126);
        m.put("NS5", 127);
        m.put("NS7", 128);
        m.put("NS8", 129);
        m.put("NS9", 130);
        m.put("NS10", 131);
        m.put("NS11", 132);
        // m.put("NS12", 133); No NS12
        m.put("NS13", 134);
        m.put("NS14", 135);
        m.put("NS15", 136);
        m.put("NS16", 115);
        m.put("NS17", 97);
        m.put("NS18", 96);
        m.put("NS19", 95);
        m.put("NS20", 94);
        m.put("NS21", 123);
        m.put("NS22", 122);
        m.put("NS23", 121);
        m.put("NS24", 120);
        m.put("NS25", 119);
        m.put("NS26", 118);
        m.put("NS27", 117);
        m.put("NS28", 116);

        // Changi Airport
        // m.put("CG", 115);
        m.put("CG1", 114);
        m.put("CG2", 133);

        return m;
    }

    /**
     * Creates an adjacency map by reading the traveltime.txt file.
     * 
     * @return Adjacency map of the train stations.
     */
    public static HashMap<String, List<Station>> createAdjMap() {
        // create a hashmap that stores the value of the current station and maps it to its adjacent stations (can be more than 1)
        HashMap<String, List<Station>> adjMap = new HashMap<String, List<Station>>();
        try (Scanner sc = new Scanner(new File("C:/Users/User/Desktop/CS201G2T5/src/data/traveltime.txt"));) { // <-- Put the path to the traveltime.txt file
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" ");
                // System.out.println(Arrays.toString(arr));

                // if the array is length of 3 and does not contain "//"
                if (arr.length == 3 && !(arr[0].contains("//"))) {
                    // create the next train station from arr[1] with the travel time at arr[2]
                    Station nextStation = new Station(arr[1], Integer.parseInt(arr[2]));
                    // checks for the adjacent stations that is mapped from the current station (if any)
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
}
