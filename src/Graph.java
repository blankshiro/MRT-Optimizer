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
    private HashMap<String, List<Station>> adjMap;
    private HashMap<String, String> parentMap;

    public Graph(int numOfStations) {
        this.numOfStations = numOfStations;
        this.visited = new HashSet<String>();
        this.pq = new PriorityQueue<Station>(numOfStations, new Station());
    }

    public void solve(HashMap<String, List<Station>> adjMap, String start) {
        this.adjMap = adjMap;
        this.distMap = new HashMap<String, Integer>();
        this.parentMap = new HashMap<>();

        for (Map.Entry<String, List<Station>> entry : adjMap.entrySet()) {
            distMap.put(entry.getKey(), Integer.MAX_VALUE);
        }

        pq.add(new Station(start, 0));
        distMap.put(start, 0);
        parentMap.put(start, null);

        while (pq.size() != 0) {
            String current = pq.remove().getStationCode();
            visited.add(current);
            adjustAdjacentDistances(current);
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
                    distMap.put(neighbourCode, newDist);
                    // parentMap.put(current, neighbourCode);
                    parentMap.put(neighbourCode, current);

                }

                pq.add(new Station(neighbourCode, distMap.get(neighbourCode)));
            }
        }
    }

    public HashMap<String, Integer> getDistMap() {
        return distMap;
    }

    public HashMap<String, String> getParentMap() {
        return parentMap;
    }
}
