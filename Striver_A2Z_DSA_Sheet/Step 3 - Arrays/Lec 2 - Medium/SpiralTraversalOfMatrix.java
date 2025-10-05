import java.util.ArrayList;

public class SpiralTraversalOfMatrix {

    public static void main(String[] args) {
        int[][] arr = { { 1, 2, 3, 4 },
		                { 5, 6, 7, 8 },
		                { 9, 10, 11, 12 },
	                    { 13, 14, 15, 16 }
                    };

        ArrayList<Integer> ans = printSpiral(arr);

        for(int i = 0 ; i < ans.size() ; i ++) {
                System.out.print(ans.get(i) + " ");
        }
    }

    // TC: O(N*M)
    // SC: O(N*M)
    // Approach: We will maintain 4 variables top, bottom, left, right to keep track of the boundaries of the matrix. 
    //           We will traverse the matrix in a spiral manner by updating these boundaries after traversing each side.
    //           We will also handle edge cases where there is a single row or a single column left to avoid duplicate traversals.
    public static ArrayList<Integer> printSpiral(int[][] arr) {
        ArrayList<Integer> ans = new ArrayList<>();

        int n = arr.length;
        int m = arr[0].length;

        // defining the boundaries
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = m - 1;

        // The while loop represents the iterations in which we are running 4 loops to traverse the 4 sides of the matrix and then shrinking the boundaries until top > bottom or left > right
        while (top <= bottom && left <= right) {

            // left to right
            for(int i = left ; i <= right ; i ++) {
                ans.add(arr[top][i]);
            }

            top++;

            // top to bottom
            for(int i = top ; i <= bottom ; i ++) {
                ans.add(arr[i][right]);
            }

            right--;

            // EDGE CASE: If input is a single row -> then at this point the below loop will traverse the same row backwards
            if(top <= bottom) { // run this only if we have more than 1 row
                // right to left
                for(int i = right ; i >= left ; i --) {
                    ans.add(arr[bottom][i]);
                }

                bottom --;
            }

            // EDGE CASE: If input is a single column -> then at this point the below loop will traverse the same column backwards
            if(left <= right) {// run this only if we have more than 1 column
                // bottom to top
                for(int i = bottom ; i >= top ; i --){
                    ans.add(arr[i][left]);
                }

                left++;
            }
        }

        return ans;

    }
    
}
