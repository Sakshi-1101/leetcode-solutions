class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        // check for nums with only one element
        if(n == 1) {
            return 0;
        }

        // Check for the 0th element
        if(nums[1] < nums[0]) {
            return 0;
        }

        // Check for the (n-1)th last element
        if(nums[n - 2] < nums[n - 1]) {
            return n - 1;
        }

        // apply binary search for search space [1...(n-2)]
        int lo = 1;
        int hi = n - 2;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                lo = mid + 1;
            } else if(nums[mid] > nums[mid + 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1; // OR hi = mid - 1 (you can move anyside)
            }
        }

        return -1; 
        
    }
}