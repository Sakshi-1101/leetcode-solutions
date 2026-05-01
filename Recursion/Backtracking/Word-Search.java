1class Solution {
2    public boolean exist(char[][] board, String word) {
3        int r = board.length;
4        int c = board[0].length;
5
6        // Iterate over all cells
7        for(int i = 0 ; i < r ; i ++) {
8            for(int j = 0 ; j < c ; j ++) {
9                // Start DFS if first letter matches
10                if(board[i][j] == word.charAt(0)) {
11                    if(dfs(board, word, i, j, 0)) {
12                        return true;
13                    }
14                }
15            }
16        }
17
18        return false;
19    }
20
21    public static boolean dfs(char[][] board, String word, int i, int j, int idx) {
22        // If word is completely found
23        if(idx == word.length()) {
24            return true;
25        }
26
27        // Boundary and mismatch check
28        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] != word.charAt(idx)) {
29            return false;
30        }
31
32        // Store character and mark visited
33        char temp = board[i][j];
34        board[i][j] = '#';
35
36        // Explore in all four directions
37        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
38                        dfs(board, word, i, j + 1, idx + 1) ||
39                        dfs(board, word, i - 1, j, idx + 1) ||
40                        dfs(board, word, i, j - 1, idx + 1);
41
42        // Restore character while backtracking
43        board[i][j] = temp;
44
45        return found;
46    }
47}