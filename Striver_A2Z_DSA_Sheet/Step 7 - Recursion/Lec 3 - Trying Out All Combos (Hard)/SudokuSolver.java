public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
            {'9', '5', '7', '.', '1', '3', '.', '8', '4'},
            {'4', '8', '3', '.', '5', '7', '1', '.', '6'},
            {'.', '1', '2', '.', '4', '9', '5', '3', '7'},
            {'1', '7', '.', '3', '.', '4', '9', '.', '2'},
            {'5', '.', '4', '9', '7', '.', '3', '6', '.'},
            {'3', '.', '9', '5', '.', '8', '7', '.', '1'},
            {'8', '4', '5', '7', '9', '.', '6', '1', '3'},
            {'.', '9', '1', '.', '3', '6', '.', '7', '5'},
            {'7', '.', '6', '1', '8', '5', '4', '.', '9'}
        };

        boolean ans = solveSudoku(board);

        System.out.println(ans);

        // print the board
        for(int i = 0 ; i < board.length ; i ++) {
            for(int j = 0 ; j < board[0].length ; j ++) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
        
    }

    // TC: O(9^(k)) -> in worst case, total empty cells = k and each cell we have 9 choices.
    // SC: O(k) -> where k = no. of total empty cells
    // Approach:
    public static boolean solveSudoku(char[][] board) {
        // traverse the entire board
        for(int i = 0 ; i < board.length ; i ++) {
            for(int j = 0 ; j < board[0].length ; j ++) {
                // check for empty cells
                if(board[i][j] == '.') {

                    // check for all values from 1 to 9 that can be put in the empty cell
                    for(char c = '1'; c <= '9'; c++) {

                        // if c is safe to put in the cell as per the rules
                        if(isSafe(board, i, j, c)) {
                            // put the curr no. in the cell
                            board[i][j] = c;

                            // call recursion on the next empty cell
                            if(solveSudoku(board) == true){ 
                                // if we get true means it was able to fill all the cells and we got a valid config
                                return true;
                            } else { 
                                // if recursion call returns false, we'll backtrack and remove the last added value
                                board[i][j] = '.';
                            }
                        }
                    }

                    // If no number fits return false
                    return false;
                }
            }
        }

        // if we traverse entire board and didn't get a false, means we got a valid config
        return true;
    }

    /*
        A sudoku solution must satisfy all of the following rules:
        - Each of the digits 1-9 must occur exactly once in each row.
        - Each of the digits 1-9 must occur exactly once in each column.
        - Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     */
    private static boolean isSafe(char[][] board, int row, int col, char c) {
        // traverse the board to check the rules
        for(int i = 0 ; i < board.length ; i ++) {
            // check the entire row
            if(board[row][i] == c) {
                return false;
            }

            // check entire column
            if(board[i][col] == c) {
                return false;
            }

            // check the entire 3x3 grid
            if(board[3 * (row / 3) + (i / 3)][3 * (col / 3) + (i % 3)] == c) {
                return false;
            }
        }

        return true;
    }
    
}
