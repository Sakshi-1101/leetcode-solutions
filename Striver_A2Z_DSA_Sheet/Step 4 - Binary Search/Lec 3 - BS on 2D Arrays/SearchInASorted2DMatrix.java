public class SearchInASorted2DMatrix {

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int[][] mat = {{1, 2, 3, 4}, 
                       {5, 6, 7, 8}, 
                       {9, 10, 11, 12}};
        int target = 8;

        boolean ansBrute = searchBrute(mat, n, m, target);
        boolean ansBetter = searchBetter(mat, n, m, target);
        boolean ansOptimal = searchOptimal(mat, n, m, target);
        boolean ansOptimalMyApp = searchOptimalMyApproach(mat, n, m, target);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
        System.out.println(ansOptimalMyApp);
    }

    // TC: O(n * m)
    // SC: O(1)
    // Approach: In this approach, we will traverse the entire matrix and check if the target element is present in any of the cells. 
    public static boolean searchBrute(int[][] mat, int n, int m, int target) {
        // traverse the matrix
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < m ; j ++) {
                if(mat[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    // TC: O(n * log (base 2) m)
    // sc: O(1)
    // Approach: In this approach, we will traverse each row of the matrix and check if the target element lies in the range of 
    //           the first and last element of the row. If it does, we will perform a binary search on that row to find the target 
    //           element.
    public static boolean searchBetter(int[][] mat, int n, int m, int target) {
        // traverse each row
        for(int i = 0 ; i < n ; i ++) {
            // for each sorted row, check if target lies in this row
            if(mat[i][0] <= target && target <= mat[i][m - 1]) {
                // search for the element in the row
                return findElement(mat, i, m, target);
            }
        }

        // if element not found
        return false;
    }

    // helper function to find the element in the given row using binary search
    private static boolean findElement(int[][] mat, int row, int m, int tar) {
        int lo = 0;
        int hi = m - 1;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;

            // if element found at mid
            if(mat[row][mid] == tar) {
                return true;
            } else if(mat[row][mid] > tar) { // if element is smaller, search in the left half
                hi = mid - 1;
            } else { // if element is greater, search in the right half
                lo = mid + 1;
            }
        }

        // if element not found
        return false;
    }

    // TC: O(log (base 2) n) * O(log (base 2) m) -> binary search on the entire matrix
    // SC: O(1)
    // Approach: In this, we'll perform binary search on the rows to find the potential row that may contain the target element. 
    //           Once we find the potential row, we will perform a binary search on that row to find the target element.
    public static boolean searchOptimalMyApproach(int[][] mat, int n, int m, int target) {
        // edge cases
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return false;
        }

        // binary search on rows
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            //for each row, check if target lies in this row
            if (mat[mid][0] <= target && target <= mat[mid][m - 1]) {
                return findElement(mat, mid, m, target); // binary search for each row
            } else if (target < mat[mid][0]) { // if the target is smaller than the first element of the current row, check for rows above
                hi = mid - 1;
            } else { // if the target is greater than the last element of the current row, check for rows below
                lo = mid + 1;
            }
        }

        // if no element found
        return false;

    }
    
    // TC: O(log (base 2) (n * m))
    // SC: O(1)
    // Approach: In this approach, we'll perform binary search on entire matrix by treating it as a hypothetical 1D array. We will 
    //           calculate the corresponding 2D matrix coordinates from the 1D array index and check if the target element is 
    //           present at that coordinate. If it is, we will return true. If the target element is smaller than the element at 
    //           the current coordinate, we will search in the lower part of the matrix. If the target element is greater than the 
    //           element at the current coordinate, we will search in the upper part of the matrix.
    public static boolean searchOptimal(int[][] mat, int n, int m, int target) {
        // set the binary search arrange as per the 2D matrix when flattened into hypothetical 1D array
        int lo = 0;
        int hi = (n * m) - 1;

        // binary search on the hypothetical 1D array
        while(lo <= hi) {
            // 1D array coordinate
            int mid = (lo + hi) / 2;

            // find the corresponding 2D matrix coordinate
            int row = mid / m;
            int col = mid % m;

            // if element found, return
            if(mat[row][col] == target) {
                return true;
            } else if(mat[row][col] < target) { // if element is smaller than target, search in the lower part of matrix
                lo = mid + 1;
            } else { // if element is greater than target, search in the upper part of matrix
                hi = mid - 1;
            }
        }

        // if element not found
        return false;

    }
}
