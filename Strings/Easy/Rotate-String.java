1class Solution {
2    public boolean rotateString(String s, String goal) {
3        // Strings must be same length to be rotations of each other
4        if (s.length() != goal.length()) {
5            return false;
6        }
7
8        // Concatenate the string with itself
9        String temp = s + s;
10
11        // Check if the string t is a substring of the concatenated string
12        return temp.contains(goal);
13        
14    }
15}