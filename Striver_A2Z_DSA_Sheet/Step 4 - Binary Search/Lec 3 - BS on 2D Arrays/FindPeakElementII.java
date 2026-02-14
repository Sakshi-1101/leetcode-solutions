public class FindPeakElementII {

    public static void main(String[] args) {
        int[][] mat = {{5, 10, 8}, 
                       {4, 25, 7}, 
                       {3, 9, 6}};
        int n = 3;
        int m = 3;

        /*
            NOTE:
            Brute and Better appraoch is similar to Ques: Find Peak Element in 1D Array.
         */
        int[] ans = findPeakOptimal(mat, n, m);

        for(int i = 0 ; i < ans.length ; i ++) {
            System.out.print(ans[i] + " ");
        }
    }

    // TC: O(n * log (base 2) m) -> binary search on m columns + scanning each column vertically to find max element
    // SC: O(1)
    // Approach: In this approach, we will apply binary search on columns. We will find the row having the largest element in the 
    //           mid column and then compare it with its adjacent neighbours. If the mid element is greater than both of its 
    //           adjacent neighbours, then we have found our peak element. Otherwise, if the left neighbour is greater than the 
    //           mid element, then there must be a peak element in the left half of the matrix. Else, there must be a peak element 
    //           in the right half of the matrix.
    public static int[] findPeakOptimal(int[][] mat, int n, int m) {
        // binary search on columns
        int lo = 0;
        int hi = m - 1;

        while(lo <= hi) {
            int mid = (lo + hi) / 2; // mid column

            // find the row having largest element in the mid column
            int row = findMaxElementRow(mat, mid);

            // find the adjacent neighbours of the element at mat[row][mid] 
            /*
                NOTE: We won't check for top and bottom neightbour bcoz since we are comparing the max element in the given 
                      column. Therefore, it will always be greater than top and bottom elements.
             */
            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
            int right = mid + 1 < m ? mat[row][mid + 1] : -1;

            // peak element found
            if(mat[row][mid] > left && mat[row][mid] > right) {
                return new int[]{row, mid};
            } else if(left > mat[row][mid]) { // peak element might be in the left half
                hi = mid - 1; // discard right half
            } else { // if(right > mat[row][mid])
                lo = mid + 1; // discard left, peak element might be in the right half
            }
        }

        // if peak element not found
        return new int[]{-1, -1};
    }

    // helper function to find the row no. having the largest element in the given column
    private static int findMaxElementRow(int[][] mat, int col) {
        int maxEle = -1;
        int maxRow = -1;

        for(int row = 0 ; row < mat.length ; row++) {
            if(mat[row][col] > maxEle) {
                maxEle = mat[row][col];
                maxRow = row;
            }
        }

        return maxRow;

    }
    
}
