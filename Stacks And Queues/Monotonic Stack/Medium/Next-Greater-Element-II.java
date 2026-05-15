1class Solution {
2    public int[] nextGreaterElements(int[] nums) {
3        int n = nums.length;
4        int[] ans = new int[n];
5
6        Stack<Integer> st = new Stack<>(); // monotonic stack
7
8        // traverse the circular array (hypothetically double size array) from right to left since we need nge to right
9        for(int i = 2 * n - 1 ; i >= 0 ; i --) {
10            int ele = nums[i % n] ; // bcoz we are starting traversal from hypothetical idx where no value exists
11
12            // pop all the ele <= curr ele bcoz if ele are smaller than curr ele then it will never be next greater for elements
13            // on the left of curr element
14            while(!st.isEmpty() && st.peek() <= ele) {
15                st.pop();
16            }
17
18            // if i < n, that mean we are not in hypothetical part of nums and we can assign the nge for curr ele in ans array, 
19            // otherwise we are in hypothetical part and we just need to push the ele in stack for finding nge for elements on 
20            // left of curr ele
21            if(i < n) {
22                ans[i] = st.isEmpty() ? -1 : st.peek();
23            }
24
25            // push curr ele in stack
26            st.push(ele);
27            
28        }
29
30        return ans;
31        
32    }
33}