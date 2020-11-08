package datastructure;

import java.util.*;

/**
 * Train station class.
 */
public class Station implements Comparator<Station> {
    /** The MRT station code. */
    private String stationCode;
    /** The travel time from one MRT station to another. */
    private int travelTime;

    public Station() {
    }

    /**
     * Constructor for a MRT station.
     * 
     * @param stationCode The MRT station code.
     * @param travelTime The travel time from one MRT station to another.
     */
    public Station(String stationCode, int travelTime) {
        this.stationCode = stationCode;
        this.travelTime = travelTime;
    }

    /**
     * Gets the MRT station code.
     * 
     * @return The MRT station code.
     */
    public String getStationCode() {
        return this.stationCode;
    }

    /**
     * Gets the travel time from one MRT station to another.
     * 
     * @return The travel time from one MRT station to another.
     */
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
