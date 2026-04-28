1class Solution {
2    public List<List<String>> partition(String s) {
3        List<List<String>> res = new ArrayList<>();
4        List<String> currStr = new ArrayList<>();
5
6        helper(s, 0, currStr, res);
7
8        return res;
9    }
10
11    private static void helper(String s, int idx, List<String> curr, List<List<String>> res) {
12        // base case
13        if(idx == s.length()) {
14            res.add(new ArrayList<>(curr));
15            return;
16        }
17
18        for(int i = idx ; i < s.length() ; i ++) {
19            if(isPalindrome(s, idx, i)) {
20                curr.add(s.substring(idx, i + 1));
21                helper(s, i + 1, curr, res);
22                curr.remove(curr.size() - 1);
23            }
24        }
25    }
26
27    private static boolean isPalindrome(String s, int start, int end) {
28        while(start <= end) {
29            if(s.charAt(start) != s.charAt(end)) {
30                return false;
31            }
32
33            start++;
34            end--;
35        }
36
37        return true;
38    }
39}