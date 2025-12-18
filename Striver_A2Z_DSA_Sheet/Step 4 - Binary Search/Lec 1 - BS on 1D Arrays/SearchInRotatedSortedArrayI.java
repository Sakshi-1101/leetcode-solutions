public class SearchInRotatedSortedArrayI {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int k = 0;

        int ansBrute = searchBrute(arr, k);
        int ansOptimal = searchOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse the array and search for the element in the array
    public static int searchBrute(int[] arr, int k) {

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == k) { // if found return the idx
                return i;
            }
        }

        return -1; // if element not found
    }

    // TC: O(log (base2) N)
    // SC: O(1)
    // Approach: Using binary search by identifying the sorted half in the rotated sorted array. 
    //           In this we'll identify the sorted half and check if the target element lies in that half or not. 
    //           If it lies in that half, we discard the other half and continue our search in that half only. 
    //           If it doesn't lie in that half, we discard that half and continue our search in the other half.
    public static int searchOptimal(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if found at mid idx return mid
            if(arr[mid] == k) {
                return mid;
            } 

            // identify the sorted half
            if(arr[lo] <= arr[mid]) { // left half is sorted
                if(arr[lo] <= k && k <= arr[mid]) { // if target element lies in left half
                    hi = mid - 1; // discard right half
                } else {
                    lo = mid + 1; // if target element doesn't lie in left half, discard the left half
                }
            } else { // right half is sorted
                if(arr[mid] <= k && k <= arr[hi]) { // if target element lies in right half
                    lo = mid + 1; // discard left half
                } else {
                    hi = mid - 1; // if target element doesn't lie in right half, discard the right half
                }
            }
        }
    
       // if target element not found in arr
       return -1;

    }
    
}
