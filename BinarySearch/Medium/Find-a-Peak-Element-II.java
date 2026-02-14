1class Solution {
2    public int[] findPeakGrid(int[][] mat) {
3        // binary search on columns
4        int lo = 0;
5        int hi = mat[0].length - 1;
6
7        while(lo <= hi) {
8            int mid = (lo + hi) / 2;
9
10            // find the row having largest element in the mid column
11            int row = findMaxElementRow(mat, mid);
12
13            // find the adjacent neighbours of the element at mat[row][mid] 
14            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
15            int right = mid + 1 < mat[0].length ? mat[row][mid + 1] :  -1;
16
17            // peak element found
18            if(mat[row][mid] > left && mat[row][mid] > right) {
19                return new int[]{row, mid};
20            } else if(left > mat[row][mid]) { // peak element might be in the left half
21                hi = mid - 1; 
22            } else { 
23                lo = mid + 1; 
24            }
25        }
26
27        return new int[]{-1, -1};
28    }
29
30    // helper function to find the row no. having the largest element in the given column
31    private static int findMaxElementRow(int[][] mat, int col) {
32        int maxEle = -1;
33        int maxRow = -1;
34
35        for(int row = 0 ; row < mat.length ; row++) {
36            if(mat[row][col] > maxEle) {
37                maxEle = mat[row][col];
38                maxRow = row;
39            }
40        }
41
42        return maxRow;
43    }
44}