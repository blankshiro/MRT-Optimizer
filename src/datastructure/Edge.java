package datastructure;

/**
 * Edges connecting the train stations
 */
public class Edge {
    private final String id;
    private final Vertex fromStation;
    private final Vertex toStation;
    private final int distance;

    public Edge(String id, Vertex fromStation, Vertex toStation, int distance) {
        this.id = id;
        this.fromStation = fromStation;
        this.toStation  = toStation;
        this.distance = distance;
    }

    public String getId() {
        return id;
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
}
