1class Solution {
2    public boolean isValid(String s) {
3        if(s.length() %2 != 0) {
4            return false;
5        }
6
7        Stack<Character> st = new Stack<>();
8
9        for(int i = 0 ; i < s.length() ; i ++) {
10            char ch = s.charAt(i);
11            if(ch == ')') {
12                if(!st.isEmpty() && st.peek() == '(') {
13                    st.pop();
14                } else {
15                    // st.push(ch);
16                    return false;
17                }
18            } else if(ch == ']') {
19                if(!st.isEmpty() && st.peek() == '[') {
20                    st.pop();
21                } else {
22                    // st.push(ch);
23                    return false;
24                }
25            } else if(ch == '}') {
26                if(!st.isEmpty() && st.peek() == '{') {
27                    st.pop();
28                } else {
29                    // st.push(ch);
30                    return false;
31                }
32            } else { // if any opening bracket
33                st.push(ch);
34            }
35        }
36
37        if(!st.isEmpty()) {
38            return false;
39        } else {
40            return true;
41        }
42        
43    }
44}