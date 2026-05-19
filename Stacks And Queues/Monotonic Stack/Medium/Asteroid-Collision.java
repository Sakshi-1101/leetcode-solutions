1class Solution {
2    public int[] asteroidCollision(int[] asteroids) {
3        Stack<Integer> st = new Stack<>();
4
5        for(int i = 0 ; i < asteroids.length ; i ++) {
6            int val = asteroids[i];
7
8            // if +ve element (right direction)
9            if(val > 0) {
10                st.push(val);
11            } 
12            // if -ve element (left direction)
13            else {
14                
15                while(!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(val)) {
16                    st.pop();
17                }
18
19                if(!st.isEmpty() && st.peek() == Math.abs(val)) {
20                    st.pop();
21                }
22                else if(st.empty() || st.peek() < 0) {
23                    st.push(val); // push -ve value
24                }
25            }
26        }
27
28        int[] ans = new int[st.size()];
29
30        for(int i = ans.length - 1 ; i >= 0 ; i --) {
31            ans[i] = st.pop();
32        }
33
34        return ans;
35        
36    }
37}