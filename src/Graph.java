// package datastructure;

import java.util.*;

/**
 * Graph
 */
public class Graph {
    private int numOfStations;
    private Set<String> visited;
    private PriorityQueue<Station> pq;
    private HashMap<String, Integer> distMap;
    private HashMap<String, Integer> distMap2;
    private HashMap<String, List<Station>> adjMap;
    private HashMap<String, String> parentMap;
    private HashMap<String, String> parentMap2;

    public Graph(int numOfStations) {
        this.numOfStations = numOfStations;
        this.visited = new HashSet<String>();
        this.pq = new PriorityQueue<Station>(numOfStations, new Station());
    }

    public void solve(HashMap<String, List<Station>> adjMap, String start) {
        this.adjMap = adjMap;
        this.distMap = new HashMap<>();
        this.distMap2 = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.parentMap2 = new HashMap<>();

        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            distMap.put(entry.getKey(), Integer.MAX_VALUE);
            distMap2.put(entry.getKey(), Integer.MAX_VALUE);
        }

        pq.add(new Station(start, 0));
        distMap.put(start, 0);
        distMap2.put(start, 0);
        parentMap.put(start, null);
        parentMap2.put(start, null);

        while (pq.size() != 0) {
            // System.out.println("PQ: " + pq);
            String current = pq.remove().getStationCode();
            // System.out.println("Visiting " + current);
            visited.add(current);
            adjustAdjacentDistances(current);
        }

        for (Map.Entry<String, String> entry : parentMap2.entrySet()) {
            if (entry.getValue() == null) {
                parentMap2.put(entry.getKey(), parentMap.get(entry.getKey()));
            }
        }
    }

    private void adjustAdjacentDistances(String current) {
        int edgeDist = -1;
        int newDist = -1;

        for (Station neighbour : adjMap.get(current)) {
            String neighbourCode = neighbour.getStationCode();

            if (!visited.contains(neighbourCode)) {
                edgeDist = neighbour.getTravelTime();
                newDist = distMap.get(current) + edgeDist;

                if (newDist < distMap.get(neighbourCode)) {
                    distMap2.put(neighbourCode, distMap.get(neighbourCode));
                    distMap.put(neighbourCode, newDist);
                    parentMap2.put(neighbourCode, parentMap.get(neighbourCode));
                    parentMap.put(neighbourCode, current);
                } else if (newDist < distMap2.get(neighbourCode)) {
                    distMap2.put(neighbourCode, newDist);
                    parentMap2.put(neighbourCode, current);
                }

                pq.add(new Station(neighbourCode, distMap.get(neighbourCode)));
            }
        }
    }

    public HashMap<String, Integer> getDistMap() {
        return distMap;
    }

    public HashMap<String, Integer> getDistMap2() {
        return distMap2;
    }

    public HashMap<String, String> getParentMap() {
        return parentMap;
    }

    public HashMap<String, String> getParentMap2() {
        return parentMap2;
    }
}
