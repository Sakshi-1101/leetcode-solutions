import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {

    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};
        int n = 5;

        // next smaller element to the right
        int[] ansBrute = nseBrute(arr, n);

        for (int i = 0; i < ansBrute.length ; i++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();
        
        int[] ansOptimal = nseOptimal(arr, n);

        for (int i = 0; i < ansOptimal.length ; i++) {
            System.out.print(ansOptimal[i] + " ");
        }

    }

    // TC: O(N^2)
    // SC: O(N)
    // Approach: In this approach we will traverse the array for each element and for each element we will move n steps from the 
    //           next index of current element to check for the next smaller element and if we find the next smaller element then 
    //           we will assign it in ans array and break the loop otherwise we will assign -1 in ans array for that element.
    public static int[] nseBrute(int[] arr, int n) {
        int[] res = new int[n];
        Arrays.fill(res, -1);

        // traverse array
        for(int i = 0 ; i < n ; i ++) {
            int ele = arr[i];

            // find nse to the right of the curr ele
            for(int j = i + 1 ; j < n ; j ++) {
                if(arr[j] < ele) {
                    res[i] = arr[j];
                    break;
                }
            }
        }

        return res;
    }
    
    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach we will use a stack to keep track of the elements for which we are trying to find the next 
    //           smaller element. We will traverse the array from right to left and for each element we will pop elements from 
    //           the stack that are not smaller than the current element. If the stack is not empty after popping, then the top 
    //           of the stack will be the next smaller element for the current element. Finally, we will push the current element 
    //           onto the stack.
    public static int[] nseOptimal(int[] arr, int n) {
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for(int i = n - 1 ; i >= 0 ; i --) {
            int ele = arr[i];

            // Pop elements from stack that are not smaller than curr ele
            while(!st.isEmpty() && st.peek() > ele) {
                st.pop();
            }

            // If stack not empty, top is the next smaller element
            if(!st.isEmpty()) {
                res[i] = st.peek();
            }

            // Push current element to stack
            st.push(ele);
        }

        return res;
    }
}
