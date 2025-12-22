public class FindMinInSortedRotatedArr {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};

        int ansBrute = findMinBrute(arr);
        int ansOptimal = findMinOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Traverse the array linearly and search for the min value element.
    public static int findMinBrute(int[] arr) {
        int min = Integer.MAX_VALUE;

        for(int i = 0 ;  i < arr.length ; i ++) {
            min = Math.min(min, arr[i]);
        }

        return min;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the min value element. The approach would be to check 
    //           which half is sorted and discard that half while updating the minVal with the min value of that sorted half. 
    //           And then search the unsorted half for the min value element.
    public static int findMinOptimal(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int minVal = Integer.MAX_VALUE;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if the search space is already sorted, then arr[lo] will always be smaller in that search space
            if(arr[lo] <= arr[hi]) { // array is sorted  
                minVal = Math.min(minVal, arr[lo]);
                break;
            }

            // check for sorted half
            /*
                NOTE: we are checking arr[lo] <= arr[mid] bcoz if arr = [2,1] then in this case left half = 2 (bcoz lo = mid = 0)
             */
            if(arr[lo] <= arr[mid]) { // if left half is sorted
                minVal = Math.min(minVal, arr[lo]); // find the min value in left half that would be arr[lo] and update the minVal
                lo = mid + 1; // discard the sorted half
            } else { // if right half is sorted
                minVal = Math.min(minVal, arr[mid]); // find the min value in right half that would be arr[mid] and update the minVal
                hi = mid - 1; // // discard the sorted half
            }
        }

        return minVal;
    }
    
}
