import java.util.*;

public class NextGreaterElement {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 4};
        int n = 4;

        // next greater element to right
        int[] ans = nextGreaterElement(arr, n);

        for(int i = 0 ; i < ans.length ; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach we will use the concept of monotonic stack and we will traverse the array from right to left 
    //           since we need to find nge to right, we will pop all the elements from stack which are smaller than or equal to 
    //           curr element since they will never be nge for elements on left of curr element and if stack is empty after 
    //           popping then there is no greater element for curr element otherwise the top of stack will be the nge for curr 
    //           element, we will assign the nge for curr element in ans array and push the curr element in stack for finding nge 
    //           for elements on left of curr element.
    public static int[] nextGreaterElement(int[] arr, int n) {
        // Stack to store elements
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n]; // Result array of same size

        // Traverse from right to left
        for(int i = n - 1 ; i >= 0 ; i --) {
            // pop all the ele <= curr ele bcoz if ele are smaller than curr ele then it will never be next greater for elements
            // on the left of curr element
            while(!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            // If stack is empty, no greater element (this will generally run for last element in arr)
            if(st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(arr[i]);
        }
        
        return ans;
    }
    
}
