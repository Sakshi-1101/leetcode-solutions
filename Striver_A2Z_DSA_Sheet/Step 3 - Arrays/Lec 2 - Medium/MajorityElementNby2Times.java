import java.util.Arrays;
import java.util.HashMap;

public class MajorityElementNby2Times {
    
    public static void main (String[] args) {
        int[] arr = {4,4,2,4,3,4,4,3,2,4};

        int ans1 = majorityElementBrute(arr);
        int ans2 = majorityElementBetter(arr);
        int ans3 = majorityElementApproach(arr);
        int ans4 = majorityElementOptimal(arr);

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: We will run a loop for each element such that it will check the rest of the array and count its occurrences.
    public static int majorityElementBrute(int[] arr) {
        int ans = 0;
        for(int i = 0 ; i < arr.length ; i ++) {
            int count = 1;
            for(int j = i + 1 ; j < arr.length ; j ++) {
                if(arr[i] == arr[j]) {
                    count++;
                }
            }

            // if the count > n/2 for this element that will be our ans
            if(count > (arr.length / 2)) {
                ans = arr[i];
            }
        }
        
        return ans;
    }

    // TC: O(N*logN)
    // SC: O(1)
    // Approach: Sort the array and return the middle element
    public static int majorityElementApproach(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length/2];
    }

    // TC: O(N*logN) + O(N)
    // SC: O(N)
    // Approach: We'll store the frequency of occurrence of each element in hashmap and then we'll traverse the map and find the element whose count of occurrence is greater than n/2
    public static int majorityElementBetter(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for(int key: map.keySet()) {
            if(map.get(key) > (arr.length / 2)){
                ans = key;
            }
        }
        
        return ans;
    }

    // TC : O(N) + O(N)
    // SC: O(1)
    // Approach: Using Moore's Voting Algorithm / Boyer-Moore Majority Vote Algorithm.
    public static int majorityElementOptimal(int[] arr) {
        int count = 0;
        int majEle = 0;

        // Step 1: apply the moore's algo
        for(int i = 0 ; i < arr.length ; i ++) {
            if(count == 0) { // checking the new section of array, reset majEle to current element and count to 1
                count = 1;
                majEle = arr[i];
            } else if(majEle == arr[i]) { 
                count++;
            } else { // majEle != arr[i]
                count --;
            }
        }

        int counter = 0;

        // Step 2: check if the potential majEle is actually the majority element or not.
        /*
         * NOTE: Only perform this step if it is mentioned in the ques that the majority element may or may not exists.
         * If the ques says there always exists the majority element then skip this step. In this case you potential majEle from Step 1 will be the final ans.
         */
        for(int i = 0 ; i < arr.length ; i ++) {
            if(majEle == arr[i]) {
                counter++;
            }
        }

        // checking the majority element condition
        if(counter > arr.length / 2) {
            return majEle;
        }

        return -1; // if no majority element exists
        
    }
}
