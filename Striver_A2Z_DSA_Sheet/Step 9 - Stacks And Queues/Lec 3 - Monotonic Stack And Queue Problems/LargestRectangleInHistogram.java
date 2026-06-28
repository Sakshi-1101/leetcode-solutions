import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        int n = 6;

        int ansBrute = getAreaBrute(arr, n);
        int ansBetter = getAreaBetter(arr, n);
        int ansOptimal = getAreaOptimal(arr, n);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this approach we will consider each bar as the starting boundary of the rectangle and then we will find the 
    //           minimum height of the bar in the range (i, j) where j is the ending boundary of the rectangle. Then we will 
    //           calculate the area of the rectangle using the formula: area = minHeight * (j - i + 1). Finally, we will return 
    //           the maximum area.
    public static int getAreaBrute(int[] arr, int n) {
        int maxArea = 0;

        // i will be the starting boundary for the current rectangle in consideration
        for (int i = 0; i < n; i++) {
            // this we'll reset for each starting boundary (new rectangle will be considered starting from each i)
            int minHeight = Integer.MAX_VALUE;

            // j will be the ending boundary for the current rectangle in consideration
            for (int j = i; j < n; j++) {
                // Update minimum height of bar in current range (i, j)
                minHeight = Math.min(minHeight, arr[j]);

                // Calculate area of current rectangle that can be formed with (i, j) boundary
                int area = minHeight * (j - i + 1);

                // Update maxArea
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
    
    // TC: O(2N) + O(2N) + O(N) = O(5N) 
    // SC: O(2N) + O(2N) = O(4N)
    // Approach: In this approach, we will find the next smaller element and previous smaller element for each bar in the 
    //           histogram. Then we will calculate the area for each bar using the formula: area = height * (width) where 
    //           width = nse[i] - pse[i] - 1. Finally, we will return the maximum area.
    public static int getAreaBetter(int[] arr, int n) {
        int maxArea = 0;

        // find the nse and pse for each bar in histogram
        int[] nse = getNse(arr, n);
        int[] pse = getPse(arr, n);

        // traverse all the bars
        for(int i = 0 ; i < n ; i ++) {
            // area = height * (width)
            int area = arr[i] * (nse[i] - pse[i] - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // find the next smaller element to right for each element
    private static int[] getNse(int[] arr, int n) {
        Stack<Integer> st = new Stack<>(); // store indexes
        int[] nse = new int[n];

        for(int i = n - 1 ; i >= 0 ; i --) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        return nse;
    }

    // find the previous smaller element to left for each element
    private static int[] getPse(int[] arr, int n) {
        Stack<Integer> st = new Stack<>(); // store indexes
        int[] pse = new int[n];

        for(int i = 0 ; i < n ; i ++) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return pse;
    }
    
    // TC: O(N) + O(N) -> O(N) for loop traversal + O(N) for whenever we are doing stack pop operation, we are calculating 
    //                    the area for that element and we are not pushing it back to stack again. So, each element will be 
    //                    pushed and popped only once from the stack.
    // SC: O(N) for stack
    // Approach: In this approach, we will traverse all the bars in histogram while maintaining the pse in a increasing order 
    //           in the stack. As soon as we find the nse for the top of the stack element, we'll pop all the elements from the 
    //           stack and calculate the area for each of them till the point we get the pse for the current element (considered 
    //           as nse) on the top of stack.
    public static int getAreaOptimal(int[] arr, int n) {
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
            // if stack is empty, then there is no pse for the popped element, else the top of stack after popping is the 
            // pse for the popped element
            int pse = st.isEmpty() ? -1 : st.peek();

            // calculate the area for the popped element and update the maxArea
            int area = arr[eleIdx] * (nse - pse - 1);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

}
