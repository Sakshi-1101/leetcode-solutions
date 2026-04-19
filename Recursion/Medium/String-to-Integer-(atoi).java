1class Solution {
2    public int myAtoi(String s) {
3        int sign = 1; // 1 represents + sign and -1 represents - sign
4        int i = 0;
5
6        // skip leading spaces
7        while(i < s.length() && s.charAt(i) == ' ') {
8            i++;
9        }
10
11       // check the sign and store the correct value to append in final ans
12       if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
13            sign = (s.charAt(i) == '-') ? -1 : 1;
14            i++;
15        }
16
17        // skip leading 0s
18        while(i < s.length() && s.charAt(i) == '0') {
19            i++;
20        }
21
22        // 
23        return helper(s, i, sign, 0);
24    }
25
26    // 
27    private static int helper(String s, int i, int sign, long ans) {
28        // base case: 
29        if(i >= s.length() || !Character.isDigit(s.charAt(i))){
30            return (int)(sign * ans);
31        }
32
33        // 
34        ans = ans * 10 + (s.charAt(i) - '0');
35
36        // check the overflow condition
37        if (sign * ans <= Integer.MIN_VALUE) {
38            return Integer.MIN_VALUE;
39        }
40        if (sign * ans >= Integer.MAX_VALUE) {
41            return Integer.MAX_VALUE;
42        }
43
44        //
45        return helper(s, i + 1, sign, ans);
46    }
47}