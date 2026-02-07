1class Solution {
2    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
3       int n1 = nums1.length;
4        int n2 = nums2.length;
5
6        // length of merged sorted array
7        int n = n1 + n2;
8
9        // to ensure that we always do binary search on the smaller array
10        if(n1 > n2) {
11            return findMedianSortedArrays(nums2, nums1);
12        }
13
14        // number of elements that should be on the left half of the merged sorted array. 
15        int leftEleCount = (n1 + n2 + 1) / 2;
16
17        // setting lo and hi for bianry search
18        int lo = 0;
19        int hi = n1; // we can have at max n1 elements on the left side from nums1, so hi is set to n1.
20
21        while(lo <= hi) {
22            // mid1 represents the number of elements we are taking from nums1 for the left half of the symmetry. 
23            // mid2 represents the number of elements we are taking from nums2 for the left half of the symmetry. 
24            int mid1 = (lo + hi) / 2;
25            int mid2 = leftEleCount - mid1;
26
27            // setting default values for l1, l2, r1 and r2. We use Integer.MIN_VALUE and Integer.MAX_VALUE to handle edge cases
28            // where there are no elements on one side of the symmetry from either array.
29            int l1 = Integer.MIN_VALUE; // l1 -> largest element on the left side of the symmetry from nums1
30            int l2 = Integer.MIN_VALUE; // l2 -> largest element on the left side of the symmetry from nums2
31            int r1 = Integer.MAX_VALUE; // r1 -> smallest element on the right side of the symmetry from nums1
32            int r2 = Integer.MAX_VALUE; // r2 -> smallest element on the right side of the symmetry from nums2
33
34            // assigning values to l1, l2, r1 and r2 based on mid1 and mid2. We check if mid1 and mid2 are within the bounds of 
35            // their respective arrays before accessing the elements to avoid ArrayIndexOutOfBoundsException.
36            if(mid1 < n1) {
37                r1 = nums1[mid1];
38            }
39
40            if(mid2 < n2) {
41                r2 = nums2[mid2];
42            }
43
44            if(mid1 - 1 >= 0) {
45                l1 = nums1[mid1 - 1];
46            }
47
48            if(mid2 - 1 >= 0) {
49                l2 = nums2[mid2 - 1];
50            }
51
52            // if below condition is true, it means this is a valid symmetry where all elements on the left side are less than or equal to all elements on the right side.
53            if(l1 <= r2 && l2 <= r1) {
54                // if n is even, median will be the avg of the max of left elements and min of right elements. 
55                if(n % 2 == 0) {
56                    return (double)(Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
57                } else { // if n is odd, median will be the max of left elements 
58                    return Math.max(l1, l2);
59                }
60            } 
61            // if l1 > r2, it means that we need to discard right half and move left.
62            else if(l1 > r2) {
63                hi = mid1 - 1;
64            } else { // if l2 > r1, it means that we need to discard left half and move right.
65                lo = mid1 + 1;
66            }
67        }
68
69        // this is dummy value, code will never reach here
70        return 0;
71    }
72}