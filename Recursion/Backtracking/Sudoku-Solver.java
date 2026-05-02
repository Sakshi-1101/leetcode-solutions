1class Solution {
2    public void solveSudoku(char[][] board) {
3        solve(board);
4    }
5
6    public boolean solve(char[][] board) {
7        // traverse the entire board
8        for(int i = 0 ; i < board.length ; i ++) {
9            for(int j = 0 ; j < board[0].length ; j ++) {
10                // check for empty cells
11                if(board[i][j] == '.') {
12
13                    // check for all values from 1 to 9 that can be put in the empty cell
14                    for(char c = '1'; c <= '9'; c++) {
15
16                        // if c is safe to put in the cell as per the rules
17                        if(isSafe(board, i, j, c)) {
18                            // put the curr no. in the cell
19                            board[i][j] = c;
20
21                            // call recursion on the next empty cell
22                            if(solve(board) == true){ 
23                                // if we get true means it was able to fill all the cells and we got a valid config
24                                return true;
25                            } else { 
26                                // if recursion call returns false, we'll backtrack and remove the last added value
27                                board[i][j] = '.';
28                            }
29                        }
30                    }
31
32                    // If no number fits return false
33                    return false;
34                }
35            }
36        }
37
38        // if we traverse entire board and didn't get a false, means we got a valid config
39        return true;
40    }
41
42    private static boolean isSafe(char[][] board, int row, int col, char c) {
43        // traverse the board to check the rules
44        for(int i = 0 ; i < board.length ; i ++) {
45            // check the entire row
46            if(board[row][i] == c) {
47                return false;
48            }
49
50            // check entire column
51            if(board[i][col] == c) {
52                return false;
53            }
54
55            // check the entire 3x3 grid
56            if(board[3 * (row / 3) + (i / 3)][3 * (col / 3) + (i % 3)] == c) {
57                return false;
58            }
59        }
60
61        return true;
62    }
63}