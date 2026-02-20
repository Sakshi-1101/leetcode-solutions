1class Solution {
2    public boolean isAnagram(String s, String t) {
3        if(s.length() != t.length()) {
4            return false;
5        }
6
7        int[] freq = new int[26];
8
9        for(int i = 0 ; i < s.length() ; i ++) {
10            char ch = s.charAt(i);
11            freq[ch - 'a']++;
12        }
13
14        for(int i = 0 ; i < t.length() ; i ++) {
15            char ch = t.charAt(i);
16            freq[ch - 'a']--;
17        }
18
19        for(int i = 0 ; i < freq.length ; i ++) {
20            if(freq[i] != 0) {
21                return false;
22            }
23        }
24        
25        return true;
26    }
27}