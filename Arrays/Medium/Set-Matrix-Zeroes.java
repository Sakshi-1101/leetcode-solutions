class Solution {
    public void setZeroes(int[][] matrix) {

        int col0 = 1;

        // traverse the matrixay and mark the rows and columns which need to be set to 0
        for(int i = 0 ; i < matrix.length ; i ++) {
            for(int j = 0 ; j < matrix[0].length ; j ++) {
                if(matrix[i][j] == 0) { // if we find a 0, mark the corresponding row and column index in the first row and first column of the matrix
                    if(j == 0) { // if the 0 is found in the first column, mark col0 as 0
                        col0 = 0;
                    } else { // else mark the jth index of the first row as 0
                         matrix[0][j] = 0;
                    }
                    matrix[i][0] = 0; // mark the ith index of the first column as 0
                }
            }
        }

        for(int i = matrix.length - 1 ; i > 0 ; i --) {
            for(int j = matrix[0].length - 1 ; j > 0 ; j --) {
                if(matrix[i][j] != 0) { // if the element is not already 0 (no need to check for elements which are already 0)
                    if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

       if(matrix[0][0] == 0){
            for(int j = 0 ; j < matrix[0].length ; j ++) {
                matrix[0][j] = 0;
            }
       }

       //now we need to check which all elements in the first column need to be set to 0 and since col0 is keeping track of that, we'll use that.
       if(col0 == 0) {
            for(int i = 0 ; i < matrix.length ; i ++) {
                matrix[i][0] = 0;
            }
       }
        
    }

    // APPROACH 2: Complexity wise same but a little faster
    public void setZeroesApproach2(int[][] matrix) {
        /*
        Approach: Similar to the optimal approach but instead of using the first row and first column as markers, we'll use two boolean variables to keep track if the first row and 
                first column need to be set to 0 or not.
                Then we'll traverse the matrix and mark the rows and columns which need to be set to 0 in the first row and first column of the matrix.
                Finally, we'll traverse the matrix again and wherever we find the row or column index marked in the first row or first column, we'll set all the elements in that row or column to 0.
                Finally, we'll check if the first row and first column need to be set to 0 or not and set them to 0 if needed.
        */
        int m = matrix.length;
        int n = matrix[0].length;

        // boolean variables to keep track if the first row and first column need to be set to 0 or not
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // check if the first row needs to be set to 0 or not
        for(int j=0; j<n ; j++){
            if(matrix[0][j] == 0){
                firstRowZero = true;
                break;
            }
        }

        // check if the first column needs to be set to 0 or not
        for(int i=0; i<m; i++){
            if(matrix[i][0] == 0){
                firstColZero = true;
                break;
            }
        }

        // set the elements for the rest of the matrix except the first row and first column to 0 based on the markers in the first row and first column
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // If column or row marker is zero, make entire column or row zero.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // Finally, we need to check which all elements in the first row need to be set to 0
        if(firstRowZero){
            for(int j=0; j<n; j++){
                matrix[0][j] = 0;
            }
        }

        // Now we need to check which all elements in the first column need to be set to 0
        if(firstColZero){
            for(int i=0; i<m; i++){
                matrix[i][0] = 0;
            }
        }

    }
}