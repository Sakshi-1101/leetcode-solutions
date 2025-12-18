class Solution {
    public int[] searchRange(int[] nums, int target) {
        return searchRangeHelperBinary(nums, target);
    }

    // TC: O(2 * LogN) , SC: O(1)
    // Approach: Using binary search to find first and last occurence of target element. In this we'll perform two binary
    //           searches, one to find the first occurence and other to find the last occurence. 
    //           To find first occurence, when we find the target element at mid, we won't return mid instead we'll update
    //           the fidx with mid and move our search space to the left side of mid bcoz there is a possibility of the target element to 
    //           occur at a further left idx since array is sorted so it will be present in left half only not in right half.
    //           Similarly, to find last occurence, when we find the target element at mid, we won't return mid instead we'll update
    //           the lidx with mid and move our search space to the right side of mid bcoz there is a possibility of the target element to 
    //           occur at a further right idx since array is sorted so it will be present in right half only not in left half.
    /*
        NOTE: We cannot find both first and last occurence in a single binary search traversal bcoz the direction to move
              when we find the target element is different for both cases. For first occurence we move left and discard right
              and for last occurence we move right and discard left.
     */
    public static int[] searchRangeHelperBinary(int[] nums, int tar) {
        //To find first occurrence, move left direction and discard right
        int fidx = -1;
        int lo = 0;
        int hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < tar) {
                lo = mid + 1;
            } else if(nums[mid] > tar) {
                hi = mid - 1;
            } else {
                fidx = mid; // store possible 1st occurrence idx
                hi = mid - 1; // look in the left half for smaller idx
            }
        }
        
        //To find last occurrence, move right direction and discard left
        int lidx = -1;
        lo = 0;
        hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < tar) {
                lo = mid + 1;
            } else if(nums[mid] > tar) {
                hi = mid - 1;
            } else {
                lidx = mid; // store possible last occurrence idx
                lo = mid + 1; // look in the right half for bigger idx
            }
        }

        return new int[]{fidx, lidx};
    }  

    // TC: O(N) , SC: O(1)
    // Approach: In this we'll linearly traverse the array to find the first and last occurence of the target element. 
    //           So let us maintain two variables fidx and lidx to store the first and last occurence idx respectively.
    //           Now while traversing the array, when we find the target element for the first time we store that idx in 
    //           fidx and for every occurence of target element we update lidx to that idx. This way after complete traversal 
    //           fidx will have first occurence idx and lidx will have last occurence idx. We wont' update fidx after first 
    //           occurence is found.
    public static int[] searchRangeHelperLinear(int[] nums, int tar) {
        int fidx = -1; // first occ.
        int lidx = -1; // last occ.

        for(int i = 0 ; i < nums.length ; i ++) {
            if(nums[i] == tar) {
                if(fidx == -1) { // only update the first time
                    fidx = i;
                }

                lidx = i;
            }
        }

        return new int[]{fidx, lidx};
    }

    // TC: O(2 * LogN) , SC: O(1)
    // binary search using lower bound and upper bound algo
    public int[] searchRangeBinary2(int[] nums, int target) {
        // first occurence -> lower bound of element
        // last occurence -> (upper bound - 1) of element

        int firstOcc = lowerBound(nums, 0, nums.length - 1, target);
        int lastOcc = upperBound(nums, 0, nums.length - 1, target);

        /*
            NOTE: We are checking the below condition bcoz if the target element is not present in the array
                  then lower bound will return the idx where the element can be inserted to keep the array sorted.
                  So if that idx is equal to the length of array or the element at that idx is not equal to target
                  then it means target is not present in the array.
                  Hence, if there is no first occurence, there won't be any last occurence as well. Therefore, we can
                  return {-1, -1} in that case.
         */
        if(firstOcc == nums.length || nums[firstOcc] != target) {
            return new int[]{-1, -1};
        }

        /*
            NOTE: Since upper bound returns the idx of the first element greater than target,
                  therefore, to get the last occurence idx of target we need to do (lastOcc - 1).
                  In case there is only one occurence of target, then first and last occurence will be same.
         */
        return new int[]{firstOcc, lastOcc - 1};
    }

    public static int lowerBound(int[] nums, int lo, int hi, int tar) {
        int lowerBound = nums.length;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] >= tar) {
                lowerBound = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lowerBound;
    }

    public static int upperBound(int[] nums, int lo, int hi, int tar) {
        int upperBound = nums.length;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] > tar) {
                upperBound = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return upperBound;
    }
}