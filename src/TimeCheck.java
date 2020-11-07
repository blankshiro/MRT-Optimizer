import java.util.*;
import java.io.*;
import datastructure.*;
import java.time.LocalTime;

public class TimeCheck{
    public static void main(String args[]) {
        HashMap<String, ArrayList<HashMap<String,LocalTime>>> timeMap = createTimeMap();
        for (Map.Entry<String, ArrayList<HashMap<String,LocalTime>>> entry : timeMap.entrySet()) {
            System.out.println("entry key: " + entry.getKey());
            System.out.println("entry value: " + entry.getValue());
        }
    }

    public static HashMap<String, ArrayList<HashMap<String,LocalTime>>> createTimeMap(){
        HashMap<String, ArrayList<HashMap<String,LocalTime>>> m = new HashMap<>();
        try (Scanner sc = new Scanner(new File("last_train_timing.txt"));) {
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

        return m;
    }

    //Input: String array of station names
    public static boolean makeStnTime(String[] arr, HashMap<String, ArrayList<HashMap<String,LocalTime>>> timeMap){
        for (int i = 0; i < arr.length; i++){

        }

        return false;
    }
}