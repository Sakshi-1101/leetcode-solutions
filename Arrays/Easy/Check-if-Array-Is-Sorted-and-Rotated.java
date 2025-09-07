class Solution {

    /*
     * Brute Force Approach
     * The approach would be to generate all possible rotations of the array
     * and check if any of them is sorted.
     * TC: O(N^2)
     * SC: O(N)
     */
    public boolean checkIfSortedAndRotatedBruteApproacheck(int[] nums) {
        int n = nums.length;

        // Construct the rotated array
        int[] checkSorted = new int[n];

        // Iterate through all possible rotation offsets
        for (int rotationOffset = 0; rotationOffset < n; ++rotationOffset) {
            int currIndex = 0;
            for (int index = rotationOffset; index < n; ++index) {
                checkSorted[currIndex++] = nums[index];
            }
            for (int index = 0; index < rotationOffset; ++index) {
                checkSorted[currIndex++] = nums[index];
            }

            // Check if the constructed array is sorted
            boolean isSorted = true;
            for (int index = 0; index < n - 1; ++index) {
                if (checkSorted[index] > checkSorted[index + 1]) {
                    isSorted = false;
                    break;
                }
            }

            // If sorted, return true
            if (isSorted) {
                return true;
            }
        }

        // If no rotation makes the array sorted, return false
        return false;
    }

    // TC: O(N)
    // SC: O(N)
    // This approach tries to rotate the array from the break point and then checks if the rotated array is sorted
    public boolean checkIfSortedAndRotatedBetterApproach(int[] arr) {
        int count = 1;

        // Check if array elements are in increasing order until a break point
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            if(arr[i] <= arr[i + 1]){
                count++; // Increment count if order is maintained
            } else {
                // Once we find break point, try rotating array from the count position
                boolean res = reverseRotateArray(arr, count, arr.length);
                return res;
            }
        }

        return true; // Array is already sorted (if count == array length)
    }

    public static boolean reverseRotateArray(int[] arr, int count, int n){

        // Create a new array to hold the rotated version
        int[] ans = new int[arr.length];

        // Rotate array by count positions using modulo to try generating the original array
        for(int i = 0 ; i < n ; i ++){
            ans[i] = arr[(i + count) % n];
        }

        // Check if rotated array is sorted
        for(int i = 0 ; i < ans.length - 1 ; i ++){
            if(ans[i] > ans[i + 1]){
                return false; // Not sorted after rotation
            }
        }

        return true; // Array is sorted after rotation
    }

    // TC: O(N)
    // SC: O(1)
    // This approach counts the number of positions where array order breaks
    // For a sorted and rotated array, there should be exactly one break point
    public boolean checkIfSortedAndRotatedOptimalApproach(int[] arr) {

        int count = 0; // to count the breakpoints

        // Count number of positions where order breaks
        for(int i = 0 ; i < arr.length - 1 ; i++) {
            if(arr[i] > arr[i + 1]){
                count++;

                if(count > 1) {
                    return false; // More than one break point means array cannot be sorted by rotation
                }
            }
        }

        // Check if last element is greater than first element
        // This handles cases like [1,1,1] where no breaks are found in above loop
        if(arr[arr.length - 1] > arr[0]){ // [3,4,5,1,2] in this it will check for 2 & 3 in cyclic manner also it should be sorted
            count++;
        }

        // array is sorted if there is at most one break point
        if(count <= 1){
            return true;
        } else {
            return false;
        }        
    }
}