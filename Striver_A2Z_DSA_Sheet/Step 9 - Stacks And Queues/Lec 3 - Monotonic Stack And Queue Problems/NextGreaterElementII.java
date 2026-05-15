import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementII {

    public static void main(String[] args) {
        int[] arr = {3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9};

        int[] ansBrute = ngeBrute(arr);
        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();

        int[] ansOptimal = ngeOptimal(arr);

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i] + " ");
        }
        
    }

    // TC: O(N^2)
    // SC: O(N)
    // Approach: In this approach we will traverse the array for each element and for each element we will move n steps from the 
    //           next index of current element to check in circular manner for the next greater element and if we find the next 
    //           greater element then we will assign it in ans array and break the loop otherwise we will assign -1 in ans array 
    //           for that element.
    public static int[] ngeBrute(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n]; // To store the next greater elements

        // assign -1 to entire arr assuming nge is not there for any ele as of now
        Arrays.fill(ans, -1);

        // traverse the array for each ele
        for(int i = 0 ; i < n ; i ++) {
            int ele = arr[i]; // get the curr ele

            // move n steps from the next idx of curr element i.e. (i + n - 1) to check in circular manner
            for(int j = i + 1 ; j < (i + n) ; j ++) {
                // get the idx for circular arr i.e. after checking last idx ele, check again from 0th idx
                int idx = j % n; 

                // check for nge for curr ele
                if(arr[idx] > ele) {
                    ans[i] = arr[idx];
                    break;
                }
            }
        }

        return ans;

    }

    // TC: O(2N) + O(2N) = O(4N) => O(2N) for traversing the circular array and O(2N) for popping and pushing all the elements 
    //                              from stack in worst case when arr is in decreasing order
    // SC: O(2N) + O(N) = O(3N) => O(2N) for stack and O(N) for ans array
    // Approach: In this approach we will use the concept of monotonic stack and we will traverse the circular array from right 
    //           to left since we need to find nge to right, we will traverse the circular array in hypothetical manner i.e. we 
    //           will traverse the array as if it is of size 2N and we will use the modulo operator to get the actual value from 
    //           arr when we are in hypothetical part of arr, we will pop all the elements from stack which are smaller than or 
    //           equal to curr element since they will never be nge for elements on left of curr element and if stack is empty 
    //           after popping then there is no greater element for curr element otherwise the top of stack will be the nge for 
    //           curr element, we will assign the nge for curr element in ans array only when we are in actual part of arr i.e. 
    //           when i < n otherwise we are in hypothetical part and we just need to push the curr element in stack for finding 
    //           nge for elements on left of curr element.
    public static int[] ngeOptimal(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>(); // monotonic stack

        // traverse the circular array (hypothetically double size array) from right to left since we need nge to right
        for(int i = 2 * n - 1 ; i >= 0 ; i --) {
            /*
                EXAMPLE:
                arr = [2, 10, 12, 1, 11] => n = 5
                hypothetical arr = [2, 10, 12, 1, 11,  2, 10, 12, 1, 11] => n = 10
                if arr[i] = 1 where i = 3
                then hyp arr if i = 8, then arr[i] is hypothetical, actual value exists at arr[i % n] = arr[8 % 5] = arr[3] = 1
             */
            int ele = arr[i % n] ; // bcoz we are starting traversal from hypothetical idx where no value exists

            // pop all the ele <= curr ele bcoz if ele are smaller than curr ele then it will never be next greater for elements
            // on the left of curr element
            while(!st.isEmpty() && st.peek() <= ele) {
                st.pop();
            }

            // if i < n, that means we are not in hypothetical part of arr and we can assign the nge for curr ele in ans array, 
            // otherwise we are in hypothetical part and we just need to push the ele in stack for finding nge for elements on 
            // left of curr ele
            if(i < n) {
                ans[i] = st.isEmpty() ? -1 : st.peek();
            }

            // push curr ele in stack
            st.push(ele);
            
        }

        return ans;
    }
}
