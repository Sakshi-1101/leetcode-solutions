1class Solution {
2    public String largestOddNumber(String num) {
3        // find the last position of the odd no. in the string
4        int oddIdx = -1;
5
6        for(int i = num.length() - 1 ; i >= 0 ; i --) {
7            char ch = num.charAt(i);
8
9            // as soon as an odd no. is encouter we'll break since we need last odd no. in string
10            if((ch - '0') % 2 == 1) {
11                oddIdx = i;
12                break;
13            }
14        }
15
16        // return an empty string, if odd no. not found
17        if(oddIdx == -1) {
18            return "";
19        }
20
21        // Skip leading zeroes up to the odd digit
22        int i = 0;
23
24        while(i <= oddIdx && num.charAt(i) == '0') {
25            i++;
26        }
27
28        // return substring from first non-zero digit to last occurrence of the odd digit
29        return num.substring(i, oddIdx + 1);
30    }
31}