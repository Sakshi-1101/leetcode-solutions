1class Solution {
2    public int romanToInt(String s) {
3        int res = 0;
4        
5        // Create a map of Roman numerals to integers
6        Map<Character, Integer> map = new HashMap<>();
7        map.put('I', 1);
8        map.put('V', 5);
9        map.put('X', 10);
10        map.put('L', 50);
11        map.put('C', 100);
12        map.put('D', 500);
13        map.put('M', 1000);
14
15        // Loop through the string, except the last character
16        for (int i = 0; i < s.length() - 1; i++) {
17
18            // Subtract if current value is less than next value
19            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
20                res -= map.get(s.charAt(i));
21            } else {
22                res += map.get(s.charAt(i)); // Otherwise, add the value
23            }
24        }
25
26        // Add the value of the last character
27        return res + map.get(s.charAt(s.length() - 1));
28    }
29}