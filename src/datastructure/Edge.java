package datastructure;

/**
 * Edges connecting the train stations
 */
public class Edge {
    private final String id;
    private final Station fromStation;
    private final Station toStation;
    private final int distance;

    public Edge(String id, Station fromStation, Station toStation, int distance) {
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
