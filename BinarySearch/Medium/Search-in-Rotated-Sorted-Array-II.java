1class Solution {
2    public boolean search(int[] nums, int target) {
3        int lo = 0;
4        int hi = nums.length - 1;
5
6        while(lo <= hi) {
7            int mid = lo + (hi - lo) / 2;
8
9            // if found at mid idx return mid
10            if(nums[mid] == target) {
11                return true;
12            } 
13
14            // skip duplicates to find sorted and unsorted half
15            if(nums[mid] == nums[lo] && nums[mid] == nums[hi]) {
16                lo++;
17                hi--;
18                continue;
19            }
20
21            // identify the sorted half
22            if(nums[lo] <= nums[mid]) { // left half is sorted
23                if(nums[lo] <= target && target <= nums[mid]) { // if target element lies in left half
24                    hi = mid - 1; // discard right half
25                } else {
26                    lo = mid + 1; // if target element doesn't lie in left half, discard the left half
27                }
28            } else { // right half is sorted
29                if(nums[mid] <= target && target <= nums[hi]) { // if target element lies in right half
30                    lo = mid + 1; // discard left half
31                } else {
32                    hi = mid - 1; // if target element doesn't lie in right half, discard the right half
33                }
34            }
35        }
36    
37       // if target element not found in nums
38       return false;
39        
40    }
41}