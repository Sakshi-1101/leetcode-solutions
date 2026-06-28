1class Solution {
2    public int maximalRectangle(char[][] matrix) {
3        int maxArea = 0;
4        int n = matrix.length;
5        int m = matrix[0].length;
6
7        // in this we'll maintain the histogram for each row
8        int[] height = new int[m];
9
10        // traverse the matrix
11        for(int i = 0 ; i < n ; i ++) {
12            // for each row, traverse the column and update the height[] array to create the histogram for the rows
13            for(int j = 0 ; j < m ; j ++) {
14                if(matrix[i][j] == '1') {
15                    height[j]++;
16                } else {
17                    height[j] = 0;
18                }
19            }
20
21            // find the max area rectangle for the current rows considered in histogram
22            int maxAreaInRow = getAreaInRow(height, m);
23            maxArea = Math.max(maxArea, maxAreaInRow); // find the max area rectangle in entire matrix
24        }
25
26        return maxArea;
27    }
28
29    public static int getAreaInRow(int[] arr, int n) {
30        Stack<Integer> st = new Stack<>(); // store indexes
31        int maxArea = 0;
32
33        // traverse all the bars in histogram while maintaining the pse in a increasing order in the stack.
34        for(int i = 0 ; i < n ; i ++) {
35
36            // as soon as we find the nse for the top of the stack element, we'll pop all the elements from the stack and 
37            // calculate the area for each of them till the point we get the pse for the current element (considered as nse)
38            // on the top of stack.
39            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
40                int eleIdx = st.pop();
41                int nse = i; // curr element is the nse for the popped element
42                // if stack is empty, then there is no pse for the popped element, else the top of stack after popping is the 
43                // pse for the popped element
44                int pse = st.isEmpty() ? -1 : st.peek(); 
45
46                // calculate the area for the popped element and update the maxArea
47                int area = arr[eleIdx] * (nse - pse - 1);
48                maxArea = Math.max(area, maxArea); // update the max area accordingly
49            }
50
51            // push the current index to stack assuming that it could be the pse for any future element
52            st.push(i);
53        }
54
55        // if elements left in stack (for which area is not calculated yet)
56        while(!st.isEmpty()) {
57            int eleIdx = st.pop();
58            int nse = n ; // since remaining elements don't have nse
59            int pse = st.isEmpty() ? -1 : st.peek();
60
61            int area = arr[eleIdx] * (nse - pse - 1);
62            maxArea = Math.max(area, maxArea);
63        }
64
65        return maxArea;
66    }
67
68
69}