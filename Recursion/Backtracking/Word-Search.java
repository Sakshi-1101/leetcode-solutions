1class Solution {
2    public boolean exist(char[][] board, String word) {
3        int r = board.length;
4        int c = board[0].length;
5
6        // Iterate over all cells
7        for(int i = 0 ; i < r ; i ++) {
8            for(int j = 0 ; j < c ; j ++) {
9                // Start DFS if first letter matches
10                if(dfs(board, word, i, j, 0)) {
11                    return true;
12                }
13            }
14        }
15
16        return false;
17    }
18
19    public static boolean dfs(char[][] board, String word, int i, int j, int idx) {
20        // If word is completely found
21        if(idx == word.length()) {
22            return true;
23        }
24
25        // Boundary and mismatch check
26        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] != word.charAt(idx)) {
27            return false;
28        }
29
30        // Store character and mark visited
31        char temp = board[i][j];
32        board[i][j] = '#';
33
34        // Explore in all four directions
35        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
36                        dfs(board, word, i, j + 1, idx + 1) ||
37                        dfs(board, word, i - 1, j, idx + 1) ||
38                        dfs(board, word, i, j - 1, idx + 1);
39
40        // Restore character while backtracking
41        board[i][j] = temp;
42
43        return found;
44    }
45}