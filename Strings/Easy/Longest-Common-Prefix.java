1class Solution {
2    public String longestCommonPrefix(String[] strs) {
3        // To store the longest common prefix
4        StringBuilder ans = new StringBuilder();
5
6        // sort the strings in lexicographical order bcoz the common prefix of the first and last string in the sorted array 
7        // will be the longest common prefix for the entire array.
8        Arrays.sort(strs);
9
10        //find the smallest(first) and largest(last) string
11        String first = strs[0];
12        String last = strs[strs.length - 1];
13
14        // we'll traverse till the min length of the first and last string because the common prefix can't be longer than the 
15        // smallest string
16        int min = Math.min(first.length() , last.length());
17
18        // Compare characters of the first and last strings until a mismatch is found
19        for(int i = 0 ; i < min ; i ++) {
20            // Stop if characters are different
21            if(first.charAt(i) != last.charAt(i)) {
22                return ans.toString();
23            }
24
25            // Add matching character to result
26            ans.append(first.charAt(i));
27        }
28
29        // Return the final common prefix
30        return ans.toString();
31        
32    }
33}