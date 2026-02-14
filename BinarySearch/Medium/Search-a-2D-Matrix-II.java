1class Solution {
2    public boolean searchMatrix(int[][] matrix, int target) {
3         // initialise the starting point
4        int row = 0;
5        int col = matrix[0].length - 1;
6
7        // traverse the matrix until we find the target element or we go out of bounds
8        while(row < matrix.length && col >= 0) {
9            // if element found at the current position
10            if(matrix[row][col] == target) {
11                return true;
12            } else if(matrix[row][col] > target) { // if element is greater, move left
13                col--;
14            } else { // if element is smaller, move down
15                row++;
16            }
17        }
18
19        // if element not found
20        return false;
21        
22    }
23}