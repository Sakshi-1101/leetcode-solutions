import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlappingIntervals {

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        List<List<Integer>> ansBrute = mergeIntervalsBrute(arr);
        List<List<Integer>> ansBruteAlt = mergeIntervalsBruteAlternate(arr);
        List<List<Integer>> ansOptimal = mergeIntervalsOptimal(arr);
 
        System.out.println(ansBrute);
        System.out.println(ansBruteAlt);
        System.out.println(ansOptimal);

    }

    // TC: O(2N + N*LogN) -> O(N*LogN) for sorting and O(2N) for traversing each element twice in the array
    // SC: O(N)
    // Approach: In this approach, we first sort the intervals and then for each interval, we check the rest of the intervals for overlapping. 
    //           If they overlap, we merge them by updating the end time of the current interval.
    //           If they don't overlap, we add the current interval to the answer list and move to the next interval. 
    public static List<List<Integer>> mergeIntervalsBrute(int[][] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        // Sort the intervals based on the start time
        Arrays.sort(arr, (a,b) -> a[0] - b[0]);
    
        // Traverse through all intervals
        for(int i = 0 ; i < n ; i ++) {
            // Get the start and end time of the current interval
            int start = arr[i][0];
            int end = arr[i][1];

            // if there is already an interval in ans and the end time of the current interval is less than or equal to the end time of the last 
            // interval in ans, then we can skip this interval as it is already merged or we can say it is already a part of the last overlapping interval.
            if(ans.size() != 0 && end <= ans.get(ans.size() - 1).get(1)) {
                continue;
            }

            // Check for overlapping intervals and merge them
            for(int j = i + 1 ; j < n ; j ++){
                // If the start time of the next interval is less than or equal to the end time of the current interval, then there is an overlap
                if(arr[j][0] <= end) {
                    end = Math.max(end, arr[j][1]); // Update the end time if there is an overlap
                } else { // if it doesn't overlap, no need to check further intervals as they are sorted
                    break;
                }
            }

            // Add the merged interval to the answer list
            ans.add(Arrays.asList(start, end));

        }

        return ans;
    }

    // TC: O(N*LogN + 2 * N) -> O(N*LogN) for sorting and O(2N) for traversing each element twice in the array
    // SC: O(N)
    // Approach: In this approach, we first sort the intervals and then for each interval, we check the rest of the intervals for overlapping. 
    //           If they overlap, we merge them by updating the end time of the current interval.
    //           If they don't overlap, we add the current interval to the answer list and move to the next interval.
    public static List<List<Integer>> mergeIntervalsBruteAlternate(int[][] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        // Sort the intervals based on the start time
        Arrays.sort(arr, (a,b) -> a[0] - b[0]);

        int i = 0;
        
        // Traverse through all intervals
        while (i < n) {
            // Get the start and end time of the current interval
            int s1 = arr[i][0];
            int e1 = arr[i][1];

            int j = i + 1;

            // Check for overlapping intervals and merge them
            while (j < n && arr[j][0] <= e1) {
                e1 = Math.max(e1, arr[j][1]); // Update the end time if there is an overlap
                j++;
            }

            // Add the merged interval to the answer list
            ans.add(Arrays.asList(s1, e1));

            // Move to next non-overlapping interval
            /*
                NOTE: At this point, all intervals from index i to j-1 have been merged into one interval [s1, e1].
                      Therefore, we set i to j to continue checking from the next interval that has not been processed yet.
             */
            i = j;
        }

        return ans;
    }

    // TC: O(N*logN + N) -> O(N*LogN) for sorting and O(N) for traversing each element once in the array
    // SC: O(N)
    // Approach: In this approach, we first sort the intervals and then for each interval, we check if it overlaps with the last interval in the ans list. 
    //           If they overlap, we'll directly update the end time of the last interval in the answer list.
    //           If they don't overlap, we add the current interval to the answer list.
    public static List<List<Integer>> mergeIntervalsOptimal(int[][] arr) {
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        // Sort the intervals based on the start time
        Arrays.sort(arr, (a,b) -> a[0] - b[0]);

        // Traverse through all intervals
        for(int i = 0 ; i < n ; i ++) { 

            // if ans is empty means this is the first interval or if the start time of the current interval is greater than 
            // the end time of the last interval in ans, then there is no overlap, so we can add this interval to ans
            if(ans.isEmpty() || arr[i][0] > ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.asList(arr[i][0], arr[i][1]));
            } else { // if there is an overlap, update the end time of the last interval in ans
                int end = Math.max(arr[i][1], ans.get(ans.size() - 1).get(1));
                ans.get(ans.size() - 1).set(1, end);
            }
        }

        return ans;

    }
}
