package datastructure;

import java.util.*;
import java.io.*;
import datastructure.*;
import java.time.*;
import java.math.BigInteger;

public class TimeCheck{
    
    // public static void main(String args[]) {
    //     HashMap<String, ArrayList<HashMap<String,LocalTime>>> timeMap = createTimeMap();
    //     ArrayList<String> testList = new ArrayList<>();
    //     testList.add("DT10");
    //     testList.add("DT9");
    //     testList.add("CC19");
    //     testList.add("CC17");
    //     testList.add("CC15");
    //     testList.add("CC14");

    //     System.out.println(makeStnTime(testList, timeMap, LocalTime.of(4, 30)));
    //     System.out.println(makeStnTime(new String[]{"DT10", "DT11"}, timeMap, LocalTime.of(4, 30)));
    //     System.out.println(makeStnTime(new String[]{"DT10", "DT9"}, timeMap, LocalTime.of(22, 30)));
    // }

    public static HashMap<String, ArrayList<HashMap<String,LocalTime>>> createTimeMap(){
        HashMap<String, ArrayList<HashMap<String,LocalTime>>> m = new HashMap<>();
        try (Scanner sc = new Scanner(new File("src/data/last_train_timing.txt"));) {
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" ");
                ArrayList<HashMap<String,LocalTime>> list = new ArrayList<>();
                HashMap<String, LocalTime> destination = new HashMap<>();
                if (!m.containsKey(arr[0])){
                    m.put(arr[0], list);
                } 

                LocalTime time = LocalTime.parse(arr[2]);
                destination.put(arr[1], time);

                if (!m.get(arr[0]).isEmpty()){
                    list = m.get(arr[0]);
                } 

                list.add(destination);
                m.replace(arr[0], list);
            }
        } catch (FileNotFoundException e) {
            // swallow the exception
            e.printStackTrace();
        }

        //m output sample: {"DT27": [{"DT1" = 00:00}, ...], ...}
        return m;
    }

    //checks and returns if first station can be made, if not return false
    public static boolean checkFirstStation(String start, String end, HashMap<String, ArrayList<HashMap<String,LocalTime>>> timeMap, LocalTime now){
        LocalTime midnight = LocalTime.of(23,59);
        // LocalTime oneMinuteBeforeMidnight = LocalTime.of(23,59);
        LocalDate today = LocalDate.now();
        LocalDateTime todayBeforeMidnight = LocalDateTime.of(today, LocalTime.of(23,59));
        LocalDateTime todayAfterMidnight = LocalDateTime.of(today, LocalTime.of(0,0));
        LocalDateTime mrtStartTime = LocalDateTime.of(today, LocalTime.of(6, 00));

        //retrieve the relevant dataset from the hashmap
        ArrayList<HashMap<String,LocalTime>> temp = timeMap.get(start);

        String stopNoOrigin = "";
        String stopNoDestination = "";

        //check if which direction the route is taking, i.e. if the next stop if before or after the current stop
        if (start.length() < 4){
            stopNoOrigin = start.substring(2,3);
        } else {
            stopNoOrigin = start.substring(2,4);
        }

        if (end.length() < 4){
            stopNoDestination = end.substring(2,3);
        } else {
            stopNoDestination = end.substring(2,4);
        }

        boolean bigger = false;

        if (Integer.parseInt(stopNoDestination) > Integer.parseInt(stopNoOrigin)){
            bigger = true;
        }

        boolean madeIT = false;

        for (int l = 0; l < temp.size(); l++){
            if (temp.get(l).containsKey(end)){
                LocalTime v = temp.get(l).get(end);
                LocalDateTime nowDT = LocalDateTime.of(today, now);
                LocalDateTime valueDT = LocalDateTime.of(today, v);

                if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                    todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                }

                // if (v.isAfter(LocalTime.MIDNIGHT) && nowDT.isBefore(todayBeforeMidnight) || nowDT.equals(todayBeforeMidnight)){                    
                //     valueDT = valueDT.plusDays(1);
                //     mrtStartTime = mrtStartTime.plusDays(1);
                // }
                if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                    if (v.isAfter(LocalTime.MIDNIGHT)){                    
                        valueDT = valueDT.plusDays(1);
                        mrtStartTime = mrtStartTime.plusDays(1);
                    }
                }
                
                System.out.println("nowDT.isAfter(todayBeforeMidnight): " +  nowDT.isAfter(todayBeforeMidnight));
                System.out.println("v.isAfter(LocalTime.MIDNIGHT): " +  v.isAfter(LocalTime.MIDNIGHT));
                System.out.println("now.isAfter(midnight): " +  now.isAfter(midnight));
                System.out.println("now.equals(LocalTime.of(23,59)): " +  now.equals(LocalTime.of(23,59)));
                System.out.println("todayBeforeMidnight: " +  todayBeforeMidnight);
                System.out.println("nowDT: " +  nowDT);
                System.out.println("valueDT: " +  valueDT);
                System.out.println("nowDT.isBefore(valueDT): " +  nowDT.isBefore(valueDT));

                if (nowDT.isBefore(valueDT)){
                    madeIT = true;
                }

                if (nowDT.isAfter(mrtStartTime)){
                    madeIT = true;
                }

            } else {
                HashMap<String,LocalTime> tempHash = temp.get(l);
                for (Map.Entry<String, LocalTime> entry : tempHash.entrySet()) {
                    String k = entry.getKey();
                    LocalTime v = entry.getValue();

                    int stnCode = 0;
                    
                    if (k.length() < 4){
                        stnCode = Integer.parseInt(k.substring(2,3));
                    } else {
                        stnCode = Integer.parseInt(k.substring(2,4));
                    }

                    if (bigger){
                        //Retrieve the localtime for the hashmap, convert to datetime and check if the current time is before the last train time
                        if (stnCode > Integer.parseInt(stopNoOrigin)){
                            LocalDateTime nowDT = LocalDateTime.of(today, now);
                            LocalDateTime valueDT = LocalDateTime.of(today, v);

                            System.out.println("now.isBefore(LocalTime.of(23,59)): " + now.isBefore(LocalTime.of(23,59)));
                            if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                                todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                            }

                            // if (v.isAfter(LocalTime.MIDNIGHT) && nowDT.isBefore(todayBeforeMidnight) || nowDT.equals(todayBeforeMidnight)){                    
                            //     valueDT = valueDT.plusDays(1);
                            //     mrtStartTime = mrtStartTime.plusDays(1);
                            // }

                            if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                                if (v.isAfter(LocalTime.MIDNIGHT)){                    
                                    valueDT = valueDT.plusDays(1);
                                    mrtStartTime = mrtStartTime.plusDays(1);
                                }
                            }


                            // if (v.isAfter(midnight) && !now.isAfter(midnight)){
                            //     valueDT = valueDT.plusDays(1);
                            // }
                            
                            System.out.println("v.isAfter(LocalTime.MIDNIGHT): " +  v.isAfter(LocalTime.MIDNIGHT));
                            System.out.println("nowDT.isAfter(todayBeforeMidnight): " +  nowDT.isAfter(todayBeforeMidnight));
                            System.out.println("now.isAfter(midnight): " +  now.isAfter(midnight));
                            System.out.println("now.equals(LocalTime.of(23,59)): " +  now.equals(LocalTime.of(23,59)));
                            System.out.println("todayBeforeMidnight: " +  todayBeforeMidnight);
                            System.out.println("nowDT: " +  nowDT);
                            System.out.println("valueDT: " +  valueDT);
                            System.out.println("nowDT.isBefore(valueDT): " +  nowDT.isBefore(valueDT));
                            System.out.println("nowDT.isAfter(mrtStartTime): " +  nowDT.isAfter(mrtStartTime));

                            if (nowDT.isBefore(valueDT)){
                                madeIT = true;
                            }

                            if (nowDT.isAfter(mrtStartTime)){
                                madeIT = true;
                            }
                        }
                    } else {
                        if (stnCode < Integer.parseInt(stopNoOrigin)){
                            LocalDateTime nowDT = LocalDateTime.of(today, now);
                            LocalDateTime valueDT = LocalDateTime.of(today, v);

                            if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                                todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                            }

                            if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                                if (v.isAfter(LocalTime.MIDNIGHT)){                    
                                    valueDT = valueDT.plusDays(1);
                                    mrtStartTime = mrtStartTime.plusDays(1);
                                }
                            }

                            System.out.println("v.isAfter(LocalTime.MIDNIGHT): " +  v.isAfter(LocalTime.MIDNIGHT));
                            System.out.println("nowDT.isAfter(todayBeforeMidnight): " +  nowDT.isAfter(todayBeforeMidnight));
                            System.out.println("now.isAfter(midnight): " +  now.isAfter(midnight));
                            System.out.println("now.equals(LocalTime.of(23,59)): " +  now.equals(LocalTime.of(23,59)));
                            System.out.println("todayBeforeMidnight: " +  todayBeforeMidnight);
                            System.out.println("nowDT: " +  nowDT);
                            System.out.println("valueDT: " +  valueDT);
                            System.out.println("nowDT.isBefore(valueDT): " +  nowDT.isBefore(valueDT));
                            System.out.println("nowDT.isAfter(mrtStartTime): " +  nowDT.isAfter(mrtStartTime));

                            if (nowDT.isBefore(valueDT)){
                                madeIT = true;
                            }

                            if (nowDT.isAfter(mrtStartTime)){
                                madeIT = true;
                            }
                        }
                    }
    
                }
             
            }
        }

        return madeIT;
    }

    //Input: String array of station names
    public static ArrayList<String> checkInterchangeTime(ArrayList<String> arr, HashMap<String, ArrayList<HashMap<String,LocalTime>>> timeMap, LocalTime now){
        //get current system time - REPLACE BEFORE SUBMISSION
        // LocalTime now = LocalTime.now();
        
        LocalTime midnight = LocalTime.of(23,59);
        // LocalTime oneMinuteBeforeMidnight = LocalTime.of(23,59);
        LocalDate today = LocalDate.now();
        LocalDateTime todayBeforeMidnight = LocalDateTime.of(today, LocalTime.of(23,59));
        LocalDateTime todayAfterMidnight = LocalDateTime.of(today, LocalTime.of(0,0));
        LocalDateTime mrtStartTime = LocalDateTime.of(today, LocalTime.of(6, 00));

        ArrayList<String> checkingInterchanges = new ArrayList<String>();
        ArrayList<String> failedInterchanges = new ArrayList<String>();

        //retrieve station code and number
        String firstStn = arr.get(0).substring(0,2);

        //retrieves all important stations that needs to compare time
        for (int i = 0; i < arr.size(); i++){
            String currentLine = arr.get(i).substring(0,2);

            if (i == 0){
                checkingInterchanges.add(arr.get(i));
            } 

            if (!currentLine.equals(firstStn)){
                if (i - 1 != 0){
                    checkingInterchanges.add(arr.get(i-1));
                }
                checkingInterchanges.add(arr.get(i));
                firstStn = arr.get(i).substring(0,2); //change the first station to the new line
            } else{ //if reached the end of the path, add the last station
                int temp = i + 1;
                if (temp == arr.size()){
                    checkingInterchanges.add(arr.get(i));
                }
            }
        }

        //if list only has 2 stations, that means no interchange needed, and original line has already been checked
        if (checkingInterchanges.size() > 2){
            boolean madeIT = false;
            //now checking all the interchanges
            for (int j = 2; j < checkingInterchanges.size(); j+=2){
                String origin = checkingInterchanges.get(j);
                String destination = checkingInterchanges.get(j+1);
    
                //retrieve the relevant dataset from the hashmap
                ArrayList<HashMap<String,LocalTime>> temp = timeMap.get(origin);
    
                String stopNoOrigin = "";
                String stopNoDestination = "";
    
                //check if which direction the route is taking, i.e. if the next stop if before or after the current stop
                if (origin.length() < 4){
                    stopNoOrigin = origin.substring(2,3);
                } else {
                    stopNoOrigin = origin.substring(2,4);
                }
    
                if (destination.length() < 4){
                    stopNoDestination = destination.substring(2,3);
                } else {
                    stopNoDestination = destination.substring(2,4);
                }
    
                boolean bigger = false;
    
                if (Integer.parseInt(stopNoDestination) > Integer.parseInt(stopNoOrigin)){
                    bigger = true;
                }
    
                for (int l = 0; l < temp.size(); l++){
                    if (temp.get(l).containsKey(destination)){
                        LocalTime v = temp.get(l).get(destination);
                        LocalDateTime nowDT = LocalDateTime.of(today, now);
                        LocalDateTime valueDT = LocalDateTime.of(today, v);

                        if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                            todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                        }

                        if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                            if (v.isAfter(LocalTime.MIDNIGHT)){                    
                                valueDT = valueDT.plusDays(1);
                                mrtStartTime = mrtStartTime.plusDays(1);
                            }
                        }
    
                        if (nowDT.isBefore(valueDT)){
                            madeIT = true;
                        }

                        if (nowDT.isAfter(mrtStartTime)){
                            madeIT = true;
                        }
    
                    } else {
                        HashMap<String,LocalTime> tempHash = temp.get(l);
                        for (Map.Entry<String, LocalTime> entry : tempHash.entrySet()) {
                            String k = entry.getKey();
                            LocalTime v = entry.getValue();
    
                            int stnCode = 0;
                            
                            if (k.length() < 4){
                                stnCode = Integer.parseInt(k.substring(2,3));
                            } else {
                                stnCode = Integer.parseInt(k.substring(2,4));
                            }
    
                            if (bigger){
                                //Retrieve the localtime for the hashmap, convert to datetime and check if the current time is before the last train time
                                if (stnCode > Integer.parseInt(stopNoOrigin)){
                                    LocalDateTime nowDT = LocalDateTime.of(today, now);
                                    LocalDateTime valueDT = LocalDateTime.of(today, v);

                                    if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                                        todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                                    }
        
                                    if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                                        if (v.isAfter(LocalTime.MIDNIGHT)){                    
                                            valueDT = valueDT.plusDays(1);
                                            mrtStartTime = mrtStartTime.plusDays(1);
                                        }
                                    }
    
                                    if (nowDT.isBefore(valueDT)){
                                        madeIT = true;
                                    }
        
                                    if (nowDT.isAfter(mrtStartTime)){
                                        madeIT = true;
                                    }
                                }
                            } else {
                                if (stnCode < Integer.parseInt(stopNoOrigin)){
                                    LocalDateTime nowDT = LocalDateTime.of(today, now);
                                    LocalDateTime valueDT = LocalDateTime.of(today, v);

                                    if (now.isBefore(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59))){
                                        todayAfterMidnight = todayAfterMidnight.plusDays(1);   
                                    }
        
                                    if (!nowDT.isAfter(todayBeforeMidnight) && (now.isAfter(LocalTime.of(23,59)) || now.equals(LocalTime.of(23,59)))){
                                        if (v.isAfter(LocalTime.MIDNIGHT)){                    
                                            valueDT = valueDT.plusDays(1);
                                            mrtStartTime = mrtStartTime.plusDays(1);
                                        }
                                    }
    
                                    if (nowDT.isBefore(valueDT)){
                                        madeIT = true;
                                    }
        
                                    if (nowDT.isAfter(mrtStartTime)){
                                        madeIT = true;
                                    }
                                }
                            }
            
                        }
                     
                    }
                }
    
                if (!madeIT){
                    //add to arraylist for failed interchanges for removal later and recalculation
                    failedInterchanges.add(checkingInterchanges.get(j-1));
                    failedInterchanges.add(origin);
                }
            }
        }

        return failedInterchanges;
    }
}