import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
         ArrayList<Integer> ans = new ArrayList<>();

        int n = matrix.length;
        int m = matrix[0].length;

        // defining the boundaries
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = m - 1;

        // The while loop represents the iterations in which we are running 4 loops to traverse the 4 sides of the matrix and then shrinking the boundaries until top > bottom or left > right
        while (top <= bottom && left <= right) {

            // left to right
            for(int i = left ; i <= right ; i ++) {
                ans.add(matrix[top][i]);
            }

            top++;

            // top to bottom
            for(int i = top ; i <= bottom ; i ++) {
                ans.add(matrix[i][right]);
            }

            right--;

            // EDGE CASE: If input is a single row -> then at this point the below loop will traverse the same row backwards
            if(top <= bottom) { // run this only if we have more than 1 row
                // right to left
                for(int i = right ; i >= left ; i --) {
                    ans.add(matrix[bottom][i]);
                }

                bottom --;
            }

            // EDGE CASE: If input is a single column -> then at this point the below loop will traverse the same column backwards
            if(left <= right) {// run this only if we have more than 1 column
                // bottom to top
                for(int i = bottom ; i >= top ; i --){
                    ans.add(matrix[i][left]);
                }

                left++;
            }
        }

        return ans;

    }


    // APPROACH 2
    public List<Integer> spiralOrderApproach2(int[][] matrix) {

        ArrayList<Integer> list = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;

        int minr = 0;
        int maxr = m - 1;

        int minc = 0;
        int maxc = n - 1;

        int count = 1;

        int total = m * n;

        while(count <= total){

            // top wall

            for(int j = minc; j <= maxc && count <= total; j++){
                list.add(matrix[minr][j]);
                count++;
            }
            minr++;
            
            // right wall
            for(int i = minr; i <= maxr && count <= total; i++){
                list.add(matrix[i][maxc]);
                count++;
            }
            maxc--;

            // bottom wall
            for(int j = maxc; j >= minc && count <= total; j--){
                list.add(matrix[maxr][j]);
                count++;
            }
            maxr--;

            // left wall
            for(int i = maxr; i >= minr && count <= total; i--){
                list.add(matrix[i][minc]);
                count++;
            }
            minc++;
            
        }
        return list;
    }
}