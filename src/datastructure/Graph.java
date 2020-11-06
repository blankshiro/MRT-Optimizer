package datastructure;

import java.util.*;

/**
 * Graph
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
    /** Graph of stations.  */
    private HashMap<String, List<Station>> adjMap;
    /** HashMap to keep track of parents of the station. */
    private HashMap<String, String> parentMap;

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
     * @param start The starting station.
     */
    public void solve(HashMap<String, List<Station>> adjMap, String start) {
        this.adjMap = adjMap;
        this.distMap = new HashMap<String, Integer>();
        this.parentMap = new HashMap<>();

        // for each list of stations in the adjacency map, put the stations into the distMap and set all the keys to max value.
        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            distMap.put(entry.getKey(), Integer.MAX_VALUE);
        }

        // adds the starting station to the queue.
        pq.add(new Station(start, 0));
        // set the starting station key to 0.
        distMap.put(start, 0);
        // add the starting station to the parent map and set the key to null.
        parentMap.put(start, null);

        while (pq.size() != 0) { // 
            // gets the current station from the top of the queue.
            String current = pq.remove().getStationCode();
            // adds the current station to the set of visited stations.
            System.out.println("PQ = " + pq.toString());
            visited.add(current);
            adjustAdjacentDistances(current);
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

        // for each adjacent station in the adjacency map that maps to the current station.
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
                    // add the adjacent station and time into the dist map.
                    distMap.put(neighbourCode, newDist);
                    // add the adjacent station and the current station to the parent map. 
                    parentMap.put(neighbourCode, current);
                }
                // add the neighbour station and the timing to the queue.
                pq.add(new Station(neighbourCode, distMap.get(neighbourCode)));
            }
        }
    }

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
     * Getter method to get the parent map.
     * 
     * @return The parent map.
     */
    public HashMap<String, String> getParentMap() {
        return parentMap;
    }
}
