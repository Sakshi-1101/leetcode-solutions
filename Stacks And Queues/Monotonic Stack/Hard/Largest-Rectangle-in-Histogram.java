1class Solution {
2    public int largestRectangleArea(int[] heights) {
3        int n = heights.length;
4        Stack<Integer> st = new Stack<>(); // store indexes
5        int maxArea = 0;
6
7        for(int i = 0 ; i < n ; i ++) {
8
9            while(!st.isEmpty() && heights[st.peek()] > heights[i]) {
10                int eleIdx = st.pop();
11                int nse = i;
12                int pse = st.isEmpty() ? -1 : st.peek();
13
14                int area = heights[eleIdx] * (nse - pse - 1);
15                maxArea = Math.max(area, maxArea);
16            }
17
18            st.push(i);
19        }
20
21        // if elements left in stack (for which area is not calculated yet)
22        while(!st.isEmpty()) {
23            int eleIdx = st.pop();
24            int nse = n ; // since remaining elements don't have nse
25            int pse = st.isEmpty() ? -1 : st.peek();
26
27            int area = heights[eleIdx] * (nse - pse - 1);
28            maxArea = Math.max(area, maxArea);
29        }
30
31        return maxArea;
32        
33    }
34}