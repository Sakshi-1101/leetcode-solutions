1class Solution {
2    public int[] searchRange(int[] nums, int target) {
3        return searchRangeHelper(nums, target);
4    }
5
6    public static int[] searchRangeHelper(int[] nums, int tar) {
7        //To find first occurrence, move left direction and discard right
8        int fidx = -1;
9        int lo = 0;
10        int hi = nums.length - 1;
11
12        while(lo <= hi) {
13            int mid = lo + (hi - lo) / 2;
14
15            if(nums[mid] < tar) {
16                lo = mid + 1;
17            } else if(nums[mid] > tar) {
18                hi = mid - 1;
19            } else {
20                fidx = mid;
21                hi = mid - 1;
22            }
23        }
24        
25        //To find last occurrence, move right direction and discard left
26        int lidx = -1;
27        lo = 0;
28        hi = nums.length - 1;
29
30        while(lo <= hi) {
31            int mid = lo + (hi - lo) / 2;
32
33            if(nums[mid] < tar) {
34                lo = mid + 1;
35            } else if(nums[mid] > tar) {
36                hi = mid - 1;
37            } else {
38                lidx = mid;
39                lo = mid + 1;
40            }
41        }
42
43        return new int[]{fidx, lidx};
44    }
45}