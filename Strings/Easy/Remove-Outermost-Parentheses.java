1class Solution {
2    public String removeOuterParentheses(String s) {
3        int level = 0;
4        StringBuilder sb = new StringBuilder("");
5
6        for(int i = 0 ; i < s.length() ; i ++) {
7            char ch = s.charAt(i);
8
9            if(ch == '(') {
10                if(level > 0) {
11                    sb.append(ch);
12                }
13
14                level++;
15
16            } else {
17                level--;
18
19                if(level > 0) {
20                    sb.append(ch);
21                }
22
23            }
24        }
25
26        return sb.toString();
27        
28    }
29}