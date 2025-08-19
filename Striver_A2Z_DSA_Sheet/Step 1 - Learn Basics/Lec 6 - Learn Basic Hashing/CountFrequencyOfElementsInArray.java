import java.util.*;

public class CountFrequencyOfElementsInArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        countFrequencyBrute(arr);
        countFrequencyOptimal(arr);
    }

    // TC: O(N^2)
    // SC: O(N)
    public static void countFrequencyBrute(int[] arr) {
         boolean visited[] = new boolean[arr.length];
 
        for (int i = 0; i < arr.length; i++) {
    
            // Skip this element if already counted
            if (visited[i] == true)
                continue;
    
            
            int count = 1; // count the current arr[i]th element
            for (int j = i + 1; j < arr.length; j++) { // count all the occurences of the arr[i]th element 
                if (arr[i] == arr[j]) {
                    visited[j] = true; // whatever matched is counted so mark it as visited.
                    count++;
                }
            }
            System.out.println(arr[i] + " : " + count);
        }
    }

    // TC: O(N)
    // SC: O(N)
    public static void countFrequencyOptimal(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i ++){
            if(map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        // used when we need to fetch only keys from the map
        // poor performance when we need to fetch values as for each key, a separate get() on the map is needed to retrieve its value, which incurs additional overhead.
        for(Integer key: map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        // used when we need to fetch all key-value pairs from the map
        // more efficient than keySet() because it provides direct access to both components of each mapping without requiring additional lookups.
        for (Map.Entry<Integer,Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
}
