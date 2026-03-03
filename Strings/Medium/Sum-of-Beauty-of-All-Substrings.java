1class Solution {
2    public int beautySum(String s) {
3        int beauty = 0;
4
5        for(int i = 0 ; i < s.length() ; i ++) {
6            HashMap<Character, Integer> map = new HashMap<>();
7
8            for(int j = i ; j < s.length() ; j ++) {
9                char ch = s.charAt(j);
10                map.put(ch, map.getOrDefault(ch, 0) + 1);
11
12                int maxFreq = Integer.MIN_VALUE;
13                int minFreq = Integer.MAX_VALUE;
14
15                for(int freq: map.values()) {
16                    minFreq = Math.min(minFreq, freq);
17                    maxFreq = Math.max(maxFreq, freq);
18                }
19
20                beauty += (maxFreq - minFreq);
21            }
22        }
23
24        return beauty;
25
26
27
28        
29    }
30}