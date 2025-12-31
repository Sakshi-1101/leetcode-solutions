1class Solution {
2    public int singleNonDuplicate(int[] nums) {
3       int n = nums.length;
4
5        // edge case 1: if nums has single element
6        if(n == 1) {
7            return nums[0];
8        }
9
10        // edge case 2: check for 0th element bcoz there is no left half to check
11        if(nums[0] != nums[1]) {
12            return nums[0];
13        }
14
15        // edge case 3: check for (n-1)th i.e. last element bcoz there is no right half to check
16        if(nums[n - 1] != nums[n - 2]) {
17            return nums[n - 1];
18        }
19
20        // since we have already checked for 0th and (n-1)th element, we'll reduce our search space to [1..(n-2)]
21        int lo = 1;
22        int hi = n - 2;
23
24        while(lo <= hi) {
25            int mid = lo + (hi - lo) / 2;
26
27            // check if nums[mid] is the single element
28            if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
29                return nums[mid];
30            }
31
32            // identify the left or right half
33            if((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) { // left half
34                lo = mid + 1; // discard left half since single element will be in right half
35            } else { // right half
36                hi = mid - 1; // discard right half since element will be in left half
37            }
38        }
39
40        // if no single element found
41        return -1;
42    }
43}