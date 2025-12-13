class Solution {
    public int search(int[] nums, int target) {
        return searchHelper(nums, 0, nums.length - 1, target);
    }

    public int searchHelper(int[] nums, int low, int high, int target) {
        // base case
        if(low > high) {
            return -1; // element not found
        }

        // calculate mid
        int  mid = low + ((high - low) / 2);

        if(nums[mid] < target) {
            return searchHelper(nums, mid + 1, high, target);
        } else if(nums[mid] > target) {
            return searchHelper(nums, low, mid - 1, target);
        } else {
            return mid; // element found
        }
        
    }
}