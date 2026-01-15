public class PaintersPartitionProblem {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        int k = 2;

        // split the units to be painted represented by arr[i] into k painters in such a way that the max time taken by any 
        // painter is minimized.
        /*
            CONSTRAINTS:
            1. Each painter can only paint the continuous boards.
            2. Each painter should get atleadt one board to paint.
            3. Each unit of board takes 1 unit of time to be painted. 
               Example: arr[i] = 10 means the painter will take 10 mins to paint 10 units of board.
         */
        int ansBrute = findMinOfMaxTimeBrute(arr, k);
        int ansOptimal = findMinOfMaxTimeOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
        
    }

    // TC: O(sum - max + 1) * O(N) => O(N) -> time complexity of findPainterCount()
    // SC: O(1)
    // Approach: In this approach, we will try to find the minimum time by traversing through all the possible answers from 
    //           the maximum board length to the summation of all board lengths. For each possible time, we will check how 
    //           many painters are needed such that no painter takes more time than the possible answer. If the number of 
    //           painters needed is equal to k, we return that possible time as the result.
    public static int findMinOfMaxTimeBrute(int[] arr, int k) {
        // edge case: when no. of boards to be painted < no. of painters available
        if(arr.length < k) {
            return -1; // not possible 
        }

        int maxBoardLength = Integer.MIN_VALUE;
        int totalTime = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            maxBoardLength = Math.max(arr[i], maxBoardLength);
            totalTime += arr[i];
        }

        // define the range where ans will lie
        int start = maxBoardLength; // max board length, so that each painter gets at least one board
        int end = totalTime; // if there was only 1 painter then they paint all the units of board. Hence, max time = summation of board lengths
    
        // traverse on the possible answers
        for(int i = start ; i <= end ; i ++) {
            // for i as max time, check how many painters are needed for the current max time
            int paintersNeeded = findPainterCount(arr, i);

            // if you get k painters with max time as i return the ans since we need the min value
            if(paintersNeeded == k) {
                return i;
            }
        }

        //return max board length as there cannot exist any answer smaller than that.
        return start;
    
    }

    // function to find the number of painters needed such that no painter takes more time than maxTime.
    /* The code logic is as follows:
        1. Start with the first painter and keep assigning the board units until adding another unit of board would exceed maxTime.
        2. When the time exceeds maxTime, assign the current unit of board to a new painter.
        3. Repeat this process until all the units of board are assigned.
        4. Return the total number of painters needed.
    */
    private static int findPainterCount(int[] arr, int maxTime) {
        // this implies currently we have 1 painter assigned
        int paintersUsed = 1; // start with painter 1
        int painterTime = 0; // time consumed by current painter

        // traverse the arr to assign units of board to painters to paint
        for(int i = 0 ; i < arr.length ; i ++) {
            // if the current board can be painted by the current painter without exceeding maxTime
            if(painterTime + arr[i] <= maxTime) {
                painterTime += arr[i]; // assign the current board to the current painter
            } else { // if the current board cannot be painted by the current painter
                paintersUsed++; // assign the current board to a new painter and increment painter count
                painterTime = arr[i]; // set the current board as the first board for the new painter
            }
        }

        // return the number of painters needed
        return paintersUsed;
    }

    // TC: O(log (base 2) (sum - max + 1)) * O(N) => O(N) -> time complexity of findPainterCount()
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the minimum time taken by any painter. The idea is to
    //           use binary search on the range of possible maximum times (from max board length to sum of all board lengths) and 
    //           check for each mid value how many painters are needed with that mid as the maximum time. If we need k or fewer 
    //           painters, we try to find a smaller maximum time. If we need more than k painters, we increase the maximum time. 
    //           We continue this process until we find the minimum possible maximum time.
    public static int findMinOfMaxTimeOptimal(int[] arr, int k) {
        // edge case: when no. of boards to be painted < no. of painters available
        if(arr.length < k) {
            return -1; // not possible
        }

        int maxBoardLength = Integer.MIN_VALUE;
        int totalTime = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            maxBoardLength = Math.max(arr[i], maxBoardLength);
            totalTime += arr[i];
        }

        // define the range where ans will lie
        int lo = maxBoardLength; // max board length, so that each painter gets at least one board
        int hi = totalTime; // if there was only 1 painter then they paint all the units of board. Hence, max time = summation of board lengths
    
        // initialize ans to lo because lo is the minimum possible value of the max time
        int ans = lo;

        // binary search
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // for mid as max time, check how many painters are needed
            int paintersNeeded = findPainterCount(arr, mid);

            
            // if the number of painters needed is <= k, then mid is a valid ans, this means we can paint all boards with k or 
            // fewer painters in mid time so we store mid as a potential answer and try to find a smaller time by searching in the left half
            if(paintersNeeded <= k)  {
                ans = mid;  // mid is a valid answer, store it
                hi = mid - 1;  // search for a smaller maximum time in the left half
            } else { //if more than k painters are needed, means mid time is too small we need to increase the max time to reduce the no. of painters needed
                lo = mid + 1;  // search in the right half for a larger maximum time
            }
        }
        
        return ans; // OR return lo;
    }
    
}
