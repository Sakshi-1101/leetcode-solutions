1class Solution {
2    public int search(int[] nums, int target) {
3        int lo = 0;
4        int hi = nums.length - 1;
5
6        while(lo <= hi) {
7            int mid = lo + (hi - lo) / 2;
8
9            if(nums[mid] == target) {
10                return mid;
11            } 
12
13            // identify the sorted half
14            if(nums[lo] <= nums[mid]) { // left half is sorted
15                if(nums[lo] <= target && target <= nums[mid]) { // if target element lies in left half
16                    hi = mid - 1; // discard right half
17                } else {
18                    lo = mid + 1; // if target element doesn't lie in left half, discard the left half
19                }
20            } else { // right half is sorted
21                if(nums[mid] <= target && target <= nums[hi]) { // if target element lies in right half
22                    lo = mid + 1; // discard left half
23                } else {
24                    hi = mid - 1; // if target element doesn't lie in right half, discard the right half
25                }
26            }
27        }
28    
29       // if target element not found in nums
30       return -1;
31        
32    }
33}