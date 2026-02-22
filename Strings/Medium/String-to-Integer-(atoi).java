1class Solution {
2    public int myAtoi(String s) {
3        // base case: empty string
4        if(s.length() == 0) {
5            return 0;
6        }
7
8        int sign = 1; // 1 represents + sign and -1 represents - sign
9        int i = 0;
10
11        // skip leading spaces
12        while(i < s.length() && s.charAt(i) == ' ') {
13            i++;
14        }
15
16       // check the sign and store the correct value to append in final ans
17       if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
18            sign = (s.charAt(i) == '-') ? -1 : 1;
19            i++;
20        }
21
22        // skip leading 0s
23        while(i < s.length() && s.charAt(i) == '0') {
24            i++;
25        }
26
27        int ans = 0; // to store final integer value
28
29        // traverse the string till a non-digit is encountered
30        while(i < s.length() && Character.isDigit(s.charAt(i))) {
31            int digit = s.charAt(i) - '0';
32
33            // check the overflow condition
34            if (ans > (Integer.MAX_VALUE - digit) / 10) {
35                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
36            }
37
38            // create the integer value
39            ans = ans * 10 + digit;
40            i++;
41        }
42
43        // multiply the correct sign value to the final ans
44        return ans * sign;
45    }
46}