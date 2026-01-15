1class Solution {
2    public int splitArray(int[] nums, int k) {
3        // edge case: when no. of elements in array < no. of subarrays to be formed
4        if(nums.length < k) {
5            return -1; 
6        }
7
8        int max = Integer.MIN_VALUE;
9        int sum = 0;
10
11        for(int i = 0 ; i < nums.length ; i ++) {
12            max = Math.max(nums[i], max);
13            sum += nums[i];
14        }
15
16        // define the range where ans will lie
17        int lo = max;
18        int hi = sum; 
19        
20        // initialize ans to lo bcoz lo is the minimum possible value of the max subarray sum
21        int ans = lo;
22
23        // binary search
24        while(lo <= hi) {
25            int mid = lo + (hi - lo) / 2;
26
27            // for mid as max subarray sum, check how many subarrays you are able to form
28            int countSubarrays = findSubarrayCount(nums, mid);
29
30            // if you are able to form k subarrays with max subarray sum as mid look for smaller value since we 
31            // need the min value of the max subarray sum
32            if(countSubarrays <= k)  {
33                ans = mid;
34                hi = mid - 1; 
35            } else { 
36                lo = mid + 1; 
37            }
38        }
39        
40        return lo;
41    }
42
43    private static int findSubarrayCount(int[] nums, int maxSum) {
44        // this implies currently we have 1 subaarray with 0 sum
45        int subArrays= 1;
46        int subArrSum = 0;
47
48        // traverse the array to form subarrays
49        for(int i = 0 ; i < nums.length ; i ++) {
50            // if the current element can be added to the current subarray and it doesn't exceed maxSum
51            if(subArrSum + nums[i] <= maxSum) {
52                subArrSum += nums[i]; // add the current element to the current subarray sum
53            } else {
54                subArrays++; // count the current element as the start of new subarray and increment subarray count
55                subArrSum = nums[i]; // set the current element as the first element of the new subarray
56            }
57        }
58
59        // return the number of subarrays formed
60        return subArrays;
61    }
62}