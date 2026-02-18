1class Solution {
2    public boolean isIsomorphic(String s, String t) {
3        // If the lengths of the strings are different, they cannot be isomorphic
4        if(s.length() != t.length()) {
5            return false;
6        }
7
8        // Arrays to track last seen positions of characters in s and t.
9        int[] arrS = new int[256];
10        int[] arrT = new int[256];
11
12        for(int i = 0 ; i < s.length() ; i ++) {
13            char ch1 = s.charAt(i);
14            char ch2 = t.charAt(i);
15
16            // If the last seen positions of ch1 and ch2 do not match, then s and t are not isomorphic
17            if(arrS[ch1] != arrT[ch2]) {
18                return false;
19            }
20
21            // Update the last seen positions of ch1 and ch2 to the current index + 1
22            arrS[ch1] = i + 1;
23            arrT[ch2] = i + 1;
24        }
25
26        return true;
27    }
28}