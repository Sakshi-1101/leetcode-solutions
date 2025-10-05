public class RotateMatrixClockwise {
    
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};

        int[][] ansBrute = rotateMatrixBrute(arr);
        int[][] ansOptimal = rotateMatrixOptimal(arr);

        System.out.println("Brute Apporach:");

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            for(int j = 0 ; j < ansBrute[0].length ; j ++) {
                System.out.print(ansBrute[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Optimal Apporach:");

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            for(int j = 0 ; j < ansOptimal[0].length ; j ++) {
                System.out.print(ansOptimal[i][j] + " ");
            }
            System.out.println();
        }
    }

    // TC: O(N^2)
    // SC: O(N^2) 
    // Approach: In this we'll pick ith row and place it in (N-i-1)th column of the new array.
    public static int[][] rotateMatrixBrute(int[][] arr) {
        int[][] ans = new int[arr.length][arr[0].length];

        int idx = arr.length - 1; // to handle the index value of the new array

        //take the first row of the matrix and put it in the last column of the dummy matrix, take the second row of the matrix, and put it in the second last column of the matrix and so on.
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length ; j ++) {
                ans[j][idx] = arr[i][j];
            }

            idx--;
        }

        /*
         * OR in this we don't have to create a new variable idx. Instead we can directly use arr.length - i - 1 to handle the index value.
         */
        // for(int i = 0 ; i < arr.length ; i ++) {
        //     for(int j = 0 ; j < arr[0].length ; j ++) {
        //         ans[j][arr.length - i - 1] = arr[i][j];
        //     }
        // }

        return ans;

    }

    // TC: O(N^2) + O(N^2) = O(2 * N^2)
    // SC: O(1)
    // Approach: By observation, we see that the first column of the original matrix is the reverse of the first row of the rotated matrix.
    //           Hence, we'll first transpose the matrix and then reverse each row of the transposed matrix to get the rotated matrix.
    public static int[][] rotateMatrixOptimal(int[][] arr) {

        // transpose the matrix i.e. changing columns to rows and rows to columns
        for(int i = 0 ; i < arr.length ; i ++) {
            /*
             * NOTE:
             * If you loop j from 0 to n-1, then you'll end up swapping each element twice and you'll get the original matrix.
             * Therefore, to avoid that, we start the loop for j from i bcoz the elements before the diagonal are already swapped.
             */
            for(int j = i ; j < arr[0].length ; j ++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        // reverse each row of the transposed matrix
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = 0 ; j < arr[0].length / 2 ; j ++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][arr.length - j - 1];
                arr[i][arr.length - j - 1] = temp;
            }
        }

        return arr;
    }
}
