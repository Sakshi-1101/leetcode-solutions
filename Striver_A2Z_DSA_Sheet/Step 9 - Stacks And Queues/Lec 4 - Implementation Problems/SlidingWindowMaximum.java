
import java.util.Deque;
import java.util.LinkedList;


public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int n = 8;
        int k = 3;
        
        int[] ansBrute = getMaxInWindowKBrute(arr, n, k);

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i]);
        }

        int[] ansOptimal = getMaxInWindowKOptimal(arr, n, k);

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i]);
        }
        
    }

    // TC: O(N - k) * O(k) ~ O(N * k)
    // SC: O(N - k) -> max size of the ans array i.e. total no. of windows
    // Approach: In this approach we will iterate over the array and for each window we will find the maximum element in 
    //           that window and store it in the ans array.
    public static int[] getMaxInWindowKBrute(int[] arr, int n, int k) {
        int[] ans = new int[n - k + 1]; // total no. of possible windows
        int itr = 0;

        // traverse the array and for each window find the maximum element in that window
        for(int i = 0 ; i <= n - k ; i ++) {
            int max = arr[i]; // store the current element as the max element in the window

            // traverse the rest of the elements in the window and find the maximum element in that window
            for(int j = i ; j < k + i; j ++) {
                max = Math.max(max, arr[j]);
            }

            // store the maximum element in the ans array
            ans[itr] = max;
            itr++;
        }

        return ans;
    }

    // TC: O(N) + O(N) ~ O(2N) => O(N) for traversal and O(N) for adding and removing elements from the deque.
    // SC: O(K) + O(N - K) => O(K) for the deque as at any moment we'll store atmost K elements and O(N - K) for the ans array.
    // Approach: In this approach we will use a deque to store the indices of the elements in the current window. The deque will 
    //           be maintained in such a way that the front of the deque will always have the index of the maximum element in 
    //           the current window. We will iterate over the array and for each element we will check if it is greater than the 
    //           elements in the deque, if yes then we will remove all smaller elements from the deque. We will also check if 
    //           the front element of the deque is out of the current window, if yes then we will remove it from the deque. 
    //           Finally, we will add the current element index to the deque and if we have reached the end of a complete window,
    //           we will add the maximum element of that window to the ans array.
    public static int[] getMaxInWindowKOptimal(int[] arr, int n, int k) {
        // monotonic deque 
        //  => front - remove elements not in curr window + maintain max value elements
        //  => back - remove smaller value elements + add the elements from back
        Deque<Integer> dq = new LinkedList<>();

        int[] ans = new int[n - k + 1]; // total possible windows
        int itr = 0;

        // traverse the array
        for(int i = 0 ; i < n ; i ++) {

            // check if the front element is out of the current window, if yes then remove it from the deque
            if(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst(); // remove expired elements
            }

            // check if the current element is greater than the elements in the deque, if yes then remove all 
            // smaller elements from the deque
            while(!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) {
                dq.pollLast();
            }

            // add the current element index to the deque
            dq.offerLast(i);

            // if the current index is greater than or equal to k - 1, then we found the first valid window and we can add the 
            // maximum element of that window to the ans array.
            /*
                Have I reached the end of the first complete window? If yes, start recording answers.
                From here onward, every iteration represents a complete window, so record the maximum.
             */
            if(i >= k - 1) {
                ans[itr] = arr[dq.peekFirst()];
                itr++;
            }
        }

        return ans;
    }
    
}
