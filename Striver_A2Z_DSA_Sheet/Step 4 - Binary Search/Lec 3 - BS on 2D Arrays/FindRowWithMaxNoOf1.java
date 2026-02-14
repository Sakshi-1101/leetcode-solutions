public class FindRowWithMaxNoOf1 {

    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        int[][] mat = {{1, 1, 1}, 
                       {0, 0, 1}, 
                       {0, 0, 0}};

        int ansBrute = findRowBrute(mat, n, m);
        int ansOptimal = findRowOptimal(mat, n, m);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(n * m)
    // SC: O(1)
    // Approach: In this approach, we will traverse the matrix and count the number of 1s in each row. We will keep track of the 
    //           maximum count of 1s and the index of the row with the maximum count. If we find a row with a higher count of 1s, 
    //           we will update our maximum count and index. Finally, we will return the index of the row with the maximum number 
    //           of 1s. If there are no 1s in the matrix, we will return -1.
    public static int findRowBrute(int[][] mat, int n, int m) {
        int maxCount = 0; // max count of 1s
        int idx = -1; // row idx with max 1s, if no 1s return -1

        // traverse the matrix
        for(int i = 0 ; i < n ; i ++) {
            int countOne = 0; // keeps track of no. of 1s in each row
            for(int j = 0 ; j < m ; j ++)  {
                if(mat[i][j] == 1) {
                    countOne++;
                }
            }

            // if the current row 1s count is greater than maxCount update idx and maxCount
            if(countOne > maxCount) {
                idx = i;
                maxCount = countOne;
            }
        }

        return idx;
    }

    // TC: O(n * log (base 2) m) -> O(log (base 2) m) for binary search in each row
    // SC: O(1)
    // Approach: In this approach, we will traverse the matrix and for each row, we will use binary search to find the first 
    //           occurrence index of 1. The count of 1s in the row can be calculated as m - first occurrence index. We will keep 
    //           track of the maximum count of 1s and the index of the row with the maximum count. If we find a row with a higher 
    //           count of 1s, we will update our maximum count and index. Finally, we will return the index of the row with the 
    //           maximum number of 1s. If there are no 1s in the matrix, we will return -1.
    public static int findRowOptimal(int[][] mat, int n, int m) {
        int maxCount = 0; // max count of 1s
        int idx = -1; // row idx with max 1s, if no 1s return -1

        // traverse the matrix
        for(int i = 0 ; i < n ; i ++) {
            // find the first occurrence idx of 1
            int lowerBound = findLowerBound(mat, i, m, 1);            
            int countOne = m - lowerBound; // calculate the count of 1 in ith row

            // if the current row 1s count is greater than maxCount update idx and maxCount
            if(countOne > maxCount) {
                idx = i;
                maxCount = countOne;
            }
        }

        return idx;
    }

    // Helper function to find the first occurrence index or lower bound of a value in a sorted array using binary search
    private static int findLowerBound(int[][] mat, int row , int m, int val) {
        int lo = 0;
        int hi = m - 1;

        // first occurrence idx of 1, default to m if no such index is found
        int fidx = m;

        // binary search
        while(lo <= hi) {
            int mid = (lo + hi) / 2;

            if(mat[row][mid] >= val) { 
                fidx = mid; // possible first occur. idx
                hi = mid - 1; // try searching for smaller value in the left
            } else {
                lo = mid + 1; // discard left since value is smaller, look in the right
            }
        }

        return fidx;

    }
}
