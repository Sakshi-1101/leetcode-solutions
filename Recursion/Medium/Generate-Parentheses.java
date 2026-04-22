1class Solution {
2    public List<String> generateParenthesis(int n) {
3         List<String> res = new ArrayList<>();
4        helper(0, 0, n, "", res);
5        return res;
6    }
7
8    private static void helper(int open, int close, int n, String curr, List<String> res) {
9        // Base case
10        if (curr.length() == 2 * n) {
11            res.add(curr);
12            return;
13        }
14
15        // Add '(' until limit is reached i.e. n
16        if (open < n) {
17            helper(open + 1, close, n, curr + "(", res);
18        }
19
20        // Add ')' until it exceeds the count of '(' bracket
21        if (close < open) {
22            helper(open, close + 1, n, curr + ")", res);
23        }
24    }
25}