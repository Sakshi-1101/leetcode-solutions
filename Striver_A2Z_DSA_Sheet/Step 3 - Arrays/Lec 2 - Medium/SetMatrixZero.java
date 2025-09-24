public class SetMatrixZero {

    public static void main(String[] args) {
        int[][] arr = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};

        int[][] ansBrute = setMatrixBrute(arr);
        int[][] ansBetter = setMatrixBetter(arr);
        int[][] ansOptimal = setMatrixOptimal(arr);

        System.out.println("Brute Approach");

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            for(int j = 0 ; j < ansBrute[0].length ; j ++) {
                System.out.print(ansBrute[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Better Approach");

        for(int i = 0 ; i < ansBetter.length ; i ++) {
            for(int j = 0 ; j < ansBetter[0].length ; j ++) {
                System.out.print(ansBetter[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Optimal Approach");

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            for(int j = 0 ; j < ansOptimal[0].length ; j ++) {
                System.out.print(ansOptimal[i][j] + " ");
            }
            System.out.println();
        }
    }

    // TC: O((N*M)*(N+M)) + O(N*M) ~ O(N^3)
    /*
     * O((N*M)*(N+M))
        * O(N*M) = first array traversal
        * (N+M) = traversing row and col for only for indexes 0 and setting it to -1
        * Hence for each index having 0 value in 2d array we are doing O(N+M) work
     * O(N*M) = traversing the array again and marking -1 values as 0
     */
    // SC: O(1)
    // Approach: Traverse the matrix and whenever we find a 0, we'll mark the entire row and column with -1. After that, we'll traverse the matrix again and wherever we find -1, we'll mark it with 0.
    public static int[][] setMatrixBrute(int[][] arr) {
        // traverse the array and whenever we find a 0, we'll mark the entire row and column with -1
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                /*
                 * we are marking with -1 bcoz if we mark with 0, we won't be able to differentiate between the original 0s and the 0s which we have marked.
                 * Due to this, we might end up marking the entire matrix with 0s. Hence, we use -1 as a marker.
                 * NOTE: In case the matrix contains -1 as an original element, we can use any other marker value which is not present in the matrix.
                 */
                if(arr[i][j] == 0) {
                    markRow(arr, i); // mark the entire row with -1
                    markColumn(arr, j); // mark the entire column with -1
                }
            }
        }

        // now traverse the array again and wherever we find -1, we'll mark it with 0
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                if(arr[i][j] == -1) {
                    arr[i][j] = 0;
                }
            }
        }

        return arr;
    }

    // mark the entire row with -1
    public static void markRow(int[][] arr, int idx) {
        for(int i = 0 ; i < arr[0].length ; i ++) {
            // only mark the element with -1 if it is not already 0 or else it might mark original 0s with -1
            if(arr[idx][i] != 0) {
                arr[idx][i] = -1;
            }
        }
    }

    // mark the entire column with -1
    public static void markColumn(int[][] arr, int idx) {
        for(int i = 0 ; i < arr.length ; i ++) {
            // only mark the element with -1 if it is not already 0 or else it might mark original 0s with -1
            if(arr[i][idx] != 0) {
                arr[i][idx] = -1;
            }
        }
    }


    // TC: O(N*M) + O(N*M) = O(2*(N*M)) ~ O(N^2)
    // SC: O(N) + O(M)
    // Approach: Create two arrays one for rows and one for columns and mark the rows and columns which need to be set to 0. 
    //           Then traverse the array again and wherever we find the row or column index marked in the row or col arrays, 
    //           we'll set all the elements in that row or column to 0.
    public static int[][] setMatrixBetter(int[][] arr) {
        // create two arrays one for rows and one for columns and mark the rows and columns which need to be set to 0
        boolean[] row = new boolean[arr.length];
        boolean[] col = new boolean[arr[0].length];

        // traverse the array and mark the rows and columns which need to be set to 0
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                if(arr[i][j] == 0) { // if we find a 0, mark the corresponding row and column index in the row and col arrays
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // now traverse the array again and wherever we find the row or column index marked in the row or col arrays, we'll set all the elements in that row or column to 0
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                if(row[i] == true || col[j] == true) {
                    arr[i][j] = 0;
                }
            }
        }

        return arr;
        
    }

    // TC: O(2*(N*M))
    // SC: O(1)
    // Approach: The approach is similar to Better Approach. In this instead of creating two separate arrays we'll use the first row and first column of 
    //           the matrix itself to mark the rows and columns which need to be set to 0. 
    public static int[][] setMatrixOptimal(int[][] arr) {
        // Instead of using two extra arrays, we can use the first row and first column of the matrix itself to mark the rows and columns which need to be set to 0.
        // i.e. here int[] row = arr[0][0...m-1] and int[] col = arr[0...n-1][0]

        // arr[0][0] will be a common intersection index for both row and column. Hence, we need to take care of it separately.
        int col0 = 1; // so instead of using arr[0][0] for keeping track of column 0, we will use a separate variable col0 to mark if the first column needs to be set to 0 or not.

        // traverse the array and mark the rows and columns which need to be set to 0
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                if(arr[i][j] == 0) { // if we find a 0, mark the corresponding row and column index in the first row and first column of the matrix
                    if(j == 0) { // if the 0 is found in the first column, mark col0 as 0
                        col0 = 0;
                    } else { // else mark the jth index of the first row as 0
                         arr[0][j] = 0;
                    }
                    arr[i][0] = 0; // mark the ith index of the first column as 0
                }
            }
        }

        /*
         * Now traverse the array again from bottom right corner to top left corner (excluding the first row and first column) and 
         * wherever we find the row or column index marked in the first row or first column, we'll set all the elements in that row or column to 0.
         * NOTE: 
         * 1. We cannot start traversing from 0th column and 0th row because if we do that and change the values it will impact the entire matrix and 
         *    we might end up marking the entire first row and first column as 0 which is incorrect due to which rest of the matrix will also become 0.
         * 2. Also since they are acting as markers, we need to traverse them at the end only.
         */
        for(int i = arr.length - 1 ; i > 0 ; i --) {
            for(int j = arr[0].length - 1 ; j > 0 ; j --) {
                if(arr[i][j] != 0) { // if the element is not already 0 (no need to check for elements which are already 0)
                    if(arr[i][0] == 0 || arr[0][j] == 0) {
                        arr[i][j] = 0;
                    }
                }
            }
        }

        // finally, we need to check which all elements in the first row need to be set to 0
        // For that we know if arr[0][0] is 0, then all elements in the first row need to be set to 0
        /*
         * NOTE: We are checking the row before the column because if we check the column first and suppose initially arr[0][j] = 1, arr[0][0] = 1 and col0 = 0, and 
         *       if we do the column operation first, then arr[0][0] will become 0 and then when we do the row operation, we will end up marking the entire 
         *       first row as 0 which is incorrect.
         *       Therefore, if we do row operation first in the same scenario, arr[0][j] = 1 will remain unchanged bcoz as of now arr[0][0] = 1. It is not changed yet.
         */
       if(arr[0][0] == 0){
            for(int j = 0 ; j < arr[0].length ; j ++) {
                arr[0][j] = 0;
            }
       }

       //now we need to check which all elements in the first column need to be set to 0 and since col0 is keeping track of that, we'll use that.
       if(col0 == 0) {
            for(int i = 0 ; i < arr.length ; i ++) {
                arr[i][0] = 0;
            }
       }

        return arr;
        
    }

    
}
