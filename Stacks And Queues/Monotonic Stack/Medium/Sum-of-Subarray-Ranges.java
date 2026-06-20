1class Solution {
2    public long subArrayRanges(int[] nums) {
3        int n = nums.length;
4
5        // find the sum of smallest element in all the subarrays and sum of largest element in all the subarrays
6        long sumSubArrayMin = getSumSubarrayMin(nums, n);
7        long sumSubArrayMax = getSumSubarrayMax(nums, n);
8
9        // subtract the sum of smallest element from the sum of largest element to get the sum of ranges of all the subarrays
10        // to get the sum of ranges of all the subarrays
11        long sum = sumSubArrayMax - sumSubArrayMin;
12        return sum;
13    }
14
15    // find the sum of smallest element in all the subarrays
16    public static long getSumSubarrayMin(int[] arr, int n) {
17        long sum = 0;
18
19        int[] nse = nse(arr, n); // find the nse idx for all the ele
20        int[] psee = psee(arr, n); // find the psee idx for all the ele
21        
22        for(int i = 0 ; i < n ; i ++) {
23
24            // find the nse idx for the curr ele
25            int nseIdx = nse[i];
26
27            // find the pse idx for the curr ele
28            int pseeIdx = psee[i];
29
30            // Count of elements to the left including current
31            long leftCount = i - pseeIdx;
32
33            // Count of elements to the right including current
34            long rightCount = nseIdx - i;
35
36            // count total subarrays where arr[i] is the min ele
37            long totalSubarr = leftCount * rightCount;
38            // Contribution of curr ele = frequency of occurence (total subarrays) * curr ele value
39            long totalContri = totalSubarr * (long)arr[i];
40
41            // Add contribution to sum
42            sum = sum + totalContri;
43        }
44
45        return sum;
46    }
47
48    // function to find the idx of next smallest element to the right for each of the array element
49    public static int[] nse(int[] arr, int n) {
50        Stack<Integer> st = new Stack<>();
51        int[] ans = new int[n];
52
53        // Traverse array from right to left
54        for(int i = n - 1 ; i >= 0 ; i --) {
55
56            // Pop elements that are greater or equal to current
57            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
58                st.pop();
59            }
60
61            if(!st.isEmpty()) {
62                ans[i] = st.peek();
63            } else {
64                ans[i] = n; // If stack is empty, NSE doesn't exist → set to n
65            }
66
67            // Push current index to stack
68            st.push(i);
69
70        }
71
72        return ans;
73    }
74
75    // function to find the idx of previous smallest or equal element to the left for each of the array element
76    public static int[] psee(int[] arr, int n) {
77        Stack<Integer> st = new Stack<>();
78        int[] ans = new int[n];
79
80        // Traverse array from left to right
81        for(int i = 0 ; i < n ; i ++) {
82
83            // Pop elements greater than current
84            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
85                st.pop();
86            }
87
88            if(!st.isEmpty()) {
89                ans[i] = st.peek();
90            } else {
91                ans[i] = -1; // If stack is empty, pse doesn't exist → set to -1
92            }
93
94            // Push current index to stack
95            st.push(i);
96
97        }
98
99        return ans;
100    }
101
102    // find the sum of largest element in all the subarrays
103    public static long getSumSubarrayMax(int[] arr, int n) {
104        long sum = 0;
105
106        int[] nge = nge(arr, n); // find the nge idx for all the ele
107        int[] pgee = pgee(arr, n); // find the pgee idx for all the ele
108        
109        for(int i = 0 ; i < n ; i ++) {
110
111            // find the nge idx for the curr ele
112            int ngeIdx = nge[i];
113
114            // find the pge idx for the curr ele
115            int pgeeIdx = pgee[i];
116
117            // Count of elements to the left including current
118            long leftCount = i - pgeeIdx;
119
120            // Count of elements to the right including current
121            long rightCount = ngeIdx - i;
122
123            // count total subarrays where arr[i] is the min ele
124            long totalSubarr = leftCount * rightCount;
125
126            // Contribution of curr ele = frequency of occurence (total subarrays) * curr ele value
127            long totalContri = totalSubarr * (long)arr[i];
128
129            // Add contribution to sum
130            sum = sum + totalContri;
131        }
132
133        return sum;
134
135
136
137    }
138
139    // function to find the idx of next greater element to the right for each of the array element
140    public static int[] nge(int[] arr, int n) {
141        Stack<Integer> st = new Stack<>();
142        int[] ans = new int[n];
143
144        // Traverse array from right to left
145        for(int i = n - 1 ; i >= 0 ; i --) {
146
147            // Pop elements that are less than or equal to current
148            while(!st.isEmpty() && arr[st.peek()] <= arr[i]) {
149                st.pop();
150            }
151
152            if(!st.isEmpty()) {
153                ans[i] = st.peek();
154            } else {
155                ans[i] = n; // If stack is empty, NSE doesn't exist → set to n
156            }
157
158            // Push current index to stack
159            st.push(i);
160
161        }
162
163        return ans;
164    }
165
166    // function to find the idx of previous greater or equal element to the left for each of the array element
167    public static int[] pgee(int[] arr, int n) {
168        Stack<Integer> st = new Stack<>();
169        int[] ans = new int[n];
170
171        // Traverse array from left to right
172        for(int i = 0 ; i < n ; i ++) {
173
174            // Pop elements less than current
175            while(!st.isEmpty() && arr[st.peek()] < arr[i]) {
176                st.pop();
177            }
178
179            if(!st.isEmpty()) {
180                ans[i] = st.peek();
181            } else {
182                ans[i] = -1; // If stack is empty, pse doesn't exist → set to -1
183            }
184
185            // Push current index to stack
186            st.push(i);
187
188        }
189
190        return ans;
191    }
192
193}