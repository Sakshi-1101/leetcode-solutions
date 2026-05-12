1class Solution {
2    // APPROACH 1
3    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
4        int n1 = nums1.length;
5        int n2 = nums2.length;
6
7        Stack<Integer> st = new Stack<>();
8        HashMap<Integer, Integer> map = new HashMap<>();
9        int[] ans = new int[n1];
10
11        // push all ele and it's idx values in map for nums1
12        for(int i = 0 ; i < n1 ; i ++) {
13            map.put(nums1[i], i);
14        }
15
16        // traverse nums2 and find the next greater ele for only that ele which is present in nums1
17        for(int i = n2 - 1 ; i >= 0 ; i --) {
18            if(!map.containsKey(nums2[i])) {
19                st.push(nums2[i]);
20                continue;
21            }
22
23            int idx = map.get(nums2[i]);
24
25            while(!st.isEmpty() && st.peek() <= nums2[i]) {
26                st.pop();
27            }
28
29            if(st.isEmpty()) {
30                ans[idx] = -1;
31            } else {
32                ans[idx] = st.peek();
33            }
34
35            st.push(nums2[i]);
36        }
37
38        return ans;
39    }
40
41    // APPROACH 2
42    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
43        int n1 = nums1.length;
44        int n2 = nums2.length;
45
46        Stack<Integer> st = new Stack<>();
47        HashMap<Integer, Integer> map = new HashMap<>(); // map<curr num, next greater ele for that num>
48        int[] ans = new int[n1];
49
50        // push all the next greater elements for nums2 array in map
51        for(int i = n2 - 1 ; i >= 0 ; i --) {
52            while(!st.isEmpty() && st.peek() <= nums2[i]) {
53                st.pop();
54            }
55
56            if(st.isEmpty()) {
57                map.put(nums2[i], -1);
58            } else {
59                map.put(nums2[i], st.peek());
60            }
61
62            st.push(nums2[i]);
63        }
64
65        // from map get the next greater element corresponding to equivalent ele present in nums1
66        for(int i = 0 ; i < n1 ; i ++) {
67            ans[i] = map.get(nums1[i]);
68        }
69
70        return ans;
71    }
72}