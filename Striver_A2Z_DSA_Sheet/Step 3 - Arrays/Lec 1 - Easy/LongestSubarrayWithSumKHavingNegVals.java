import java.util.HashMap;

public class LongestSubarrayWithSumKHavingNegVals {

    public static void main(String[] args) {
        int[] arr = {-1, 1, 1, 2, 0, 4};
        int k = 1;

        int ans1 = LongestSubarrayWithSumKBrute(arr, k);
        int ans2 = LongestSubarrayWithSumKBetter(arr, k);
        int ans3 = LongestSubarrayWithSumKOptimal(arr, k);

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);

    }


    // TC: O(n^3)
    // SC: O(1)
    // Approach: we are checking the sum of each subarray if it is equal to k.
    public static int LongestSubarrayWithSumKBrute(int[] arr, int k) {
        int maxlen = 0;

        // generate all the subarrays
        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = i ; j < arr.length; j ++){
                
                int sum = 0 ;

                //subarray generation
                for(int l = i ;  l <= j ; l ++) {
                    sum += arr[l];
                }
                
                if(sum == k){
                    maxlen = Math.max(maxlen, (j - i + 1));
                }
            }
        }

        return maxlen;

    }

    // TC: O(n^2)
    // SC: O(1)
    // Approach: we are checking the sum of each subarray if it is equal to k.
    public static int LongestSubarrayWithSumKBetter(int[] arr, int k) {
        int maxlen = 0;

        // generate all the subarrays
        for(int i = 0 ; i < arr.length ; i ++){
            int sum = 0;
            for(int j = i ; j < arr.length; j ++){
                sum += arr[j]; // at this step each subarray elements are getting added in each iteration.
                
                if(sum == k){
                    maxlen = Math.max(maxlen, (j - i + 1));
                }
            }
        }

        return maxlen;

    }

    // TC: O(N*logN) -> bcoz in java 8, worst-case complexity is O(nlogn) due to too many hash collisions. When collisions in a bucket get too high, the chain is converted into a balanced tree (red-black tree). Lookup/insert in that bucket becomes O(log n).
    // SC: O(N)
    // Approach: Using Hashmap and prefix sum.
    // Edge case: arr containing 0s -> [2,0,0,0,3] and negatives -> [-1,-2,0,5,6]
    // For negatives this is the most optimised solution.
    public static int LongestSubarrayWithSumKOptimal(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>(); // key -> prefix sum, value -> idx

        int sum = 0;
        int maxlen = 0;

        for(int i = 0 ; i < arr.length ; i ++){
            sum += arr[i]; // calculating the prefix sum

            if(sum == k) { // if at any point sum = k, update the length
                maxlen = Math.max(maxlen, i + 1);
            }

            int remSum = sum - k; // calculate the remaining sum

            if(map.containsKey(remSum)){ // check if remaining sum already exists in map, if yes then find the len 
                maxlen = Math.max(maxlen, i - map.get(remSum)); // len would be the difference of sum - remSum = k {diff = sum(0,i) - sum(0, remSum idx)}
            }

            // this we are doing so that we get the leftmost idx or the first idx where we got this sum for the first time, otherwise if we get it again at a later stage it will update the idx and then we might not get the longest subarray
            if(!map.containsKey(sum)) { // put the prefix sum with idx in map
                map.put(sum, i);
            }
        }

         return maxlen;
    }
    
}
