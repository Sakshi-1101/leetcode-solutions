1class Solution {
2    public List<List<String>> solveNQueens(int n) {
3        List<List<String>> res = new ArrayList<>();
4
5        char[][] board = new char[n][n];
6
7        // Initialize board with '.'
8        for(int i = 0 ; i < n ; i ++) {
9            Arrays.fill(board[i], '.');
10        }
11
12        // hash lists to mark the position where the Q is already placed in the directions
13        int[] leftRow = new int[n];
14        int[] upperLeftDgnl = new int[2 * n - 1];
15        int[] lowerLeftDgnl = new int[2 * n - 1];
16
17        // start adding Q from column 0 
18        helperOptimal(board, 0, res, leftRow, upperLeftDgnl, lowerLeftDgnl, n);
19        return res;
20    } 
21
22    private static void helperOptimal(char[][] board, int col, List<List<String>> res, int[] leftRow, int[] upperLeftDgnl, int[] lowerLeftDgnl, int n) {
23        // If col idx reaches last column i.e. n then all columns are filled, means we got a config
24        if(col == n) {
25            // add the each row of curr config to the list
26            List<String> currConfig = new ArrayList<>();
27
28            for(int i = 0 ; i < n ; i ++) {
29                currConfig.add(new String(board[i]));
30            }
31
32            // add the currconfig list to the final ans
33            res.add(currConfig);
34            return;
35        }
36
37        // Try placing queen in each row for the current column
38        for(int row = 0 ; row < n ; row ++) {
39            // check the hash list of all the directions (left, upper left diagonal, lower left diagonal)
40            if(leftRow[row] == 0 && lowerLeftDgnl[row + col] == 0 && upperLeftDgnl[(n-1) + (col - row)] == 0) {
41                // place the Q and mark the lists for this position as visited
42                board[row][col] = 'Q';
43                leftRow[row] = 1;
44                lowerLeftDgnl[row + col] = 1;
45                upperLeftDgnl[(n-1) + (col - row)] = 1;
46
47                // call recursion on the next Q for the next column
48                helperOptimal(board, col + 1, res, leftRow, upperLeftDgnl, lowerLeftDgnl, n);
49
50                // remove the last placed Q while backtracking
51                board[row][col] = '.';
52                leftRow[row] = 0;
53                lowerLeftDgnl[row + col] = 0;
54                upperLeftDgnl[(n-1) + (col - row)] = 0;
55            }
56        }
57    }
58}