1class Solution {
2    public int findMin(int[] nums) {
3        int lo = 0;
4        int hi = nums.length - 1;
5        int minVal = Integer.MAX_VALUE;
6
7        while(lo <= hi) {
8            int mid = lo + (hi - lo) / 2;
9
10            // check for sorted half
11            if(nums[lo] <= nums[mid]) { // if left half is sorted
12                minVal = Math.min(minVal, nums[lo]); // find min value
13                lo = mid + 1; // discard the sorted half
14            } else { // if right half is sorted
15                minVal = Math.min(minVal, nums[mid]); // find min value
16                hi = mid - 1; // // discard the sorted half
17            }
18        }
19
20        return minVal;
21        
22    }
23}