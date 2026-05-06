import java.util.*;

public class RatInaMaze {

    public static void main(String[] args) {
        int n = 4;
        int[][] maze = {{1, 0, 0, 0},
                        {1, 1, 0, 1},
                        {1, 1, 0, 0},
                        {0, 1, 1, 1}};

        List<String> res = new ArrayList<>();
        String path = "";

        int[][] visited = new int[n][n];

        // find path only if the starting point (0,0) cell = 1
        if(maze[0][0] == 1) {
            findPaths(maze, 0, 0, n, res, path, visited);
        }

        System.out.println(res);
    }

    // TC: O(4^(N^2)) -> worst case: visit all cells once in a path where each cell has 4 choices
    // SC: O(n^2 + k*(n^2)) -> n^2 for recursion stack space and k*(n^2) where k = total paths and each path length = n^2 
    // Approach: In this approach, we will use backtracking to find all possible paths from the top-left corner to the 
    //           bottom-right corner of the maze. We will explore all 4 possible directions (up, right, down, left) from each 
    //           cell and keep track of the visited cells to avoid cycles. When we reach the destination cell, we will add the 
    //           current path to the result list. After exploring all directions from a cell, we will backtrack by unmarking the 
    //           cell as visited before returning to explore other paths.
    private static void findPaths(int[][] maze, int row, int col, int n, List<String> res, String path, int[][] visited) {
        // base case: If destination reached, store the path
        if(row == n - 1 && col == n - 1) {
            res.add(path); // add curr path to the list
            return;
        }

        // check for valid move: cell = 1, cell within the maze and cell not visited
        if(row < 0 || col < 0 || row >= n || col >= n || maze[row][col] == 0 || visited[row][col] == 1) {
            return;
        }

        // mark the current cell as visited
        visited[row][col] = 1;

        // call recursion in all 4 directions for the current cell

        // up
        findPaths(maze, row - 1, col, n, res, path + "U", visited);

        // right
        findPaths(maze, row, col + 1, n, res, path + "R", visited);

        // down
        findPaths(maze, row + 1, col, n, res, path + "D", visited);

        // left
        findPaths(maze, row, col - 1, n, res, path + "L", visited);

        // unmark the cell as visited while backtracking
        visited[row][col] = 0;

    }

    
    
}
