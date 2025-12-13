class Solution {
    public int searchInsert(int[] nums, int target) {
        return searchInsertHelper(nums, 0, nums.length - 1, target);
    }

    public static int searchInsertHelper(int[] nums, int low, int high, int target){
        if(low > high) {
            return low;
        }

        int mid = low + ((high - low) / 2);

        if(nums[mid] < target) {
            return searchInsertHelper(nums, mid + 1, high, target);
        } else if(nums[mid] > target) {
            return searchInsertHelper(nums, low, mid - 1, target);
        } else {
            return mid;
        }
    }
}