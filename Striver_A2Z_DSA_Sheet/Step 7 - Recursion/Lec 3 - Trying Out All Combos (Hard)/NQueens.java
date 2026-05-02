import java.util.*;

public class NQueens {

    public static void main(String[] args) {
        int n = 4;

        List<List<String>> ansBrute = nQueensBrute(n);
        List<List<String>> ansOptimal = nQueensOptimal(n);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N!*N) => we try all possible permutations of placing the queens and check for safety.
    // SC: O(N^2 + N + K * N^2) = O(k * N^2)=> additional space used for storing distinct boards and stack space.
    // Approach:
    public static List<List<String>> nQueensBrute(int n) {
        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];

        // Initialize board with '.'
        for(int i = 0 ; i < n ; i ++) {
            Arrays.fill(board[i], '.');
        }

        // start adding Q from column 0 
        helper(board, 0, res, n);
        return res;
    }   

    public static void helper(char[][] board, int col, List<List<String>> res, int n) {
        // If col idx reaches last column i.e. n then all columns are filled, means we got a config
        if(col == n) {
            // add the each row of curr config to the list
            List<String> currConfig = new ArrayList<>();

            for(int i = 0 ; i < n ; i ++) {
                currConfig.add(new String(board[i]));
            }

            // add the currconfig list to the final ans
            res.add(currConfig);
            return;
        }

        // Try placing queen in each row for the current column
        for(int row = 0 ; row < n ; row ++) {
            // check if queen is safe to add
            if(isSafe(board, col, row, n)) {
                board[row][col] = 'Q'; // add the Q
                helper(board, col + 1, res, n); // call recursion to add the next Q in the next column
                board[row][col] = '.'; // remove the last added Q while backtracking
            }
        }
    }

    // Function to check if placing a queen is safe
    public static boolean isSafe(char[][] board, int col, int row, int n) {
        /*
            NOTE: Since we are traversing and adding queen in columns from left to right so there is no need to check in the same 
                  column i.e. top and bottom direction and fwd directions i.e. right, upper and lower right diagonals.
        */

        //checking left to the Q
        for(int i = col ; i >= 0 ; i --) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        // check upper left diagonal to the Q
        for(int i = row, j = col ; i >= 0 && j >= 0 ; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // checking lower left diagonal to the Q
        for(int i = row, j = col ; i < n && j >= 0 ; i++ , j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Return true if it's safe to place
        return true;
    }
    
    // TC: O(N!) -> isSafe() check is now happening in O(1)
    // SC: O(k * N^2)
    // Approach:
    public static List<List<String>> nQueensOptimal(int n) {
        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];

        // Initialize board with '.'
        for(int i = 0 ; i < n ; i ++) {
            Arrays.fill(board[i], '.');
        }

        // hash lists to mark the position where the Q is already placed in the directions
        int[] leftRow = new int[n];
        int[] upperLeftDgnl = new int[2 * n - 1];
        int[] lowerLeftDgnl = new int[2 * n - 1];

        // start adding Q from column 0 
        helperOptimal(board, 0, res, leftRow, upperLeftDgnl, lowerLeftDgnl, n);
        return res;
    } 

    private static void helperOptimal(char[][] board, int col, List<List<String>> res, int[] leftRow, int[] upperLeftDgnl, int[] lowerLeftDgnl, int n) {
        // If col idx reaches last column i.e. n then all columns are filled, means we got a config
        if(col == n) {
            // add the each row of curr config to the list
            List<String> currConfig = new ArrayList<>();

            for(int i = 0 ; i < n ; i ++) {
                currConfig.add(new String(board[i]));
            }

            // add the currconfig list to the final ans
            res.add(currConfig);
            return;
        }

        // Try placing queen in each row for the current column
        for(int row = 0 ; row < n ; row ++) {
            // check the hash list of all the directions (left, upper left diagonal, lower left diagonal)
            if(leftRow[row] == 0 && lowerLeftDgnl[row + col] == 0 && upperLeftDgnl[(n-1) + (col - row)] == 0) {
                // place the Q and mark the lists for this position as visited
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerLeftDgnl[row + col] = 1;
                upperLeftDgnl[(n-1) + (col - row)] = 1;

                // call recursion on the next Q for the next column
                helperOptimal(board, col + 1, res, leftRow, upperLeftDgnl, lowerLeftDgnl, n);

                // remove the last placed Q and unmark the position while backtracking
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerLeftDgnl[row + col] = 0;
                upperLeftDgnl[(n-1) + (col - row)] = 0;
            }
        }
    }
}
