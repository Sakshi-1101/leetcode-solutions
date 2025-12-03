import java.util.HashMap;

public class LengthOfLongestSubArraySumToZero {

    public static void main(String[] args) {
        int[] arr = {9, -3, 3, -1, 6, -5};

        int lenBrute = findLengthBrute(arr);
        int lenBetter = findLengthBetter(arr);
        int lenOptimal = findLengthOptimal(arr);

        System.out.println(lenBrute);
        System.out.println(lenBetter);
        System.out.println(lenOptimal);
    }

    // TC: O(N^3)
    // SC: O(1)
    // Approach: In this we'll generate all subarrays using three nested loops and for each subarray,we'll calculate the maxlen whose sum is zero.
    public static int findLengthBrute(int[] arr) {
        int maxLen = Integer.MIN_VALUE;

        // generate all subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i ; j < arr.length ; j ++) {
                int sum = 0;
                for(int k = i ; k <= j ; k ++) {
                    sum += arr[k];
                }
                
                // check if sum is zero, calculate maxLen
                if(sum == 0) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }

            }
        }

        return maxLen;
    }


    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this we'll generate all subarrays using two nested loops and for each subarray,we'll maintain a running sum to calculate the maxlen whose sum is zero.
    public static int findLengthBetter(int[] arr) {
        int maxLen = Integer.MIN_VALUE;

        // generate all subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            int sum = 0;
            for(int j = i ; j < arr.length ; j ++) {

                // running sum
                sum += arr[j];

                // check if sum is zero, calculate maxLen
                if(sum == 0) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }

            }
        }

        return maxLen;
    }

    // TC: O(N * logN) -> due to hashmap operations
    // SC: O(N) -> we are using a fixed-size hash array of size 256 (for ASCII characters) and not using any 
    //             additional data structures that grow with input size, so we can say it is O(1) space complexity.
    // Approach: In this approach, we'll maintain a hashmap in which we'll store the prefix sum and it's index.
    //           If at any point the prefix sum becomes zero, it means the subarray from the start to the current index has a sum of zero.
    //           If the prefix sum has been seen before which means it is present in the map, it means the subarray between the previous index and the current index has a sum of zero.
    /* 
        NOTE: If we get a sum which already exists in the map, we won't update the index of that sum as we need the longest 
        subarray, so we'll keep the index having first occurrence of that sum.
    */
    public static int findLengthOptimal(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLen = Integer.MIN_VALUE;

        // traverse the array
        for(int i = 0 ; i < arr.length ; i ++) {
            sum += arr[i]; // calculate prefix sum

            // if sum is zero, maxlen will be i+1 (from start to current index)
            if(sum == 0) {
                maxLen = i + 1;
            } else { 
                if(map.containsKey(sum)) { // if sum is already present in map which means it has occured before
                    maxLen = Math.max(maxLen, i - map.get(sum)); // maxlen will be current index - previous index of that sum
                } else {
                    map.put(sum, i); // if sum is not present, add it to map with current index
                }
            }
        }

        return maxLen;
    }

    
}
