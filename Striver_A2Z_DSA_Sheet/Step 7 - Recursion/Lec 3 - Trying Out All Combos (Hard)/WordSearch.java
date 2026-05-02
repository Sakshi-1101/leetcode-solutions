public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        String word = "ABCCED";

        boolean ans = findWord(board, word);

        System.out.println(ans);
    }

    // TC: O(n * m) * O(4^k) => k = length of word/ recursion tree and at each level we have 4 calls so 4^k
    // SC: O(k) 
    // Approach: In this approach, we will iterate over each cell in the board and start DFS if the first letter of the word 
    //           matches the cell's character. The DFS will explore all four possible directions (up, down, left, right) to 
    //           find the next character in the word. We will mark cells as visited by temporarily changing their value and 
    //           restore it during backtracking. If we find the entire word, we return true; otherwise, we continue searching 
    //           until all possibilities are exhausted.
    public static boolean findWord(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;

        // Iterate over all cells
        for(int i = 0 ; i < r ; i ++) {
            for(int j = 0 ; j < c ; j ++) {
                // Start DFS if first letter matches
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean dfs(char[][] board, String word, int i, int j, int idx) {
        // If word is completely found
        if(idx == word.length()) {
            return true;
        }

        // Boundary and mismatch check
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] != word.charAt(idx)) {
            return false;
        }

        // Store character and mark visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Explore in all four directions
        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
                        dfs(board, word, i, j + 1, idx + 1) ||
                        dfs(board, word, i - 1, j, idx + 1) ||
                        dfs(board, word, i, j - 1, idx + 1);

        // Restore character while backtracking
        board[i][j] = temp;

        return found;
    }
    
}
