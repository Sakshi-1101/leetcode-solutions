public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        // in this ques there may be duplicate elements present in the rotated sorted array
        int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        // int[] arr = {1, 0, 1, 1, 1};
        // int k = 0;
        int k = 3;

        boolean ansBrute = searchBrute(arr, k);
        boolean ansOptimal = searchOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse the array and search for the element in the array
    public static boolean searchBrute(int[] arr, int k) {

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == k) {
                return true;
            }
        }

        return false;
    }
    
    // TC: O(logN) for the best and average case. 
    //     O(N/2) for the worst case. Here, N = size of the given array.
    // SC: O(1)
    // Approach: In this optimal approach, we will use modified binary search. To handle duplicates, we will skip the duplicate 
    //           elements such that we can always find one half sorted and other half unsorted.
    public static boolean searchOptimal(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if found at mid idx return mid
            if(arr[mid] == k) {
                return true;
            } 

           // skip duplicates to find sorted and unsorted half
            if(arr[mid] == arr[lo] && arr[mid] == arr[hi]) {
                lo++;
                hi--;
                /*
                    we'll continue bcoz there is a possibility that next element for eg: arr[lo] == arr[lo+1] due to which 
                    the condition (arr[mid] == arr[lo] && arr[mid] == arr[hi]) will still remain true hence we need to skip 
                    this element also, so better repeat this process until this condition becomes false and you get one half 
                    proper sorted and other half unsorted.
                 */
                continue;
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
       return false;

    }
}
