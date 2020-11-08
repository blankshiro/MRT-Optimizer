package datastructure;

import java.util.*;

/**
 * Graph class.
 */
public class Graph {
    /** Number of stations. */
    private int numOfStations;
    /** Set of visited stations. */
    private Set<String> visited;
    /** Queue of the adjacent stations. */
    private PriorityQueue<Station> pq;
    /** HashMap to keep track of the time travelled. */
    private HashMap<String, Integer> distMap;
    /** Second HashMap to keep track of the alternative time travelled. */
    private HashMap<String, Integer> distMap2;
    /** Graph of stations. */
    private HashMap<String, List<Station>> adjMap;
    /** HashMap to keep track of parents of the station. */
    private HashMap<String, String> parentMap;
    /** Second HashMap to keep track of the parents of the station. */
    private HashMap<String, String> parentMap2;

    /**
     * Constructs a graph based on the adjacency map size.
     * 
     * @param numOfStations The number of stations.
     */
    public Graph(int numOfStations) {
        this.numOfStations = numOfStations;
        this.visited = new HashSet<String>();
        this.pq = new PriorityQueue<Station>(numOfStations, new Station());
    }

    /**
     * Find the shortest path from the starting station.
     * 
     * @param adjMap The adjacency map.
     * @param start  The starting station.
     */
    public void solve(HashMap<String, List<Station>> adjMap, String start) {
        this.adjMap = adjMap;
        this.distMap = new HashMap<>();
        this.distMap2 = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.parentMap2 = new HashMap<>();
        /**
         * for each list of stations in the adjacency map, put the stations into the
         * distMap and set all the keys to max value.
         */
        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            distMap.put(entry.getKey(), Integer.MAX_VALUE);
            distMap2.put(entry.getKey(), Integer.MAX_VALUE);
        }
        // adds the starting station to the queue.
        pq.add(new Station(start, 0));
        // set the starting station key to 0.
        distMap.put(start, 0);

        distMap2.put(start, 0);
        // add the starting station to the parent map and set the key to null.
        parentMap.put(start, null);

        parentMap2.put(start, null);

        while (pq.size() != 0) {
            // System.out.println("PQ: " + pq);
            // gets the current station from the top of the queue.
            String current = pq.remove().getStationCode();
            // System.out.println("Visiting " + current);
            // adds the current station to the set of visited stations.
            visited.add(current);
            adjustAdjacentDistances(current);
        }

        for (Map.Entry<String, String> entry : parentMap2.entrySet()) {
            if (entry.getValue() == null) {
                parentMap2.put(entry.getKey(), parentMap.get(entry.getKey()));
            }
        }
    }

    /**
     * Algorithm to find the shortest path from the starting station.
     * 
     * @param current
     */
    private void adjustAdjacentDistances(String current) {
        int edgeDist = -1;
        int newDist = -1;

        /**
         * for each adjacent station in the adjacency map that maps to the current
         * station.
         */
        for (Station neighbour : adjMap.get(current)) {
            // get the adjacent station.
            String neighbourCode = neighbour.getStationCode();
            // if the set of visited stations does not contain the adjacent station.
            if (!visited.contains(neighbourCode)) {
                // get the time needed to travel to the adjacent station.
                edgeDist = neighbour.getTravelTime();
                // set the new timing to current timing + adjacent timing.
                newDist = distMap.get(current) + edgeDist;

                // if the new timing is less than the neighbour time.
                if (newDist < distMap.get(neighbourCode)) {

                    distMap2.put(neighbourCode, distMap.get(neighbourCode));
                    // add the adjacent station and time into the dist map.
                    distMap.put(neighbourCode, newDist);

                    parentMap2.put(neighbourCode, parentMap.get(neighbourCode));
                    // add the adjacent station and the current station to the parent map.
                    parentMap.put(neighbourCode, current);
                } else if (newDist < distMap2.get(neighbourCode)) {
                    distMap2.put(neighbourCode, newDist);
                    parentMap2.put(neighbourCode, current);
                }
                // add the neighbour station and the timing to the queue.
                pq.add(new Station(neighbourCode, distMap.get(neighbourCode)));
            }
        }
    }

    /**
     * Gets the number of MRT stations.
     * 
     * @return The number of MRT stations.
     */
    public int getNumOfStations() {
        return numOfStations;
    }

    /**
     * Getter method to get the dist map.
     * 
     * @return The dist map.
     */
    public HashMap<String, Integer> getDistMap() {
        return distMap;
    }

    /**
     * Getter method to get the second dist map.
     * 
     * @return The second dist map.
     */
    public HashMap<String, Integer> getDistMap2() {
        return distMap2;
    }

    /**
     * Getter method to get the parent map.
     * 
     * @return The parent map.
     */
    public HashMap<String, String> getParentMap() {
        return parentMap;
    }

    /**
     * Getter method to get the second parent map.
     * 
     * @return The second parent map.
     */
    public HashMap<String, String> getParentMap2() {
        return parentMap2;
    }
}
