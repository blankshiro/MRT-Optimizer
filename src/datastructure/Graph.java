package datastructure;

import java.util.*;

/**
 * Graph of train stations.  
 */
public class Graph {
    private Set<Vertex> trainStations;
    
    /**
     * A graph of all the train stations.
     */
    public Graph() {
        trainStations = new HashSet<>();  // Use hashset to avoid duplicates
    }

    public void addTrainStation(Vertex trainStation) {
        trainStations.addAll(Arrays.asList(trainStation));
    }

    /**
     * Adds the train stations into the graph.
     * 
     * @param fromTrainStation From MRT station.
     * @param toTrainStation To MRT station
     * @param distance The distance between the two MRT stations.
     */
    public void addEdge(Vertex fromTrainStation, Vertex toTrainStation, int distance) {
        trainStations.add(fromTrainStation);
        trainStations.add(toTrainStation);

        // Check for invalid input
        if (fromTrainStation != toTrainStation) {
            fromTrainStation.edges.add(new Edge(fromTrainStation, toTrainStation, distance));
        }
    }

    public boolean hasConnection(Vertex fromTrainStation, Vertex toTrainStation) {
        LinkedList<Edge> edges = fromTrainStation.edges;
        for(Edge edge : edges) {
            if (edge.getToStation() == toTrainStation) {
                return true;
            }
        }
        return false;
    }
}
