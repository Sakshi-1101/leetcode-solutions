
import java.util.HashMap;
import java.util.Map;

public class FindHighestLowestFreqOfElement {

    public static void main(String[] args) {
        int[] arr = {10,5,10,15,10,5};
        findHighLowFreqBrute(arr);
        findHightLowFreqOptimal(arr);
    }

    // TC: O(N^2)
    // SC: O(N)
    public static void findHighLowFreqBrute(int[] arr){
        int[] visited = new int[arr.length];
        int maxFreq = Integer.MIN_VALUE;
        int minFreq = Integer.MAX_VALUE;
        int maxEle = 0;
        int minEle = 0;

        for(int i = 0 ; i < arr.length ; i ++){

            // Skip this element if already counted
            if(visited[i] == 1){
                continue;
            }

            visited[i] = 1;
            int count = 1; // count the current arr[i]th element

            for(int j = i + 1 ; j < arr.length ; j ++){
                if(arr[i] == arr[j]){
                    count++;
                }
            }

            if(count > maxFreq) {
                maxFreq = count;
                maxEle = arr[i];
            }

            if(count < minFreq) {
                minFreq = count;
                minEle = arr[i];
            }
        }

        System.out.println(maxEle + " , " + minEle);
    }

    // TC: O(N)
    // SC: O(N)
    public static void findHightLowFreqOptimal(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();

        // Using HashMap to store frequency of each element
        for(int i = 0 ; i < arr.length ; i ++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i] , 1);
            }
        }

        int maxFreq = Integer.MIN_VALUE;
        int minFreq = Integer.MAX_VALUE;
        int maxEle = 0;
        int minEle = 0;


        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int ele = entry.getKey();
            int count = entry.getValue();

            if(count > maxFreq) {
                maxFreq = count;
                maxEle = ele;
            }

            if(count < minFreq) {
                minFreq = count;
                minEle = ele;
            }
        }
        
        System.out.println(maxEle + " , " + minEle);
    }

}
