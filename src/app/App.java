package app;

import java.util.*;
import java.time.*;

import datastructure.*;

/**
 * Main application class.
 */
public class App {
    HashMap<String, List<Station>> adjMap = DataUtilities.createAdjMap();
    HashMap<String, ArrayList<HashMap<String, LocalTime>>> timeMap = TimeCheck.createTimeMap();
    int numOfStations = adjMap.size();
    Graph network = new Graph(numOfStations);

    public App() {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("== MRT Optimizer App ==");
            System.out.print("Please enter the starting MRT code: ");
            String start = sc.nextLine();
            System.out.print("Please enter the destination MRT code: ");
            String end = sc.nextLine();
            System.out.println("=============================================================================");

            if (isValid(start) && isValid(end)) {

                //check if the start station have trains running to begin with
                // LocalTime now = LocalTime.now();

                LocalTime now = LocalTime.of(23,55);
                // LocalTime now = LocalTime.of(0,15);


                boolean validTime = TimeCheck.checkFirstStation(start, end, timeMap, now);
                System.out.println("validTime: " + validTime);

                if (validTime){
                    network.solve(adjMap, start);
                    ArrayList<String> firstPath = new ArrayList<>();
                    ArrayList<String> failedInterchanges = new ArrayList<>();

                    DataUtilities.getPath(network.getParentMap(), start, end, end, firstPath);
                   
                    //Check time for interchanges if any
                    failedInterchanges = TimeCheck.checkInterchangeTime(firstPath, timeMap, now);
                    System.out.println("Failed Interchanges: " + failedInterchanges);
                    System.out.printf("Best path from %s to %s is: ", start, end);
                    System.out.print(firstPath);
                    System.out.println();

                    while(firstPath.size() != 0 && failedInterchanges.size() != 0){
                        String stnOne = failedInterchanges.get(0);
                        String stnTwo = failedInterchanges.get(1);
                        DataUtilities.removeStationFromNeighbours(adjMap, failedInterchanges.get(0), failedInterchanges.get(1));

                        failedInterchanges.clear();
                        firstPath.clear();
                        network = new Graph(numOfStations);
                        network.solve(adjMap, start);
                        DataUtilities.getPath(network.getParentMap(), start, end, end, firstPath);
                        System.out.println("Reran first path: " + firstPath);

                        if (firstPath.size() == 0){
                            break;
                        }

                        failedInterchanges = TimeCheck.checkInterchangeTime(firstPath, timeMap, now);

                        System.out.println(firstPath);
                        System.out.println("Failed Interchanges: " + failedInterchanges);
                    }

                    if (firstPath.size() != 0){
                        System.out.printf("Best path from %s to %s is: ", start, end);
                        System.out.print(firstPath);
                        System.out.println();
                    } else {
                        String printStr = String.format("There is no path to get from %s to %s at this time.", start, end);
                        System.out.println(printStr);
                    }
                } else {
                    System.out.println("There is no train running from this station at this time!");
                }
                    
                run = false;
            } else {
                System.out.println("You did not enter a valid train code!");
            }
        }
        sc.close();
        System.out.println("=============================================================================");
        System.out.println("Thank you for using our app.");
        System.out.println("Bye bye!");
    }

    /**
     * Check if the user input is a valid MRT station code.
     * 
     * @param station The MRT station code input.
     * @return True if the input is valid. Otherwise, return false.
     */
    public boolean isValid(String station) {
        //Check for missing stations - they do not exist on the mrt map.
        if (station.equals("DT4") || station.equals("NE2") || station.equals("NS6") || station.equals("NS12") || station.equals("CC18")){
            return false;
        }


        String line = station.substring(0, 2);
        String num = station.substring(2);
        int stationNum = 0;
        try {
            stationNum = Integer.parseInt(num);
        } catch (Exception e) {
            return false;
        }
        if (stationNum < 1)
            return false; // all stations start from 1
        switch (line) {

            case "DT":
                if (stationNum > 35)
                    return false;
                break;
            case "EW":
            case "CC":
                if (stationNum > 29)
                    return false;
                break;
            case "CE":
            case "CG":
                if (stationNum > 2)
                    return false;
                break;
            case "NE":
                if (stationNum > 17)
                    return false;
                break;
            case "NS":
                if (stationNum > 28)
                    return false;
                break;
            default:
                return false;
        }
        return true;
    }

    public static void main(String args[]) {
        App app = new App();
    }
}