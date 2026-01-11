
import java.util.*;

public class AggressiveCows {

    public static void main(String[] args) {
        int[] arr = {0, 3, 4, 7, 10, 9};
        int k = 4;

        int ansBrute = findMaxOfMinDistBrute(arr, k);
        int ansOptimal = findMaxOfMinDistOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(nlogn) + O(max - min) * O(n) -> O(n) is for canWePlace() and O(nlogn) for sorting
    // SC: O(1)
    // Approach: In this approach, we try placing cows with min distance from 1 to (max - min) and check if we can place all 
    //           cows with that min distance using canWePlace() function. If we can place all cows with that min distance, we 
    //           try for more distance since we want to maximize the min distance. If we cannot place all cows with that min 
    //           distance, we break the loop and return the last successful min distance.
    public static int findMaxOfMinDistBrute(int[] arr, int cows) {
        // sort the array to have the stalls in increasing order of their positions so that we can easily calculate min distances.
        Arrays.sort(arr);
        
        // Range of min distance will be between 1 to (max - min) bcoz max distance between any two stalls will be when they 
        // are placed at extreme ends.
        int maxDist = arr[arr.length - 1] - arr[0];

        // to store final ans i.e. the max of min distances
        int ans = 0;

        // try placing cows with min distance from 1 to end
        // i -> min distance to maintain
        for(int i = 1 ; i <= maxDist ; i++) {
        
            // check if we can place all cows with ith min distance
            if(canWePlace(arr, i, cows)) { 
                ans = i; // we were able to place all cows with ith min distance, try for more distance since we want to maximize the min distance
            } else {
                break; // we were not able to place all cows with current ith min distance, so don't check further
            }
        }

        return ans;
    }

    // This function checks if we can place all cows in the stalls such that the min distance between any two cows is at least minDist.
    /*
        The code logic: 
        - We place the first cow at the 0th index and then try placing the rest of the cows in the subsequent stalls.
        - If the distance between the current stall and the last placed cow's position is >= minDist, we place the cow there 
          and update the last placed cow's position.
        - We continue this until we either place all cows or run out of stalls.
        - If we successfully place all cows, we return true; otherwise, we return false.
     */
    private static boolean canWePlace(int[] arr, int minDist, int cows) {
        // place the first cow at the 0th index
        int countCows = 1; // represents the number of cows placed so far
        int cowPos = arr[0]; // represents the last cow placed position

        // place the rest of the cows, traverse on stalls
        for(int i = 1 ; i < arr.length ; i ++) {
            // if at the current stall i.e. arr[i] we'll try placing the next cow and if it's dist from the last 
            // cowPos is >= minDist, then we can place the cow at the arr[i]th stall
            if(arr[i] - cowPos >= minDist) {
                countCows++; // placed one more cow
                cowPos = arr[i]; // update the last cow placed position
            } 
        }

        if(countCows >= cows) {
            return true; // this means we were able to place all the cows while maintaining the min distance
        } else {
            return false; //not able to place all cows
        }
    }
    
    // TC: O(nlogn) + O(log (base 2) (max - min)) * O(N) -> O(N) is for canWePlace() and O(nlogn) for sorting
    // SC: O(1)
    // Approach: In this approach, we use binary search on the range of possible min distances [1... (max - min)]. We calculate 
    //           mid of the range and check if we can place all cows with mid min distance using canWePlace() function. If we can 
    //           place all cows with mid min distance, we store the current mid and try for greater min distance since we want to 
    //           maximize the min distance. If we cannot place all cows with mid min distance, we try for smaller min distance. 
    //           We continue this until the search space is exhausted and finally return the last successful mid value.
    public static int findMaxOfMinDistOptimal(int[] arr, int cows) {
        // sort the array
        Arrays.sort(arr);

        // Range of ans will be [1... (max - min)]
        int lo = 1; // NOTE: to optimise more and trim the range you can also assign low = min(distance between all the consecutive stalls)
        int hi = arr[arr.length - 1] - arr[0];
        
        // to store the final answer
        int ans = 0;

        while(lo <= hi) {
            // mid -> represents the min distance to maintain
            int mid = lo + (hi - lo) / 2;

            // check if we can place all cows with mid min distance
            if(canWePlace(arr, mid, cows)) {
                // we were able to place all cows with mid min distance, store the current mid and try for greater min distance 
                // since we want to maximize the min distance
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1; // we were not able to place all cows with mid min distance, try for smaller min distance
            }
        }

        return ans; // OR return hi
    }
}
