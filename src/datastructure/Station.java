package datastructure;

import java.util.*;

/**
 * Train station
 */
public class Station implements Comparator<Station> {
    private String stationCode;
    private int travelTime;

    public Station() {
    }

    public Station(String stationCode, int travelTime) {
        this.stationCode = stationCode;
        this.travelTime = travelTime;
    }

    public String getStationCode() {
        return this.stationCode;
    }

    public int getTravelTime() {
        return this.travelTime;
    }

    @Override
    public String toString() {
        // return getStationCode() + " " + getTravelTime();
        return getStationCode();
    }

    @Override
    public int compare(Station station1, Station station2) {
        if (station1.getTravelTime() < station2.getTravelTime())
            return -1;
        else if (station1.getTravelTime() > station2.getTravelTime())
            return 1;
        return 0;
    }
}
