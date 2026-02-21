1class Solution {
2    public int maxDepth(String s) {
3        int maxDepth = 0;
4        int currDepth = 0;
5
6        for(int i = 0 ; i < s.length() ; i ++) {
7            char ch = s.charAt(i);
8
9            if(ch == '(') {
10                currDepth++;
11            } else if(ch == ')') {
12                currDepth --;
13            }
14
15            maxDepth = Math.max(maxDepth, currDepth);
16        }
17
18        return maxDepth;
19        
20    }
21}