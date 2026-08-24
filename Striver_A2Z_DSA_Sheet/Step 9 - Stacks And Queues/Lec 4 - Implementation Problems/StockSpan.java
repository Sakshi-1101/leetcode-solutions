import java.util.Stack;

public class StockSpan {
    
    public static void main(String[] args) {
        int[] arr = {120, 100, 60, 80, 90, 110, 115};
        int n = 7;

        int[] ansBrute = stockSpanBrute(arr, n);
        int[] ansOptimal = stockSpanOptimal(arr, n);

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i] + " ");
        }
    }

    // TC: O(N^2)
    // SC: O(N)
    // Approach: In this approach, we will traverse the array from right to left and for each element, we will check the elements
    //           on its left side. If we find an element greater than the current element, we will store its index and break the 
    //           loop. If we don't find any greater element, we will store -1. Finally, we will calculate the span for each 
    //           element based on the index of the greater element found.
    public static int[] stockSpanBrute(int[] arr, int n) {
        int[] ans = new int[n];

        // traverse the array from right to left
        for(int i = n - 1 ; i >= 0 ; i --) {
            
            int max = -1; // set max to -1, which will be used to store nge idx to the left of the curr ele.

            // traverse the array from right to left and check for the nge on the left side
            for(int j = i - 1 ; j >= 0 ; j --) {
                if(arr[j] > arr[i]) {
                    max = j;
                    break;
                }
            }

            // calculate the span for the current element based on the index of the greater element found
            ans[i] = max == -1 ? i + 1 : i - max;
        }


        return ans;
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will use a stack to store the indices of the elements. We will traverse the array from 
    //           left to right and for each element, we will pop the elements from the stack until we find an element greater 
    //           than the current element or the stack becomes empty. Then we will calculate the span for the current element 
    //           based on the index of the greater element found.
    public static int[] stockSpanOptimal(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>(); // to store indexes

        // traverse the array from left to right
        for(int i = 0 ; i < n ; i ++) {

            // pop the elements from the stack until we find an element greater than the current element or the stack becomes 
            // empty
            while(!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }

            // calculate the span for the current element based on the index of the greater element found
            /*
                NOTE: (i + 1) bcoz if the stack is empty, it means there is no greater element on the left side, so the span 
                      will be equal to the index of the current element + 1.
             */
            ans[i] = st.isEmpty() ? i + 1 : i - st.peek();

            st.push(i); // push the index of the current element to the stack
        }

        return ans;
    }
}
