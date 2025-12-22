1class Solution {
2    public int findMin(int[] nums) {
3        int lo = 0;
4        int hi = nums.length - 1;
5        int minVal = Integer.MAX_VALUE;
6
7        while(lo <= hi) {
8            int mid = lo + (hi - lo) / 2;
9
10            // if the search space is already sorted, then arr[lo] will always be smaller in that search space
11            if(nums[lo] <= nums[hi]) { // array is sorted  
12                minVal = Math.min(minVal, nums[lo]);
13                break;
14            }
15
16            // check for sorted half
17            if(nums[lo] <= nums[mid]) { // if left half is sorted
18                minVal = Math.min(minVal, nums[lo]); // find min value
19                lo = mid + 1; // discard the sorted half
20            } else { // if right half is sorted
21                minVal = Math.min(minVal, nums[mid]); // find min value
22                hi = mid - 1; // // discard the sorted half
23            }
24        }
25
26        return minVal;
27        
28    }
29}