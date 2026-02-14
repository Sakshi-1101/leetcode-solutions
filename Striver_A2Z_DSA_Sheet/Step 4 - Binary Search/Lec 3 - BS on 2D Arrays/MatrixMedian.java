
import java.util.*;

public class MatrixMedian {

    public static void main(String[] args) {
        int[][] mat = {{1, 4, 9}, 
                       {2, 5, 6}, 
                       {3, 8, 7}};
        int n = 3;
        int m = 3;

        int ansBrute = findMedianBrute(mat, n, m);
        int ansOptimal = findMedianOptimal(mat, n, m);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);

    }

    // TC: O(n *m) + O((n * m) log (n * m)) ~ O(2 * (n * m)) -> O(n * m) for traversing matrix + O((n * m) log (n * m)) for sorting
    // SC: O(n * m)
    // Approach: In this approach we will store all the elements of matrix in a 1D array and then sort the array and return the 
    //           mid element of array as median.
    public static int findMedianBrute(int[][] mat, int n, int m) {
        int[] arr = new int[n * m];
        int k = 0;

        // store all the elements of matrix in a 1D array
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < m ; j ++) {
                arr[k] = mat[i][j];
                k++;
            }
        }

        // sort the array
        Arrays.sort(arr);

        // since (n*m) is odd i.e. total no. of elements is odd so the median will be mid element of array
        return arr[(n * m) / 2];
    }

    // TC: O(log (base 2) 10^9) * O(n * log (base 2) m)
        /*
            1. log (base 2) 10^9
            => In constraints it's given, samllest value in matrix can be as small as 1 and largest value can be as large as 10^9. 
               At max, search space can be of size 10^9. In worst case, we'll be doing binary search on search space of size 10^9.
            2. O(n * log (base 2) m)
            => finding upperbound using binary search on each row
        */
    // SC: O(1)
    // Approach: In this approach we will apply binary search on the search space [smallest value in matrix -> largest value in 
    //           matrix]. For each mid value, we will count the no. of elements <= mid and check if it's > than (n * m) / 2 or 
    //           not. If it's > than (n * m) / 2 then we need to check left half bcoz we need to find smaller value that can be 
    //           median. If it's <= than (n * m) / 2 then we need to check right half bcoz we need > than (n * m) / 2 elements to 
    //           be <= median.
    public static int findMedianOptimal(int[][] mat, int n, int m) {
        // binary search space -> [smallest value -> largest value in mat]
        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;

        /*
            lo = smallest value in 0th column
            hi = largest value in (m-1)th column
         */

        for(int i = 0 ; i < n ; i ++) {
            lo = Math.min(lo, mat[i][0]);
            hi = Math.max(hi, mat[i][m - 1]);
        }

        // no. of elements required that are <= median is > than (n * m) / 2
        int numEleRequired = (n * m) / 2;

        while(lo <= hi) {
            // mid represents the potential median element
            int mid = (lo + hi) / 2;

            // Count number of elements <= mid
            int eleCount = countElements(mat, n, m, mid);

            // if no. of elements <= mid is <= numEleRequired then we need to check right half bcoz we need > than numEleRequired elements to be <= median. 
            if(eleCount <= numEleRequired){
                lo = mid + 1; // discard left, check in right half
            } else { // if eleCount > numEleRequired, we need to check left half bcoz we need to find smaller value that can be median
                hi = mid - 1;
            }
        }
        
        // at the end of loop, lo will be pointing to the median element
        return lo;

    }

    // helper function that will count the no. of elements <= val in the matrix
    private static int countElements(int[][] mat, int n, int m, int val) {
        int count = 0;

        for(int i = 0 ; i < n ; i ++) {
            // for each row, we'll find the no. of elements <= val using upperBound function and add it to count
            count += upperBound(mat, i, m, val);
        }

        return count;
    }

    // helper function that will find the no. of elements <= val
    private static int upperBound(int[][] mat, int row, int col, int val) {
        /* 
            Binary search on each row.
            We'll find the upperbound(val) bcoz it'll return the first occurence of no. > val. Let's say idx = x
            So no. of elements <= val => [0 -> (x-1)]
        */
        int lo = 0;
        int hi = col - 1;
        int ans = col;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;
 
            if(mat[row][mid] > val) {
                ans = mid; // possible value
                hi = mid - 1; // look for more smaller value on left
            } else {
                lo = mid + 1; // discard left, check in right half
            }
            
        }

        return ans;
    }
    
}
