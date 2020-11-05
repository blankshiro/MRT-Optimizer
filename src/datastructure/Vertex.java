package datastructure;

import java.util.LinkedList;
/**
 * Train station. For every train station there might be multiple edges (AKA connections to other train stations).
 */
public class Vertex {
    private String stationCode;
    private String stationName;
    private String lineColor;
    public int timeStoppedAtStation;
    private int peakWaitingTime = 5;
    private int nonPeakWaitingTime = 3;
    private boolean visited;
    LinkedList<Edge> edges;

    public Vertex(String stationName, String stationCode, String lineColor, int timeStoppedAtStation) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.lineColor = lineColor;
        this.timeStoppedAtStation = timeStoppedAtStation;
        visited = false;
        edges = new LinkedList<>();
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getLineColor() {
        return lineColor;
    }

    public int getTimeStoppedAtStation() {
        return timeStoppedAtStation;
    }

    public int getPeakWaitingTime() {
        return peakWaitingTime;
    }

    public int getNonPeakWaitingTime() {
        return nonPeakWaitingTime;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}