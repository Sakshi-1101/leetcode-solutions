1class Solution {
2    public String longestPalindrome(String s) {
3        if (s == null || s.length() < 1) {
4            return "";
5        }
6
7        String ans = "";
8        int maxLen = 0;
9
10        for(int i = 0 ; i < s.length() ; i ++) {
11            String oddPalin = expand(s, i, i);
12            String evenPalin = expand(s, i, i + 1);
13
14            if(oddPalin.length() > maxLen) {
15                maxLen = oddPalin.length();
16                ans = oddPalin;
17            }
18
19            if(evenPalin.length() > maxLen) {
20                maxLen = evenPalin.length();
21                ans = evenPalin;
22            }
23        }
24
25        return ans;
26    }
27
28    private static String expand(String s, int left, int right) {
29
30        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
31            left-- ;
32            right++ ;
33        }
34
35        return s.substring(left + 1, right);
36    }
37}