package datastructure;

import java.util.List;

/**
 * Graph
 */
public class Graph {
    private final List<Vertex> trainStations;
    private final List<Edge> edges;

    public Graph(List<Vertex> trainStations, List<Edge> edges) {
        this.trainStations = trainStations;
        this.edges = edges;
    }

    public List<Vertex> getTrainStations() {
        return trainStations;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
