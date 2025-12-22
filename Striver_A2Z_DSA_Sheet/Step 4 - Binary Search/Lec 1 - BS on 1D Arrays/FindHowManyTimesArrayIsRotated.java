public class FindHowManyTimesArrayIsRotated {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};

        int ansBrute = countRotationsBrute(arr);
        int ansBetter = countRotationsBetter(arr);
        int ansOptimal1 = countRotationsOptimal1(arr);
        int ansOptimal2 = countRotationsOptimal2(arr);
        int ansOptimal3 = countRotationsOptimal3(arr);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal1);
        System.out.println(ansOptimal2);
        System.out.println(ansOptimal3);

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Traverse the array linearly to find the index of the minimum element. The index of the minimum element is the 
    //           number of times the array has been rotated. This is because in a rotated sorted array, the minimum element is 
    //           the pivot point where the rotation occurs.
    public static int countRotationsBrute(int[] arr) {
        int min = arr[0];  // Assume the first element is the smallest
        int minIdx = 0;  // Index of the smallest element

        // Traverse the array
        for(int i = 1 ; i < arr.length ; i ++) {
            // If current element is smaller than min value, update
            if(arr[i] < min) {
                min = arr[i];
                minIdx = i;
            }
        }

        // The index of smallest element = number of rotations
        return minIdx;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will linearly traverse the array to find the point where the asc order breaks. The index 
    //           of the next element will be the number of rotations. This is because in a rotated sorted array, the point where 
    //           the asc order breaks is the pivot point where the rotation occurs.
    public static int countRotationsBetter(int[] arr) {
        // traverse the array
        for(int i = 0 ; i < arr.length ; i ++) {
            // If at any point the adjacent elements are no longer in asc order, we found the breakpoint
            if(arr[i + 1] < arr[i]) {
                return i + 1;  // Index of next element is rotation count
            }
        }

        // No rotation found
        return 0;
    }

    // TC: O(log (base2) N)
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the minimum element in the array. The index of the minimum 
    //           element is the number of rotations. The approach would be to check which half is sorted and discard that half 
    //           while updating the minVal and idx with the min value and its index of that sorted half. And then search the 
    //           unsorted half for the min value element. Update the index at every step when a new min value is found. FInally, 
    //           return the index of the min value element.
    public static int countRotationsOptimal1(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int idx = 0; // let's suppose initially defualt value is 0 rotations
        int minVal = Integer.MAX_VALUE;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // if entire array or search space is sorted
            if(arr[lo] <= arr[hi]) {
                if(arr[lo] < minVal) { // the min value ele will be at arr[lo] since entire array is sorted
                    minVal = arr[lo];
                    idx = lo; // update the idx
                }

                break;
            }

            // check if left half is sorted
            if(arr[lo] <= arr[mid]) {
                if(arr[lo] < minVal) { // the min value ele will be at arr[lo] since left half is sorted
                    minVal = arr[lo];
                    idx = lo; // update the idx
                }

                lo = mid + 1;

            } else { // if right half is sorted
                if(arr[mid] < minVal) { // the min value ele will be at arr[mid] since right half is sorted
                    minVal = arr[mid];
                    idx = mid; // update the idx
                }

                hi = mid - 1;
            }
        }

        // The index of smallest element = number of rotations
        return idx;
    }
    
    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the index of the minimum element directly. The index of 
    //           the minimum element is the number of rotations. The approach would be to check which half is sorted and discard that half. 
    //           If the left half is sorted, then the pivot (minimum element index) must lie in the right half. Otherwise, the 
    //           left half is unsorted, and the minimum element lies there (including mid).
    public static int countRotationsOptimal2(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        // we'll check lo < hi and not lo <= hi because when lo == hi, we have found the smallest element
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;

             // If the current subarray is already sorted, then the smallest element is at index 'lo'
            if(arr[lo] <= arr[hi]) {
                return lo;
            }

            // If left half [lo..mid] is sorted, then the pivot (minimum element idx) must lie in the right half
            if(arr[lo] <= arr[mid]) {
                lo = mid + 1;
            } 
            // Otherwise, the left half is unsorted, and the minimum element lies there (including mid)
            else {
                hi = mid;
            }
        }

        // When lo == hi, it points to the smallest element
        return lo;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: This is another variation of binary search to find the index of the minimum element directly. The index of 
    //           the minimum element is the number of rotations. The approach would be to compare the mid element with the high element.
    //           If mid element is greater than high element, then the smallest element lies to the right of mid. Otherwise, the smallest
    //           element is at mid or to the left.
    /*
        NOTE: Intuition behind this:
              Compare mid with the rightmost element to know which side is unsorted — the minimum always lies in the unsorted part.
              1. The smallest element is always in the unsorted half
              2. arr[mid] > arr[high] -> mid is in the left (larger) part. Hence, Min element must be to the right of mid.
              3. arr[mid] <= arr[high] -> mid is in the right sorted half. Hence, Min element is either at mid, or somewhere to the left of mid.
              4. If mid is bigger than high → pivot on right, Else → pivot on left (including mid).
              5. Stop when search space collapses i.e. when (low == high). At this point, you are standing on the min element itself.
     */
    public static int countRotationsOptimal3(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        // Loop until low meets high
        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than element at high, smallest element lies to the right of mid
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                // Else smallest element is at mid or to the left
                high = mid;
            }
        }

        // When low == high, we found the smallest element
        return low;
    }
}
