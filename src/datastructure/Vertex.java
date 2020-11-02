package datastructure;

/**
 * Train station
 */
public class Vertex {
    private final String stationCode;
    private final String stationName;
    private final String lineColor;
    public final int timeStoppedAtStation;
    private final int peakWaitingTime = 5;
    private final int nonPeakWaitingTime = 3;

    public Vertex(String stationName, String stationCode, String lineColor, int timeStoppedAtStation) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.lineColor = lineColor;
        this.timeStoppedAtStation = timeStoppedAtStation;
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

}
