import java.util.Stack;

public class MaximumRectangleArea {

    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1, 0, 0},
                       {1, 0, 1, 1, 1},
                       {1, 1, 1, 1, 1},
                       {1, 0, 0, 1, 0}};
        int n = 4;
        int m = 5;

        int ans = getAreaInMatrix(mat, n, m);
        System.out.println(ans);
    }

    // TC: O(N * (M + N)) -> O(n*m) is for matrix traversal and during each column traversal we are doing O(n) work to find the 
    //                       max area rectangle in a row
    // SC: O(N) -> for stack
    // Approach: In this approach, we will traverse the matrix and for each row, we will maintain a histogram of heights of bars. 
    //           Then we will find the largest rectangle area in the histogram for each row and return the maximum area rectangle 
    //           in the entire matrix. The height[] array will be updated for each row by adding the current row's value to the 
    //           previous row's height so that we can maintain the histogram for like for row 0, then row 0,1 and then row 0,1,2 
    //           and so on. For each row, we will find the largest rectangle area in the histogram using the getAreaInRow() 
    //           function.
    public static int getAreaInMatrix(int[][] mat, int n, int m) {
        int maxArea = 0;

        // in this we'll maintain the histogram for each row
        int[] height = new int[m];

        // traverse the matrix
        for(int i = 0 ; i < n ; i ++) {
            // for each row, traverse the column and update the height[] array to create the histogram for the rows
            for(int j = 0 ; j < m ; j ++) {
                height[j] = height[j] + mat[i][j];
            }

            // find the max area rectangle for the current rows considered in histogram
            int maxAreaInRow = getAreaInRow(height, m);
            maxArea = Math.max(maxArea, maxAreaInRow); // find the max area rectangle in entire matrix
        }

        return maxArea;
    }

    // function to find the largest rectangle area in a histogram
    public static int getAreaInRow(int[] arr, int n) {
        Stack<Integer> st = new Stack<>(); // store indexes
        int maxArea = 0;

        // traverse all the bars in histogram while maintaining the pse in a increasing order in the stack.
        for(int i = 0 ; i < n ; i ++) {

            // as soon as we find the nse for the top of the stack element, we'll pop all the elements from the stack and 
            // calculate the area for each of them till the point we get the pse for the current element (considered as nse)
            // on the top of stack.
            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
                int eleIdx = st.pop();
                int nse = i; // curr element is the nse for the popped element
                // if stack is empty, then there is no pse for the popped element, else the top of stack after popping is the 
                // pse for the popped element
                int pse = st.isEmpty() ? -1 : st.peek(); 

                // calculate the area for the popped element and update the maxArea
                int area = arr[eleIdx] * (nse - pse - 1);
                maxArea = Math.max(area, maxArea); // update the max area accordingly
            }

            // push the current index to stack assuming that it could be the pse for any future element
            st.push(i);
        }

        // if elements left in stack (for which area is not calculated yet)
        while(!st.isEmpty()) {
            int eleIdx = st.pop();
            int nse = n ; // since remaining elements don't have nse
            int pse = st.isEmpty() ? -1 : st.peek();

            int area = arr[eleIdx] * (nse - pse - 1);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }
}
