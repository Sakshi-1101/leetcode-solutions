public class SearchInA2DMatrixII {

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] mat = {{1, 4, 7, 11}, 
                       {2, 5, 8, 12}, 
                       {3, 6, 9, 16},
                       {10, 13, 14, 19}};
        int target = 13;

        int[] ansBrute = searchBrute(mat, n, m, target);
        int[] ansBetter = searchBetter(mat, n, m, target);
        int[] ansOptimal = searchOptimal(mat, n, m, target);

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();

        for(int i = 0 ; i < ansBetter.length ; i ++) {
            System.out.print(ansBetter[i] + " ");
        }

        System.out.println();

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i] + " ");
        }
    }

    // TC: O(n * m)
    // SC: O(1)
    // Approach: In this approach, we will traverse the entire matrix and check if the target element is present in any of the cells. 
    public static int[] searchBrute(int[][] mat, int n, int m, int target) {
        // traverse the matrix
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < m ; j ++) {
                if(mat[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    // TC: O(n * log (base 2) m)
    // sc: O(1)
    // Approach: In this approach, we will traverse each row of the matrix and check if the target element lies in the range of 
    //           the first and last element of the row. If it does, we will perform a binary search on that row to find the target 
    //           element.
    public static int[] searchBetter(int[][] mat, int n, int m, int target) {
        // traverse each row
        for(int i = 0 ; i < n ; i ++) {
            // search for the element in the row
            int[] coordinate = findElementBinarySearch(mat, i, m, target);

            if(coordinate[0] != -1){
                return coordinate;
            }
        }

        // if element not found
        return new int[]{-1, -1};
    }

    // helper function to find the element in the given row using binary search
    private static int[] findElementBinarySearch(int[][] mat, int row, int m, int tar) {
        int lo = 0;
        int hi = m - 1;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;

            // if element found at mid
            if(mat[row][mid] == tar) {
                return new int[]{row, mid};
            } else if(mat[row][mid] > tar) { // if element is smaller, search in the left half
                hi = mid - 1;
            } else { // if element is greater, search in the right half
                lo = mid + 1;
            }
        }

        // if element not found
        return new int[]{-1, -1};
    }


    // TC: O(n + m)
    // SC: O(1)
    // Approach: In this approach, we will start from the top right corner of the matrix and compare the current element with the 
    //           target element. If the current element is equal to the target element, we have found the target and we can 
    //           return its coordinates. If the current element is greater than the target element, we can move left to find a 
    //           smaller element. If the current element is smaller than the target element, we can move down to find a larger 
    //           element. We will continue this process until we find the target element or we go out of bounds of the matrix.
    public static int[] searchOptimal(int[][] mat, int n, int m, int target) {
        // initialise the starting point
        int row = 0;
        int col = m - 1;

        // traverse the matrix until we find the target element or we go out of bounds
        while(row < n && col >= 0) {
            // if element found at the current position
            if(mat[row][col] == target) {
                return new int[]{row, col};
            } else if(mat[row][col] > target) { // if element is greater, move left
                col--;
            } else { // if element is smaller, move down
                row++;
            }
        }

        // if element not found
        return new int[]{-1, -1};

    }


    
}
