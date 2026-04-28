1class Solution {
2     static final String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
3
4    public List<String> letterCombinations(String digits) {
5        List<String> res = new ArrayList<>();
6        StringBuilder curr = new StringBuilder();
7
8        if(digits.length() == 0) {
9            return res;
10        }
11
12        helper(digits, 0, curr, res);
13
14        return res;
15    }
16    
17    private static void helper(String digits, int idx, StringBuilder curr, List<String> res) {
18        if(idx == digits.length()) {
19            res.add(curr.toString());
20            return;
21        }
22
23        String s = map[digits.charAt(idx) - '0'];
24
25        for(char ch: s.toCharArray()) {
26            curr.append(ch);
27            helper(digits, idx + 1, curr, res);
28            curr.deleteCharAt(curr.length() - 1);
29        }
30    }
31}