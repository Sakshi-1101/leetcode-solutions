1class Solution {
2    public int searchInsert(int[] nums, int target) {
3        return searchInsertHelper(nums, 0, nums.length - 1, target);
4    }
5
6    public static int searchInsertHelper(int[] nums, int low, int high, int target){
7        if(low > high) {
8            return low;
9        }
10
11        int mid = low + ((high - low) / 2);
12
13        if(nums[mid] < target) {
14            return searchInsertHelper(nums, mid + 1, high, target);
15        } else if(nums[mid] > target) {
16            return searchInsertHelper(nums, low, mid - 1, target);
17        } else {
18            return mid;
19        }
20    }
21}