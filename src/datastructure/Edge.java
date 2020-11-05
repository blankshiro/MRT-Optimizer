package datastructure;

/**
 * Edges connecting the train stations.
 */
public class Edge {
    private final Vertex fromStation;
    private final Vertex toStation;
    private final int distance;

    /**
     * Represents the edge connecting two train stations.
     * 
     * @param fromStation From MRT station.
     * @param toStation To MRT station.
     * @param distance Distance between the two MRT stations.
     */
    public Edge(Vertex fromStation, Vertex toStation, int distance) {
        this.fromStation = fromStation;
        this.toStation  = toStation;
        this.distance = distance;
    }

    public Vertex getFromStation() {
        return fromStation;
    }

    public Vertex getToStation() {
        return toStation;
    }

    public int getDistance() {
        return distance;
    }

    public Vertex getNeighborIndex(Vertex trainStation) {
        if (this.fromStation == trainStation) {
            return this.toStation;
        } else {
            return this.fromStation;
        }
    }
}
