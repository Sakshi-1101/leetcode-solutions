1class Solution {
2    public int search(int[] nums, int target) {
3        return searchHelper(nums, 0, nums.length - 1, target);
4    }
5
6    public int searchHelper(int[] nums, int low, int high, int target) {
7        // base case
8        if(low > high) {
9            return -1; // element not found
10        }
11
12        // calculate mid
13        int  mid = low + ((high - low) / 2);
14
15        if(nums[mid] < target) {
16            return searchHelper(nums, mid + 1, high, target);
17        } else if(nums[mid] > target) {
18            return searchHelper(nums, low, mid - 1, target);
19        } else {
20            return mid; // element found
21        }
22        
23    }
24}