1public class Solution {
2    public boolean searchMatrix(int[][] matrix, int target) {
3        int n = matrix.length;
4        int m = matrix[0].length;
5
6       // set the binary search arrange as per the 2D matrix when flattened into hypothetical 1D array
7        int lo = 0;
8        int hi = (n * m) - 1;
9
10        // binary search on the hypothetical 1D array
11        while(lo <= hi) {
12            // 1D array coordinate
13            int mid = (lo + hi) / 2;
14
15            // find the corresponding 2D matrix coordinate
16            int row = mid / m;
17            int col = mid % m;
18
19            if(matrix[row][col] == target) {
20                return true;
21            } else if(matrix[row][col] < target) {
22                lo = mid + 1;
23            } else { 
24                hi = mid - 1;
25            }
26        }
27
28        // if element not found
29        return false;
30    }
31}