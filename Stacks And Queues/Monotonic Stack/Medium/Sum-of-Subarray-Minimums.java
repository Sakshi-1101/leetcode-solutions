1class Solution {
2    int mod = (int)1e9 + 7; // Modulo value to prevent integer overflow
3
4    public int sumSubarrayMins(int[] arr) {
5        long sum = 0;
6        int n = arr.length;
7
8        int[] nse = nse(arr, n); // find the nse idx for all the ele
9        int[] psee = psee(arr, n); // find the psee idx for all the ele
10        
11        for(int i = 0 ; i < n ; i ++) {
12
13            // find the nse idx for the curr ele
14            int nseIdx = nse[i];
15
16            // find the pse idx for the curr ele
17            int pseeIdx = psee[i];
18
19            // Count of elements to the left including current
20            int leftCount = i - pseeIdx;
21
22            // Count of elements to the right including current
23            int rightCount = nseIdx - i;
24
25            // count total subarrays where arr[i] is the min ele
26            long totalSubarr = leftCount * rightCount * 1L; // 1L bcoz 
27
28            // Contribution of curr ele = frequency of occurence (total subarrays) * curr ele value
29            long totalContri = (totalSubarr * arr[i]) % mod;
30
31            // Add contribution to sum
32            sum = (sum + totalContri) % mod;
33        }
34
35        return (int)sum;
36    }
37
38    // function to find the idx of next smallest element to the right for each of the array element
39    public int[] nse(int[] arr, int n) {
40        Stack<Integer> st = new Stack<>();
41        int[] ans = new int[n];
42
43        // Traverse array from right to left
44        for(int i = n - 1 ; i >= 0 ; i --) {
45
46            // Pop elements that are greater or equal to current
47            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
48                st.pop();
49            }
50
51            if(!st.isEmpty()) {
52                ans[i] = st.peek();
53            } else {
54                ans[i] = n; // If stack is empty, NSE doesn't exist → set to n
55            }
56
57            // Push current index to stack
58            st.push(i);
59
60        }
61
62        return ans;
63    }
64
65    // function to find the idx of previous smallest or equal element to the left for each of the array element
66    public int[] psee(int[] arr, int n) {
67        Stack<Integer> st = new Stack<>();
68        int[] ans = new int[n];
69
70        // Traverse array from left to right
71        for(int i = 0 ; i < n ; i ++) {
72
73            // Pop elements greater than current
74            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
75                st.pop();
76            }
77
78            
79            if(!st.isEmpty()) {
80                ans[i] = st.peek();
81            } else {
82                ans[i] = -1; // If stack is empty, pse doesn't exist → set to -1
83            }
84
85            // Push current index to stack
86            st.push(i);
87
88        }
89
90        return ans;
91    }
92
93}